package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Represents the loading screen of the game.
 * This screen is displayed while the game is loading resources or preparing for the main menu.
 * ((redundant for now because loading takes a negligible amount of time))
 *
 */
public class LoadingScreen implements Screen {
    private final Stage stage;

    /**
     * Constructor for the LoadingScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public LoadingScreen(){
        stage = new Stage(new ScreenViewport());

    }
    @Override
    public void show() {
        Screens.MENU.setAsCurrent();
    }

    /**
     * When the loading screen has finished loading, set the screen to the menu screen.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Not currently used
    }

    @Override
    public void resume() {
        // Not currently used
    }

    @Override
    public void hide() {
        // Not currently used
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}