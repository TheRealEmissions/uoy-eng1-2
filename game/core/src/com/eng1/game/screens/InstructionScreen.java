package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.skins.SkinAssets;
import lombok.experimental.UtilityClass;
/**
 * Represents the instruction screen of the game.
 * Provides instructions on how to play the game.
 *
 * @since v2 -- New screen added to make the game more user-friendly
 */
public class InstructionScreen extends ScreenAdapter {
    private final Stage stage; // Stage for handling UI elements
    private final Skin uiSkin = SkinAssets.UI.get(); // Skin for UI elements
    private final Table table = new Table(); // Table for organizing UI elements
    private final Image mapOverview = new Image(ImageAssets.NEW_WORLD_MAP_OVERVIEW.get());
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    @UtilityClass
    private class InstructionContents{
        private static final Skin UI_SKIN = SkinAssets.UI.get();
        private static final Label TITLE = new Label("Welcome to Heslington Hustle!", UI_SKIN);
        private static final Label INTRO = new Label("You wake up in your student accommodation.\n" +
                "It is Monday morning and you are still sleepy. It comes to your realisation that\n" +
                "this is your last week until exams begin! Trying not to panic, you think about today,\n" +
                "and how to best manage your day with a healthy mix of studying, taking care of yourself and sleeping.\n" +
                "Yes, you do need sleep, even if you are hesitant to admit it!", UI_SKIN);
        private static final Label INSTRUCTIONS = new Label("Welcome to the game!\n\n" +
                "Use the following controls to navigate:\n" +
                "W / ↑ - Move Up\n" +
                "A / ← - Move Left\n" +
                "S / ↓ - Move Down\n" +
                "D / → - Move Right\n" +
                "Press ESC anytime to pause \n\n", UI_SKIN);

        private static final Label START = new Label("Press Enter to start!", UI_SKIN);

        static {
            TITLE.setFontScale(2f);
            INTRO.setFontScale(1.5f);
            INSTRUCTIONS.setFontScale(1.5f);
            START.setFontScale(1.5f);

            TITLE.setAlignment(Align.center);
            INTRO.setAlignment(Align.center);
            INSTRUCTIONS.setAlignment(Align.center);
            START.setAlignment(Align.center);
        }

        public static void dispose() {
            SkinAssets.UI.dispose(UI_SKIN);
        }
    }

    /**
     * Constructor for the InstructionScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public InstructionScreen() {
        this.stage = new Stage(new ScreenViewport());
        mapOverview.setPosition(-1000, 0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        stage.addActor(table);

        // Create a table that fills the screen. Everything else will go inside this table.
        table.setFillParent(true);
        table.setTransform(true);

        if (table.getChildren().isEmpty()) {
            setTableContents();
        }

        // Check if the Enter key is pressed
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ENTER)) {
            // Transition to the main play screen
            Screens.MAIN.setAsCurrent();
        }
    }

    private void setTableContents() {
        table.setOrigin(Align.center);
        table.setPosition(0, 0);

        float width = Gdx.graphics.getWidth();

        // Add elements to the table
        table.add(InstructionContents.TITLE).colspan(2).padBottom(20).row();
        table.add(InstructionContents.INTRO).colspan(2).padBottom(20).row();
        table.add(InstructionContents.INSTRUCTIONS).colspan(2).padBottom(20).row();
        table.add(InstructionContents.START).colspan(2).padBottom(20).row();
    }
    @Override
    public void render(float delta) {
        // Clear the screen ready for the next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        // Draw the map overview
        Batch batch = stage.getBatch();
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        mapOverview.draw(batch, 1f);
        batch.end();

        // Render a semi-transparent overlay
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth() * 10, Gdx.graphics.getHeight() * 10);
        shapeRenderer.end();

        // Draw the stage (including the instruction label)
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        // Check if the Enter key is pressed
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ENTER)) {
            // Transition to the main play screen
            Screens.MAIN.setAsCurrent();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Change the stage's viewport when the screen size is changed
        Viewport viewport = stage.getViewport();
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        // Dispose of assets when not needed anymore
        stage.dispose();
        SkinAssets.UI.dispose(uiSkin);
        TextureRegionDrawable drawable = (TextureRegionDrawable) mapOverview.getDrawable();
        ImageAssets.NEW_WORLD_MAP_OVERVIEW.dispose(drawable.getRegion().getTexture());
        shapeRenderer.dispose();
    }
}
