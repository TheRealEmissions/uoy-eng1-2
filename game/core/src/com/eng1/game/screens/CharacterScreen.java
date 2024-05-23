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
    /**
     * The stage where all the UI elements for this screen are drawn.
     */
    private final Stage stage;

    /**
     * The table layout used to organize the UI elements on the screen.
     * This is a flexible and efficient layout manager that arranges its children in a grid.
     */
    private final Table table = new Table();

    /**
     * The skin used for styling the UI elements on this screen.
     * It contains all the resources needed to style the UI widgets.
     */
    private final Skin uiSkin = SkinAssets.UI.get();

    /**
     * A utility class that contains the UI elements and their configurations for the character selection table.
     * This class is marked with {@link UtilityClass} which makes the class final and its constructor private.
     */
    @UtilityClass
    private class TableContents {
        /**
         * The skin used for styling the UI elements in this table.
         * It contains all the resources needed to style the UI widgets.
         */
        private static final Skin UI_SKIN = SkinAssets.UI.get();

        /**
         * The title label for the character selection table.
         */
        public static final Label TITLE_LABEL = new Label("I'd like to play as", UI_SKIN);

        /**
         * The button for selecting the character "Liam".
         */
        public static final TextButton CHARACTER1_BUTTON = new TextButton("Liam", UI_SKIN);

        /**
         * The button for selecting the character "Lucy".
         */
        public static final TextButton CHARACTER2_BUTTON = new TextButton("Lucy", UI_SKIN);

        /**
         * The button for selecting the character "Sammy".
         */
        public static final TextButton CHARACTER3_BUTTON = new TextButton("Sammy", UI_SKIN);

        /**
         * The image for the character "Liam".
         */
        public static final Image CHARACTER1_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_1.get());

        /**
         * The image for the character "Lucy".
         */
        public static final Image CHARACTER2_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_2.get());

        /**
         * The image for the character "Sammy".
         */
        public static final Image CHARACTER3_IMAGE = new Image(ImageAssets.PLAYER_CHARACTER_3.get());

        /**
         * Static initializer block that sets up the font scales for the labels and the sizes for the images.
         */
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

        /**
         * Disposes of the resources used by the UI elements in this table.
         */
        public static void dispose() {
            SkinAssets.UI.dispose(UI_SKIN);
        }
    }


    /**
     * Constructs a new CharacterScreen.
     *
     * This constructor initializes the stage where all the UI elements for this screen are drawn.
     * The stage is set up with a new ScreenViewport, which handles scaling of the UI elements based on the screen size.
     */
    public CharacterScreen() {
        stage = new Stage(new ScreenViewport());
    }

    /**
     * This method is called when this screen becomes the current screen.
     * It sets up UI elements and handles input events.
     */
    @Override
    public void show() {
        // Set the input processor to the stage to handle input events
        Gdx.input.setInputProcessor(stage);
        // Add the table actor to the stage
        stage.addActor(table);

        // Set the table to fill the parent stage and enable transformations
        table.setFillParent(true);
        table.setTransform(true);

        // If the table has no children, set up the table contents
        if (table.getChildren().isEmpty()) {
            setTableContents();
        }

        // Add listeners to character selection buttons
        // These listeners change the selected character and the current screen when the buttons are clicked

        // Listener for the "Liam" character selection button
        TableContents.CHARACTER1_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Set the selected character to "Liam"
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_1);
                // Change the screen to the instruction screen
                Screens.INSTRUCTION.setAsCurrent();
            }
        });

        // Listener for the "Lucy" character selection button
        TableContents.CHARACTER2_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Set the selected character to "Lucy"
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_2);
                // Change the screen to the instruction screen
                Screens.INSTRUCTION.setAsCurrent();
            }
        });

        // Listener for the "Sammy" character selection button
        TableContents.CHARACTER3_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Set the selected character to "Sammy"
                PlayScreen.setSelectedCharacter(ImageAssets.PLAYER_CHARACTER_3);
                // Change the screen to the instruction screen
                Screens.INSTRUCTION.setAsCurrent();
            }
        });
    }

    /**
     * Sets up the contents of the character selection table.
     *
     * This method adds padding and spacing between elements, and adds actors (UI elements) to the table.
     * The actors are added with increased size for better visibility.
     * The actors include the title label, character selection buttons, and character images.
     * The layout of the actors is managed by the table, which arranges its children in a grid.
     */
    private void setTableContents() {
        // Add padding and spacing between elements
        table.pad(20f);
        table.defaults().pad(10f);

        // Add actors to the table with increased size
        // Add the title label to the table, spanning 3 columns, with extra padding at the bottom
        table.add(TableContents.TITLE_LABEL)
            .colspan(3)
            .padBottom(40f);
        table.row();
        // Add the character selection buttons to the table, filling their cells horizontally and uniformly
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
        // Add the character images to the table, centered in their cells, with their original sizes
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
     * This method is responsible for rendering the screen.
     * It first clears the screen with a black color, then updates and draws the stage.
     *
     * The stage acts based on the minimum value between the time elapsed since the last frame and 1/30 seconds.
     * This is to ensure that the stage does not act more than once within a 30th of a second.
     *
     * @param delta The time elapsed since the last frame, in seconds. This is used to make the game frame rate independent.
     */
    @Override
    public void render(float delta) {
        // Set the clear color to black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update the stage based on the time elapsed since the last frame
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // Draw the stage
        stage.draw();
    }

    /**
     * This method is called when the screen size changes.
     * It updates the viewport of the stage to match the new screen size.
     * The viewport is responsible for mapping the coordinates of the stage to the screen coordinates.
     * By updating the viewport, the stage and its actors are scaled and positioned correctly on the screen.
     *
     * @param width  The new width of the screen, in pixels.
     * @param height The new height of the screen, in pixels.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes of resources used by this screen.
     *
     * This method is called when the screen is no longer needed and is about to be destroyed.
     * It disposes of the resources that the screen has been using.
     * These resources include the stage, the UI skin, and the contents of the character selection table.
     * Disposing of these resources frees up memory and prevents memory leaks.
     */
    @Override
    public void dispose() {
        // Dispose of the stage
        stage.dispose();
        // Dispose of the UI skin
        SkinAssets.UI.dispose(uiSkin);
        // Dispose of the contents of the character selection table
        TableContents.dispose();
    }
}