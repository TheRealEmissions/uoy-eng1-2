package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.game.Score;

/**
 * This class represents the screen that is displayed when the player achieves a high score.
 * It contains a stage for managing and displaying UI elements, a skin for styling the UI elements,
 * and the player's score percentage.
 * The screen displays a message congratulating the player, a field for the player to enter their name,
 * and a button to submit their score to the leaderboard.
 */
public class HighScoreEntryScreen implements Screen {
    /**
     * The stage on which the game's UI elements are displayed.
     */
    private final Stage stage;

    /**
     * The skin used for styling the game's UI elements.
     */
    private final Skin skin;

    /**
     * The player's score percentage.
     */
    private final int scorePercentage;

    /**
     * Constructor for the HighScoreEntryScreen class.
     * It initialises the stage with a new ScreenViewport, the skin with the UI skin from the SkinAssets,
     * and sets the player's score percentage.
     *
     * @param scorePercentage The player's score percentage.
     */
    public HighScoreEntryScreen(int scorePercentage) {
        this.stage = new Stage(new ScreenViewport());
        this.skin = SkinAssets.UI.get();
        this.scorePercentage = scorePercentage;
    }

    /**
     * This method is called when the HighScoreEntryScreen is shown.
     * It sets up the UI elements for the high score entry screen.
     */
    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label highScoreLabel = new Label("Congratulations! You got a high score!", skin);
        highScoreLabel.setFontScale(1.5f);
        table.add(highScoreLabel).colspan(2).padBottom(20.0f);
        table.row().pad(10.0f, 0.0f, 0.0f, 10.0f);

        Label nameLabel = new Label("Enter your name:", skin);
        nameLabel.setFontScale(1.2f);
        table.add(nameLabel).colspan(2);
        table.row().pad(10.0f, 0.0f, 0.0f, 10.0f);

        final TextField nameField = new TextField("", skin);
        nameField.getStyle().font.getData().setScale(1.2f);
        table.add(nameField).colspan(2);
        table.row().pad(10.0f, 0.0f, 0.0f, 10.0f);

        TextButton submitButton = new TextButton("Save to leaderboard", skin);
        submitButton.getLabel().setFontScale(1.2f);
        table.add(submitButton).colspan(2);

        // Adds a ChangeListener to the submit button.
        // The listener's changed method is called when the button is clicked.
        submitButton.addListener(new ChangeListener() {
            @Override
            // The method that is called when the button is clicked.
            // It retrieves the player's name from the nameField, trims it and converts it to lowercase.
            // If the player's name is empty, it shows a dialog prompting the player to enter their name.
            // If the player's name is not empty, it saves the score and switches to the leaderboard screen.
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                // Retrieves the player's name from the nameField, trims it and converts it to lowercase.
                String playerName = nameField.getText().trim().toLowerCase();

                // Checks if the player's name is empty.
                if (playerName.isEmpty()) {
                    // Creates a new dialog with the title "Error", the default skin and the window style "dialog".
                    Dialog dialog = new Dialog("Error", skin, "dialog") {
                        {
                            // Sets the text of the dialog to "Please enter your name." and adds a padding of 20.
                            text("Please enter your name.").pad(20);
                            // Adds an "OK" button to the dialog.
                            button("OK");
                        }
                    };
                    // Adds a padding of 10 to the content table of the dialog.
                    dialog.getContentTable().pad(10);
                    // Shows the dialog on the stage.
                    dialog.show(stage);
                } else {
                    // Saves the score with the player's name and the score percentage.
                    Score.saveScore(playerName, scorePercentage);
                    // Switches to the leaderboard screen.
                    Screens.LEADERBOARD.setAsCurrent();
                }
            }
        });
        stage.setKeyboardFocus(submitButton);
    }

    /**
     * This method is called every frame to render the game screen.
     * It first clears the screen with a black color.
     * Then it updates the stage actors and draws them.
     *
     * @param delta The time in seconds since the last frame.
     */
    @Override
    public void render(float delta) {
        // Sets the clear color to black.
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Clears the screen.
        Gdx.gl.glClear(16384);

        // Updates the stage actors. The minimum of the delta time and 1/30 second is used to avoid spiral of death.
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335f));

        // Draws the stage actors.
        stage.draw();
    }

    /**
     * This method is called when the game screen is resized.
     * It updates the viewport of the stage with the new width and height.
     * The third parameter is set to true, which means the stage's camera will be centered on the stage,
     * and the viewport's world size will be scaled to fit the screen while keeping the aspect ratio.
     *
     * @param width  The new width of the game screen.
     * @param height The new height of the game screen.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * This method is called when the game is paused.
     * Currently, it does not perform any actions.
     */
    @Override
    public void pause() {}

    /**
     * This method is called when the game is resumed from a paused state.
     * Currently, it does not perform any actions.
     */
    @Override
    public void resume() {}

    /**
     * This method is called when the HighScoreEntryScreen is no longer the current screen.
     * Currently, it does not perform any actions.
     */
    @Override
    public void hide() {}

    /**
     * This method is called when the HighScoreEntryScreen is disposed of.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
