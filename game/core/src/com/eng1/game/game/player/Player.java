package com.eng1.game.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.screens.PlayScreen;
import com.eng1.game.screens.Screens;
import com.eng1.game.utils.Pair;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * A class that represents the player character in the game.
 */
public class Player extends Sprite implements InputProcessor {
    private final Vector2 velocity = new Vector2();
    @Setter @Getter
    private float speed = 60 * 5f;
    @Getter
    private final TiledMapTileLayer collisionLayer;
    private final MapLayer transitionLayer;

    /**
     * Constructs a new player with the given sprite and collision layer.
     * @param sprite The sprite representing the player character.
     * @param collisionLayer The collision layer for detecting collisions with tiles.
     */
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, MapLayer transitionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.transitionLayer = transitionLayer;
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

        setX(getX() + velocity.x * delta);

        if (velocity.x != 0) {// going left
            collisionX = collidesHorizontal();
        }

        //react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        //move y
        setY(getY() + velocity.y * delta);
        if (velocity.y != 0) { // going down
            collisionY = collidesVertical();
        }
        //react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }

        @Nullable Pair<MapAssets, @Nullable String> cellTransition = getCellTransition(getX(), getY());
        if (cellTransition != null) {
            PlayScreen screen = ((PlayScreen) Screens.PLAY.get());
            screen.changeMap(cellTransition.getLeft(), cellTransition.getRight());
        }
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
            default:
                return false;
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
            default:
                return false;
        }
        return true;
    }
    /**
     * Checks whether the tile contains a property called blocked.
     * @param x which is the x coord of the player.
     * @param y which is the y coord of the player.
     * @return True if it does contain it and false if not.
     */
    private boolean isCellBlocked(float x, float y){
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        String blockedKey = "blocked";
        if (cell == null) {
            return false;
        }
        TiledMapTile tile = cell.getTile();
        return tile != null && tile.getProperties().containsKey(blockedKey);
    }

    /**
     * Checks if the cell to the right of the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    private boolean collidesHorizontal() {
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the cell above the player is blocked.
     * @return True if the cell is blocked, false otherwise.
     */
    private boolean collidesVertical() {
        for (float step = 0; step < getWidth(); step += (float) collisionLayer.getTileWidth() / 2) {
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        return false;
    }

    private @Nullable Pair<MapAssets, @Nullable String> getCellTransition(float x, float y) {
        MapObjects objects = transitionLayer.getObjects();
        for (int i = 0; i < objects.getCount(); i++) {
            MapObject mapObject = objects.get(i);
            MapProperties properties = mapObject.getProperties();
            float x1 = properties.get("x", float.class);
            float y2 = properties.get("y", float.class);
            float width = properties.get("width", float.class);
            float height = properties.get("height", float.class);
            if (x >= x1 && x <= x1 + width && y >= y2 && y <= y2 + height) {
                return Pair.of(MapAssets.valueOf(properties.get("map_id", String.class)), properties.get("spawnpoint", String.class));
            }
        }
        return null;
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

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setScale(3);
    }
}
