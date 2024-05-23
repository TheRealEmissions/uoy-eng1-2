package com.eng1.game.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * This class is responsible for managing the music preferences in the game.
 * It provides methods to enable/disable music and adjust the volume.
 */
public final class MusicPreferences implements Preference {
    // The key for the preference that determines whether music is enabled.
    private static final String ENABLED = "enabled";

    // The default value for the preference that determines whether music is enabled.
    private static final boolean DEFAULT_ENABLED = true;

    // The key for the preference that controls the volume of the music.
    private static final String VOLUME = "volume";

    // The default value for the preference that controls the volume of the music.
    private static final float DEFAULT_VOLUME = 0.5f;

    /**
     * Constructor for the MusicPreferences class.
     * This constructor initializes the music preferences with default values.
     * It retrieves the game preferences, sets the default values for music enabled status and volume,
     * and then flushes the preferences to ensure they are saved.
     */
    MusicPreferences() {
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
    public @NotNull String getKey(String key) {
        return "music." + key;
    }
}
