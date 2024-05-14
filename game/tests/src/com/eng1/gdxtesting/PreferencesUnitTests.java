package com.eng1.gdxtesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.eng1.game.settings.Preferences;

import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class PreferencesUnitTests {
    Preferences preferences = new Preferences();

    @Test
    public void testIsSoundEffectsEnabled() {
        assertEquals(
                "Testing whether AppPreferences.isSoundEffectsEnabled() works as expected.",
            Gdx.app.getPreferences(Preferences.NAME)
                    .getBoolean(Preferences.PREF_SOUND_ENABLED),
            preferences.isSoundEffectsEnabled()
        );
    }

    @Test
    public void testSetSoundEffectsEnabled() {
        boolean initialValue = Gdx.app.getPreferences(Preferences.NAME)
                                    .getBoolean(Preferences.PREF_SOUND_ENABLED);

        boolean workingAsExpected = true;

        // Test it going from false to true and true to false
        for(byte i = 0; i < 2; i++) {
            preferences.setSoundEffectsEnabled(!initialValue);

            boolean newValue = Gdx.app.getPreferences(Preferences.NAME)
                                    .getBoolean(Preferences.PREF_SOUND_ENABLED);
            if(!(initialValue != newValue)) {
                workingAsExpected = false;
                break;
            }
            initialValue = newValue;
        }

        String message = "Checks whether AppPreferences.setSoundEffectsEnabled() acts as expected."
                + "\nTest failed when changing from " + initialValue + " to " + !initialValue;
        assertTrue(message, workingAsExpected);
    }

    @Test
    public void testIsMusicEnabled() {
        assertEquals(
                "Testing whether AppPreferences.isMusicEnabled() works as expected.",
                Gdx.app.getPreferences(Preferences.NAME)
                        .getBoolean(Preferences.PREF_MUSIC_ENABLED),
                preferences.isMusicEnabled()
        );
    }

    @Test
    public void testSetMusicEnabled() {
        boolean initialValue = Gdx.app.getPreferences(Preferences.NAME)
                                    .getBoolean(Preferences.PREF_MUSIC_ENABLED);

        boolean workingAsExpected = true;

        // Test it going from false to true and true to false
        for(byte i = 0; i < 2; i++) {
            preferences.setMusicEnabled(!initialValue);

            boolean newValue = Gdx.app.getPreferences(Preferences.NAME)
                                    .getBoolean(Preferences.PREF_MUSIC_ENABLED);
            if(!(initialValue != newValue)) {
                workingAsExpected = false;
                break;
            }
            initialValue = newValue;
        }

        String message = "Checks whether AppPreferences.setMusicEnabled() acts as expected."
                + "\nTest failed when changing from " + initialValue + " to " + !initialValue;
        assertTrue(message, workingAsExpected);
    }

    @Test
    public void testGetMusicVolume() {
        assertEquals(
                "Checks whether AppPreferences.getSoundVolume()"
                + "returns the correct volume from Gdx.app as expected.",
                Gdx.app.getPreferences(Preferences.NAME)
                        .getFloat(Preferences.PREF_MUSIC_VOLUME),
                preferences.getMusicVolume(),
                0.0f // Delta = 0 as no floating point error is expected
        );
    }

    @Test
    public void testSetMusicVolume() {
        float initialVolume = Gdx.app.getPreferences(Preferences.NAME)
                                    .getFloat(Preferences.PREF_MUSIC_VOLUME);

        if(initialVolume != 0.7f) {
            preferences.setMusicVolume(0.7f);
            assertEquals(
                    "Checks AppPreferences.setSoundVolume works as expected"
                    + "As the initial volume wasn't 0.7, this test set the volume"
                    + " to 0.7 to check that it changes as expected.",
                    0.7f,
                    Gdx.app.getPreferences(Preferences.NAME)
                            .getFloat(Preferences.PREF_MUSIC_VOLUME),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        } else {
            preferences.setMusicVolume(0.4f);
            assertEquals(
                    "Checks AppPreferences.setSoundVolume works as expected"
                            + "As the initial volume was 0.7, this test set the volume"
                            + " to 0.4 to check that it changes as expected.",
                    0.4f,
                    Gdx.app.getPreferences(Preferences.NAME)
                            .getFloat(Preferences.PREF_MUSIC_VOLUME),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        }
    }

    @Test
    public void testGetSoundVolume() {
        assertEquals(
                "Checks whether AppPreferences.getSoundVolume()"
                        + "returns the correct volume from Gdx.app as expected.",
                Gdx.app.getPreferences(Preferences.NAME)
                        .getFloat(Preferences.PREF_SOUND_VOL),
                preferences.getSoundVolume(),
                0.0f // Delta = 0 as no floating point error is expected
        );
    }

    @Test
    public void testSetSoundVolume() {
        float initialVolume = Gdx.app.getPreferences(Preferences.NAME)
                .getFloat(Preferences.PREF_SOUND_VOL);

        if(initialVolume != 0.7f) {
            preferences.setSoundVolume(0.7f);
            assertEquals(
                    "Checks AppPreferences.setSoundVolume works as expected"
                            + "As the initial volume wasn't 0.7, this test set the volume"
                            + " to 0.7 to check that it changes as expected.",
                    0.7f,
                    Gdx.app.getPreferences(Preferences.NAME)
                            .getFloat(Preferences.PREF_SOUND_VOL),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        } else {
            preferences.setSoundVolume(0.4f);
            assertEquals(
                    "Checks AppPreferences.setSoundVolume works as expected"
                            + "As the initial volume was 0.7, this test set the volume"
                            + " to 0.4 to check that it changes as expected.",
                    0.4f,
                    Gdx.app.getPreferences(Preferences.NAME)
                            .getFloat(Preferences.PREF_SOUND_VOL),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        }
    }

}
