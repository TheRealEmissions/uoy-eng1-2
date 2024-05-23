package com.eng1.game.assets.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

import static com.eng1.game.assets.images.ImageAssetPaths.*;


/**
 * The {@link ImageAssets} enum implements the {@link Assets} interface with {@link Texture} as the type parameter.
 * It provides a way to manage different image assets in the game.
 * Each enum constant represents a different image asset and is associated with a {@link Supplier<Texture>} that
 * loads the corresponding image when needed.
 *
 * The enum also provides methods to dispose of loaded textures to free up memory resources.
 */
public enum ImageAssets implements Assets<Texture> {
    MAIN_MENU_TITLE(() -> new Texture(Gdx.files.internal(MAIN_MENU_TITLE_PATH))),
    NEW_WORLD_MAP_OVERVIEW(() -> new Texture(Gdx.files.internal(NEW_WORLD_MAP_OVERVIEW_PATH))),
    PLAYER_CHARACTER_1(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_1_PATH))),
    PLAYER_CHARACTER_2(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_2_PATH))),
    PLAYER_CHARACTER_3(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_3_PATH)));
    /**
     * MAIN_MENU_TITLE: Represents the main menu title image.
     * The {@link Supplier} associated with this enum constant loads the image from the internal file "images/main_menu_title.png".
     */
    MAIN_MENU_TITLE(() -> new Texture(Gdx.files.internal("images/main_menu_title.png"))),

    /**
     * NEW_WORLD_MAP_OVERVIEW: Represents the new world map overview image.
     * The {@link Supplier} associated with this enum constant loads the image from the internal file "maps/newWorldMap/newWorldMap.png".
     */
    NEW_WORLD_MAP_OVERVIEW(() -> new Texture(Gdx.files.internal("maps/newWorldMap/newWorldMap.png"))),

    /**
     * PLAYER_CHARACTER_1: Represents the first player character image.
     * The {@link Supplier} associated with this enum constant loads the image from the internal file "playerCharacters/playerCharacter1.png".
     */
    PLAYER_CHARACTER_1(() -> new Texture(Gdx.files.internal("playerCharacters/playerCharacter1.png"))),

    /**
     * PLAYER_CHARACTER_2: Represents the second player character image.
     * The {@link Supplier} associated with this enum constant loads the image from the internal file "playerCharacters/playerCharacter2.png".
     */
    PLAYER_CHARACTER_2(() -> new Texture(Gdx.files.internal("playerCharacters/playerCharacter2.png"))),

    /**
     * PLAYER_CHARACTER_3: Represents the third player character image.
     * The {@link Supplier} associated with this enum constant loads the image from the internal file "playerCharacters/playerCharacter3.png".
     */
    PLAYER_CHARACTER_3(() -> new Texture(Gdx.files.internal("playerCharacters/playerCharacter3.png")));

    /**
     * A {@link List} of {@link Texture} objects that have been loaded by this {@link ImageAssets} enum constant.
     * This list is used to keep track of loaded textures so they can be disposed of when no longer needed.
     */
    private final List<Texture> loadedTextures = new ArrayList<>();

    /**
     * A {@link Supplier} that provides a way to load the {@link Texture} associated with this {@link ImageAssets} enum constant.
     * The {@link Supplier} is called when the {@link #get()} method is invoked to load the {@link Texture} if it has not been loaded already.
     */
    private final Supplier<Texture> texture;

    /**
     * Constructor for the {@link ImageAssets} enum.
     *
     * @param texture A {@link Supplier} that provides a way to load the {@link Texture} associated with this {@link ImageAssets} enum constant.
     *                The {@link Supplier} is called when the {@link #get()} method is invoked to load the {@link Texture} if it has not been loaded already.
     */
    ImageAssets(Supplier<Texture> texture) {
        this.texture = texture;
    }

    /**
     * Retrieves the {@link Texture} associated with this {@link ImageAssets} enum constant.
     * If the {@link Texture} has not been loaded already, it is loaded by calling the {@link Supplier} associated with this enum constant.
     * The loaded {@link Texture} is added to the list of loaded textures {@link #loadedTextures} for this enum constant.
     *
     * @return The {@link Texture} associated with this {@link ImageAssets} enum constant.
     */
    @Override
    public Texture get() {
        Texture texture = this.texture.get();
        loadedTextures.add(texture);
        return texture;
    }

    /**
     * Disposes of all loaded textures from {@link #loadedTextures} associated with this {@link ImageAssets} enum constant.
     * This method iterates over the list of loaded textures and calls the {@link Texture#dispose()} method on each {@link Texture} object.
     * After all textures have been disposed of, the list of loaded textures {@link #loadedTextures} is cleared.
     */
    @Override
    public void dispose() {
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
        loadedTextures.clear();
    }

    /**
     * Disposes of a specific {@link Texture} associated with this {@link ImageAssets} enum constant.
     * This method calls the {@link Texture#dispose()} method on the provided {@link Texture} object and removes it from the list of loaded textures {@link #loadedTextures}.
     *
     * @param asset The {@link Texture} to be disposed of.
     */
    @Override
    public void dispose(@NotNull Texture asset) {
        asset.dispose();
        loadedTextures.remove(asset);
    }

    /**
     * Disposes of all loaded textures for all {@link ImageAssets} enum constants.
     * This method iterates over all enum constants and calls the {@link #dispose()} method on each one.
     * This is useful when you want to free up memory resources by disposing of all loaded textures at once.
     */
    public static void disposeAll() {
        for (ImageAssets asset : values()) {
            asset.dispose();
        }
    }
}
