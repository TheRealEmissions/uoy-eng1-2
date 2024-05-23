package com.eng1.game.audio.music;

import com.eng1.game.audio.AudioManager;
import com.eng1.game.audio.music.elements.BackgroundMusic;
import com.eng1.game.settings.Preferences;
import com.eng1.game.settings.SoundPreferences;
import lombok.Getter;

/**
 * This class is responsible for managing the music in the game, including enabling, disabling, and setting the volume of the music.
 */
public class MusicManager implements AudioManager {

    /**
     * A constant instance of {@link BackgroundMusic}.
     * This instance is used to manage the background music throughout the game.
     */
    public static final BackgroundMusic BACKGROUND_MUSIC = new BackgroundMusic();
    /**
     * A singleton instance of {@link MusicManager}.
     * This instance is used to manage the music throughout the game.
     * The {@link Getter} annotation from Lombok library generates a public getter for this instance.
     */
    @Getter
    private static final MusicManager instance = new MusicManager();

    /**
     * Private constructor for the {@link MusicManager} class.
     * This constructor is private to enforce the singleton nature of this class.
     */
    private MusicManager() {
    }

    /**
     * Enables the music based on the user's preferences.
     * This method retrieves the user's sound preferences and, if enabled, starts the background music.
     * The music is set to loop and the volume is set according to the user's preferences.
     */
    public void onEnable() {
        SoundPreferences musicPreferences = Preferences.SOUND;
        if (musicPreferences.isEnabled()) {
            BACKGROUND_MUSIC.setLooping(true);
            BACKGROUND_MUSIC.play();
            BACKGROUND_MUSIC.setVolume(musicPreferences.getVolume());
        }
    }

    /**
     * Disables the music.
     * This method stops the background music and releases any system resources associated with it.
     */
    public void onDisable() {
        BACKGROUND_MUSIC.stop();
        BACKGROUND_MUSIC.dispose();
    }

    /**
     * Sets the volume of the background music.
     * This method takes a float value as input and sets the volume of the background music accordingly.
     * The volume value should be between 0.0 (mute) and 1.0 (maximum volume).
     *
     * @param volume The volume level for the background music.
     */
    public void setVolume(float volume) {
        BACKGROUND_MUSIC.setVolume(volume);
    }
}