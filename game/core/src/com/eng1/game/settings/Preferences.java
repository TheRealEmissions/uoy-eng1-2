package com.eng1.game.settings;

import com.badlogic.gdx.Gdx;

/**
 * Manages the preferences of the game, such as volume settings and enabling/disabling sound effects and music.
 * Currently redundant as volume / sound etc isn't currently a feature
 *
 * @since v2 <p>
 * -- renamed from AppPreferences to Preferences as it is more concise <p>
 * -- preference handling has been moved to its own classes, such as {@link MusicPreferences} which can be accessed with {@link Preferences#MUSIC}
 */
public final class Preferences {
    /**
     * Represents the name of the preferences file where the game settings are stored.
     * @since v2 <p>
     *     -- renamed to NAME for consistency <p>
     *     -- changed to private access modifier
     */
    private static final String NAME = "HeslingtonHustle";
    public static final MusicPreferences MUSIC = new MusicPreferences();
    public static final SoundPreferences SOUND = new SoundPreferences();

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
}