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
 *
 * @since v2 -- renamed from Play to PlayScreen for consistency
 */
public class PlayScreen implements Screen {
    /**
     * The selected character's assets. This is used to load the character's image.
     * It is initially set to null and should be set using the setSelectedCharacter method.
     */
    private static ImageAssets selectedCharacter = null;

    /**
     * The texture of the selected character. This is used to render the character on the screen.
     * It is initially set to null and should be set using the setSelectedCharacter method.
     */
    private static Texture selectedCharacterTexture = null;

    /**
     * Sets the selected character's assets and texture.
     * This method is used to load the character's image and should be called before rendering the character.
     *
     * @param character The character's image assets. This should not be null.
     */
    public static void setSelectedCharacter(@NotNull ImageAssets character) {
        selectedCharacter = character;
        selectedCharacterTexture = selectedCharacter.get();
    }

    /**
     * Renderer for the TiledMap. This is used to draw the map on the screen.
     */
    private OrthogonalTiledMapRenderer renderer;

    /**
     * The current map that is being used in the game. It is initially set to NEW_WORLD.
     * This can be changed using the {@link #changeMap(MapAssets, String)} method.
     */
    private MapAssets currentMapEnum = MapAssets.NEW_WORLD;

    /**
     * The TiledMap object of the current map. This is used for various operations like getting layers, objects etc.
     * It is initially set to the TiledMap object of the currentMapEnum.
     */
    private TiledMap currentMap = currentMapEnum.get();

    /**
     * The camera that is used to view the game. This is used to set the view of the renderer and to move the view around.
     */
    private OrthographicCamera camera = new OrthographicCamera();

    /**
     * The player object. This is used to control the character in the game.
     */
    private Player player;

    /**
     * The font used to display the date and time in the game. This is initialized with a new BitmapFont.
     */
    private final BitmapFont displayDateTime = new BitmapFont();

    /**
     * The batch used to draw the UI elements in the game. This is initialized with a new SpriteBatch.
     */
    private final SpriteBatch uiBatch = new SpriteBatch();

    /**
     * The skin used for the UI elements in the game. This is initialized with the UI skin from the SkinAssets.
     */
    private final Skin uiSkin = SkinAssets.UI.get();

    /**
     * A map that stores the default viewport width for each map in the game.
     * The keys are the MapAssets, which represent the different maps in the game.
     * The values are the default viewport widths for the corresponding map.
     */
    private final EnumMap<MapAssets, Float> defaultViewportWidth = new EnumMap<>(MapAssets.class);

    /**
     * A map that stores the default viewport height for each map in the game.
     * The keys are the MapAssets, which represent the different maps in the game.
     * The values are the default viewport heights for the corresponding map.
     */
    private final EnumMap<MapAssets, Float> defaultViewportHeight = new EnumMap<>(MapAssets.class);

    /**
     * A map that stores the player's size for each map in the game.
     * The keys are the MapAssets, which represent the different maps in the game.
     * The values are pairs of floats representing the player's width and height for the corresponding map.
     */
    private final EnumMap<MapAssets, Pair<Float, Float>> playerSizes = new EnumMap<>(MapAssets.class);

    /**
     * A map that stores the player's speed for each map in the game.
     * The keys are the MapAssets, which represent the different maps in the game.
     * The values are the player's speeds for the corresponding map.
     */
    private final EnumMap<MapAssets, Float> playerSpeeds = new EnumMap<>(MapAssets.class);

    /**
     * Changes the current map to the new map.
     * @param map the new map to change to
     * @param transitionKey the key for the spawnpoint
     * @since v2 -- uses {@link MapAssets} to refer to the map instead of {@link String} -- also has a transition key parameter
     */
    public void changeMap(@NotNull MapAssets map, @Nullable String transitionKey) {
        // Create a copy of the current map
        TiledMap copy = currentMap;

        // Store the current map's enum value
        MapAssets old = currentMapEnum;

        // Set the new map's enum value
        currentMapEnum = map;

        // Load the new map
        currentMap = currentMapEnum.get();

        // Store the player's scale for the old map if it doesn't already exist
        playerSizes.computeIfAbsent(old, k -> Pair.of(player.getWidth(), player.getHeight()));

        // Store the player's speed for the old map if it doesn't already exist
        playerSpeeds.computeIfAbsent(old, k -> player.getSpeed());

        // Get the player's size for the new map
        Pair<Float, Float> size = playerSizes.get(map);

        // Get the player's speed for the new map
        Float speed = playerSpeeds.get(map);

        // Set the new map in the renderer
        renderer.setMap(currentMap);

        TiledMapTileLayer layer = (TiledMapTileLayer) currentMap.getLayers().get(0);
        float mapWidth = (float) layer.getWidth() * layer.getTileWidth();
        float mapHeight = (float) layer.getHeight() * layer.getTileHeight();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        float mapAspectRatio = mapWidth / mapHeight;
        // scale map
        defaultViewportWidth.computeIfAbsent(old, k -> camera.viewportWidth);
        defaultViewportHeight.computeIfAbsent(old, k -> camera.viewportHeight);
        Float defaultViewWidth = defaultViewportWidth.get(map);
        // Check if the default viewport width for the current map is set
        if (defaultViewWidth != null) {
            // If it is, set the camera's viewport width and height to the default values
            camera.viewportWidth = defaultViewWidth;
            camera.viewportHeight = defaultViewportHeight.get(map);
        } else {
            // If it's not, calculate the camera's viewport width and height based on the map's dimensions and aspect ratio
            // This is done to maintain the aspect ratio of the map when it's displayed on the screen

            // Check if the map's aspect ratio is greater than the screen's aspect ratio
            if (mapAspectRatio > aspectRatio) {
                // If it is, set the camera's viewport height to the map's height
                // and calculate the viewport width based on the map's height and the screen's aspect ratio
                camera.viewportHeight = mapHeight;
                camera.viewportWidth = mapHeight * aspectRatio;
            } else {
                // If it's not, set the camera's viewport width to the map's width
                // and calculate the viewport height based on the map's width and the screen's aspect ratio
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

        // render the bottom layer so the player appears on top, for layers we want to render over the player
        // this is rendered after the player is rendered (Gives the impression of 3D while being in a 2D game)
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
        player.update(delta);
        player.draw(batch);

        // render remaining layers here
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
