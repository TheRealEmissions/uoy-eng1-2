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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Play implements Screen {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TextureAtlas playerAtlas;
    private Player player;

    @Override
    public void show() {
        AssetManager assetManager = new AssetManager();

        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("maps/map1/map1.tmx", TiledMap.class);
        assetManager.finishLoading();
        //loads the map and sets camera size to be correct
        TiledMap map = assetManager.get("maps/map1/map1.tmx");

        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(map);

//        playerAtlas = new TextureAtlas("characterAnimation/playerCharacter1.png");
//        Animation still, left, right;
//        still = new Animation(1 / 2f, playerAtlas.findRegions("still"));
//        left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
//        right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
//        still.setPlayMode(Animation.PlayMode.LOOP);
//        left.setPlayMode(Animation.PlayMode.LOOP);
//        right.setPlayMode(Animation.PlayMode.LOOP);
// still, left, right,
        player =  new Player(new Sprite(new Texture("playerCharacters/playerCharacter1.png")), (TiledMapTileLayer) map.getLayers().get(0));
        player.setPosition(65 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 56) * player.getCollisionLayer().getTileHeight());

        Gdx.input.setInputProcessor(player);
    }
;
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
        renderer.dispose();
        playerAtlas.dispose();
    }
}
