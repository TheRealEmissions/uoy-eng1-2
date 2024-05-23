package com.eng1.game.audio.music;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import org.jetbrains.annotations.Range;



public abstract  class GameMusic implements Music {
    protected final Music music;

    protected GameMusic(String path) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public void play() {
        this.music.play();
    }

    public void pause() {
        this.music.pause();
    }

    public void stop() {
        this.music.stop();
    }

    public boolean isPlaying() {
        return this.music.isPlaying();
    }

    public void setLooping(boolean isLooping) {
        this.music.setLooping(isLooping);
    }

    public boolean isLooping() {
        return this.music.isLooping();
    }

    public void setVolume(@Range(from = 0L, to = 1L) float volume) {
        this.music.setVolume(volume);
    }

    public @Range(from = 0L, to = 1L) float getVolume() {
        return this.music.getVolume();
    }

    public void setPan(@Range(from = -1L, to = 1L) float pan, @Range(from = 0L, to = 1L) float volume) {
        this.music.setPan(pan, volume);
    }

    public void dispose() {
        this.music.dispose();
    }

    public void setPosition(float position) {
        this.music.setPosition(position);
    }

    public float getPosition() {
        return this.music.getPosition();
    }

    public void setOnCompletionListener(Music.OnCompletionListener listener) {
        this.music.setOnCompletionListener(listener);
    }

    public Music getMusic() {
        return this.music;
    }

}
