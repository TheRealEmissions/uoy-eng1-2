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
import com.eng1.game.game.Score;

public class EndScreen implements Screen {
    private final Stage stage = new Stage(new ScreenViewport());
    private final Skin skin;
    private int scorePercentage;

    public EndScreen() {
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        // Calculate the scorePercentage
        this.scorePercentage = Score.calculateScorePercentage();

        showGameOverScreen();
    }

    private void showGameOverScreen() {
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label gameOverLabel = new Label("Game Over! You got " + scorePercentage + "% on your test!", skin);
        gameOverLabel.setFontScale(1.5F);
        table.add(gameOverLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        Label classificationLabel = new Label("Classification: " + Score.getClassification((float) scorePercentage), skin);
        classificationLabel.setFontScale(1.2F);
        table.add(classificationLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        Label achievementLabel = new Label("Achievements:", skin);
        achievementLabel.setFontScale(1.2F);
        table.add(achievementLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);

        TextButton continueButton = new TextButton("Continue", skin);
        continueButton.getLabel().setFontScale(1.2F);
        table.add(continueButton).colspan(2);

        continueButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (scorePercentage > Score.getLastScore()) {
                    Screens.HIGHSCORE.setAsCurrent();
                } else {
                    Screens.LEADERBOARD.setAsCurrent();
                }
            }
        });

        stage.setKeyboardFocus(continueButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335F));
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
    public void dispose() {}
}
