package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

/**
 * Links with {@link Achievements#SOCIAL_BUTTERFLY}
 *
 * This class represents the Social Butterfly achievement in the game.
 * The Social Butterfly achievement is achieved by hanging out with friends or going to the pub at least 3 times in a week.
 * The class keeps track of the total number of times the player has socialised.
 */
@Setter
@Getter
public class SocialButterflyAchievement implements Achievement {
    //Social Butterfly
    //Achieve by hanging out with friends or going to the pub at least 3 times in a week
    //(Hang out with friends, Go to courtyard)
    //2%

    /**
     * The total number of times the player has socialised.
     *
     * This variable is used to track the progress towards the Social Butterfly achievement.
     * The Social Butterfly achievement is achieved by hanging out with friends or going to the pub at least 3 times in a week.
     */
    private int total = 0;

    /**
     * Checks if the Social Butterfly achievement has been achieved.
     *
     * The Social Butterfly achievement is achieved when the player has socialised 3 or more times.
     * This method checks if the total number of times the player has socialised is greater than or equal to 3.
     *
     * @return true if the player has socialised 3 or more times, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        return total >= 3;
    }

    /**
     * Records the instance when the player has socialised.
     *
     * This method is called when the player socialises.
     * The total number of times the player has socialised is incremented by one.
     */
    public void socialise() {
        total++;
    }

    /**
     * Resets the total number of times the player has socialised.
     */
    public void reset() {
        total = 0;
    }

}
