package com.eng1.game.settings;

import org.jetbrains.annotations.Contract;

/**
 * This class is responsible for managing the sound preferences in the game.
 * It provides methods to enable/disable sound and adjust the volume.
 */
public class SoundPreferences implements Preference {
    // The key for the preference that determines whether sound is enabled.
    private static final String ENABLED = "enabled";

    // The default value for the preference that determines whether sound is enabled.
    private static final boolean DEFAULT_ENABLED = true;

    // The key for the preference that controls the volume of the sound.
    private static final String VOLUME = "volume";

    // The default value for the preference that controls the volume of the sound.
    private static final float DEFAULT_VOLUME = 0.5f;

    /**
     * Constructor for the SoundPreferences class.
     * This constructor initializes the sound preferences with default values.
     * It retrieves the game preferences, sets the default values for sound enabled status and volume,
     * and then flushes the preferences to ensure they are saved.
     */
    public SoundPreferences() {
        com.badlogic.gdx.Preferences preferences = Preferences.getPreferences();
        preferences.putBoolean(getKey(ENABLED), DEFAULT_ENABLED);
        preferences.putFloat(getKey(VOLUME), DEFAULT_VOLUME);
        preferences.flush();
    }

    public boolean isEnabled() {
        return Preferences.getPreferences().getBoolean(getKey(ENABLED));
    }

    public void setEnabled(boolean b) {
        com.badlogic.gdx.Preferences preferences = Preferences.getPreferences();
        preferences.putBoolean(getKey(ENABLED), b);
        preferences.flush();
    }

    public float getVolume() {
        return Preferences.getPreferences().getFloat(getKey(VOLUME));
    }

    public void setVolume(float volume) {
        com.badlogic.gdx.Preferences preferences = Preferences.getPreferences();
        preferences.putFloat(getKey(VOLUME), volume);
        preferences.flush();
    }

    @Contract(pure = true)
    @Override
    public String getKey(String key) {
        return "sound." + key;
    }
}
