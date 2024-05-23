package com.eng1.game.settings;

/**
 * The Preference interface provides a contract for preference classes.
 * It declares a method getKey which should return the key for a given preference.
 */
public interface Preference {
    /**
     * Returns the key for a given preference.
     * Implementations of this method should prepend a prefix to the key to avoid key collisions.
     *
     * @param key The key for the preference.
     * @return The key for the preference with the appropriate prefix.
     */
    String getKey(String key);
}
