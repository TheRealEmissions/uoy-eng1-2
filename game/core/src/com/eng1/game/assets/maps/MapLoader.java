package com.eng1.game.assets.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import lombok.experimental.UtilityClass;

/**
 * This is a utility class, enforced by {@link UtilityClass} annotation, that provides methods for loading {@link TiledMap} objects.
 * It uses the {@link TmxMapLoader} class from the libGDX library to load {@link TiledMap} objects from TMX files.
 * The class is final and cannot be instantiated or subclassed.
 */
@UtilityClass
public final class MapLoader {
    /**
     * A static final instance of the {@link TmxMapLoader} class from the libGDX library.
     * This instance is used to load {@link TiledMap} objects from TMX files.
     * Being static, it is shared among all instances of this class.
     * Being final, it cannot be reassigned after initialization.
     */
    private static final TmxMapLoader mapLoader = new TmxMapLoader();

    /**
     * Returns the static instance of the {@link TmxMapLoader} class.
     * This method provides access to the {@link TmxMapLoader} instance that is used to load {@link TiledMap} objects.
     *
     * @return The static {@link TmxMapLoader} instance.
     */
    public static TmxMapLoader get() {
        return mapLoader;
    }

    /**
     * Loads a {@link TiledMap} object from a specified path.
     * This method uses the static {@link TmxMapLoader} instance, {@link #mapLoader}, to load a {@link TiledMap} object from a TMX file located at the provided path.
     *
     * @param path The path to the TMX file to be loaded.
     * @return The loaded {@link TiledMap} object.
     */
    public static TiledMap load(String path) {
        return mapLoader.load(path);
    }
}
