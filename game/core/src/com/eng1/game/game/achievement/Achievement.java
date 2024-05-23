package com.eng1.game.game.achievement;

/**
 * This interface defines the method that an achievement should implement.
 * An achievement is a hidden goal or objective that can be accomplished by the player.
 */
public interface Achievement {

    /**
     * Checks if the achievement has been accomplished.
     * This method should be implemented to return true if the achievement has been accomplished, and false otherwise.
     *
     * @return boolean indicating whether the achievement has been accomplished.
     */
    boolean hasAchieved();
}
