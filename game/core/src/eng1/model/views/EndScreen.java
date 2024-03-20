package eng1.model.views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.HeslingtonHustle;

import com.eng1.game.Activity;

import java.util.Map;

/**
 * Represents the Endscreen screen of the game.
 * Allows the player see their final score
 */
public class EndScreen implements Screen {

    private HeslingtonHustle parent;
    private Stage stage;
    private Label titleLabel;
    private Label scoreLabel;

    private static Map<String, Map<String, Activity>> activities; // Map of the activity types





    /**
     * Constructor for the EndScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     * @param eng1 The orchestrator of the game.
     */
    public EndScreen(HeslingtonHustle eng1) {
        parent = eng1;
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Add labels
        titleLabel = new Label("Heslington Hustle", skin);
        scoreLabel = new Label("Score: " + Activity.getFinalScore(), skin); // Retrieve the final score from Activity



        // Add actors to the table
        table.add(titleLabel).colspan(2);
        table.row().pad(10, 0, 0, 10);

        // Display completed activities
        Map<String, Integer> completedActivities = Activity.countCompletedActivities();
        for (String type : completedActivities.keySet()) {
            table.add(new Label(type + ": " + completedActivities.get(type), skin)).left().pad(10);
            table.row();
        }
        table.row().pad(10, 0, 0, 10);

        table.add(scoreLabel).row();

        // quit game button
        final TextButton quitButton = new TextButton("Quit", skin);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.row().pad(10, 0, 0, 10);
        table.add(quitButton).colspan(50);
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
