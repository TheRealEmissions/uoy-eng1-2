package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.game.Score;
import com.eng1.game.game.achievement.Achievements;

/**
 * This class represents the end screen of the game, which is displayed when the game is over.
 * It displays the game over message, the player's score, their classification, and their achievements.
 * It also provides a "Continue" button for the player to proceed to the next screen.
 * The screen layout is managed by a stage, which is a container for actors (UI elements).
 * The stage handles input events and updates and draws its actors.
 */
public class EndScreen implements Screen {
    /**
     * The stage that contains the UI elements of the end screen.
     * Handles input events and updates and draws its actors.
     */
    private final Stage stage = new Stage(new ScreenViewport());
    /**
     * The skin instance used for styling the UI elements on the screen.
     */
    private final Skin skin;

    /**
     * The score percentage achieved by the player.
     * This variable is used to display the player's score on the game over screen.
     */
    private int scorePercentage;

    /**
     * Constructor for the EndScreen class.
     * Initializes the skin instance used for styling the UI elements on the screen.
     */
    public EndScreen() {
        this.skin = SkinAssets.UI.get();
    }

    /**
     * This method is called when the EndScreen is set to be the current screen of the game.
     * It clears the stage of any previous actors, sets the input processor to the stage to handle user input,
     * calculates the score percentage achieved by the player, and then displays the game over screen.
     */
    @Override
    public void show() {
        // Clear the stage of any previous actors
        stage.clear();

        // Set the input processor to the stage to handle user input
        Gdx.input.setInputProcessor(stage);

        // Calculate the score percentage achieved by the player
        this.scorePercentage = Score.calculateScorePercentage();

        // Display the game over screen
        showGameOverScreen();
    }

    /**
     * This method is responsible for displaying the game over screen.
     * It first clears the stage of any previous actors, then creates a new table and adds it to the stage.
     * The table is filled with various labels and buttons, including the game over message, the player's score,
     * their classification, and their achievements.
     * A "Continue" button is also added to the table, which when clicked, will either set the high score screen
     * or the leaderboard screen as the current screen, depending on the player's score.
     */
    private void showGameOverScreen() {
        // Clear the stage of any previous actors
        stage.clear();

        // Create a new table and add it to the stage
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create and add the game over label to the table
        Label gameOverLabel = new Label("Game Over! You got " + scorePercentage + "% on your test!", skin);
        gameOverLabel.setFontScale(1.5F);
        table.add(gameOverLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        // Create and add the classification label to the table
        Label classificationLabel = new Label("Classification: " + Score.getClassification((float) scorePercentage), skin);
        classificationLabel.setFontScale(1.2F);
        table.add(classificationLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        // Create and add the achievements label to the table
        Label achievementLabel = new Label("Achievements:", skin);
        achievementLabel.setFontScale(1.2F);
        table.add(achievementLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        // Loop through the achievements and add each one to the table
        Achievements[] achievements = Achievements.values();
        for (Achievements achievement : achievements) {
            if (!achievement.hasAchieved()) continue;
            Label label = new Label(achievement.getName() + " - " + achievement.getDescription(), skin);
            label.setFontScale(1.0F);
            table.add(label).colspan(2);
            table.row().pad(5.0F, 0.0F, 0.0F, 10.0F);
        }

        // Create and add the continue button to the table
        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.getLabel().setFontScale(1.2F);
        table.add(continueButton).colspan(2);

        // Add a listener to the continue button to handle click events
        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // If the player's score is higher than the last score, set the high score screen as the current screen
                // Otherwise, set the leaderboard screen as the current screen
                if (scorePercentage > Score.getLastScore()) {
                    Screens.HIGHSCORE.setAsCurrent();
                } else {
                    Screens.LEADERBOARD.setAsCurrent();
                }
            }
        });

        // Set the keyboard focus to the continue button
        stage.setKeyboardFocus(continueButton);
    }

    /**
     * This method is called by the game loop to render the game graphics.
     * It first sets the clear color of the screen to black, then clears the screen.
     * After that, it updates the stage actors and draws them on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Set the clear color of the screen to black
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        // Clear the screen
        Gdx.gl.glClear(16384);

        // Update the stage actors
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335F));

        // Draw the stage actors on the screen
        stage.draw();
    }

    /**
     * This method is called when the game window is resized.
     * It updates the viewport of the stage to match the new window size.
     * The viewport controls the visible area of the stage.
     * The third parameter to the update method is set to true, which means the stage's camera will be centered on the stage,
     * and the viewport's world size will be scaled to fit the window size while keeping the aspect ratio.
     *
     * @param width  The new width of the window.
     * @param height The new height of the window.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * This method is called when the game is paused, typically when it loses focus like being minimized.
     * In this implementation, no action is taken when the game is paused.
     */
    @Override
    public void pause() {}

    /**
     * This method is called when the game is resumed from a paused state, typically when it gains focus.
     * In this implementation, no action is taken when the game is resumed.
     */
    @Override
    public void resume() {}

    /**
     * This method is called when the current screen is being hidden or replaced by another screen.
     * In this implementation, no action is taken when the screen is hidden.
     */
    @Override
    public void hide() {}

    /**
     * This method is called when the game is closing.
     * It's responsible for freeing up resources and cleaning up.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
