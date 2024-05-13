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
 * @since v2 -- now uses a singleton pattern to ensure only one instance of the game is running.
 */
public class HeslingtonHustle extends Game {
	@Getter
	private static HeslingtonHustle instance;
    private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
    /**
     * -- GETTER --
     *  Retrieves the preferences instance.
     *
     */
    @Getter
    private AppPreferences preferences;
	private CharacterScreen characterScreen;

	public HeslingtonHustle() {
		super();
		instance = this;
	}

	@Override
	public void create() {
        LoadingScreen loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		preferences = new AppPreferences();
		Activity.setGameInstance(this); // Set the game instance in Activity
	}

    /**
	 * Changes the current screen based on the specified screen constant.
	 * @param screen The screen constant indicating the screen to switch to.
	 *
	 */
	public void changeScreen(@NotNull Screens screen) {
		switch (screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				setScreen(menuScreen);
				break;
			case PREFERENCES:
				if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
				setScreen(preferencesScreen);
				break;
			case APPLICATION:
				if (mainScreen == null) mainScreen = new MainScreen(this);
				setScreen(mainScreen);
				break;
			case ENDGAME:
				if (endScreen == null) endScreen = new EndScreen();
				setScreen(endScreen);
				break;
			case CHARACTER:
				if (characterScreen == null) characterScreen = new CharacterScreen(this);
				setScreen(characterScreen);
				break;
		}
	}

	@Override
	public void render() {
		super.render();
		// Handle input events
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			if (getScreen() == preferencesScreen) {
				// If currently on preferences screen, switch to the game screen
				changeScreen(Screens.APPLICATION);
			} else {
				// Otherwise, switch to preferences screen
				changeScreen(Screens.PREFERENCES);
			}
		}
	}
}
