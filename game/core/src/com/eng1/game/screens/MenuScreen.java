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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eng1.game.assets.skins.SkinAssets;

/**
 * Represents the main menu screen of the game.
 * Provides options for starting a new game, accessing preferences, and exiting the game.
 *
 */
public class MenuScreen extends ScreenAdapter {
    private final Stage stage; // Stage for handling UI elements
    private final Skin uiSkin = SkinAssets.UI.get(); // Skin for UI elements
    private final Table table = new Table(); // Table for organizing UI elements

    private static final float DEFAULT_WIDTH = 800f;
    private static final float DEFAULT_HEIGHT = 480f;

    private static class TableContents {
        public static final Label TITLE = new Label("Heslington Hustle", SkinAssets.UI.get());
        public static final TextButton NEW_GAME = new TextButton("New Game", SkinAssets.UI.get());
        public static final TextButton PREFERENCES = new TextButton("Preferences", SkinAssets.UI.get());
        public static final TextButton EXIT = new TextButton("Exit", SkinAssets.UI.get());

        static {
            TITLE.setAlignment(Align.center);
            TITLE.setOrigin(Align.center);
            NEW_GAME.setOrigin(Align.center);
            NEW_GAME.setTransform(true);
            PREFERENCES.setOrigin(Align.center);
            PREFERENCES.setTransform(true);
            EXIT.setOrigin(Align.center);
            EXIT.setTransform(true);
        }

        public static void setSize(float width, float height) {
            TITLE.setSize(width * 0.6f, height * 0.6f);
            NEW_GAME.setSize(width * 0.3f, height * 0.3f);
            PREFERENCES.setSize(width * 0.3f, height * 0.3f);
            EXIT.setSize(width * 0.3f, height * 0.3f);
        }
    }

    /**
     * Constructor for the MenuScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public MenuScreen() {
        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage

        // Create a table that fills the screen. Everything else will go inside this table.
        table.setFillParent(true);
        stage.addActor(table);
        table.setOrigin(Align.center);
        table.setTransform(true);

        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add elements to the table
        setTableContents();

        // Create button listeners

        // Exits the game when exit is clicked
        TableContents.EXIT.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        // Changes to character screen when new game is clicked
        TableContents.NEW_GAME.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screens.CHARACTER.setAsCurrent();
            }
        });

        // Changes to preferences screen when preferences is clicked
        TableContents.PREFERENCES.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Screens.PREFERENCES.setAsCurrent();
            }
        });
    }

    private void setTableContents() {
        setTableContents(true);
    }

    private void setTableContents(boolean reset) {
        if (reset) {
            table.clear();
        }

        float width = Gdx.graphics.getWidth();
        float widthPadding = width * 0.2f;

        table
            .add(TableContents.TITLE)
            .colspan(4);
        table
            .row()
            .pad(10, 0, 0, 0);
        table
            .add(TableContents.NEW_GAME)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .expandX()
            .pad(10, widthPadding, 10, widthPadding);
        table
            .row()
            .pad(10, 0, 10, 0);
        table
            .add(TableContents.PREFERENCES)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .expandX()
            .pad(10, widthPadding, 10, widthPadding);
        table
            .row();
        table
            .add(TableContents.EXIT)
            .fillX()
            .fillY()
            .uniformX()
            .uniformY()
            .expandX()
            .pad(10, widthPadding, 10, widthPadding);
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
        Viewport viewport = stage.getViewport();
        viewport.update(width, height, true);
        TableContents.setSize(width, height);
        table.setFillParent(true);
        table.invalidate();
        setTableContents();
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
