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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.assets.maps.MapAssets;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.game.player.Player;
import com.eng1.game.game.player.Statistics;
import com.eng1.game.utils.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
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
    private MapAssets currentMapEnum = MapAssets.NEW_WORLD;
    private TiledMap currentMap = currentMapEnum.get();
    private OrthographicCamera camera = new OrthographicCamera();

    private Player player;
    private final BitmapFont displayDateTime = new BitmapFont();
    private final SpriteBatch uiBatch = new SpriteBatch();
    private final Skin uiSkin = SkinAssets.UI.get();

    private final EnumMap<MapAssets, Float> defaultViewportWidth = new EnumMap<>(MapAssets.class);
    private final EnumMap<MapAssets, Float> defaultViewportHeight = new EnumMap<>(MapAssets.class);
    private final EnumMap<MapAssets, Pair<Float, Float>> playerSizes = new EnumMap<>(MapAssets.class);
    private final EnumMap<MapAssets, Float> playerSpeeds = new EnumMap<>(MapAssets.class);

    /**
     * Changes the current map to the new map.
     * @param map the new map to change to
     * @param transitionKey the key for the spawnpoint
     * @since v2 -- uses {@link MapAssets} to refer to the map instead of {@link String} -- also has a transition key parameter
     */
    public void changeMap(@NotNull MapAssets map, @Nullable String transitionKey) {
        TiledMap copy = currentMap;
        MapAssets old = currentMapEnum;
        currentMapEnum = map; // Set the new map
        currentMap = currentMapEnum.get(); // Load the new map

        // store player scale if it doesn't already exist
        playerSizes.computeIfAbsent(old, k -> Pair.of(player.getWidth(), player.getHeight()));
        playerSpeeds.computeIfAbsent(old, k -> player.getSpeed());
        Pair<Float, Float> size = playerSizes.get(map);
        Float speed = playerSpeeds.get(map);

        // Set the map in the renderer
        renderer.setMap(currentMap);

        TiledMapTileLayer layer = (TiledMapTileLayer) currentMap.getLayers().get(0);
        float mapWidth = (float) layer.getWidth() * layer.getTileWidth();
        float mapHeight = (float) layer.getHeight() * layer.getTileHeight();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        float mapAspectRatio = mapWidth / mapHeight;
        // scale map
        defaultViewportWidth.computeIfAbsent(old, k -> camera.viewportWidth);
        defaultViewportHeight.computeIfAbsent(old, k -> camera.viewportHeight);
        if (defaultViewportWidth.get(map) != null) {
            camera.viewportWidth = defaultViewportWidth.get(map);
            camera.viewportHeight = defaultViewportHeight.get(map);
        } else {
            // use map width and height, but maintain aspect ratio
            if (mapAspectRatio > aspectRatio) {
                camera.viewportHeight = mapHeight;
                camera.viewportWidth = mapHeight * aspectRatio;
            } else {
                camera.viewportWidth = mapWidth;
                camera.viewportHeight = mapWidth / aspectRatio;
            }
        }

        // calculate scale based on scale from previous map to new map
        float scaleX = camera.viewportWidth / defaultViewportWidth.get(old);
        float scaleY = camera.viewportHeight / defaultViewportHeight.get(old);
        // scale player
        if (size != null) {
            player.setSize(size.getLeft(), size.getRight());
        } else {
            // calculate new size using that scale
            player.setSize(player.getWidth() * scaleX, player.getHeight() * scaleY);
            // store
            playerSizes.put(map, Pair.of(player.getWidth(), player.getHeight()));
        }

        if (transitionKey == null) {
            setPlayerPosition(); // Set the location of the player
        } else {
            setPlayerPosition(transitionKey); // Set the location of the player
        }

        if (speed != null) {
            player.setSpeed(speed);
        } else {
            // calculate new speed using that scale
            float scaleCombined = (scaleX + scaleY) / 2;
            player.setSpeed(player.getSpeed() * scaleCombined);
            // store
            playerSpeeds.put(map, player.getSpeed());
        }
        camera.zoom = 1f;

        // Center the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        renderer.getBatch().setProjectionMatrix(camera.combined);

        copy.dispose(); // Dispose the old map
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
        if (!found) setPlayerPosition();
    }


    /**
     * Sets the position of the player on the map.
     */
    private void setPlayerPosition(float x, float y) {
        player = new Player(
            new Sprite(selectedCharacterTexture),
            (TiledMapTileLayer) currentMap.getLayers().get("collisions"),
            currentMap.getLayers().get("spawnpoints"),
            currentMap.getLayers().get("activities")
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

        boolean isNewWorld = currentMapEnum.equals(MapAssets.NEW_WORLD);
        List<String> topLayer = isNewWorld ? List.of("trees", "background") : List.of();

        for (int i = 0; i < currentMap.getLayers().getCount(); i++) {
            MapLayer mapLayer = currentMap.getLayers().get(i);
            if (mapLayer == null) continue;
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
        float mapWidth = (float) layer.getWidth() * layer.getTileWidth();
        float mapHeight = (float) layer.getHeight() * layer.getTileHeight();

        // clamp camera to map
        camera.position.x = Math.min(mapWidth - camera.viewportWidth / 2, Math.max(camera.viewportWidth / 2, camera.position.x));
        camera.position.y = Math.min(mapHeight - camera.viewportHeight / 2, Math.max(camera.viewportHeight / 2, camera.position.y));

        camera.update();
        player.draw(batch);

        for (String layerName : topLayer) {
            MapLayer mapLayer = currentMap.getLayers().get(layerName);
            if (mapLayer == null) continue;
            renderer.renderTileLayer((TiledMapTileLayer) mapLayer);
        }

        batch.end();

        uiBatch.begin();

        final String stats = ("Day: " + Statistics.getDay() + " - Time: " + Statistics.getTime().plusHours(1));
        Label statsLabel = new Label(stats, uiSkin);
        statsLabel.setPosition(Gdx.graphics.getWidth() / 2f - statsLabel.getWidth() / 2f, Gdx.graphics.getHeight() - statsLabel.getHeight());
        statsLabel.draw(uiBatch, 1);

        player.drawHud(uiBatch);

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
        player.dispose();
        uiSkin.dispose();
    }
}
