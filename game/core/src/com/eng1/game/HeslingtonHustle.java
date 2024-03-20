package com.eng1.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import eng1.model.views.*;

/**
 * The main game class responsible for managing screens.
 */
public class HeslingtonHustle extends Game {
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private AppPreferences preferences;
	private CharacterScreen characterScreen;

	// Screen constants
	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	public final static int CHARACTER = 4;
	
	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
		preferences = new AppPreferences();
		Activity.setGameInstance(this); // Set the game instance in Activity
	}

	/**
	 * Retrieves the preferences instance.
	 * @return The preferences instance.
	 */
	public AppPreferences getPreferences() {
		return this.preferences;
	}

	/**
	 * Changes the current screen based on the specified screen constant.
	 * @param screen The screen constant indicating the screen to switch to.
	 *
	 */
	public void changeScreen(int screen) {
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
				if (endScreen == null) endScreen = new EndScreen(this);
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
				changeScreen(APPLICATION);
			} else {
				// Otherwise, switch to preferences screen
				changeScreen(PREFERENCES);
			}
		}
	}
}
