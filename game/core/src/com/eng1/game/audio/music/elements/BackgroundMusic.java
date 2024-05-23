package com.eng1.game.audio.music.elements;

import com.eng1.game.audio.music.GameMusic;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic extends GameMusic implements Music {
    public BackgroundMusic() {
        super("audio/music/gameMusic.wav");
    }

    public BackgroundMusic(String path) {
        super(path);
    }
}
