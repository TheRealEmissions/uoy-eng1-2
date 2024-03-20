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

/**
 * A class that represents the player character in the game.
 */
public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 5;
    private float animationTime = 0;
    private Animation still, left, right;
    private TiledMapTileLayer collisionLayer;
    private String blockedKey = "blocked";
    private String transitionKey = "transition";
    public static String transitionValue = "";
    private String activityKey = "activity";
    public static String activityValue = "";

    /**
     * Constructs a new player with the given sprite and collision layer.
     * @param sprite The sprite representing the player character.
     * @param collisionLayer The collision layer for detecting collisions with tiles.
     */
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

    /**
     * Updates the player's position and checks for collisions.
     * @param delta The time passed since the last frame.
     */
    public void update(float delta) {

        //save old position
        float oldX = getX();
        float oldY = getY();

        boolean collisionX = false;
        boolean collisionY = false;
        boolean transition = false;
        boolean activity = false;
        //move x
        setX(getX() + velocity.x * delta);

        if (velocity.x < 0) {// going left
            collisionX = collidesLeft();
            transition = transitionLeft();
            activity = activityLeft();
        } else if (velocity.x > 0) {// going right
            collisionX = collidesRight();
            transition = transitionRight();
            activity = activityRight();
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
        } else if (activity) {
            velocity.x = 0;
            Activity.completeActivity(activityValue);
            setX(oldX);
        }

        //move y
        setY(getY() + velocity.y * delta);
        if (velocity.y < 0) { // going down
            collisionY = collidesBottom();
            transition = transitionBottom();
            activity = activityBottom();
        } else if (velocity.y > 0) { // going up
            collisionY = collidesTop();
            transition = transitionTop();
            activity = activityTop();
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
        } else if (activity) {
            velocity.y = 0;
            Activity.completeActivity(activityValue);
            setY(oldY);
        }
//
//        animationTime += delta;
//        setRegion(velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
    }

    /**
     * Called when a key is pressed.
     *
     * @param keycode The keycode of the key that was pressed (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     */
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

    /**
     * Called when a key is pressed.
     *
     * @param keycode The keycode of the key that was pressed (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     */
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
    /**
     * Checks whether the tile contains a property called blocked.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
    private boolean isCellBlocked (float x, float y){
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }
    /**
     * Checks whether the tile contains a property called transition.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
    private boolean isCellTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(transitionKey);
    }
    /**
     * Checks whether the tile contains a property called activity.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
    public boolean isCellActivity (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(activityKey);
    }
    /**
     * Gets the value of the property from the transition tile.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return the string value of the property.
     */
    private void getTransition (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell.getTile().getProperties().containsKey(transitionKey)) {
            Object value = cell.getTile().getProperties().get("transition");
            if (value != null) {
                transitionValue = value.toString();
            }
        }
    }
    /**
     * Gets the value of the property from the activity tile.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return the string value of the property.
     */
    private void getActivity (float x, float y) {
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        if (cell.getTile().getProperties().containsKey(activityKey)) {
            Object value = cell.getTile().getProperties().get("activity");
            if (value != null) {
                activityValue = value.toString();
            }
        }
    }

    /**
     * Checks if the cell to the right of the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesRight () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell to the right the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
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
    /**
     * Checks if the cell to the right of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
    public boolean activityRight () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell to the left of the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesLeft () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell to the left of the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
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
    /**
     * Checks if the cell to the left of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
    public boolean activityLeft () {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell above the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesTop () {
        boolean collides = false;
        for (float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell above the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
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
    /**
     * Checks if the cell above of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
    public boolean activityTop() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell below the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    public boolean collidesBottom() {
        boolean collides = false;
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step)) {
                break;
            }
        }
        return collides;
    }

    /**
     * Checks if the cell below the player is a transition.
     * @return True if the cell is a transition, false otherwise.
     */
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
    /**
     * Checks if the cell below of the player is an activity.
     * @return True if the cell is an activity, false otherwise.
     */
    public boolean activityBottom() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellActivity(getX() + getWidth(), getY() + step)) {
                getActivity(getX() + getWidth(), getY() + step);
                break;
            }
        }
        return collides;
    }

    // Getters and Setters for speed, velocity and collisions
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

    // Checks for inputs
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
