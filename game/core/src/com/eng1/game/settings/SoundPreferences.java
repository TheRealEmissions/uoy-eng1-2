package com.eng1.game.settings;

import org.jetbrains.annotations.Contract;

public class SoundPreferences implements Preference {
    private static final String ENABLED = "enabled";
    private static final boolean DEFAULT_ENABLED = true;
    private static final String VOLUME = "volume";
    private static final float DEFAULT_VOLUME = 0.5f;

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
