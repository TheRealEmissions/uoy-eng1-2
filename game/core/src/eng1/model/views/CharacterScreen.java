package eng1.model.views;

import com.eng1.game.HeslingtonHustle;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eng1.game.Play;

public class CharacterScreen extends ScreenAdapter {
    private HeslingtonHustle parent;
    private Stage stage;
    private Label titleLabel;



    public CharacterScreen(HeslingtonHustle game) {
        parent = game;

        stage = new Stage(new StretchViewport(800, 600)); // Change the size as needed
        Gdx.input.setInputProcessor(stage); // Set the input processor to the stage
    }

    @Override
    public void show() {
        // Create table and skin
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Create title label
        titleLabel = new Label("Character Selection", skin);

        // Create character selection buttons
        TextButton character1Button = new TextButton("Character 1", skin);
        character1Button.setSize(1000f, 50f); // Set the width and height of the button

        TextButton character2Button = new TextButton("Character 2", skin);
        character2Button.setSize(200f, 50f); // Set the width and height of the button

        TextButton character3Button = new TextButton("Character 3", skin);
        character3Button.setSize(200f, 50f); // Set the width and height of the button

        // Create character images
        Texture character1Texture = new Texture(Gdx.files.internal("playerCharacters/playerCharacter1.png"));
        Texture character2Texture = new Texture(Gdx.files.internal("playerCharacters/playerCharacter2.png"));
        Texture character3Texture = new Texture(Gdx.files.internal("playerCharacters/playerCharacter3.png"));

        Image character1Image = new Image(new TextureRegionDrawable(new TextureRegion(character1Texture)));
        character1Image.setSize(200f, 200f); // Set the width and height of the image

        Image character2Image = new Image(new TextureRegionDrawable(new TextureRegion(character2Texture)));
        character2Image.setSize(200f, 200f); // Set the width and height of the image

        Image character3Image = new Image(new TextureRegionDrawable(new TextureRegion(character3Texture)));
        character3Image.setSize(200f, 200f); // Set the width and height of the image

        // Add listeners to character selection buttons
        character1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                selectedCharacter = "1"; // Set selected character choice
                Play.setSelectedCharacter("Character1"); // Set selected character
                parent.changeScreen(HeslingtonHustle.APPLICATION);
            }
        });

        character2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                selectedCharacter = "2"; // Set selected character choice
                Play.setSelectedCharacter("Character2"); // Set selected character
                parent.changeScreen(HeslingtonHustle.APPLICATION);
            }
        });

        character3Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                selectedCharacter = "3"; // Set selected character choice
                Play.setSelectedCharacter("Character3"); // Set selected character
                parent.changeScreen(HeslingtonHustle.APPLICATION);
            }
        });

        // Add padding and spacing between elements
        table.pad(20f); // Add padding around the table
        table.defaults().pad(10f); // Add default padding between elements

        // Add actors to the table with increased size
        table.add(titleLabel).colspan(3).padBottom(40f);
        table.row();
        table.add(character1Button).fillX().uniformX().padRight(20f);
        table.add(character2Button).fillX().uniformX().padRight(20f);
        table.add(character3Button).fillX().uniformX();
        table.row().padTop(40f);
        table.add(character1Image).center().padRight(20f);
        table.add(character2Image).center().padRight(20f);
        table.add(character3Image).center();

        // Set the input processor to the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}