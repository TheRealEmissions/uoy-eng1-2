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
import java.util.List;

/**
 * This class is responsible for creating and managing the leaderboard screen in the game.
 * It includes a stage for managing and rendering 2D actors, and a skin for styling the UI elements.
 */
public class LeaderboardScreen implements Screen {
    // The stage is used for managing and rendering 2D actors on the leaderboard screen.
    private final Stage stage;

    // The skin is used for styling the UI elements on the leaderboard screen.
    private final Skin skin;

    public LeaderboardScreen() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = SkinAssets.UI.get();
    }

    @Override
    public void show() {
        // Clear the stage and set it as the input processor
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        // Create a new table and add it to the stage
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create a label for the leaderboard title and add it to the table
        Label leaderboardLabel = new Label("Leaderboard", skin);
        leaderboardLabel.setFontScale(1.5f);
        table.add(leaderboardLabel).colspan(2);
        table.row().pad(10.0f, 0.0f, 0.0f, 10.0f);

        // Retrieve the top 10 scores
        List<Score.ScoreEntry> topScores = Score.getTop10Scores();

        // For each score, create a label and add it to the table
        for (int i = 0; i < topScores.size(); ++i) {
            Score.ScoreEntry scoreEntry = topScores.get(i);
            Label playerScore = new Label(i + 1 + ". " + scoreEntry.getPlayerName() + ": " + scoreEntry.getScore(), skin);
            playerScore.setFontScale(1.2f);
            table.add(playerScore).left().pad(5.0f);
            table.row();
        }

        // Create a button for returning to the home screen and add it to the table
        TextButton quitButton = new TextButton("Home", skin);
        quitButton.getLabel().setFontScale(1.2f);
        table.add(quitButton).colspan(2);

        // Add a listener to the button to change the screen when it's clicked
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Screens.MENU.setAsCurrent();
            }
        });

        // Set the button as the initial focus for keyboard input
        stage.setKeyboardFocus(quitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(16384);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        SkinAssets.UI.dispose(skin);
    }
}
