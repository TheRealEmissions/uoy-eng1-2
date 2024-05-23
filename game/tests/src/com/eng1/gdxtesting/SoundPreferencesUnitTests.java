package com.eng1.gdxtesting;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getFieldString;
import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getStaticFieldString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.eng1.game.settings.SoundPreferences;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.eng1.game.settings.Preferences;

import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class SoundPreferencesUnitTests {
    SoundPreferences soundPreferences = Preferences.SOUND;
    // Pulling the private variables needed for the tests using reflection.
    // The SoundPreference ones of these could be pulled via either
    // getFieldString() or getStaticFieldString().
    String preferencesNameFieldStr = getStaticFieldString(Preferences.class, "NAME");
    String soundPreferencesEnabledFieldStr = getFieldString(soundPreferences, "ENABLED");
    String getSoundPreferencesVolumeFieldStr = getFieldString(soundPreferences, "VOLUME");

    /* Tests SoundPreferences.getKey() method.
     *
     * All other SoundPreferences tests rely on this one passing as they use
     * the getKey() method in their tests.
     */
    @Test
    public void testGetKey() {
        assertEquals(
                "Testing whether SoundPreferences.getKey() works as expected.",
                "sound.testval",
                soundPreferences.getKey("testval")
        );
    }

    @Test
    public void testIsSoundEffectsEnabled() {
        assertEquals(
                "Testing whether SoundPreferences.isSoundEffectsEnabled() works as expected.",
                Gdx.app.getPreferences(preferencesNameFieldStr)
                    .getBoolean(soundPreferences.getKey(soundPreferencesEnabledFieldStr)),
                soundPreferences.isEnabled()
        );
    }

    @Test
    public void testSetSoundEffectsEnabled() {
        boolean initialValue = Gdx.app.getPreferences(preferencesNameFieldStr)
                                    .getBoolean(soundPreferences.getKey(soundPreferencesEnabledFieldStr));

        boolean workingAsExpected = true;

        // Test it going from false to true and true to false
        for(byte i = 0; i < 2; i++) {
            soundPreferences.setEnabled(!initialValue);

            boolean newValue = Gdx.app.getPreferences(preferencesNameFieldStr)
                                    .getBoolean(soundPreferences.getKey(soundPreferencesEnabledFieldStr));
            if(initialValue == newValue) {
                workingAsExpected = false;
                break;
            }
            initialValue = newValue;
        }

        String message = "Checks whether SoundPreferences.setSoundEffectsEnabled() acts as expected."
                + "\nTest failed when changing from " + initialValue + " to " + !initialValue;
        assertTrue(message, workingAsExpected);
    }

    @Test
    public void testGetSoundVolume() {
        assertEquals(
                "Checks whether SoundPreferences.getSoundVolume()"
                        + "returns the correct volume from Gdx.app as expected.",
                Gdx.app.getPreferences(preferencesNameFieldStr)
                        .getFloat(soundPreferences.getKey(getSoundPreferencesVolumeFieldStr)),
                soundPreferences.getVolume(),
                0.0f // Delta = 0 as no floating point error is expected
        );
    }

    @Test
    public void testSetSoundVolume() {
        float initialVolume = Gdx.app.getPreferences(preferencesNameFieldStr)
                .getFloat(soundPreferences.getKey(getSoundPreferencesVolumeFieldStr));

        if(initialVolume != 0.7f) {
            soundPreferences.setVolume(0.7f);
            assertEquals(
                    "Checks SoundPreferences.setSoundVolume works as expected"
                            + "As the initial volume wasn't 0.7, this test set the volume"
                            + " to 0.7 to check that it changes as expected.",
                    0.7f,
                    Gdx.app.getPreferences(preferencesNameFieldStr)
                            .getFloat(soundPreferences.getKey(getSoundPreferencesVolumeFieldStr)),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        } else {
            soundPreferences.setVolume(0.4f);
            assertEquals(
                    "Checks SoundPreferences.setSoundVolume works as expected"
                            + "As the initial volume was 0.7, this test set the volume"
                            + " to 0.4 to check that it changes as expected.",
                    0.4f,
                    Gdx.app.getPreferences(preferencesNameFieldStr)
                            .getFloat(soundPreferences.getKey(getSoundPreferencesVolumeFieldStr)),
                    0.0f // Delta = 0 as no floating point error is expected
            );
        }
    }

}
