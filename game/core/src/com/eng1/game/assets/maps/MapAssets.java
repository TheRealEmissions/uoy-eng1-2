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

/**
 * The {@link MapAssets} enum represents different map assets in the game.
 * Each enum constant is associated with a specific map file and a transition description.
 * The map file is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
 * The transition description is a string that describes the transition to this map and is shown on the screen.
 * This enum implements the {@link Assets} interface with {@link TiledMap} as the asset type.
 */
public enum MapAssets implements Assets<TiledMap> {
    /**
     * HOME is an enum constant representing the player's home location in the game.
     * The map file for this location is "maps/map8 (home)/home.tmx", which is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
     * The transition description is "go into your house", which is shown on the screen when the player transitions to this location.
     */
    HOME(
        () -> MapLoader.load(HOME_MAP_PATH),
        "go into your house"
    ),
    /**
     * GYM is an enum constant representing the Gym location in the game.
     * The map file for this location is "maps/map9 (gym)/gym.tmx", which is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
     * The transition description is "go into the Gym", which is shown on the screen when the player transitions to this location.
     */
    GYM(
        () -> MapLoader.load(GYM_MAP_PATH),
        "go into the Gym"
    ),
    /**
     * CS_BUILDING is an enum constant representing the Computer Science Building location in the game.
     * The map file for this location is "maps/map10 (cs-building)/computer-science-building.tmx", which is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
     * The transition description is "go into the Computer Science Building", which is shown on the screen when the player transitions to this location.
     */
    CS_BUILDING(
        () -> MapLoader.load(CS_BUILDING_MAP_PATH),
        "go into the Computer Science Building"
    ),
    /**
     * PIAZZA is an enum constant representing the Piazza location in the game.
     * The map file for this location is "maps/map11 (piazza)/piazza.tmx", which is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
     * The transition description is "go into the Piazza", which is shown on the screen when the player transitions to this location.
     */
    PIAZZA(
        () -> MapLoader.load(PIAZZA_MAP_PATH),
        "go into the Piazza"
    ),
    /**
     * NEW_WORLD is an enum constant representing the Campus East location in the game.
     * The map file for this location is "maps/newWorldMap/newWorldMap.tmx", which is loaded into a {@link TiledMap} object when the {@link #get()} method is invoked.
     * The transition description is "go to Campus East", which is shown on the screen when the player transitions to this location.
     */
    NEW_WORLD(
        () -> MapLoader.load(NEW_WORLD_MAP_PATH),
        "go to Campus East"
    );

    /**
     * A {@link List} of {@link TiledMap} objects that have been loaded. This is used to keep track of all loaded maps so they can be disposed when no longer needed.
     */
    private final List<TiledMap> loadedMaps = new ArrayList<>();

    /**
     * A {@link Supplier} of {@link TiledMap} objects. This is used to load the map associated with each enum constant when the {@link #get()} method is invoked.
     */
    private final Supplier<TiledMap> map;

    /**
     * The description of the transition to this map. This is shown on the screen when the player transitions to the location represented by each enum constant.
     */
    @Getter
    private final String transitionDescription;

    /**
     * Constructor for the {@link MapAssets} enum.
     *
     * @param map A {@link Supplier} of {@link TiledMap} objects. This is used to load the map associated with each enum constant when the {@link #get()} method is invoked.
     * @param transitionDescription The description of the transition to this map. This is shown on the screen when the player transitions to the location represented by each enum constant.
     */
    MapAssets(Supplier<TiledMap> map, String transitionDescription) {
        this.map = map;
        this.transitionDescription = transitionDescription;
    }

    /**
     * Retrieves the {@link TiledMap} object associated with this enum constant.
     * The map is loaded using the {@link Supplier} object stored in the {@link #map} field.
     * The loaded map is added to the {@link #loadedMaps} list for tracking and later disposal.
     *
     * @return The loaded {@link TiledMap} object.
     */
    public TiledMap get() {
        TiledMap map = this.map.get();
        loadedMaps.add(map);
        return map;
    }

    /**
     * Disposes of all loaded {@link TiledMap} objects associated with this enum constant.
     * This method iterates over the {@link #loadedMaps} list and calls the {@link TiledMap#dispose()} method on each {@link TiledMap} object.
     * After all maps have been disposed, the {@link #loadedMaps} list is cleared.
     */
    @Override
    public void dispose() {
        for (TiledMap map : loadedMaps) {
            map.dispose();
        }
        loadedMaps.clear();
    }

    /**
     * Disposes of a specific loaded {@link TiledMap} object associated with this enum constant.
     * This method calls the {@link TiledMap#dispose()} method on the provided {@link TiledMap} object and removes it from the {@link #loadedMaps} list.
     *
     * @param asset The {@link TiledMap} object to be disposed.
     */
    @Override
    public void dispose(@NotNull TiledMap asset) {
        asset.dispose();
        loadedMaps.remove(asset);
    }

    /**
     * Disposes of all loaded {@link TiledMap} objects for all enum constants.
     * This method iterates over all enum constants and calls the {@link #dispose()} method on each one.
     * This effectively disposes of all loaded maps associated with all enum constants.
     */
    public static void disposeAll() {
        for (MapAssets asset : values()) {
            asset.dispose();
        }
    }
}
