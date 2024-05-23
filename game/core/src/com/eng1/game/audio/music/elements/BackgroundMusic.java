package com.eng1.game.audio.music.elements;

import com.eng1.game.audio.music.GameMusic;
import com.badlogic.gdx.audio.Music;

/**
 * It implements the background music for the game.
 *
 * The class includes constructors for creating a BackgroundMusic object with a default music file path {@link #BackgroundMusic()},
 * or with a specified music file path {@link #BackgroundMusic(String)}
 */
public class BackgroundMusic extends GameMusic implements Music {
    /**
     * Default constructor for the {@link BackgroundMusic} class.
     * This constructor creates a {@link BackgroundMusic} instance with a default music file path.
     * The default music file path is "audio/music/gameMusic.wav".
     */
    public BackgroundMusic() {
        super("audio/music/gameMusic.wav");
    }

    /**
     * Constructor for the {@link BackgroundMusic} class with a specified music file path.
     * This constructor creates a {@link BackgroundMusic} instance with the provided music file path.
     *
     * @param path The path to the music file to be used for this {@link BackgroundMusic} object.
     */
    public BackgroundMusic(String path) {
        super(path);
    }
}
