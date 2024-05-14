package com.eng1.game.assets.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum MapAssets implements Assets<TiledMap> {
    HOME(() -> MapLoader.get().load("maps/map8 (home)/home.tmx")),
    GYM(() -> MapLoader.get().load("maps/map9 (gym)/gym.tmx")),
    CS_BUILDING(() -> MapLoader.get().load("maps/map10 (cs-building)/computer-science-building.tmx")),
    PIAZZA(() -> MapLoader.get().load("maps/map11 (piazza)/piazza.tmx")),
    NEW_WORLD(() -> MapLoader.get().load("maps/newWorldMap/newWorldMap.tmx"));

    private final List<TiledMap> loadedMaps = new ArrayList<>();
    private final Supplier<TiledMap> map;

    MapAssets(Supplier<TiledMap> map) {
        this.map = map;
    }

    public TiledMap get() {
        TiledMap map = this.map.get();
        loadedMaps.add(map);
        return map;
    }

    @Override
    public void dispose() {
        for (TiledMap map : loadedMaps) {
            map.dispose();
        }
        loadedMaps.clear();
    }

    @Override
    public void dispose(@NotNull TiledMap asset) {
        asset.dispose();
        loadedMaps.remove(asset);
    }

    public static void disposeAll() {
        for (MapAssets asset : values()) {
            asset.dispose();
        }
    }
}
