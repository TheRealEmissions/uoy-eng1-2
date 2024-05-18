package com.eng1.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.game.Score;
import com.eng1.game.game.player.Statistics;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the EndScreen screen of the game.
 * Allows the player see their final score
 */
@SuppressWarnings("SpellCheckingInspection")
public class EndScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private int scorePercentage;
    private List<String> topScores;

    /**
     * Constructor for the EndScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public EndScreen() {
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        float scoreTotal = Arrays.stream(Statistics.PlayerStatistics.values()).map(Statistics.PlayerStatistics::getTotal).reduce(0f, Float::sum);
        float maxTotal = Statistics.MAX_SCORE;

        float score = (scoreTotal / maxTotal) * 0.8f;
        scorePercentage = (int) Math.floor(score * 100);

        // Load top scores
        topScores = Score.getTop10Scores();

        // Show the initial screen
        showGameOverScreen();
    }

    private void showGameOverScreen() {
        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label gameOverLabel = new Label("Game Over! You got " + scorePercentage + "% on your test!", skin);
        table.add(gameOverLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        Label achievementLabel = new Label("Achievements:", skin);
        table.add(achievementLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        final TextButton continueButton = new TextButton("Continue", skin);
        table.add(continueButton).colspan(2);
        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (scorePercentage > getLastScore()) {
                    showHighScoreEntryScreen();
                } else {
                    showLeaderboardScreen();
                }
            }
        });
        stage.setKeyboardFocus(continueButton);
    }
    private float getLastScore() {
        // Assume the last score is the 10th score in the list if it exists
        if (topScores.size() < 10) {
            return 0;
        }
        String[] lastScoreEntry = topScores.get(topScores.size() - 1).split(",");
        return Float.parseFloat(lastScoreEntry[1]);
    }

    private void showHighScoreEntryScreen() {
        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label highScoreLabel = new Label("Congratulations! You got a high score!", skin);
        table.add(highScoreLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        Label nameLabel = new Label("Enter your name:", skin);
        table.add(nameLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        final TextField nameField = new TextField("", skin);
        table.add(nameField).colspan(2);
        table.row().pad(10, 0, 0, 10);

        final TextButton submitButton = new TextButton("Press Enter to save", skin);
        table.add(submitButton).colspan(2);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String playerName = nameField.getText();
                Score.saveScore(playerName, scorePercentage);
                showLeaderboardScreen();
            }
        });

        stage.setKeyboardFocus(submitButton);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    submitButton.fire(new ChangeListener.ChangeEvent());
                }
            }
        });
    }

    private void showLeaderboardScreen() {
        stage.clear();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label leaderboardLabel = new Label("Leaderboard", skin);
        table.add(leaderboardLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        topScores = Score.getTop10Scores(); // Retrieve the top 10 scores from the Score class
        for (int i = 0; i < topScores.size(); i++) {
            Label scoreEntry = new Label((i + 1) + ". " + topScores.get(i), skin);
            table.add(scoreEntry).left().pad(5);
            table.row();
        }

        final TextButton quitButton = new TextButton("Press Enter to quit", skin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               Gdx.app.exit();
            }
        });

        table.add(quitButton).colspan(2);
        stage.setKeyboardFocus(quitButton);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Not needed
    }

    @Override
    public void resume() {
        // Not needed
    }

    @Override
    public void hide() {
        // Not needed
    }

    @Override
    public void dispose() {
        // Not needed
    }

}
