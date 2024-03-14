package com.eng1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Play implements Screen {
    private static OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private static AssetManager assetManager = new AssetManager();
    private TextureAtlas playerAtlas;
    private static Player player;
    private static TiledMap currentMap;
    private static TiledMap oldMap;
    private static String currentMapPath = "maps/map1/map1.tmx";
    private static String oldMapPath = "";

    private void loadMap(String path) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(path, TiledMap.class);
        assetManager.finishLoading();
        currentMapPath = path;
        currentMap = assetManager.get(currentMapPath, TiledMap.class);
        setPlayerPosition();
    }

    static void changeMap(String path) {
        currentMap.dispose(); //dispose the old map

        //change the current and old map variables
        oldMap = currentMap;
        oldMapPath = currentMapPath;
        currentMapPath = path;

        assetManager.load(path, TiledMap.class); //load the new map
        assetManager.finishLoading();
        currentMap = assetManager.get(path, TiledMap.class);

        renderer.setMap(currentMap); //set the map in your renderer
        setPlayerPosition(); //sets the location of the player
    }

    private static void setPlayerPosition() {
        player =  new Player(new Sprite(new Texture("playerCharacters/playerCharacter1.png")), (TiledMapTileLayer) currentMap.getLayers().get(0));
        switch (currentMapPath) {
            case ("maps/map1/map1.tmx"):
                switch (oldMapPath) {
                    case (""):
                        player.setPosition(65 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map2/map2.tmx"):
                        player.setPosition(115  * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map3/map3.tmx"):
                        player.setPosition(5  * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
                        break;
                    case ("maps/map4/map4.tmx"):
                        player.setPosition(67  * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 5) * player.getCollisionLayer().getTileHeight());
                }
                break;
            case ("maps/map2/map2.tmx"):
                switch (oldMapPath) {
                    case ("maps/map1/map1.tmx"):
                        player.setPosition(5 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
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
                        player.setPosition(5  * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
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
                switch (oldMapPath){
                    case ("maps/map6/map6.tmx"):
                        player.setPosition(116 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 57) * player.getCollisionLayer().getTileHeight());
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
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f , 0);
        camera.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        renderer.dispose();
//        playerAtlas.dispose();
    }
}
