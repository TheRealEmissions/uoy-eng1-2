package com.eng1.game.audio.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.eng1.game.settings.Preferences;
import org.jetbrains.annotations.Range;

public abstract class GameSounds implements Sound{
    protected final Sound sound;

    protected GameSounds(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public long play() {
        return this.sound.play(Preferences.SOUND.getVolume());
    }

    public long play(@Range(from = 0L, to = 1L) float volume) {
        return this.sound.play(volume);
    }

    public long play(@Range(from = 0L, to = 1L) float volume, float pitch, @Range(from = -1L, to = 1L) float pan) {
        return this.sound.play(volume, pitch, pan);
    }

    public long loop() {
        return this.sound.loop(Preferences.SOUND.getVolume());
    }

    public long loop(@Range(from = 0L, to = 1L) float volume) {
        return this.sound.loop(volume);
    }

    public long loop(@Range(from = 0L, to = 1L) float volume, float pitch, @Range(from = -1L, to = 1L) float pan) {
        return this.sound.loop(volume, pitch, pan);
    }

    public void stop() {
        this.sound.stop();
    }

    public void dispose() {
        this.sound.dispose();
    }

    public void dispose(Sound sound) {
        this.sound.dispose();
    }

    public void pause() {
        this.sound.pause();
    }

    public void resume() {
        this.sound.resume();
    }

    public void setPan(long soundId, @Range(from = -1L, to = 1L) float pan, float volume) {
        this.sound.setPan(soundId, pan, volume);
    }

    public void setPitch(long soundId, float pitch) {
        this.sound.setPitch(soundId, pitch);
    }

    public void setVolume(long soundId, @Range(from = 0L, to = 1L) float volume) {
        this.sound.setVolume(soundId, volume);
    }

    public void setLooping(long soundId, boolean looping) {
        this.sound.setLooping(soundId, looping);
    }


}
