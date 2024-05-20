package com.eng1.gdxtesting;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.eng1.game.game.player.Statistics;
import org.junit.runner.RunWith;

import java.time.LocalTime;

// Be aware that when adding tests you may need to clean and rebuild

@RunWith(GdxTestRunner.class) // Needed as the Statistics class calls some classes that uses LibGDX
public class StatisticsUnitTests {

    @Test
    public void GameStatsIncreaseTime1HourTest() {
        LocalTime initialTime = Statistics.getTime();
        Statistics.increaseTime(LocalTime.of(1, 0));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 1 hour is added",
                initialTime.plusHours(1),
                Statistics.getTime()
        );
    }

    @Test
    public void GameStatsIncreaseTime15MinuteTest() {
        LocalTime initialTime = Statistics.getTime();
        Statistics.increaseTime(LocalTime.of(0, 15));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 15 minutes is added",
                initialTime.plusMinutes(15),
                Statistics.getTime()
        );
    }

    @Test
    public void GameStatsIncreaseTimeHoursAndMinutesTest() {
        LocalTime initialTime = Statistics.getTime();
        Statistics.increaseTime(LocalTime.of(2, 48));

        assertEquals(
                "Checks whether GameStats Increase Time method works as expected when 2:48 is added",
                initialTime.plusHours(2).plusMinutes(48),
                Statistics.getTime()
        );
    }

    @Test
    public void GameStatsIncreaseEnergyTest() {
        float initialEnergy = Statistics.PlayerStatistics.ENERGY.get();
        Statistics.PlayerStatistics.ENERGY.increase(0.1f);

        assertEquals(
                "Checks that GameStats.decreaseEnergy() works as expected",
                initialEnergy + 0.1f,
                Statistics.PlayerStatistics.ENERGY.get(),
                0.0f // No delta expected
        );
    }

    @Test
    public void GameStatsDecreaseEnergyTest() {
        float initialEnergy = Statistics.PlayerStatistics.ENERGY.get();
        Statistics.PlayerStatistics.ENERGY.decrease(0.1f);

        assertEquals(
                "Checks that GameStats.decreaseEnergy() works as expected",
                initialEnergy - 0.1f,
                Statistics.PlayerStatistics.ENERGY.get(),
                0.0f // No delta expected
        );
    }

    @Test
    public void GameStatsIncreaseScoreTest() {
        int initialScore = Statistics.getScore();
        Statistics.increaseScore(15);

        assertEquals(
                "Checks whether GameStats.increaseScore works as expected",
                initialScore + 15,
                Statistics.getScore()
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
        int initialDay = Statistics.getDay();

        Statistics.newDay();

        assertEquals(
                "Checks that GameStats.newDay() increases the day by 1",
                initialDay + 1,
                Statistics.getDay()
        );
    }

    @Test
    public void GameStatsNewDayTimeChangeTest() {
        // Ensure initialTime != DAY_START
        if(Statistics.getTime().equals(Statistics.DAY_START)) {
            Statistics.increaseTime(LocalTime.of(0, 5));
        }
        LocalTime initialTime = Statistics.getTime();

        Statistics.newDay();

        assertEquals(
                "Checks that GameStats.newDay() sets the time to DAY_START",
                Statistics.DAY_START,
                Statistics.getTime()
        );
    }

    @Test
    public void GameStatsNewDayUnchangedScoreTest() {
        int initialScore = Statistics.getScore();

        Statistics.newDay();

        assertEquals(
                "Checks that GameStats.newDay() has no effect on an unchanged score",
                initialScore,
                Statistics.getScore()
        );
    }

    @Test
    public void GameStatsNewDayChangedScoreTest() {
        Statistics.increaseScore(1);
        int initialScore = Statistics.getScore();

        Statistics.newDay();

        assertEquals(
                "Checks that GameStats.newDay() has no effect on a changed score",
                initialScore,
                Statistics.getScore()
        );
    }

}