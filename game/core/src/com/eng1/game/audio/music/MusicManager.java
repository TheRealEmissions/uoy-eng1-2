package com.eng1.game.audio.music;

import com.eng1.game.audio.AudioManager;
import com.eng1.game.audio.music.elements.BackgroundMusic;
import com.eng1.game.settings.Preferences;
import com.eng1.game.settings.SoundPreferences;
import lombok.Getter;

public class MusicManager implements AudioManager {

    public static final BackgroundMusic BACKGROUND_MUSIC = new BackgroundMusic();
    @Getter
    private static final MusicManager instance = new MusicManager();

    private MusicManager() {
    }

    public void onEnable() {
        SoundPreferences musicPreferences = Preferences.SOUND;
        if (musicPreferences.isEnabled()) {
            BACKGROUND_MUSIC.setLooping(true);
            BACKGROUND_MUSIC.play();
            BACKGROUND_MUSIC.setVolume(musicPreferences.getVolume());
        }

    }

    public void onDisable() {
        BACKGROUND_MUSIC.stop();
        BACKGROUND_MUSIC.dispose();
    }

    public void setVolume(float volume) {
        BACKGROUND_MUSIC.setVolume(volume);
    }
}