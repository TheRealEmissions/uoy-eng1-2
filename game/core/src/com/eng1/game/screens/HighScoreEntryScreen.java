package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.game.Score;

public class HighScoreEntryScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final int scorePercentage;

    public HighScoreEntryScreen(int scorePercentage) {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        this.scorePercentage = scorePercentage;
    }

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

        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                String playerName = nameField.getText().trim().toLowerCase(); // Trim and convert to lowercase
                if (playerName.isEmpty()) {
                    Dialog dialog = new Dialog("Error", skin, "dialog") {
                        {
                            text("Please enter your name.").pad(20);
                            button("OK");
                        }
                    };
                    dialog.getContentTable().pad(10);
                    dialog.show(stage);
                } else {
                    Score.saveScore(playerName, scorePercentage);
                    Screens.LEADERBOARD.setAsCurrent();
                }
            }
        });
        stage.setKeyboardFocus(submitButton);
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
    }
}
