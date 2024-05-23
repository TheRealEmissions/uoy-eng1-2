package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

/**
 * Links with {@link Achievements#TEACHERS_PET}
 *
 * This class represents the Teacher's Pet achievement in the game.
 * The Teacher's Pet achievement is achieved by attending Teaching Hours more than twice in a week.
 * The class keeps track of the total number of times the player has attended Teaching Hours.
 */
@Getter
@Setter
public class TeachersPetAchievement implements Achievement {
    //Teacher’s Pet
    //Achieve by going to Teaching Hours more than twice in a week
    //(Attend Teaching Hours)
    //3%

    /**
     * The total number of times the player has attended Teaching Hours.
     *
     * This variable is used to track the progress towards the Teacher's Pet achievement.
     * The Teacher's Pet achievement is achieved by attending Teaching Hours more than twice in a week.
     */
    private int total = 0;

    /**
     * Checks if the Teacher's Pet achievement has been achieved.
     *
     * The Teacher's Pet achievement is achieved when the player has attended Teaching Hours more than twice.
     * This method checks if the total number of times the player has attended Teaching Hours is greater than 2.
     *
     * @return true if the player has attended Teaching Hours more than twice, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        return total > 2;
    }

    /**
     * Records the instance when the player has attended Teaching Hours.
     *
     * This method is called when the player attends Teaching Hours.
     * The total number of times the player has attended Teaching Hours is incremented by one.
     */
    public void attendTeachingHours() {
        total++;
    }

    /**
     * Resets the total number of times the player has attended Teaching Hours.
     *
     * This method is used to clear the total number of times the player has attended Teaching Hours.
     * It is typically called at the start of a new week.
     */
    public void reset() {
        total = 0;
    }
}
