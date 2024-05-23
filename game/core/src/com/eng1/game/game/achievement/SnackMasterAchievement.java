package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

/**
 * Links with {@link Achievements#SNACK_MASTER}
 *
 * This class represents the Snack Master achievement in the game.
 * The Snack Master achievement is achieved by snacking 5 or more out of 7 days.
 * The class keeps track of the total number of days on which the player has snacked.
 */
@Setter
@Getter
public class SnackMasterAchievement implements Achievement {
    //Snack Master
    //Achieve by snacking 5 out of 7 days.
    //(Have a snack, Get a snack)
    //1%

    /**
     * The total number of days on which the player has snacked.
     *
     * This variable is used to track the progress towards the Snack Master achievement.
     * The Snack Master achievement is achieved by snacking on 5 or more out of 7 days.
     */
    private int total = 0;

    /**
     * Checks if the Snack Master achievement has been achieved.
     *
     * The Snack Master achievement is achieved when the player has snacked on 5 or more days.
     * This method checks if the total number of days the player has snacked is greater than or equal to 5.
     *
     * @return true if the player has snacked on 5 or more days, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        return total >= 5;
    }

    /**
     * Records the day on which the player has snacked.
     *
     * This method is called when the player snacks on a particular day.
     * The total number of days the player has snacked is incremented by one.
     */
    public void snack() {
        total++;
    }

    /**
     * Resets the total number of days on which the player has snacked.
     */
    public void reset() {
        total = 0;
    }
}
