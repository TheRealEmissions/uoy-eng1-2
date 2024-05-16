package com.eng1.game.game.player;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalTime;

@UtilityClass
public class Statistics {

    public enum PlayerStatistics implements PlayerStatistic {
        ENERGY("Energy", 1f),
        HAPPINESS("Happiness", 1f),
        STUDY("Study Level", 0.1f);

        @Getter
        private final ProgressBar progressBar = new ProgressBar(0, 1, 0.1f, false, uiSkin);

        @Getter
        private final String label;
        private final float defaultValue;

        private @Range(from=0, to=1) float value = getDefault();
        private float total = 0f;

        PlayerStatistics(String label, float defaultValue) {
            this.label = label;
            this.defaultValue = defaultValue;
            progressBar.setWidth(200);
            progressBar.setHeight(50);
            progressBar.setAnimateDuration(0.25f);
        }

        @Override
        public float get() {
            return value;
        }

        @Override
        public void set(@Range(from=0, to=1) float value) {
            this.value = Math.max(PROGRESS_BAR_MINIMUM, Math.min(1, value));
            this.progressBar.setValue(this.value);
        }

        @Override
        public void increase(float amount) {
            set(Math.min(1, value + amount));
        }

        @Override
        public void decrease(float amount) {
            set(Math.max(PROGRESS_BAR_MINIMUM, value - amount));
        }

        @Override
        public float getDefault() {
            return defaultValue;
        }

        @Override
        public void setTotal(float total) {
            this.total = total;
        }

        @Override
        public void increaseTotal(float amount) {
            this.total += amount;
        }

        @Override
        public float getTotal() {
            return total;
        }

        @Override
        public float getMaxTotal() {
            return Statistics.MAX_DAYS;
        }

        @Override
        public void reset() {
            set(getDefault());
        }
    }

    public enum Effect {
        INCREASE,
        DECREASE,
        RESET
    }

    @Getter
    private static int score = 0;
    @Getter
    public static final int MAX_DAYS = 7;
    @Getter
    private static int day = 1; //Current day
    @Getter
    private static LocalTime time = LocalTime.of(8, 0); //Current time
    public static final LocalTime DAY_START = LocalTime.of(8, 0); //When the player wakes up
    public static final LocalTime DAY_END = LocalTime.of(0, 0); //When the player has to sleep

    public static void newDay() {
        //Sets time and energy for the new day, increases day count
        Statistics.time = DAY_START;
        Statistics.day++;
    }

    public static void increaseTime(@NotNull LocalTime time) {
        //Increases current time by the inputted time
        Statistics.time = Statistics.time.plusHours(time.getHour());
        Statistics.time = Statistics.time.plusMinutes(time.getMinute());
    }

    public static void increaseScore(int score) {
        Statistics.score += score;
    }

    public static void dispose() {
        PlayerStatistic.dispose();
    }
}
