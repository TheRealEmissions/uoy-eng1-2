package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

/**
 * Links with {@link Achievements#FITNESS_FANATIC}
 * This achievement is achieved by being active at least 3 times a week.
 * Activities can include cardio, weight lifting, football, basketball, etc.
 *
 * The class is annotated with {@link Setter} and {@link Getter} from the Lombok library, which automatically generates getter and setter methods for its fields.
 */
@Setter
@Getter
public class FitnessFanaticAchievement implements Achievement {
    //Fitness Fanatic
    //Achieve by being active at least 3 times a week.
    //(Do cardio, lift some weights, play football, play basketball)
    //4%

    /**
     * The total number of times the player has been active in a week.
     *
     * This field is used to track the progress towards achieving the achievement.
     * The achievement is achieved when the player has been active more than 3 times in a week.
     */
    private int total = 0;

    /**
     * Checks if the player has achieved the Fitness Fanatic achievement.
     *
     * The Fitness Fanatic achievement is achieved when the player has been active more than 3 times in a week.
     * This method checks if the total number of times the player has been active in a week is greater than or equal to 3.
     *
     * @return true if the player has been active more than or equal to 3 times in a week, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        return total >= 3;
    }

    /**
     * Increments the total number of times the player has been active in a week.
     *
     * This method is called when the player completes an activity.
     * Each activity increases the total count by one.
     */
    public void exercise() {
        total++;
    }

    /**
     * Resets the total number of times the player has been active in a week to zero.
     */
    public void reset() {
        total = 0;
    }

}
