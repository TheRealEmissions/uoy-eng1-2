package com.eng1.game;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.eng1.game.HeslingtonHustle;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ENG1 Game");
		config.setResizable(true);
		config.setHdpiMode(HdpiMode.Logical);
		config.setWindowSizeLimits(800, 480, -1, -1);
		config.setWindowedMode(1920, 1080);
		//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		new Lwjgl3Application(new HeslingtonHustle(), config);
	}
}

