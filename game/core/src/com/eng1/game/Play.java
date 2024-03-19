package com.eng1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.List;
import java.util.Arrays;

/**
 * The Play class represents the screen where the gameplay takes place.
 * It implements the Screen interface provided by LibGDX.
 */
public class Play implements Screen {
    private static OrthogonalTiledMapRenderer renderer;

    private static OrthographicCamera camera;
    private static AssetManager assetManager = new AssetManager();
    private TextureAtlas playerAtlas;
    private static Player player;
    private static TiledMap currentMap;
    private static TiledMap oldMap;
    private static String currentMapPath = "maps/map8/home.tmx";
    private static String oldMapPath = "";
    private static final List<String> scaledMaps = Arrays.asList("maps/map8/home.tmx","maps/map9/gym.tmx");
    private static final List<String> largeScaledMaps = Arrays.asList("maps/map10/computer-science-building.tmx", "maps/map11/piazza.tmx");
    private static String selectedCharacter;
    BitmapFont displayDateTime = new BitmapFont();
    /**
     * Constructor for the Play class.
     * Initializes the camera.
     */
    public Play() {
        //create activities
        Activity.createActivities();
        // Initialize camera here
        camera = new OrthographicCamera();
    }

    /**
     * Loads a TiledMap from the given path.
     * @param path The path of the TiledMap file to load.
     */
    private void loadMap(String path) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(path, TiledMap.class);
        assetManager.finishLoading();
        currentMapPath = path;
        currentMap = assetManager.get(currentMapPath, TiledMap.class);
        setPlayerPosition();
    }

    /**
     * Changes the current map to the one specified by the given path.
     * @param path The path of the new map.
     */
    static void changeMap(String path) {
        currentMap.dispose(); // Dispose the old map

        // Change the current and old map variables
        oldMap = currentMap;
        oldMapPath = currentMapPath;
        currentMapPath = path;

        assetManager.load(path, TiledMap.class); // Load the new map
        assetManager.finishLoading();
        currentMap = assetManager.get(path, TiledMap.class);

        // Set the map in the renderer
        renderer.setMap(currentMap);
        setPlayerPosition(); // Set the location of the player

        // Check if the new map requires a different zoom level
        if (scaledMaps.contains(currentMapPath)) {
            // Set a different zoom level for scaled maps
            camera.zoom = 0.35f; // You can adjust this value as needed
        } else if (largeScaledMaps.contains(currentMapPath)) {
            camera.zoom = 0.5f; // Default zoom level for other maps
        } else {
            camera.zoom = 1f;
        }

        // Center the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }


    /**
     * Sets the selected character for the player.
     * @param character The selected character.
     */
    public static void setSelectedCharacter(String character) {
        selectedCharacter = character;
    }

    /**
     * Sets the position of the player based on the current and old map paths.
     */
    private static void setPlayerPosition() {
        // Initialize the player based on the selected character
        if (selectedCharacter.equals("Character1")) {
            player =  new Player(new Sprite(new Texture("playerCharacters/playerCharacter1.png")), (TiledMapTileLayer) currentMap.getLayers().get(0));
        } else if (selectedCharacter.equals("Character2")) {
            player =  new Player(new Sprite(new Texture("playerCharacters/playerCharacter2.png")), (TiledMapTileLayer) currentMap.getLayers().get(0));
        } else if (selectedCharacter.equals("Character3")) {
            player =  new Player(new Sprite(new Texture("playerCharacters/playerCharacter3.png")), (TiledMapTileLayer) currentMap.getLayers().get(0));
        }

        /**
         * Sets the position of the player based on the current and old map paths.
         * Various cases are handled to set the player position based on the current and old map paths.
         * For example, for the case (maps/map1/map1.tmx), the default position is (65, 57).
         * Then if a player comes from map2.tmx, the position is set to (115, 57), and so on.
         */
        switch (currentMapPath) {
            case ("maps/map1/map1.tmx"):
                switch (oldMapPath) {
                    case ("maps/map2/map2.tmx"):
                        player.setPosition(115 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map3/map3.tmx"):
                        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map4/map4.tmx"):
                        player.setPosition(67 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 5) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map8/home.tmx"):
                        player.setPosition(105 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 53) * player.getCollisionLayer().getTileHeight());
                        break;
                }
                break;
            case ("maps/map2/map2.tmx"):
                switch (oldMapPath) {
                    case ("maps/map1/map1.tmx"):
                        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map9/gym.tmx"):
                        player.setPosition(105 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                }
                break;
            case ("maps/map3/map3.tmx"):
                switch (oldMapPath) {
                    case ("maps/map1/map1.tmx"):
                        player.setPosition(115 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map5/map5.tmx"):
                        player.setPosition(62 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 5) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map6/map6.tmx"):
                        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map11/piazza.tmx"):
                        player.setPosition(30 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 54) * player.getCollisionLayer().getTileHeight());
                        break;

                }
                break;
            case ("maps/map4/map4.tmx"):
                switch (oldMapPath) {
                    case ("maps/map1/map1.tmx"):
                        player.setPosition(67 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 64) * player.getCollisionLayer().getTileHeight());
                        break;

                }
                break;
            case ("maps/map5/map5.tmx"):
                switch (oldMapPath) {
                    case ("maps/map3/map3.tmx"):
                        player.setPosition(62 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 66) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map10/computer-science-building.tmx"):
                        player.setPosition(45 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 65) * player.getCollisionLayer().getTileHeight());
                        break;
                }
                break;
            case ("maps/map6/map6.tmx"):
                switch (oldMapPath) {
                    case ("maps/map3/map3.tmx"):
                        player.setPosition(116 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map7/map7.tmx"):
                        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                }
                break;
            case ("maps/map7/map7.tmx"):
                switch (oldMapPath) {
                    case ("maps/map6/map6.tmx"):
                        player.setPosition(116 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                }
                break;
            case ("maps/map8/home.tmx"):
                switch (oldMapPath) {
                    case (""):
                    case ("maps/map1/map1.tmx"):
                        player.setPosition(56 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 40) * player.getCollisionLayer().getTileHeight());
                        player.setScale(1); // Set size to 1
                        player.setSpeed(60 * 1.7f); // Set speed to 5
                        break;
                }
                break;
            case ("maps/map9/gym.tmx"):
                switch (oldMapPath) {
                    case ("maps/map2/map2.tmx"):
                        player.setPosition(60 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 41) * player.getCollisionLayer().getTileHeight());
                        player.setScale(1); // Set size to 1
                        player.setSpeed(60 * 1.7f); // Set speed to 5
                        break;
                }
                break;
            case ("maps/map10/computer-science-building.tmx"):
                switch (oldMapPath) {
                    case ("maps/map5/map5.tmx"):
                        player.setPosition(60 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 45) * player.getCollisionLayer().getTileHeight());
                        player.setScale(1); // Set size to 1
                        player.setSpeed(60 * 1.7f); // Set speed to 5
                        break;
                }
                break;
            case ("maps/map11/piazza.tmx"):
                switch (oldMapPath) {
                    case ("maps/map3/map3.tmx"):
                        player.setPosition(58 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 48) * player.getCollisionLayer().getTileHeight());
                        player.setScale(1); // Set size to 1
                        player.setSpeed(60 * 1.7f); // Set speed to 5
                        break;
                }
                break;
        }
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void show() {
        loadMap(currentMapPath);
        // sets camera size to be correct

        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(currentMap);

//        playerAtlas = new TextureAtlas("characterAnimation/playerCharacter1.png");
//        Animation still, left, right;
//        still = new Animation(1 / 2f, playerAtlas.findRegions("still"));
//        left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
//        right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
//        still.setPlayMode(Animation.PlayMode.LOOP);
//        left.setPlayMode(Animation.PlayMode.LOOP);
//        right.setPlayMode(Animation.PlayMode.LOOP);
//        still, left, right,

    }
    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor( 0, 0, 0, 1 );
        Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        renderer.setView(camera);
        renderer.render();
        renderer.getBatch().begin();
        if (scaledMaps.contains(currentMapPath)) {
            // Set a different zoom level for scaled maps
            displayDateTime.getData().setScale(1); // Adjust the scale as needed
            displayDateTime.draw(renderer.getBatch(), ("Day: " + GameStats.getDay() + " Time: " + GameStats.getTime() + " Energy: " + GameStats.getEnergy()), 630, 725);
        } else if (largeScaledMaps.contains(currentMapPath)) {
            displayDateTime.getData().setScale(1); // Adjust the scale as needed
            displayDateTime.draw(renderer.getBatch(), ("Day: " + GameStats.getDay() + " Time: " + GameStats.getTime() + " Energy: " + GameStats.getEnergy()), 530, 780);
        } else {
            displayDateTime.getData().setScale(2); // Adjust the scale as needed
            displayDateTime.draw(renderer.getBatch(), ("Day: " + GameStats.getDay() + " Time: " + GameStats.getTime() + " Energy: " + GameStats.getEnergy()), 12, 1070);
        }
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
//
        if (scaledMaps.contains(currentMapPath)) {
            // Set a different zoom level for scaled maps
            camera.zoom = 0.35f; // You can adjust this value as needed
        } else if (largeScaledMaps.contains(currentMapPath)) {
            camera.zoom = 0.5f; // Default zoom level for other maps
        } else {
            camera.zoom = 1f;
        }

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
//        dispose();
    }

    @Override
    public void dispose() {
//        assetManager.dispose();
//        renderer.dispose();
//        playerAtlas.dispose();
    }


}
