package com.eng1.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.screens.*;
import com.eng1.game.settings.Preferences;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * The main game class responsible for managing screens.
 *
 * @since v2 <p>
 * -- now uses a singleton pattern to ensure only one instance of the game is running. <p>
 * -- now uses the {@link Screens} enum to switch screens and this class no longer stores all the screens <p>
 * -- removed changeScreen() as {@link Screens} enum now handles screen switching <p>
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
    private Preferences preferences;

	public HeslingtonHustle() {
		super();
		instance = this;
	}

	@Override
	public void create() {
		preferences = new Preferences();
		Screens.LOADING.setAsCurrent();
	}

	@Override
	public void render() {
		super.render();
		// Handle input events
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Screens preferencesScreen = Screens.PREFERENCES;
			if (preferencesScreen.isCurrent()) {
				// If currently on preferences screen, switch to the game screen
				Screens.MAIN.setAsCurrent();
			} else {
				// Otherwise, switch to preferences screen
				preferencesScreen.setAsCurrent();
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		ImageAssets.disposeAll();
		SkinAssets.disposeAll();
		MapAssets.disposeAll();
		Screens.disposeAll();
	}
}
