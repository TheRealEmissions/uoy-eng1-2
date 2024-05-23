package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

/**
 * Links with {@link Achievements#DREAMWEAVER}
 * This achievement is achieved by daydreaming more than 4 times a week.
 *
 * The class is annotated with {@link Setter} and {@link Getter} from the Lombok library, which automatically generates getter and setter methods for its fields.
 */
@Setter
@Getter
public class DreamweaverAchievement implements Achievement {
    //Dreamweaver
    //Achieve by daydreaming more than 4 times a week
    //(Daydream)
    //2%

    /**
     * The total number of times the player has daydreamed in a week.
     *
     * This field is used to track the progress towards achieving the {@link Achievements#DREAMWEAVER} achievement.
     * The Dreamweaver achievement is achieved when the player has daydreamed more than 4 times in a week.
     */
    private int total = 0;

    /**
     * Checks if the achievement has been achieved.
     *
     * The achievement is achieved when the player has daydreamed more than 4 times in a week.
     * The method returns true if the total number of times the player has daydreamed in a week is greater than 4, and false otherwise.
     *
     * @return A boolean indicating whether the achievement has been achieved.
     */
    @Override
    public boolean hasAchieved() {
        return total > 4;
    }

    /**
     * Increments the total number of times the player has daydreamed in a week.
     *
     * This method should be called when the player daydreams.
     * Each call to this method represents one instance of daydreaming.
     * The total field is incremented by one each time this method is called.
     */
    public void daydream() {
        total++;
    }

    /**
     * Resets the total number of times the player has daydreamed in a week.
     */
    public void reset() {
        total = 0;
    }
}
