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
import com.eng1.game.game.achievement.*;
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
 * This class represents the player character in the game.
 * It handles player movement, interaction with the game world, and the player's statistics.
 */
public class Player extends Sprite implements InputProcessor {
    /**
     * This skin is used for UI elements in the game.
     */
    private static final Skin uiSkin = SkinAssets.UI.get();
    /**
     * A {@link Vector2} instance representing the velocity of the player.
     * This is used to control the speed and direction of the player's movement.
     */
    private final Vector2 velocity = new Vector2();
    /**
     * The speed of the player in the game world.
     * This value is used to control the speed of the player's movement.
     * It is set to 60 * 5f by default, but can be changed during gameplay.
     * The speed is measured in units per second.
     */
    @Setter @Getter
    private float speed = 60 * 5f;
    /**
     * A final instance of the {@link TiledMapTileLayer} class representing the collision layer in the game.
     * This layer is used for detecting collisions between the player and the game world.
     */
    @Getter
    private final TiledMapTileLayer collisionLayer;
    /**
     * This layer is used for managing transitions between different maps in the game.
     */
    private final MapLayer transitionLayer;
    /**
     * This layer is used for managing activities that the player can engage in within the game world.
     */
    private final MapLayer activityLayer;
    /**
     * An instance of the {@link ActivityMapObject} class representing the potential activity the player can engage in.
     * This is a valid activity when the player is within the bounds of an activity object on the map, otherwise it is null.
     */
    private ActivityMapObject potentialActivity = null;

    /**
     * A {@link List} of {@link Statistics.PlayerStatistics} instances representing the statistics preventing the player from completing the activity.
     * Typically, this is because the player does not have enough of the particular statistic, such as energy.
     * If the player can complete the activity, this list is empty.
     * If the player cannot complete the activity, this list contains the statistics that are preventing the player from completing the activity.
     * If the player is not within the bounds of an activity object, this list is null.
     */
    private List<Statistics.PlayerStatistics> canDoActivity = null;

    /**
     * Constructs a new player with the given sprite, collision layer, transition layer, and activity layer.
     *
     * @param sprite The sprite representing the player character.
     * @param collisionLayer The collision layer for detecting collisions with tiles.
     * @param transitionLayer The layer used for managing transitions between different maps in the game.
     * @param activityLayer The layer used for managing activities that the player can engage in within the game world.
     */
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, MapLayer transitionLayer, MapLayer activityLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.transitionLayer = transitionLayer;
        this.activityLayer = activityLayer;
        setScale(3);
    }

    /**
     * Draws the Heads-Up Display (HUD) for the game.
     * The HUD includes labels for potential activities and player statistics.
     *
     * @param batch The batch used to draw the HUD.
     */
    public void drawHud(Batch batch) {
        // If there is a potential activity and a list of statistics potentially preventing the player from completing the activity
        if (potentialActivity != null && canDoActivity != null) {
            Label label;
            // Create a label based on the potential activity and the player's ability to complete it
            labelCreation: {
                // If the potential activity is not sleep
                if (!potentialActivity.getActivity().equals(Activities.SLEEP)) {
                    // If it's the end of the day
                    if (Statistics.isEndOfDay()) {
                        label = new Label("It's time to sleep!", uiSkin);
                        break labelCreation;
                    }
                    // If there's not enough time for the activity
                    if (!hasTimeForActivity()) {
                        label = new Label("It's too late to " + potentialActivity.getText(), uiSkin);
                        break labelCreation;
                    }
                }
                // If the player can complete the activity
                if (canDoActivity.isEmpty()) {
                    label = new Label("Press E to " + potentialActivity.getText(), uiSkin);
                } else {
                    // If the player cannot complete the activity
                    label = new Label("Not enough " + canDoActivity.stream().map(Statistics.PlayerStatistics::getLabel).collect(Collectors.joining(", ")), uiSkin);
                }
            }
            // Position the label at the bottom middle of the screen
            label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f, 0);
            // Draw the label
            label.draw(batch, 1);
        }

        // Get the player's statistics
        Statistics.PlayerStatistics[] statistics = Statistics.PlayerStatistics.values();
        // For each statistic
        for (int i = 0; i < statistics.length; i++) {
            Statistics.PlayerStatistics statistic = statistics[i];
            // Get the progress bar for the statistic
            ProgressBar progressBar = statistic.getProgressBar();
            // Update the visual value of the progress bar
            progressBar.updateVisualValue();
            // Create a label for the statistic
            Label label = new Label(statistic.getLabel() + ": " + (int) (statistic.get() * 100) + "%", uiSkin);

            // Calculate the y position based on the index
            float yPos = i * (progressBar.getHeight() + 10); // 10 is the margin between each statistic

            // Position the progress bar and label at the bottom right of the screen
            progressBar.setPosition(Gdx.graphics.getWidth() - progressBar.getWidth(), yPos);
            label.setPosition(Gdx.graphics.getWidth() - progressBar.getWidth(), yPos + progressBar.getHeight());

            // Draw the progress bar and label
            progressBar.draw(batch, 1);
            label.draw(batch, 1);
        }
    }

    /**
     * Updates the player's position based on the velocity and checks for collisions.
     * If a collision is detected, the player's position is reset to the previous position and the velocity is set to 0.
     * Also checks for transitions and activities at the player's current position.
     * If a transition is detected, the map is changed.
     * If an activity is detected, it is set as the potential activity and the player's ability to do the activity is checked.
     *
     * @param delta The time passed since the last frame. This is used to calculate the new position based on the velocity.
     */
    public void update(float delta) {

        // Save the old position for potential use later if a collision is detected
        float oldX = getX();
        float oldY = getY();

        // Flags to track if a collision is detected
        boolean collisionX = false;
        boolean collisionY = false;

        // Update the x position based on the x velocity
        setX(getX() + velocity.x * delta);

        // If the player is moving horizontally, check for a horizontal collision
        if (velocity.x != 0) {
            collisionX = collidesHorizontal();
        }

        // If a horizontal collision is detected, reset the x position and stop horizontal movement
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        // Update the y position based on the y velocity
        setY(getY() + velocity.y * delta);
        // If the player is moving vertically, check for a vertical collision
        if (velocity.y != 0) {
            collisionY = collidesVertical();
        }
        // If a vertical collision is detected, reset the y position and stop vertical movement
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }

        // Check for a transition at the player's current position
        @Nullable Pair<MapAssets, @Nullable String> cellTransition = getCellTransition(getX(), getY());
        // If a transition is detected, change the map
        if (cellTransition != null) {
            PlayScreen screen = ((PlayScreen) Screens.PLAY.get());
            screen.changeMap(cellTransition.getLeft(), cellTransition.getRight());
        }

        // Check for an activity at the player's current position
        ActivityMapObject cellActivity = getCellActivity(getX(), getY());
        // If an activity is detected, set it as the potential activity and check the player's ability to do the activity
        potentialActivity = cellActivity;
        canDoActivity = cellActivity == null ? null : canDoActivity();
    }

    /**
     * Handles the event when a key is pressed.
     * Depending on the key pressed, it updates the player's velocity or triggers an activity.
     *
     * @param keycode The keycode of the key that was pressed (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     *
     * The following keys have actions associated with them:
     * - W/UP: Increases the player's y velocity, making the player move upwards.
     * - A/LEFT: Decreases the player's x velocity, making the player move to the left.
     * - S/DOWN: Decreases the player's y velocity, making the player move downwards.
     * - D/RIGHT: Increases the player's x velocity, making the player move to the right.
     * - E: Triggers an activity if the player is able to do so. The conditions for an activity to be triggered are:
     *   - There must be a potential activity available.
     *   - The player must be able to do the activity (i.e., the list of statistics preventing the player from doing the activity must be empty).
     *   - If the potential activity is not sleep, it must not be the end of the day and there must be enough time for the activity.
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

    /**
     * Checks if there is enough time left in the day for the player to perform the potential activity.
     *
     * @return {@code true} if there is enough time for the activity, {@code false} otherwise.
     *
     * The method works as follows:
     * - If there is no potential activity, it returns {@code false}.
     * - If the potential activity is sleep, it returns {@code true} because sleep can be performed at any time.
     * - If it's the end of the day, it returns {@code false} because no activities can be performed at the end of the day except for sleep.
     * - It calculates the number of hours left until the end of the day.
     * - If the number of hours left is greater than or equal to the time required for the activity, it returns {@code true}.
     * - Otherwise, it returns {@code false}.
     */
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

    /**
     * This method checks if the player can perform the potential activity based on their current statistics.
     *
     * @return A list of {@link Statistics.PlayerStatistics} that the player lacks to perform the activity.
     * If the player can perform the activity, the returned list is empty.
     * If there is no potential activity, the method returns null.
     *
     * The method works as follows:
     * - If there is no potential activity, it returns null.
     * - It retrieves the potential activity and its associated effects on the player's statistics.
     * - It creates an empty list to store the statistics that the player lacks to perform the activity.
     * - For each effect of the activity:
     *   - It retrieves the affected statistic and the type of the effect (increase or decrease).
     *   - If the effect type is decrease and the player does not have enough of the statistic to decrease,
     *     it adds the statistic to the list of lacking statistics.
     */
    private @Nullable List<Statistics.PlayerStatistics> canDoActivity() {
        if (potentialActivity == null) return null;
        ActivityMapObject activity = Objects.requireNonNull(potentialActivity);
        Activities activityRef = activity.getActivity();
        List<Float> changeStats = activity.getChangeStats();
        List<Statistics.PlayerStatistics> notEnough = new ArrayList<>();
        Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects = activityRef.getEffects();
        // Iterate over the effects of the potential activity
        for (int i = 0; i < effects.length; i++) {
            // Get the current effect
            Pair<Statistics.PlayerStatistics, Statistics.Effect> effect = effects[i];
            // Get the statistic that the effect applies to
            Statistics.PlayerStatistics statistic = effect.getLeft();
            // Get the type of the effect (increase or decrease)
            Statistics.Effect effectType = effect.getRight();
            // Get the amount of change that the effect applies
            float change = changeStats.get(i);
            // If the effect is a decrease and applying the effect would result in a negative value for the statistic
            if (effectType.equals(Statistics.Effect.DECREASE) && statistic.get() - change < 0) {
                // Add the statistic to the list of statistics that the player lacks to perform the activity
                notEnough.add(statistic);
            }
        }
        return notEnough;
    }

    /**
     * This method is used to perform the potential activity that the player can engage in.
     * It checks if there is a potential activity and if the player can perform it.
     * If the player can perform the activity, it applies the effects of the activity on the player's statistics.
     * If the activity is sleep, it checks if it's the end of the week and if so, it changes the screen to the end screen.
     * Otherwise, it advances the time by the amount of time required for the activity.
     * If the activity is not sleep, it simply advances the time by the amount of time required for the activity.
     * If the activity has associated achievements, it triggers the achievements.
     */
    private void doActivity() {
        if (potentialActivity == null) return;
        ActivityMapObject activity = potentialActivity;
        Activities activityRef = activity.getActivity();
        List<Float> changeStats = activity.getChangeStats();
        Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects = activityRef.getEffects();
        // Iterate over the effects of the potential activity
        for (int i = 0; i < effects.length; i++) {
            // Get the current effect
            Pair<Statistics.PlayerStatistics, Statistics.Effect> effect = effects[i];
            // Get the statistic that the effect applies to
            Statistics.PlayerStatistics statistic = effect.getLeft();
            // Get the type of the effect (increase, decrease, or reset)
            Statistics.Effect effectType = effect.getRight();
            // Get the amount of change that the effect applies
            float change = changeStats.get(i);
            // Apply the effect based on its type
            switch (effectType) {
                case INCREASE:
                    // If the effect is an increase, increase the statistic by the change amount
                    statistic.increase(change);
                    break;
                case DECREASE:
                    // If the effect is a decrease, decrease the statistic by the change amount
                    statistic.decrease(change);
                    break;
                case RESET:
                    // If the effect is a reset, reset the statistic to its default value
                    statistic.reset();
                    break;
            }
        }
        // Check if the activity is sleep
        if (activityRef.equals(Activities.SLEEP)) {
            // If the activity is sleep, increase the total of each player statistic by its current value
            for (Statistics.PlayerStatistics statistic : Statistics.PlayerStatistics.values()) {
                statistic.increaseTotal(statistic.get());
            }
            // Check if it's the end of the game days (i.e. end of the week)
            if (Statistics.isEndOfDays()) {
                // If it's the end of the game days, change the screen to the end screen
                Screens.END.setAsCurrent();
            } else {
                // If it's not the end of the game days, start a new day
                Statistics.newDay();
            }
        } else {
            // If the activity is not sleep, advance the time by the amount of time required for the activity
            Statistics.increaseTime(LocalTime.of(activity.getAdvanceTimeBy(), 0));
        }
        // Retrieve the list of achievements associated with the activity
        List<Achievements> achievements = activity.getAchievements();

        // If the list of achievements is not empty, iterate over each achievement
        if (!achievements.isEmpty()) {
            for (Achievements achievement : achievements) {
                // Get the reference to the actual Achievement object
                Achievement achievementRef = achievement.getAchievementRef();

                // Check the type of the achievement and call the appropriate method
                if (achievementRef instanceof DreamweaverAchievement) {
                    // If the achievement is of type DreamweaverAchievement, call the daydream method
                    DreamweaverAchievement dreamweaverAchievement = (DreamweaverAchievement) achievementRef;
                    dreamweaverAchievement.daydream();
                } else if (achievementRef instanceof FitnessFanaticAchievement) {
                    // If the achievement is of type FitnessFanaticAchievement, call the exercise method
                    FitnessFanaticAchievement fitnessFanaticAchievement = (FitnessFanaticAchievement) achievementRef;
                    fitnessFanaticAchievement.exercise();
                } else if (achievementRef instanceof ScholarlySprinterAchievement) {
                    // If the achievement is of type ScholarlySprinterAchievement, call the study method with the current day as parameter
                    ScholarlySprinterAchievement scholarlySprinterAchievement = (ScholarlySprinterAchievement) achievementRef;
                    scholarlySprinterAchievement.study(Statistics.getDay());
                } else if (achievementRef instanceof SnackMasterAchievement) {
                    // If the achievement is of type SnackMasterAchievement, call the snack method
                    SnackMasterAchievement snackMasterAchievement = (SnackMasterAchievement) achievementRef;
                    snackMasterAchievement.snack();
                } else if (achievementRef instanceof SocialButterflyAchievement) {
                    // If the achievement is of type SocialButterflyAchievement, call the socialise method
                    SocialButterflyAchievement socialButterflyAchievement = (SocialButterflyAchievement) achievementRef;
                    socialButterflyAchievement.socialise();
                } else if (achievementRef instanceof TeachersPetAchievement) {
                    // If the achievement is of type TeachersPetAchievement, call the attendTeachingHours method
                    TeachersPetAchievement teachersPetAchievement = (TeachersPetAchievement) achievementRef;
                    teachersPetAchievement.attendTeachingHours();
                } else if (achievementRef instanceof WellRoundedAchievement) {
                    // If the achievement is of type WellRoundedAchievement, call the addActivity method with the current day and activity reference as parameters
                    WellRoundedAchievement wellRoundedAchievement = (WellRoundedAchievement) achievementRef;
                    wellRoundedAchievement.addActivity(Statistics.getDay(), activityRef);
                }
            }
        }
    }

    /**
     * This method is triggered when a key is released.
     * Depending on the key released, it updates the player's velocity to stop movement in that direction.
     *
     * @param keycode The keycode of the key that was released (e.g., {@link com.badlogic.gdx.Input.Keys#W} for the 'W' key).
     * @return {@code true} if the input event was handled, {@code false} otherwise.
     *
     * The following keys have actions associated with them:
     * - W/S/UP/DOWN: Sets the player's y velocity to 0, stopping vertical movement.
     * - A/D/LEFT/RIGHT: Sets the player's x velocity to 0, stopping horizontal movement.
     */
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
            case Keys.S:
            case Keys.UP:
            case Keys.DOWN:
                // Stop vertical movement
                velocity.y = 0;
                break;
            case Keys.A:
            case Keys.D:
            case Keys.LEFT:
            case Keys.RIGHT:
                // Stop horizontal movement
                velocity.x = 0;
                break;
            default:
                // If the key released does not match any of the cases, return false
                return false;
        }
        // If the key released matches any of the cases, return true
        return true;
    }

    /**
     * This method checks if a specific cell in the collision layer is blocked.
     * A cell is considered blocked if it contains a property with the key "blocked".
     *
     * @param x The x-coordinate of the cell to check. This is typically the x-coordinate of the player.
     * @param y The y-coordinate of the cell to check. This is typically the y-coordinate of the player.
     * @return {@code true} if the cell is blocked, {@code false} otherwise.
     *
     * The method works as follows:
     * - It retrieves the cell at the given coordinates from the collision layer.
     * - If the cell is null (i.e., there is no cell at the given coordinates), it returns {@code false}.
     * - It retrieves the tile of the cell.
     * - If the tile is null (i.e., there is no tile at the cell), it returns {@code false}.
     * - It checks if the tile's properties contain a key "blocked".
     * - If the tile's properties contain a key "blocked", it returns {@code true}.
     * - Otherwise, it returns {@code false}.
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
     * This method checks if the cell to the right of the player is blocked.
     * A cell is considered blocked if it contains a property with the key "blocked".
     *
     * The method works as follows:
     * - It iterates from the bottom to the top of the player's current cell.
     * - For each step, it checks if the cell to the right of the player is blocked.
     * - If a blocked cell is found, it returns {@code true}.
     * - If no blocked cells are found after checking all the steps, it returns {@code false}.
     *
     * @return {@code true} if the cell to the right of the player is blocked, {@code false} otherwise.
     */
    private boolean collidesHorizontal() {
        // This loop checks for a collision on the right side of the player.
        // It iterates from the bottom to the top of the player's current cell.
        for (float step = 0; step < getHeight(); step += (float) collisionLayer.getTileHeight() / 2) {
            // For each step, it checks if the cell to the right of the player is blocked.
            // The method isCellBlocked is used to check if a cell is blocked.
            // If a blocked cell is found, it immediately returns true, indicating a collision.
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        // If no blocked cells are found after checking all the steps, it returns false, indicating no collision.
        return false;
    }

    /**
     * This method checks if the cell above the player is blocked.
     * A cell is considered blocked if it contains a property with the key "blocked".
     *
     * The method works as follows:
     * - It iterates from the left to the right of the player's current cell.
     * - For each step, it checks if the cell above the player is blocked.
     * - If a blocked cell is found, it returns {@code true}.
     * - If no blocked cells are found after checking all the steps, it returns {@code false}.
     *
     * @return {@code true} if the cell above the player is blocked, {@code false} otherwise.
     */
    private boolean collidesVertical() {
        // This loop checks for a collision on the top side of the player.
        // It iterates from the left to the right of the player's current cell.
        for (float step = 0; step < getWidth(); step += (float) collisionLayer.getTileWidth() / 2) {
            // For each step, it checks if the cell above the player is blocked.
            // The method isCellBlocked is used to check if a cell is blocked.
            // If a blocked cell is found, it immediately returns true, indicating a collision.
            if (isCellBlocked(getX() + getWidth(), getY() + step)) {
                return true;
            }
        }
        // If no blocked cells are found after checking all the steps, it returns false, indicating no collision.
        return false;
    }

    /**
     * This method checks for a transition at the specified coordinates.
     * A transition is defined as a change from one map to another.
     *
     * @param x The x-coordinate to check for a transition. This is typically the x-coordinate of the player.
     * @param y The y-coordinate to check for a transition. This is typically the y-coordinate of the player.
     * @return A {@link Pair} containing the {@link MapAssets} of the new map and the spawnpoint on the new map (which can be null if the transition uses the map's general spawnpoint), or null if there is no transition at the specified coordinates.
     *
     * The method works as follows:
     * - It retrieves all the objects from the transition layer.
     * - It iterates over each object in the transition layer.
     * - For each object, it retrieves the properties of the object.
     * - It checks if the specified coordinates are within the bounds of the object.
     * - If the specified coordinates are within the bounds of the object, it retrieves the map_id and spawnpoint properties from the object's properties.
     * - It returns a {@link Pair} containing the {@link MapAssets} of the new map and the spawnpoint on the new map.
     * - If no object contains the specified coordinates, it returns null.
     */
    private @Nullable Pair<MapAssets, @Nullable String> getCellTransition(float x, float y) {
        MapObjects objects = transitionLayer.getObjects();
        // This loop iterates over all the objects in the transition layer.
        for (int i = 0; i < objects.getCount(); i++) {
            // Retrieves the i-th object from the transition layer.
            MapObject mapObject = objects.get(i);
            // Retrieves the properties of the i-th object.
            MapProperties properties = mapObject.getProperties();
            // Retrieves the x-coordinate of the i-th object.
            float x1 = properties.get("x", float.class);
            // Retrieves the y-coordinate of the i-th object.
            float y2 = properties.get("y", float.class);
            // Retrieves the width of the i-th object.
            float width = properties.get("width", float.class);
            // Retrieves the height of the i-th object.
            float height = properties.get("height", float.class);
            // Checks if the specified coordinates (x, y) are within the bounds of the i-th object.
            // If the specified coordinates are within the bounds of the i-th object:
            if (x >= x1 && x <= x1 + width && y >= y2 && y <= y2 + height) {
                // Retrieves the map_id and spawnpoint properties from the i-th object's properties.
                // Returns a Pair containing the MapAssets of the new map and the spawnpoint on the new map.
                return Pair.of(MapAssets.valueOf(properties.get("map_id", String.class)), properties.get("spawnpoint", String.class));
            }
        }
        // If no object contains the specified coordinates, the loop ends and the method returns null.
        return null;
    }

    /**
     * This method checks for an activity at the specified coordinates.
     * An activity is defined as an interactive element within the game world.
     *
     * @param x The x-coordinate to check for an activity. This is typically the x-coordinate of the player.
     * @param y The y-coordinate to check for an activity. This is typically the y-coordinate of the player.
     * @return An {@link ActivityMapObject} representing the activity at the specified coordinates, or null if there is no activity at the specified coordinates.
     *
     * The method works as follows:
     * - It retrieves all the objects from the activity layer.
     * - It iterates over each object in the activity layer.
     * - For each object, it retrieves the properties of the object.
     * - It checks if the specified coordinates are within the bounds of the object.
     * - If the specified coordinates are within the bounds of the object and the object is an activity (i.e., it has a property "is_activity" that is true), it returns an {@link ActivityMapObject} representing the activity.
     * - If no object contains the specified coordinates or none of the objects at the specified coordinates are activities, it returns null.
     */
    private @Nullable ActivityMapObject getCellActivity(float x, float y) {
        MapObjects objects = activityLayer.getObjects();
        // This loop iterates over all the objects in the activity layer.
        for (int i = 0; i < objects.getCount(); i++) {
            // Retrieves the i-th object from the activity layer.
            MapObject mapObject = objects.get(i);
            // Retrieves the properties of the i-th object.
            MapProperties properties = mapObject.getProperties();
            // Retrieves the x-coordinate of the i-th object.
            float x1 = properties.get("x", float.class);
            // Retrieves the y-coordinate of the i-th object.
            float y2 = properties.get("y", float.class);
            // Retrieves the width of the i-th object.
            float width = properties.get("width", float.class);
            // Retrieves the height of the i-th object.
            float height = properties.get("height", float.class);
            // Checks if the specified coordinates (x, y) are within the bounds of the i-th object.
            // If the specified coordinates are within the bounds of the i-th object:
            if (x >= x1 && x <= x1 + width && y >= y2 && y <= y2 + height) {
                // Retrieves the is_activity property from the i-th object's properties.
                Boolean isActivity = properties.get("is_activity", Boolean.class);
                // If the is_activity property is not true, it continues to the next iteration of the loop.
                if (!Boolean.TRUE.equals(isActivity)) continue;
                // If the is_activity property is true, it returns an ActivityMapObject representing the activity.
                return ActivityMapObject.fromMapObject(mapObject);
            }
        }
        // If no object contains the specified coordinates or none of the objects at the specified coordinates are activities, the loop ends and the method returns null.
        return null;
    }

    // This method is triggered when a key is typed.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    // This method is triggered when a touch down event occurs.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    // This method is triggered when a touch up event occurs.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    // This method is triggered when a touch is cancelled.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    // This method is triggered when a touch is dragged.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    // This method is triggered when the mouse is moved.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    // This method is triggered when the mouse wheel is scrolled.
    // Currently, it does not perform any action and always returns false.
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    /**
     * Sets the size of the player's sprite and scales it.
     * This method overrides the {@link Sprite#setSize(float, float)} method.
     * It first calls {@link Sprite#setSize(float, float)} method to set the size of the sprite.
     * Then, it scales the sprite by a factor of 3.
     *
     * @param width  The width to set for the sprite.
     * @param height The height to set for the sprite.
     */
    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setScale(3);
    }

    /**
     * Disposes of the resources used by the player.
     * This method should be called when the player is no longer needed to free up memory.
     * Currently, it disposes of the skin used for UI elements in the game.
     */
    public void dispose() {
        uiSkin.dispose();
    }
}
