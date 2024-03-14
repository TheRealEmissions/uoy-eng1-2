package com.eng1.game;

import com.badlogic.gdx.Game;
<<<<<<< HEAD
import eng1.model.views.*;

public class ENG1 extends Game {
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private AppPreferences preferences;

	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;





	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
				this.setScreen(menuScreen);
				break;
			case PREFERENCES:
				if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this); // added (this)
				this.setScreen(preferencesScreen);
				break;
			case APPLICATION:
				if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
				this.setScreen(mainScreen);
				break;
			case ENDGAME:
				if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
				this.setScreen(endScreen);
				break;
		}
	}

	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);

		preferences = new AppPreferences();

	}

	public AppPreferences getPreferences(){
		return this.preferences;
	}

=======

public class ENG1 extends Game {
	@Override
	public void create () {
		setScreen(new Play());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void pause() {
		super.pause();
	}

	public void resume() {
		super.resume();
	}

>>>>>>> c78bd13548ef38d8da02d0c29dfe0f9ccb7042ae

}
