package com.eng1.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.game.player.Player;
import com.eng1.game.game.activity.Activity;
import com.eng1.game.game.player.GameStats;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
     * @param transitionKey the key for the spawnpoint
     * @since v2 -- uses {@link MapAssets} to refer to the map instead of {@link String} -- also has a transition key parameter
     */
    public void changeMap(@NotNull MapAssets map, @Nullable String transitionKey) {
        currentMap.dispose(); // Dispose the old map
        currentMap = map.get(); // Load the new map

        // Set the map in the renderer
        renderer.setMap(currentMap);
        if (transitionKey == null) {
            setPlayerPosition(); // Set the location of the player
        } else {
            setPlayerPosition(transitionKey); // Set the location of the player
        }

        // Center the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    /**
     * Sets the position of the player on the map based on the map's general spawnpoint
     */
    private void setPlayerPosition() {
        MapLayer layer = currentMap.getLayers().get("spawnpoint");
        boolean found = false;
        MapObjects objects = layer.getObjects();
        for (int i = 0; i < objects.getCount(); i++) {
            MapObject mapObject = objects.get(i);
            if (mapObject.getName().equals("spawnpoint")) {
                MapProperties properties = mapObject.getProperties();
                setPlayerPosition(
                    (float) properties.get("x"),
                    (float) properties.get("y")
                );
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("No spawnpoint found in map");
        }
    }

    /**
     * Sets the position of the player on the map based on the transition key
     * @param transitionKey the key for the spawnpoint
     */
    private void setPlayerPosition(String transitionKey) {
        MapLayer layer = currentMap.getLayers().get("spawnpoints");
        boolean found = false;
        MapObjects objects = layer.getObjects();
        for (int i = 0; i < objects.getCount(); i++) {
            MapObject mapObject = objects.get(i);
            if (mapObject.getName().equals(transitionKey)) {
                MapProperties properties = mapObject.getProperties();
                setPlayerPosition(
                    (float) properties.get("x"),
                    (float) properties.get("y")
                );
                found = true;
                break;
            }
        }
        if (!found) {
            setPlayerPosition();
        }
    }


    /**
     * Sets the position of the player on the map.
     */
    private void setPlayerPosition(float x, float y) {
        player = new Player(
            new Sprite(selectedCharacterTexture),
            (TiledMapTileLayer) currentMap.getLayers().get("collisions")
        );
        player.setPosition(x, y);
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
        Batch batch = renderer.getBatch();
        batch.begin();

        List<String> topLayer = List.of("trees", "background");

        for (int i = 0; i < currentMap.getLayers().getCount(); i++) {
            MapLayer mapLayer = currentMap.getLayers().get(i);
            if (topLayer.contains(mapLayer.getName())) {
                continue;
            }
            if (mapLayer instanceof TiledMapTileLayer) {
                renderer.renderTileLayer((TiledMapTileLayer) mapLayer);
            }
        }

        // set camera to players position
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);

        TiledMapTileLayer layer = (TiledMapTileLayer) currentMap.getLayers().get(0);
        float mapWidth = layer.getWidth() * layer.getTileWidth();
        float mapHeight = layer.getHeight() * layer.getTileHeight();

        // clamp camera to map
        camera.position.x = Math.min(mapWidth - camera.viewportWidth / 2, Math.max(camera.viewportWidth / 2, camera.position.x));
        camera.position.y = Math.min(mapHeight - camera.viewportHeight / 2, Math.max(camera.viewportHeight / 2, camera.position.y));

        camera.update();
        player.draw(batch);

        for (String layerName : topLayer) {
            renderer.renderTileLayer((TiledMapTileLayer) currentMap.getLayers().get(layerName));
        }

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
