package com.eng1.game.settings;

import com.badlogic.gdx.Gdx;

/**
 * Manages the preferences of the game, such as volume settings and enabling/disabling sound effects and music.
 * Currently redundant as volume / sound etc isn't currently a feature
 *
 * @since v2
 * -- renamed from AppPreferences to Preferences as it is more concise
 * -- preference handling has been moved to its own classes, such as {@link MusicPreferences} which can be accessed with {@link Preferences#MUSIC}
 */
public final class Preferences {
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
     * @since v2 <p>
     *     -- renamed to getPreferences from getPrefs for clarity <p>
     *     -- changed to package-private access modifier <p>
     *     -- changed to static method
     */
    static com.badlogic.gdx.Preferences getPreferences() {
        return Gdx.app.getPreferences(NAME);
    }

    public boolean isSoundEffectsEnabled() {
        return getPreferences().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPreferences().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
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