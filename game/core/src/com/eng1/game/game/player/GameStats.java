package com.eng1.game.game.player;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;

@UtilityClass
public class GameStats {
    @Getter
    private static int energy = 100; //Keeps track of players current energy
    public static final int MAX_ENERGY = 100; //Max energy (energy is reset to this at the start of every day)
    @Getter
    private static int score = 0;
    @Getter
    private static int day = 1; //Current day
    @Getter
    private static LocalTime time = LocalTime.of(8, 0); //Current time
    public static final LocalTime DAY_START = LocalTime.of(8, 0); //When the player wakes up
    public static final LocalTime DAY_END = LocalTime.of(0, 0); //When the player has to sleep

    public static void decreaseEnergy(int energy) {
        GameStats.energy -= energy;
    }

    public static void newDay() {
        //Sets time and energy for the new day, increases day count
        GameStats.time = DAY_START;
        GameStats.energy = MAX_ENERGY;
        GameStats.day++;
    }

    public static void increaseTime(@NotNull LocalTime time) {
        //Increases current time by the inputted time
        GameStats.time = GameStats.time.plusHours(time.getHour());
        GameStats.time = GameStats.time.plusMinutes(time.getMinute());
    }

    public static void increaseScore(int score) {
        GameStats.score += score;
    }

}
