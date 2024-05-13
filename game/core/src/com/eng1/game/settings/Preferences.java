package com.eng1.game.settings;

import com.badlogic.gdx.Gdx;

/**
 * Manages the preferences of the game, such as volume settings and enabling/disabling sound effects and music.
 * Currently redundant as volume / sound etc isn't currently a feature
 *
 * @since v2
 * -- renamed from AppPreferences to Preferences as it is more concise
 */
public class Preferences {
    public static final String PREF_MUSIC_VOLUME = "volume";
    public static final String PREF_MUSIC_ENABLED = "music.enabled";
    public static final String PREF_SOUND_ENABLED = "sound.enabled";
    public static final String PREF_SOUND_VOL = "sound";
    /**
     * Represents the name of the preferences file where the game settings are stored.
     * @since v2 -- renamed to NAME for consistency
     */
    public static final String NAME = "HeslingtonHustle";

    protected com.badlogic.gdx.Preferences getPrefs() {
        return Gdx.app.getPreferences(NAME);
    }

    public boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    public float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        getPrefs().flush();
    }

    public float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public void setSoundVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOL, volume);
        getPrefs().flush();
    }
}