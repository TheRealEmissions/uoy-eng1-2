package com.eng1.game.screens;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.skins.SkinAssets;
import lombok.experimental.UtilityClass;

/**
 * Represents the character selection screen of the game.
 * Allows the player to select their character from available choices.
 *
 * @since v2 -- the screen no longer stretches to scale the UI elements, instead it uses more
 * sophisticated methods, such as individual scaling and table width/height management
 */
public class CharacterScreen extends ScreenAdapter {
    private final Stage stage;
    private final Table table = new Table();
    private final Skin uiSkin = SkinAssets.UI.get();

    @UtilityClass
    private class TableContents {
        private static final Skin UI_SKIN = SkinAssets.UI.get();
        public static final Label TITLE_LABEL = new Label("I'd like to play as", UI_SKIN);
        public static final TextButton CHARACTER1_BUTTON = new TextButton("Liam", UI_SKIN);
        public static final TextButton CHARACTER2_BUTTON = new TextButton("Lucy", UI_SKIN);
        public static final TextButton CHARACTER3_BUTTON = new TextButton("Sammy", UI_SKIN);
        public static final Image CHARACTER1_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_1.get());
        public static final Image CHARACTER2_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_2.get());
        public static final Image CHARACTER3_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_3.get());

        static {
            TITLE_LABEL.setFontScale(2f);
            CHARACTER1_BUTTON.getLabel().setFontScale(1.6f);
            CHARACTER2_BUTTON.getLabel().setFontScale(1.6f);
            CHARACTER3_BUTTON.getLabel().setFontScale(1.6f);
            final float width = 400f;
            final float height = 400f;
            CHARACTER1_IMAGE.setSize(width, height);
            CHARACTER2_IMAGE.setSize(width, height);
            CHARACTER3_IMAGE.setSize(width, height);
        }

        public static void dispose() {
            SkinAssets.UI.dispose(UI_SKIN);
        }
    }


    /**
     * Constructs a new CharacterScreen.
     */
    public CharacterScreen() {
        stage = new Stage(new ScreenViewport());
    }

    /**
     * Called when this screen becomes the current screen.
     * Sets up UI elements and handles input events.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);

        // Create table and skin
        table.setFillParent(true);
        table.setTransform(true);

        if (table.getChildren().isEmpty()) {
            setTableContents();
        }

        // Add listeners to character selection buttons
        TableContents.CHARACTER1_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_1); // Set selected character
                // Change the screen to the main game screen
                Screens.INSTRUCTION.setAsCurrent();
            }
        });

        TableContents.CHARACTER2_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_2);
                Screens.INSTRUCTION.setAsCurrent();
            }
        });

        TableContents.CHARACTER3_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_3);
                Screens.INSTRUCTION.setAsCurrent();
            }
        });
    }

    private void setTableContents() {
        // Add padding and spacing between elements
        table.pad(20f);
        table.defaults().pad(10f);

        // Add actors to the table with increased size
        table.add(TableContents.TITLE_LABEL)
            .colspan(3)
            .padBottom(40f);
        table.row();
        table.add(TableContents.CHARACTER1_BUTTON)
            .fillX()
            .uniformX()
            .padRight(20f);
        table.add(TableContents.CHARACTER2_BUTTON)
            .fillX()
            .uniformX()
            .padRight(20f);
        table.add(TableContents.CHARACTER3_BUTTON)
            .fillX()
            .uniformX();
        table.row()
            .padTop(40f);
        Image character1Image = TableContents.CHARACTER1_IMAGE;
        table.add(character1Image)
            .center()
            .padRight(20f)
            .width(character1Image.getWidth())
            .height(character1Image.getHeight());
        Image character2Image = TableContents.CHARACTER2_IMAGE;
        table.add(character2Image)
            .center()
            .padRight(20f)
            .width(character2Image.getWidth())
            .height(character2Image.getHeight());
        Image character3Image = TableContents.CHARACTER3_IMAGE;
        table.add(character3Image)
            .center()
            .width(character3Image.getWidth())
            .height(character3Image.getHeight());
    }

    /**
     * Renders the screen.
     * Clears the screen and renders the stage.
     *
     * @param delta The time elapsed since the last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen size is changed.
     * Updates the viewport of the stage.
     *
     * @param width  The new width of the screen.
     * @param height The new height of the screen.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes of resources used by this screen.
     */
    @Override
    public void dispose() {
        stage.dispose();
        SkinAssets.UI.dispose(uiSkin);
        TableContents.dispose();
    }
}