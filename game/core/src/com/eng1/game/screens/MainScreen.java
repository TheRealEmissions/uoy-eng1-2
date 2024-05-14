package com.eng1.game.screens;

import com.eng1.game.HeslingtonHustle;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 * This class represents the main screen of the game.
 * This screen serves as the playing point of the game and handles user interactions such as escape to go to a settings screen / quit
 */
public class MainScreen implements Screen {
    /**
     * Constructs a new MainScreen instance.
     */
    public MainScreen(){

        // Add input listener to handle escape key press
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Keys.ESCAPE) {
                    // Navigate to preferences screen or quit game
                    Screens.PREFERENCES.setAsCurrent();
                    return true; // Key press handled -> navigates to screen
                }
                return false; // Key press not handled -> doesn't navigate to screen
            }
        });
    }

    /**
     * @since v2 -- In v2, the screen is now loaded dynamically via the {@link Screens} enum
     */
    @Override
    public void show() {
        // This method is called when the screen is first shown.
        Screens.PLAY.setAsCurrent(); // Set the play screen as the current screen
    }


    // Override methods from Screen interface
    // These methods are called by the game framework at various stages of the screen lifecycle
    // Not currently used / needed.
    @Override
    public void render(float delta) {
        // This method is called every frame to render the screen.
        // You can put your rendering code here.
    }

    @Override
    public void resize(int width, int height) {
        // This method is called when the screen size is changed.
        // You can handle resizing of your screen here.
    }

    @Override
    public void pause() {
        // This method is called when the game is paused.
        // You can pause any ongoing processes or animations here.
    }

    @Override
    public void resume() {
        // This method is called when the game is resumed from a paused state.
        // You can resume any paused processes or animations here.
    }

    @Override
    public void hide() {
        // This method is called when the screen is hidden or switched to another screen.
        // You can dispose of any resources associated with the screen here.
    }

    @Override
    public void dispose() {
        // This method is called when the screen is about to be disposed.
        // You can dispose of any resources associated with the screen here.
    }
}