package com.eng1.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.eng1.game.screens.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * The main game class responsible for managing screens.
 *
 * @since v2 <p>
 * -- now uses a singleton pattern to ensure only one instance of the game is running. <p>
 * -- now uses the {@link Screens} enum to switch screens and this class no longer stores all the screens
 */
public class HeslingtonHustle extends Game {
	@Getter
	private static HeslingtonHustle instance;
    /**
     * -- GETTER --
     *  Retrieves the preferences instance.
     *
     */
    @Getter
    private AppPreferences preferences;

	public HeslingtonHustle() {
		super();
		instance = this;
	}

	@Override
	public void create() {
        LoadingScreen loadingScreen = new LoadingScreen();
		setScreen(loadingScreen);
		preferences = new AppPreferences();
	}

    /**
	 * Changes the current screen based on the specified screen constant.
	 * @param screen The screen constant indicating the screen to switch to.
     *
     * @since v2 -- now uses the {@link Screens} enum to switch screens.
	 *
	 */
	public void changeScreen(@NotNull Screens screen) {
		screen.change();
	}

	/**
	 * @since v2 -- fixed wrong equality checking (== vs .equals)
	 */
	@Override
	public void render() {
		super.render();
		// Handle input events
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			if (getScreen().equals(Screens.PREFERENCES.get())) {
				// If currently on preferences screen, switch to the game screen
				changeScreen(Screens.APPLICATION);
			} else {
				// Otherwise, switch to preferences screen
				changeScreen(Screens.PREFERENCES);
			}
		}
	}
}
