package com.eng1.gdxtesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.eng1.game.player.GameStats;
import java.time.LocalTime;

// Be aware that when adding tests you may need to clean and rebuild

public class GameStatsUnitTests {

    @Test
    public void GameStatsIncreaseTime1HourTest() {
        LocalTime intialTime = GameStats.getTime();
        GameStats.increaseTime(LocalTime.of(1, 0));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 1 hour is added",
                intialTime.plusHours(1),
                GameStats.getTime()
        );
    }

    @Test
    public void GameStatsIncreaseTime15MinuteTest() {
        LocalTime intialTime = GameStats.getTime();
        GameStats.increaseTime(LocalTime.of(0, 15));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 15 minutes is added",
                intialTime.plusMinutes(15),
                GameStats.getTime()
        );
    }

    @Test
    public void GameStatsIncreaseTimeHoursAndMinutesTest() {
        LocalTime intialTime = GameStats.getTime();
        GameStats.increaseTime(LocalTime.of(2, 48));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 2:48 is added",
                intialTime.plusHours(2).plusMinutes(48),
                GameStats.getTime()
        );
    }

    @Test
    public void GameStatsDecreaseEnergyTest() {
        int initialEnergy = GameStats.getEnergy();
        GameStats.decreaseEnergy(1);

        assertEquals(
                "Checks that GameStats.decreaseEnergy() works as expected",
                initialEnergy - 1,
                GameStats.getEnergy()
        );
    }

    @Test
    public void GameStatsIncreaseScoreTest() {
        int initialScore = GameStats.getScore();
        GameStats.increaseScore(15);

        assertEquals(
                "Checks whether GameStats.increaseScore works as expected",
                initialScore + 15,
                GameStats.getScore()
        );
    }

    /**
     * Tests to check all the variables in GameStats are effected as expected
     * by the GameStats.newDay() method.
     * Some of these tests are dependent on other Time, Score and Energy tests
     * passing to know that the other methods are working as expected.
     */
    @Test
    public void GameStatsNewDayDayChangeTest() {
        int intialDay = GameStats.getDay();

        GameStats.newDay();

        assertEquals(
                "Checks that GameStats.newDay() increases the day by 1",
                intialDay + 1,
                GameStats.getDay()
        );
    }

    @Test
    public void GameStatsNewDayTimeChangeTest() {
        // Ensure initialTime != DAY_START
        if(GameStats.getTime().equals(GameStats.DAY_START)) {
            GameStats.increaseTime(LocalTime.of(0, 5));
        }
        LocalTime initialTime = GameStats.getTime();

        GameStats.newDay();

        assertEquals(
                "Checks that GameStats.newDay() sets the time to DAY_START",
                GameStats.DAY_START,
                GameStats.getTime()
        );
    }

    @Test
    public void GameStatsNewDayEnergyChangeTest() {
        // Ensure initialEnergy != MAX_ENERGY
        if (GameStats.getEnergy() == GameStats.MAX_ENERGY) {
            GameStats.decreaseEnergy(1);
        }
        int initialEnergy = GameStats.getEnergy();

        GameStats.newDay();

        assertEquals(
                "Checks that GameStats.newDay() sets the energy to MAX_ENERGY",
                GameStats.MAX_ENERGY,
                GameStats.getEnergy()
        );
    }

    @Test
    public void GameStatsNewDayUnchangedScoreTest() {
        int initialScore = GameStats.getScore();

        GameStats.newDay();

        assertEquals(
                "Checks that GameStats.newDay() has no effect on an unchanged score",
                initialScore,
                GameStats.getScore()
        );
    }

    @Test
    public void GameStatsNewDayChangedScoreTest() {
        GameStats.increaseScore(1);
        int initialScore = GameStats.getScore();

        GameStats.newDay();

        assertEquals(
                "Checks that GameStats.newDay() has no effect on a changed score",
                initialScore,
                GameStats.getScore()
        );
    }

}