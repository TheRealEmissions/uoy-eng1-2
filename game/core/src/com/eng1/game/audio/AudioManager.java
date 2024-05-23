package com.eng1.game.audio;

/**
 * This interface defines the methods that an audio manager should implement.
 * An audio manager is responsible for managing the audio in the game.
 */
public interface AudioManager {

    /**
     * Enables the audio.
     * This method should be implemented to start the audio when called.
     */
    void onEnable();

    /**
     * Disables the audio.
     * This method should be implemented to stop the audio when called.
     */
    void onDisable();
}
