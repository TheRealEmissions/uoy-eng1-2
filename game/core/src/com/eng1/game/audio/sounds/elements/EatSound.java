package com.eng1.game.audio.sounds.elements;

import com.eng1.game.audio.sounds.GameSounds;
import com.eng1.game.settings.Preferences;
import com.eng1.game.settings.SoundPreferences;

public class EatSound extends GameSounds{
    public EatSound() {
        super("audio/sounds/eat.wav");
    }

    public long play(){
        SoundPreferences soundPreferences = Preferences.SOUND;
        return soundPreferences.isEnabled() ? super.play() : -1L;
    }

    public void stop(long soundId){
        this.sound.stop(soundId);
    }

    public void pause(long soundId){
        this.sound.pause(soundId);
    }

    public void resume(long soundId){
        this.sound.resume(soundId);
    }
}
