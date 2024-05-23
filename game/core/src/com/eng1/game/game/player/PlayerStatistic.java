package com.eng1.game.game.player;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.skins.SkinAssets;

/**
 * The PlayerStatistic interface defines the contract for player statistics in the game.
 * It includes methods for getting and setting the value of the statistic, increasing and decreasing the value,
 * getting the default value, setting and getting the total value, increasing the total value, getting the maximum total value,
 * resetting the statistic, and disposing of the resources used by the statistic.
 * It also includes a method for getting a progress bar representing the statistic and a method for getting a label for the statistic.
 * The interface includes a constant for the minimum value of the progress bar and a Skin object for the UI.
 */
public interface PlayerStatistic {
    /**
     * The minimum value of the progress bar.
     */
    float PROGRESS_BAR_MINIMUM = 0f;
    /**
     * The UI skin used for the progress bar.
     */
    Skin uiSkin = SkinAssets.UI.get();

    /**
     * Returns a {@link ProgressBar} object that represents the player's statistic.
     * @return {@link ProgressBar} object.
     */
    ProgressBar getProgressBar();

    /**
     * Returns a label ({@link String}) that represents the player's statistic.
     * @return String object.
     */
    String getLabel();

    /**
     * Returns the current value of the player's statistic.
     * @return float value.
     */
    float get();

    /**
     * Sets the current value of the player's statistic.
     * @param value The value to set.
     */
    void set(float value);

    /**
     * Increases the current value of the player's statistic by a specified amount.
     * @param amount The amount to increase by.
     */
    void increase(float amount);

    /**
     * Decreases the current value of the player's statistic by a specified amount.
     * @param amount The amount to decrease by.
     */
    void decrease(float amount);

    /**
     * Returns the default value of the player's statistic.
     * @return float value.
     */
    float getDefault();

    /**
     * Sets the total value of the player's statistic.
     * @param total The total value to set.
     */
    void setTotal(float total);

    /**
     * Increases the total value of the player's statistic by a specified amount.
     * @param amount The amount to increase by.
     */
    void increaseTotal(float amount);

    /**
     * Returns the total value of the player's statistic.
     * @return float value.
     */
    float getTotal();

    /**
     * Returns the maximum total value of the player's statistic.
     * @return float value.
     */
    float getMaxTotal();

    /**
     * Resets the player's statistic to its default state.
     */
    void reset();

    /**
     * Disposes of the resources used by {@link #uiSkin}
     * This method should be called when the UI skin is no longer needed to free up memory.
     */
    static void dispose() {
        uiSkin.dispose();
    }
}
