package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.player.Player;
import com.eng1.game.game.activity.Activity;
import com.eng1.game.player.GameStats;
import org.jetbrains.annotations.NotNull;

/**
 * The Play class represents the screen where the gameplay takes place.
 * It implements the Screen interface provided by LibGDX.
 *
 * @since v2 -- renamed from Play to PlayScreen for consistency
 */
public class PlayScreen implements Screen {
    private static ImageAssets selectedCharacter = null;
    private static Texture selectedCharacterTexture = null;

    public static void setSelectedCharacter(@NotNull ImageAssets character) {
        selectedCharacter = character;
        selectedCharacterTexture = selectedCharacter.get();
    }

    private OrthogonalTiledMapRenderer renderer;
    private TiledMap currentMap = MapAssets.NEW_WORLD.get();
    private OrthographicCamera camera = new OrthographicCamera();

    private Player player;
    private final BitmapFont displayDateTime = new BitmapFont();
    private final SpriteBatch uiBatch = new SpriteBatch();

    /**
     * Constructor for the Play class.
     * Initializes the camera.
     */
    public PlayScreen() {
        //create activities
        Activity.createActivities();
    }

    /**
     * Changes the current map to the new map.
     * @param map the new map to change to
     * @since v2 -- uses {@link MapAssets} to refer to the map instead of {@link String}
     */
    public void changeMap(@NotNull MapAssets map) {
        currentMap.dispose(); // Dispose the old map
        currentMap = map.get(); // Load the new map

        // Set the map in the renderer
        renderer.setMap(currentMap);
        setPlayerPosition(); // Set the location of the player

        // Center the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }


    /**
     * Sets the position of the player based on the current and old map paths.
     */
    private void setPlayerPosition() {
        player = new Player(
            new Sprite(selectedCharacter.get()),
            (TiledMapTileLayer) currentMap.getLayers().get(0)
        );
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        setPlayerPosition();
    }
    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        renderer.setView(camera);
        renderer.render();
        Batch batch = renderer.getBatch();
        batch.begin();

        // set camera to players position
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();
        player.draw(batch);
        batch.end();

        uiBatch.begin();

        final String stats = ("Day: " + GameStats.getDay() + " Time: " + GameStats.getTime() + " Energy: " + GameStats.getEnergy());
        displayDateTime.getData().setScale(2); // Adjust the scale as needed
        displayDateTime.draw(uiBatch, stats, 12, 1070);

        uiBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.zoom = 1f;

        // Center the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        renderer.getBatch().setProjectionMatrix(camera.combined);
    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        currentMap.dispose();
        renderer.dispose();
        displayDateTime.dispose();
        selectedCharacter.dispose(selectedCharacterTexture);
        uiBatch.dispose();
    }
}
