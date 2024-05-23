package com.eng1.game.audio.sounds.elements;

import com.eng1.game.audio.sounds.GameSounds;
import com.eng1.game.settings.Preferences;
import com.eng1.game.settings.SoundPreferences;

public class InteractSound extends GameSounds{
    public InteractSound() {
        super("audio/sounds/interact.wav");
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
