package com.eng1.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.eng1.game.settings.Preferences;
import com.eng1.game.settings.MusicPreferences;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getFieldString;
import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getStaticFieldString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class MusicPreferencesUnitTests {
    MusicPreferences musicPreferences = Preferences.MUSIC;
    String preferencesNameFieldStr = getStaticFieldString(Preferences.class, "NAME");
    String musicPreferencesEnabledFieldStr = getFieldString(musicPreferences, "ENABLED");
    String musicPreferencesVolumeFieldStr = getFieldString(musicPreferences, "VOLUME");

    @Test
    public void testGetKey() {
        String expected = "music." + musicPreferencesEnabledFieldStr;

        assertEquals(
                "Testing whether MusicPreferences.getKey() works as expected",
                expected,
                musicPreferences.getKey(musicPreferencesEnabledFieldStr)
        );
    }

    @Test
    public void testIsEnabled() {
        assertEquals(
                "Testing whether MusicPreferences.isEnabled() works as expected.",
                Gdx.app.getPreferences(preferencesNameFieldStr)
                        .getBoolean(
                                musicPreferences
                                        .getKey(musicPreferencesEnabledFieldStr)
                        ),
                musicPreferences.isEnabled()
        );
    }

    @Test
    public void testSetEnabled() {
        boolean initialValue = Gdx.app.getPreferences(preferencesNameFieldStr)
                .getBoolean(
                        musicPreferences
                                .getKey(musicPreferencesEnabledFieldStr)
                );

        boolean workingAsExpected = true;

        // Test it going from false to true and true to false
        for(byte i = 0; i < 2; i++) {
            musicPreferences.setEnabled(!initialValue);

            boolean newValue = Gdx.app.getPreferences(preferencesNameFieldStr)
                    .getBoolean(
                            musicPreferences
                                    .getKey(musicPreferencesEnabledFieldStr)
                    );
            if(!(initialValue != newValue)) {
                workingAsExpected = false;
                break;
            }
            initialValue = newValue;
        }

        String message = "Checks whether MusicPreferences.setEnabled() acts as expected."
                + "\nTest failed when changing from " + initialValue + " to " + !initialValue;
        assertTrue(message, workingAsExpected);
    }

    @Test
    public void testGetVolume() {
        assertEquals(
                "Checks whether MusicPreferences.getVolume()"
                        + "returns the correct volume from Gdx.app as expected.",
                Gdx.app.getPreferences(preferencesNameFieldStr)
                        .getFloat(
                                musicPreferences
                                        .getKey(musicPreferencesVolumeFieldStr)
                        ),
                musicPreferences.getVolume(),
                0.0f // Delta = 0 as no floating point error is expected
        );
    }

    @Test
    public void testSetVolume() {
        String baseMessage = "Checks MusicPreference.setVolume() works as expected";

        float initialVolume = Gdx.app.getPreferences(preferencesNameFieldStr)
                .getFloat(
                        musicPreferences
                                .getKey(musicPreferencesVolumeFieldStr)
                );

        if(initialVolume != 0.7f) {
            musicPreferences.setVolume(0.7f);
            assertEquals(
                    baseMessage
                            + "As the initial volume wasn't 0.7, this test set the volume"
                            + " to 0.7 to check that it changes as expected.",
                    0.7f,
                    Gdx.app.getPreferences(preferencesNameFieldStr)
                            .getFloat(
                                    musicPreferences
                                            .getKey(musicPreferencesVolumeFieldStr)
                            ),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        } else {
            musicPreferences.setVolume(0.4f);
            assertEquals(
                    baseMessage
                            + "As the initial volume was 0.7, this test set the volume"
                            + " to 0.4 to check that it changes as expected.",
                    0.4f,
                    Gdx.app.getPreferences(preferencesNameFieldStr)
                            .getFloat(
                                    musicPreferences
                                            .getKey(musicPreferencesVolumeFieldStr)
                            ),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        }
    }
}
