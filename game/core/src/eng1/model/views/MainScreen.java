package eng1.model.views;

import com.eng1.game.HeslingtonHustle;
import com.eng1.game.Play;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 * This class represents the main screen of the game.
 * This screen serves as the playing point of the game and handles user interactions such as escape to go to a settings screen / quit.
 */
public class MainScreen implements Screen {
    private HeslingtonHustle parent; // a field to store our orchestrator
    private Play play;

    /**
     * Constructs a new MainScreen instance.
     * @param heslingtonHustle The orchestrator of the game.
     *                         This parameter is used to navigate between screens.
     */
    public MainScreen(HeslingtonHustle heslingtonHustle){
        parent = heslingtonHustle;     // setting the argument to our field

        // Add input listener to handle escape key press
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Keys.ESCAPE) {
                    // Navigate to preferences screen or quit game
                    parent.changeScreen(HeslingtonHustle.PREFERENCES);
                    return true; // Key press handled -> navigates to screen
                }
                return false; // Key press not handled -> doesn't navigate to screen
            }
        });
    }

    @Override
    public void show() {
        // This method is called when the screen is first shown.
        play = new Play(); // Instantiate the Play class
        parent.setScreen(play); // Switch to the 'Play' screen (where the game is run / rendered)
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