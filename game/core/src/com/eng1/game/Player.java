package com.eng1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;


public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 10;
    private float animationTime = 0;
    private Animation still, left, right;
    private TiledMapTileLayer collisionLayer;
    private String blockedKey = "blocked";
    private String transitionKey = "transition";
    public static String transitionValue = "";

    // Animation still, Animation left, Animation right,
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
//        super((Texture) still.getKeyFrame(0));
//        this.still = still;
//        this.left = left;
//        this.right = right;
        this.collisionLayer = collisionLayer;
        setScale(3);



    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {

        //save old position
        float oldX = getX();
        float oldY = getY();

        boolean collisionX = false;
        boolean collisionY = false;
        boolean transition = false;

        //move x
        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {// going left
            collisionX = collidesLeft();
            transition = transitionLeft();
        } else if (velocity.x > 0) {// going right
            collisionX = collidesRight();
            transition = transitionRight();
        }

        //react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        } else if (transition) {
            velocity.x = 0;
            Play.changeMap(transitionValue);
            setX(oldX);
            transition = false;
        }

        //move y
        setY(getY() + velocity.y * delta);
        if (velocity.y < 0) { // going down
            collisionY = collidesBottom();
            transition = transitionBottom();
        } else if (velocity.y > 0) { // going up
            collisionY = collidesTop();
            transition = transitionTop();
        }
        //react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        } else if (transition) {
            velocity.y = 0;
            Play.changeMap(transitionValue);
            setY(oldY);
            transition = false;
        }
//
//        animationTime += delta;
//        setRegion(velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.UP:
                velocity.y = speed;
                break;
            case Keys.A:
            case Keys.LEFT:
                velocity.x = -speed;
                break;
            case Keys.S:
            case Keys.DOWN:
                velocity.y = -speed;
                break;
            case Keys.D:
            case Keys.RIGHT:
                velocity.x = speed;
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.S:
            case Keys.UP:
            case Keys.DOWN:
                velocity.y = 0;
                break;
            case Keys.A:
            case Keys.D:
            case Keys.LEFT:
            case Keys.RIGHT:
                velocity.x = 0;
                break;
        }
        return true;
    }

    private boolean isCellBlocked (float x, float y){
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }

    private boolean isCellTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(transitionKey);
    }

    private void getTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell.getTile().getProperties().containsKey(transitionKey)) {
            Object value = cell.getTile().getProperties().get("transition");
            if (value != null) {
                transitionValue = value.toString();
            }
        }
    }

    public boolean collidesRight () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    public boolean transitionRight () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    public boolean collidesLeft () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    public boolean transitionLeft () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    public boolean collidesTop () {
        boolean collides = false;
        for (float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    public boolean transitionTop() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    public boolean collidesBottom() {
        boolean collides = false;
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    public boolean transitionBottom() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellTransition(getX() + getWidth(), getY() + step)) {
                getTransition(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }
    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
