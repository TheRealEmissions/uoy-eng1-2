package com.eng1.game.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.game.activity.Activities;
import com.eng1.game.game.activity.ActivityMapObject;
import com.eng1.game.screens.PlayScreen;
import com.eng1.game.screens.Screens;
import com.eng1.game.utils.Pair;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A class that represents the player character in the game.
 */
public class Player extends Sprite implements InputProcessor {
    private static final Skin uiSkin = SkinAssets.UI.get();
    private final Vector2 velocity = new Vector2();
    @Setter @Getter
    private float speed = 60 * 5f;
    @Getter
    private final TiledMapTileLayer collisionLayer;
    private final MapLayer transitionLayer;
    private final MapLayer activityLayer;
    private ActivityMapObject potentialActivity = null;
    private List<Statistics.PlayerStatistics> canDoActivity = null;

    /**
     * Constructs a new player with the given sprite and collision layer.
     * @param sprite The sprite representing the player character.
     * @param collisionLayer The collision layer for detecting collisions with tiles.
     */
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, MapLayer transitionLayer, MapLayer activityLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.transitionLayer = transitionLayer;
        this.activityLayer = activityLayer;
        setScale(3);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void drawHud(Batch batch) {
        if (potentialActivity != null && canDoActivity != null) {
            Label label;
            labelCreation: {
                if (!potentialActivity.getActivity().equals(Activities.SLEEP)) {
                    if (Statistics.isEndOfDay()) {
                        label = new Label("It's time to sleep!", uiSkin);
                        break labelCreation;
                    }
                    if (!hasTimeForActivity()) {
                        label = new Label("It's too late to " + potentialActivity.getText(), uiSkin);
                        // set position to bottom middle
                        break labelCreation;
                    }
                }
                if (canDoActivity.isEmpty()) {
                    label = new Label("Press E to " + potentialActivity.getText(), uiSkin);
                    // set position to bottom middle
                } else {
                    label = new Label("Not enough " + canDoActivity.stream().map(Statistics.PlayerStatistics::getLabel).collect(Collectors.joining(", ")), uiSkin);
                    // set position to bottom middle
                }
            }
            label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f, 0);
            label.draw(batch, 1);
        }


        Statistics.PlayerStatistics[] statistics = Statistics.PlayerStatistics.values();
        for (int i = 0; i < statistics.length; i++) {
            Statistics.PlayerStatistics statistic = statistics[i];
            ProgressBar progressBar = statistic.getProgressBar();
            progressBar.updateVisualValue();
            Label label = new Label(statistic.getLabel() + ": " + (int) (statistic.get() * 100) + "%", uiSkin);

            // Calculate the y position based on the index
            float yPos = i * (progressBar.getHeight() + 10); // 10 is the margin between each statistic

            // bottom right of screen
            progressBar.setPosition(Gdx.graphics.getWidth() - progressBar.getWidth(), yPos);
            label.setPosition(Gdx.graphics.getWidth() - progressBar.getWidth(), yPos + progressBar.getHeight());

            progressBar.draw(batch, 1);
            label.draw(batch, 1);
        }
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

        ActivityMapObject cellActivity = getCellActivity(getX(), getY());
        potentialActivity = cellActivity;
        canDoActivity = cellActivity == null ? null : canDoActivity();
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
            case Keys.E:
                if (potentialActivity == null) break;
                if (canDoActivity == null) break;
                if (!canDoActivity.isEmpty()) break;
                if (!potentialActivity.getActivity().equals(Activities.SLEEP)) {
                    if (Statistics.isEndOfDay()) break;
                    if (!hasTimeForActivity()) break;
                }
                doActivity();
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean hasTimeForActivity() {
        if (potentialActivity == null) return false;
        if (potentialActivity.getActivity().equals(Activities.SLEEP)) return true;
        if (Statistics.isEndOfDay()) return false;
        ActivityMapObject activity = potentialActivity;
        LocalTime time = Statistics.getTime();
        LocalTime dayEnd = Statistics.DAY_END;
        int hoursBeforeEnd = dayEnd.getHour() - time.getHour();
        return hoursBeforeEnd >= activity.getAdvanceTimeBy();
    }

    private @Nullable List<Statistics.PlayerStatistics> canDoActivity() {
        if (potentialActivity == null) return null;
        ActivityMapObject activity = Objects.requireNonNull(potentialActivity);
        Activities activityRef = activity.getActivity();
        List<Float> changeStats = activity.getChangeStats();
        List<Statistics.PlayerStatistics> notEnough = new ArrayList<>();
        Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects = activityRef.getEffects();
        for (int i = 0; i < effects.length; i++) {
            Pair<Statistics.PlayerStatistics, Statistics.Effect> effect = effects[i];
            Statistics.PlayerStatistics statistic = effect.getLeft();
            Statistics.Effect effectType = effect.getRight();
            float change = changeStats.get(i);
            if (effectType.equals(Statistics.Effect.DECREASE) && statistic.get() - change < 0) {
                notEnough.add(statistic);
            }
        }
        return notEnough;
    }

    private void doActivity() {
        if (potentialActivity == null) return;
        ActivityMapObject activity = potentialActivity;
        Activities activityRef = activity.getActivity();
        List<Float> changeStats = activity.getChangeStats();
        Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects = activityRef.getEffects();
        for (int i = 0; i < effects.length; i++) {
            Pair<Statistics.PlayerStatistics, Statistics.Effect> effect = effects[i];
            Statistics.PlayerStatistics statistic = effect.getLeft();
            Statistics.Effect effectType = effect.getRight();
            float change = changeStats.get(i);
            switch (effectType) {
                case INCREASE:
                    statistic.increase(change);
                    break;
                case DECREASE:
                    statistic.decrease(change);
                    break;
                case RESET:
                    statistic.reset();
                    break;
            }
        }
        if (activityRef.equals(Activities.SLEEP)) {
            for (Statistics.PlayerStatistics statistic : Statistics.PlayerStatistics.values()) {
                statistic.increaseTotal(statistic.get());
            }
            if (Statistics.isEndOfDays()) {
                Screens.END.setAsCurrent();
                return;
            } else {
                Statistics.newDay();
            }
        } else {
            Statistics.increaseTime(LocalTime.of(activity.getAdvanceTimeBy(), 0));
        }
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

    private @Nullable ActivityMapObject getCellActivity(float x, float y) {
        MapObjects objects = activityLayer.getObjects();
        for (int i = 0; i < objects.getCount(); i++) {
            MapObject mapObject = objects.get(i);
            MapProperties properties = mapObject.getProperties();
            float x1 = properties.get("x", float.class);
            float y2 = properties.get("y", float.class);
            float width = properties.get("width", float.class);
            float height = properties.get("height", float.class);
            if (x >= x1 && x <= x1 + width && y >= y2 && y <= y2 + height) {
                Boolean isActivity = properties.get("is_activity", Boolean.class);
                if (!Boolean.TRUE.equals(isActivity)) continue;
                return ActivityMapObject.fromMapObject(mapObject);
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

    public void dispose() {
        uiSkin.dispose();
    }
}
