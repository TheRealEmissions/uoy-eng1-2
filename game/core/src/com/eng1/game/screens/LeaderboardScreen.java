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
import java.util.List;

public class LeaderboardScreen implements Screen {
    private final Stage stage;
    private final Skin skin;

    public LeaderboardScreen() {
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label leaderboardLabel = new Label("Leaderboard", skin);
        leaderboardLabel.setFontScale(1.5f);
        table.add(leaderboardLabel).colspan(2);
        table.row().pad(10.0f, 0.0f, 0.0f, 10.0f);

        List<Score.ScoreEntry> topScores = Score.getTop10Scores();

        for (int i = 0; i < topScores.size(); ++i) {
            Score.ScoreEntry scoreEntry = topScores.get(i);
            Label playerScore = new Label(i + 1 + ". " + scoreEntry.getPlayerName() + ": " + scoreEntry.getScore(), skin);
            playerScore.setFontScale(1.2f);
            table.add(playerScore).left().pad(5.0f);
            table.row();
        }

        TextButton quitButton = new TextButton("Home", skin);
        quitButton.getLabel().setFontScale(1.2f);
        table.add(quitButton).colspan(2);

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Screens.MENU.setAsCurrent();
            }
        });

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
    }
}
