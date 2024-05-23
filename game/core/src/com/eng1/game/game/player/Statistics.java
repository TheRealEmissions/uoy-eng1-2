package com.eng1.game.game.player;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.eng1.game.game.activity.Activities;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalTime;

/**
 * This is a utility class that holds all the statistics related to the player in the game.
 * It includes player's energy, happiness, study level, score, current day and time.
 * It also provides methods to manipulate these statistics such as increasing or decreasing values,
 * checking if it's the end of the day or the game, and moving to a new day.
 */
@UtilityClass
public class Statistics {

    /**
     * This is an enumeration that represents the different types of player statistics in the game.
     * Each statistic is represented as an enum constant and implements the {@link PlayerStatistic} interface.
     * The statistics include energy, happiness, and study level.
     */
    public enum PlayerStatistics implements PlayerStatistic {
        /**
         * Represents the player's energy level in the game.
         * The initial value is set to 0.5f.
         */
        ENERGY("Energy", 0.5f),

        /**
         * Represents the player's happiness level in the game.
         * The initial value is set to 0.5f.
         */
        HAPPINESS("Happiness", 0.5f),

        /**
         * Represents the player's study level in the game.
         * The initial value is set to 0f.
         */
        STUDY("Study Level", 0f);

        /**
         * A {@link ProgressBar} object representing the current state of the player's statistic.
         * The {@link ProgressBar} is initialized with a minimum value of 0, a maximum value of 1, and an increment of 0.1.
         * The {@link ProgressBar} is not vertical and uses the {@link #uiSkin} for its appearance.
         */
        @Getter
        private final ProgressBar progressBar = new ProgressBar(0, 1, 0.1f, false, uiSkin);

        /**
         * The label for the player statistic.
         * This is a descriptive string that represents the name of the statistic.
         * For example, "Energy", "Happiness", or "Study Level".
         */
        @Getter
        private final String label;
        /**
         * The default value for the player statistic.
         * This is the initial value that the statistic starts with when the game begins.
         * For example, the energy and happiness statistics start with a default value of 0.5,
         * while the study level statistic starts with a default value of 0.
         */
        private final float defaultValue;

        /**
         * The current value of the player statistic in the current day.
         * This value is within the range of 0 to 1, inclusive.
         * It represents the current state of the player's statistic in the game.
         * For example, if the player's energy level is at half capacity, this value would be 0.5.
         */
        private @Range(from=0, to=1) float value;

        /**
         * The total accumulated value of the player statistic.
         * This value starts at 0 and increases over time as the player progresses in the game, incrementing everytime the player sleeps and transitions to a new day.
         * It represents the total amount of the statistic that the player has gained throughout the game.
         */
        private float total = 0f;

        /**
         * Constructor for the {@link PlayerStatistics} enum.
         *
         * This constructor initializes the player statistic with a label and a default value.
         * It also sets up the progress bar associated with the statistic.
         *
         * @param label The label for the player statistic. This is a descriptive string that represents the name of the statistic.
         * @param defaultValue The default value for the player statistic. This is the initial value that the statistic starts with when the game begins.
         */
        PlayerStatistics(String label, float defaultValue) {
            this.label = label;
            this.defaultValue = defaultValue;
            this.value = defaultValue;
            progressBar.setValue(defaultValue);
            progressBar.setWidth(200);
            progressBar.setHeight(50);
            progressBar.setAnimateDuration(0.25f);
            progressBar.setRound(false);
        }

        /**
         * Gets the current value of the player statistic.
         *
         * This method returns the current value of the player's statistic in the game.
         * The value is within the range of 0 to 1, inclusive.
         *
         * @return The current value of the player statistic.
         */
        @Override
        public float get() {
            return value;
        }

        /**
         * Sets the current value of the player statistic.
         *
         * This method sets the current value of the player's statistic in the game.
         * The value is within the range of 0 to 1, inclusive.
         * It also updates the progress bar to reflect the new value.
         * If the new value is less than the minimum progress bar value, the progress bar is set to the minimum.
         * If the new value is more than 1, the progress bar is set to 1.
         *
         * @param value The new value for the player statistic.
         */
        @Override
        public void set(@Range(from=0, to=1) float value) {
            this.value = value;
            this.progressBar.setValue(Math.max(PROGRESS_BAR_MINIMUM, Math.min(1, value)));
        }

        /**
         * Increases the current value of the player statistic by a specified amount.
         *
         * This method increases the current value of the player's statistic in the game by the specified amount.
         * The new value is capped at 1, meaning that if the increase would result in a value greater than 1, the value is set to 1 instead.
         *
         * @param amount The amount to increase the player statistic by.
         */
        @Override
        public void increase(float amount) {
            set(Math.min(1, value + amount));
        }

        /**
         * Decreases the current value of the player statistic by a specified amount.
         *
         * This method decreases the current value of the player's statistic in the game by the specified amount.
         * The new value is capped at 0, meaning that if the decrease would result in a value less than 0, the value is set to 0 instead.
         *
         * @param amount The amount to decrease the player statistic by.
         */
        @Override
        public void decrease(float amount) {
            set(Math.max(0, value - amount));
        }

        /**
         * Gets the default value of the player statistic.
         *
         * This method returns the default value of the player's statistic in the game.
         * The default value is the initial value that the statistic is initialised with.
         *
         * @return The default value of the player statistic.
         */
        @Override
        public float getDefault() {
            return defaultValue;
        }

        /**
         * Sets the total accumulated value of the player statistic.
         *
         * This method sets the total accumulated value of the player's statistic in the game.
         * This value starts at 0 and increases over time as the player progresses in the game, incrementing every time the player sleeps and transitions to a new day.
         * It represents the total amount of the statistic that the player has gained throughout the game.
         *
         * @param total The new total accumulated value for the player statistic.
         */
        @Override
        public void setTotal(float total) {
            this.total = total;
        }

        /**
         * Increases the total accumulated value of the player statistic by a specified amount.
         *
         * This method increases the total accumulated value of the player's statistic in the game by the specified amount.
         * The total accumulated value starts at 0 and increases over time as the player progresses in the game, incrementing every time the player sleeps and transitions to a new day.
         * It represents the total amount of the statistic that the player has gained throughout the game.
         *
         * @param amount The amount to increase the total accumulated value of the player statistic by.
         */
        @Override
        public void increaseTotal(float amount) {
            this.total += amount;
        }

        /**
         * Gets the total accumulated value of the player statistic.
         *
         * This method returns the total accumulated value of the player's statistic in the game.
         * The total accumulated value starts at 0 and increases over time as the player progresses in the game, incrementing every time the player sleeps and transitions to a new day.
         * It represents the total amount of the statistic that the player has gained throughout the game.
         *
         * @return The total accumulated value of the player statistic.
         */
        @Override
        public float getTotal() {
            return total;
        }

        /**
         * Gets the maximum total accumulated value of the player statistic.
         *
         * This method returns the maximum total accumulated value of the player's statistic in the game.
         * The maximum total accumulated value is equal to the maximum number of days in the game.
         * It represents the maximum amount of the statistic that the player can gain throughout the game.
         *
         * @return The maximum total accumulated value of the player statistic.
         */
        @Override
        public float getMaxTotal() {
            return Statistics.MAX_DAYS;
        }

        /**
         * Resets the current value of the player statistic to its default value.
         *
         * This method resets the current value of the player's statistic in the game to its default value.
         * The default value is the initial value that the statistic starts with when the game begins.
         */
        @Override
        public void reset() {
            set(getDefault());
        }

        /**
         * Gets the maximum score that can be achieved in a single day.
         *
         * This method returns the maximum score that can be achieved in a single day in the game.
         * The maximum score per day is equal to the number of player statistics, as each statistic contributes to the daily score.
         *
         * @return The maximum score that can be achieved in a single day.
         */
        public static int getMaxScorePerDay() {
            return values().length;
        }
    }

    /**
     * This is an enumeration that represents the different types of effects that can be applied to the player's statistics in the game.
     * Each effect is represented as an enum constant.
     * The effects include increase, decrease, and reset.
     */
    public enum Effect {
        /**
         * Represents the effect of increasing a player's statistic.
         */
        INCREASE,

        /**
         * Represents the effect of decreasing a player's statistic.
         */
        DECREASE,

        /**
         * Represents the effect of resetting a player's statistic to its default value.
         */
        RESET
    }

    /**
     * Gets the current score of the player in the game.
     *
     * This method returns the current score of the player in the game.
     * The score starts at 0 and increases over time as the player progresses in the game.
     * It represents the total points that the player has gained throughout the game.
     *
     * @return The current score of the player.
     * @deprecated in favour of {@link com.eng1.game.game.Score}
     */
    @Getter
    @Deprecated(forRemoval = true)
    private static int score = 0;
    /**
     * The maximum number of days in the game.
     * This constant represents the total number of days that the game lasts.
     */
    public static final int MAX_DAYS = 7;

    /**
     * The maximum score that can be achieved in the game.
     * This constant represents the total maximum score that can be achieved in the game.
     * It is calculated as the maximum score that can be achieved in a single day multiplied by the maximum number of days in the game.
     */
    public static final int MAX_SCORE = PlayerStatistics.getMaxScorePerDay() * MAX_DAYS;
    @Getter
    private static int day = 1; //Current day
    /**
     * The current time in the game.
     *
     * This field represents the current time in the game.
     * It is initialized to 7:00, which represents the start of the day in the game.
     * However, time is displayed 1 hour ahead to the player due to rolling over behaviour of {@link LocalTime}
     * The time increases as the player performs activities in the game.
     *
     * @return The current time in the game.
     */
    @Getter
    private static LocalTime time = LocalTime.of(7, 0);

    /**
     * The start time of the day in the game.
     *
     * This constant represents the time when the player wakes up and the day starts in the game.
     * It is set to 7:00.
     */
    public static final LocalTime DAY_START = LocalTime.of(7, 0);

    /**
     * The end time of the day in the game.
     *
     * This constant represents the time when the player has to sleep and the day ends in the game.
     * It is set to 23:00.
     */
    public static final LocalTime DAY_END = LocalTime.of(23, 0);

    /**
     * Transitions the game to a new day.
     *
     * This method is responsible for transitioning the game to a new day.
     * It sets the current time to the start of the day and increments the current day count.
     */
    public static void newDay() {
        Statistics.time = DAY_START;
        Statistics.day++;
    }

    /**
     * Checks if the current game time has reached the end of the day.
     *
     * This method compares the current game time with the predefined end of day time.
     * If the current game time is equal to the end of day time, it returns true, indicating that it's the end of the day.
     * Otherwise, it returns false.
     *
     * @return A boolean value indicating whether the current game time has reached the end of the day.
     */
    public static boolean isEndOfDay() {
        return Statistics.time.equals(DAY_END);
    }

    /**
     * Checks if the current game day has reached the maximum number of days.
     *
     * This method compares the current game day with the predefined maximum number of days.
     * If the current game day is equal to the maximum number of days, it returns true, indicating that it's the end of the game.
     * Otherwise, it returns false.
     *
     * @return A boolean value indicating whether the current game day has reached the maximum number of days.
     */
    public static boolean isEndOfDays() {
        return Statistics.day == MAX_DAYS;
    }

    /**
     * Increases the current game time by the specified amount of time.
     *
     * This method increases the current game time by the amount of time specified in the input parameter.
     * The input time is broken down into hours and minutes, and these are added separately to the current game time.
     * This allows for precise control over the time increment.
     *
     * @param time The amount of time to add to the current game time. This is a {@link LocalTime} object.
     */
    public static void increaseTime(@NotNull LocalTime time) {
        //Increases current time by the inputted time
        Statistics.time = Statistics.time.plusHours(time.getHour());
        Statistics.time = Statistics.time.plusMinutes(time.getMinute());
    }

    /**
     * Increases the current score of the player in the game.
     *
     * This method increases the current score of the player in the game by the specified amount.
     * The score starts at 0 and increases over time as the player progresses in the game.
     * It represents the total points that the player has gained throughout the game.
     *
     * @param score The amount to increase the player's score by.
     * @deprecated in favour of {@link com.eng1.game.game.Score}
     */
    @Deprecated(forRemoval = true)
    public static void increaseScore(int score) {
        Statistics.score += score;
    }

    /**
     * Disposes of the player statistics.
     *
     * This method disposes of the player statistics by calling the dispose method of the PlayerStatistic interface.
     * It is used to clean up resources related to the player statistics when they are no longer needed.
     */
    public static void dispose() {
        PlayerStatistic.dispose();
    }
}
