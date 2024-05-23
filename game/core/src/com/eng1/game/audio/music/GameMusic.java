package com.eng1.game.audio.music;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import lombok.Getter;
import org.jetbrains.annotations.Range;



/**
 * This class represents a generic type of music that can be played in the game.
 *
 * The class includes methods for playing, pausing, stopping, and other operations related to the music.
 * It also includes a constructor for creating a GameMusic object with a specified music file path.
 *
 * The class is annotated with {@link Getter} from the Lombok library, which automatically generates getter methods for all fields in the class.
 */
@Getter
public abstract class GameMusic implements Music {
    /**
     * Represents the actual music asset that this {@link GameMusic} object controls.
     * The music asset is loaded from the provided file path when a GameMusic object is created via {@link #GameMusic(String)}
     */
    protected final Music music;

    /**
     * Constructor for the {@link GameMusic} class with a specified music file path.
     * This constructor creates a {@link GameMusic} instance and loads the music asset from the provided file path.
     *
     * @param path The path to the music file to be used for this {@link GameMusic} object.
     */
    protected GameMusic(String path) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    /**
     * Plays the music.
     * This method starts playing the music from the current position.
     */
    public void play() {
        this.music.play();
    }

    /**
     * Pauses the music.
     * This method pauses the music at the current position.
     */
    public void pause() {
        this.music.pause();
    }

    /**
     * Stops the music.
     * This method stops the music and resets the position to the start.
     */
    public void stop() {
        this.music.stop();
    }

    /**
     * Checks if the music is playing.
     * This method returns true if the music is currently playing, and false otherwise.
     *
     * @return A boolean indicating whether the music is currently playing.
     */
    public boolean isPlaying() {
        return this.music.isPlaying();
    }

    /**
     * Sets the looping status of the music.
     * This method enables or disables looping for the music.
     *
     * @param isLooping A boolean indicating whether the music should loop.
     */
    public void setLooping(boolean isLooping) {
        this.music.setLooping(isLooping);
    }

    /**
     * Checks if the music is looping.
     * This method returns true if the music is set to loop, and false otherwise.
     *
     * @return A boolean indicating whether the music is set to loop.
     */
    public boolean isLooping() {
        return this.music.isLooping();
    }

    /**
     * Sets the volume of the music.
     * This method sets the volume of the music to the specified value.
     * The volume should be a float between 0.0 (mute) and 1.0 (maximum volume).
     *
     * @param volume The volume to set for the music.
     */
    public void setVolume(@Range(from = 0L, to = 1L) float volume) {
        this.music.setVolume(volume);
    }

    /**
     * Gets the current volume of the music.
     * This method returns the current volume of the music as a float between 0.0 (mute) and 1.0 (maximum volume).
     *
     * @return The current volume of the music.
     */
    public @Range(from = 0L, to = 1L) float getVolume() {
        return this.music.getVolume();
    }

    /**
     * Sets the pan and volume of the music.
     * This method sets the pan and volume of the music to the specified values.
     * The pan should be a float between -1.0 (full left) and 1.0 (full right).
     * The volume should be a float between 0.0 (mute) and 1.0 (maximum volume).
     *
     * @param pan The pan to set for the music.
     * @param volume The volume to set for the music.
     */
    public void setPan(@Range(from = -1L, to = 1L) float pan, @Range(from = 0L, to = 1L) float volume) {
        this.music.setPan(pan, volume);
    }

    /**
     * Disposes of the music asset.
     * This method releases all resources of this music asset. It should be called when the music asset is no longer needed.
     */
    public void dispose() {
        this.music.dispose();
    }

    /**
     * Sets the current position of the music.
     * This method sets the current position of the music to the specified value.
     * The position should be a float representing the number of seconds from the start.
     *
     * @param position The position to set for the music.
     */
    public void setPosition(float position) {
        this.music.setPosition(position);
    }

    /**
     * Gets the current position of the music.
     * This method returns the current position of the music as a float representing the number of seconds from the start.
     *
     * @return The current position of the music.
     */
    public float getPosition() {
        return this.music.getPosition();
    }

    /**
     * Sets the completion listener for the music.
     * This method sets the completion listener for the music to the specified listener.
     * The listener will be notified when the music has finished playing.
     *
     * @param listener The completion listener to set for the music.
     */
    public void setOnCompletionListener(Music.OnCompletionListener listener) {
        this.music.setOnCompletionListener(listener);
    }
}
