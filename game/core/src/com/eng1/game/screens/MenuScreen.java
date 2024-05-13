package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.assets.skins.SkinAssets;

/**
 * Represents the main menu screen of the game.
 * Provides options for starting a new game, accessing preferences, and exiting the game.
 *
 */
public class MenuScreen extends ScreenAdapter {
    private final Stage stage; // Stage for handling UI elements
    private final Skin uiSkin = SkinAssets.UI.get(); // Skin for UI elements

    /**
     * Constructor for the MenuScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public MenuScreen() {
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create buttons
        // Label for displaying the game title
        Label titleLabel = new Label("Heslington Hustle", uiSkin);
        TextButton newGame = new TextButton("New Game", uiSkin);
        TextButton preferences = new TextButton("Preferences", uiSkin);
        TextButton exit = new TextButton("Exit", uiSkin);

        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add elements to the table
        table
            .add(titleLabel)
            .colspan(4);
        table
            .row()
            .pad(10, 0, 0, 0);
        table
            .add(newGame)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .pad(10, 150, 10, 150);
        table
            .row()
            .pad(10, 0, 10, 0);
        table
            .add(preferences)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .pad(10, 150, 10, 150);
        table
            .row();
        table
            .add(exit)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .pad(10, 150, 10, 150);

        // Create button listeners

        // Exits the game when exit is clicked
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        // Changes to character screen when new game is clicked
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screens.CHARACTER.setAsCurrent();
            }
        });

        // Changes to preferences screen when preferences is clicked
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screens.PREFERENCES.setAsCurrent();
            }
        });
    }

    @Override
    public void render(float delta) {
        // Clear the screen ready for the next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Change the stage's viewport when the screen size is changed
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
        // Dispose of assets when not needed anymore
        stage.dispose();
        uiSkin.dispose();
    }
}
