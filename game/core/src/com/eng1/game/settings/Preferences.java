package com.eng1.game.settings;

import com.badlogic.gdx.Gdx;

/**
 * Manages the preferences of the game, such as volume settings and enabling/disabling sound effects and music.
 * Currently redundant as volume / sound etc isn't currently a feature
 *
 * @since v2
 * -- renamed from AppPreferences to Preferences as it is more concise
 */
public final class Preferences {
    public static final String PREF_MUSIC_VOLUME = "volume";
    public static final String PREF_MUSIC_ENABLED = "music.enabled";
    public static final String PREF_SOUND_ENABLED = "sound.enabled";
    public static final String PREF_SOUND_VOL = "sound";
    /**
     * Represents the name of the preferences file where the game settings are stored.
     * @since v2 <p>
     *     -- renamed to NAME for consistency <p>
     *     -- changed to private access modifier
     */
    private static final String NAME = "HeslingtonHustle";
    public static final MusicPreferences MUSIC = new MusicPreferences();

    /**
     * Retrieves the preferences object from GDX.
     * @return The preferences object.
     * @since v2 -- renamed to getPreferences from getPrefs for clarity
     */
    private com.badlogic.gdx.Preferences getPreferences() {
        return Gdx.app.getPreferences(NAME);
    }

    public boolean isSoundEffectsEnabled() {
        return getPreferences().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPreferences().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPreferences().flush();
    }

    public boolean isMusicEnabled() {
        return getPreferences().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getPreferences().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPreferences().flush();
    }

    public float getMusicVolume() {
        return getPreferences().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public void setMusicVolume(float volume) {
        getPreferences().putFloat(PREF_MUSIC_VOLUME, volume);
        getPreferences().flush();
    }

    public float getSoundVolume() {
        return getPreferences().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public void setSoundVolume(float volume) {
        getPreferences().putFloat(PREF_SOUND_VOL, volume);
        getPreferences().flush();
    }
}