package com.eng1.game.assets.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.eng1.game.assets.Assets;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.eng1.game.assets.maps.MapAssetPaths.*;

public enum MapAssets implements Assets<TiledMap> {
    HOME(
        () -> MapLoader.load(HOME_MAP_PATH),
        "go into your house"
    ),
    GYM(
        () -> MapLoader.load(GYM_MAP_PATH),
        "go into the Gym"
    ),
    CS_BUILDING(
        () -> MapLoader.load(CS_BUILDING_MAP_PATH),
        "go into the Computer Science Building"
    ),
    PIAZZA(
        () -> MapLoader.load(PIAZZA_MAP_PATH),
        "go into the Piazza"
    ),
    NEW_WORLD(
        () -> MapLoader.load(NEW_WORLD_MAP_PATH),
        "go to Campus East"
    );

    private final List<TiledMap> loadedMaps = new ArrayList<>();
    private final Supplier<TiledMap> map;
    @Getter
    private final String transitionDescription;

    MapAssets(Supplier<TiledMap> map, String transitionDescription) {
        this.map = map;
        this.transitionDescription = transitionDescription;
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
