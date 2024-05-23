package com.eng1.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.audio.AudioManager;
import com.eng1.game.audio.music.MusicManager;
import com.eng1.game.audio.sounds.SoundsManager;
import com.eng1.game.game.player.Statistics;
import com.eng1.game.screens.*;
import lombok.Getter;

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

	public HeslingtonHustle() {
		super();
		instance = this;
	}

	@Override
	public void create() {


		AudioManager musicManager = MusicManager.getInstance();
		musicManager.onEnable();
		AudioManager soundManager = SoundsManager.getInstance();
		soundManager.onEnable();
		Screens.LOADING.setAsCurrent();
	}

	@Override
	public void render() {
		super.render();
		// Handle input events
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Screens main = Screens.MAIN;
			if (!main.isLoaded()) return;
			Screens preferencesScreen = Screens.PREFERENCES;
			if (preferencesScreen.isCurrent()) {
				// If currently on preferences screen, switch to the game screen
				main.setAsCurrent();
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
		Statistics.dispose();
		AudioManager musicManager = MusicManager.getInstance();
		musicManager.onDisable();
		AudioManager soundManager = SoundsManager.getInstance();
		soundManager.onDisable();
	}
}
