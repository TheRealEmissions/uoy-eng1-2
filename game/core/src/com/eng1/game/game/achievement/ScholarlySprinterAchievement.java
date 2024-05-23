package com.eng1.game.game.achievement;

import java.util.*;

/**
 * Links with {@link Achievements#SCHOLARLY_SPRINTER}
 *
 * This class represents the Scholarly Sprinter achievement in the game.
 * The Scholarly Sprinter achievement is achieved by studying once every day.
 * The class keeps track of the days on which the player has studied.
 */
public class ScholarlySprinterAchievement implements Achievement {
    //Scholarly Sprinter
    //Achieve by studying once every day
    //(Study at desk, Study)
    //6%

    /**
     * A {@link HashSet} that stores the days on which the player has studied.
     *
     * Each integer in the set represents a unique day.
     * This set is used to track the progress towards the Scholarly Sprinter achievement.
     * The Scholarly Sprinter achievement is achieved by studying once every day.
     */
    private final Set<Integer> daysStudied = new HashSet<>();

    /**
     * Checks if the Scholarly Sprinter achievement has been achieved.
     *
     * The Scholarly Sprinter achievement is achieved when the player has studied every day of the week.
     * This method checks if the total number of unique days the player has studied is greater than or equal to 7.
     *
     * @return true if the player has studied on 7 unique days, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        return getDaysStudied() >= 7;
    }

    /**
     * Records the day on which the player has studied.
     *
     * This method is called when the player studies on a particular day.
     * The day is represented as an integer and added to the set of days on which the player has studied.
     *
     * @param day the day on which the player has studied
     */
    public void study(int day) {
        daysStudied.add(day);
    }

    /**
     * Sets the days on which the player has studied.
     *
     * This method is used to manually set the days on which the player has studied.
     * It first clears the current set of days, then adds all the days from the provided list.
     *
     * @param daysStudied a list of days on which the player has studied
     */
    public void setDaysStudied(List<Integer> daysStudied) {
        this.daysStudied.clear();
        this.daysStudied.addAll(daysStudied);
    }

    /**
     * Resets the days on which the player has studied.
     */
    public void reset() {
        daysStudied.clear();
    }

    /**
     * Gets the number of unique days on which the player has studied.
     *
     * This method returns the size of the set of days on which the player has studied.
     * It is used to check the progress towards the Scholarly Sprinter achievement.
     *
     * @return the number of unique days on which the player has studied
     */
    public int getDaysStudied() {
        return daysStudied.size();
    }
}
