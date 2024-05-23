package com.eng1.game.assets.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.eng1.game.assets.skins.SkinAssetPaths.UISKIN_JSON_PATH;

/**
 * Enum representing different types of {@link Skin} assets in the game.
 * Each enum constant is associated with a {@link Supplier} that can be used to create new instances of the {@link Skin}.
 */
public enum SkinAssets implements Assets<Skin> {
    UI(() -> new Skin(Gdx.files.internal(UISKIN_JSON_PATH)));
    /**
     * Associated with a {@link Supplier} that creates a new instance of the {@link Skin} class.
     * The Supplier loads the {@link Skin} from the "skin/uiskin.json" file located in the internal storage.
     */
    UI(() -> new Skin(Gdx.files.internal("skin/uiskin.json")));

    /**
     * A {@link List} of {@link Skin} objects that have been loaded.
     * This list is used to keep track of all the {@link Skin} objects that have been created and loaded by this enum constant.
     * When a {@link Skin} object is created and loaded, it is added to this list.
     * When a {@link Skin} object is disposed, it is removed from this list.
     */
    private final List<Skin> loadedSkins = new ArrayList<>();

    /**
     * A {@link Supplier} of {@link Skin} objects.
     * This {@link Supplier} is used to create new instances of the {@link Skin} class.
     * Each enum constant is associated with a specific {@link Supplier} that creates a {@link Skin} object related to that enum constant.
     */
    private final Supplier<Skin> skin;

    /**
     * Constructor for the {@link SkinAssets} enum.
     * This constructor is used to associate a {@link Supplier} with each enum constant.
     * The {@link Supplier} is used to create new instances of the {@link Skin} class.
     *
     * @param skin A {@link Supplier} that is used to create new instances of the {@link Skin} class.
     */
    SkinAssets(Supplier<Skin> skin) {
        this.skin = skin;
    }

    /**
     * Retrieves a {@link Skin} object.
     * This method uses the {@link Supplier} associated with the enum constant to create a new instance of the {@link Skin} class.
     * The created {@link Skin} object is added to the list of loaded Skins, {@link #loadedSkins}.
     *
     * @return The created {@link Skin} object.
     */
    @Override
    public Skin get() {
        Skin skin = this.skin.get();
        loadedSkins.add(skin);
        return skin;
    }

    /**
     * Disposes of all loaded {@link Skin} objects associated with this enum constant.
     * This method iterates over the list of loaded Skins from {@link #loadedSkins} and calls the {@link Skin#dispose()} method on each {@link Skin} object.
     * After all {@link Skin} objects have been disposed, the list of loaded Skins, {@link #loadedSkins}, is cleared.
     */
    @Override
    public void dispose() {
        for (Skin skin : loadedSkins) {
            skin.dispose();
        }
        loadedSkins.clear();
    }

    /**
     * Disposes of a specific {@link Skin} object associated with this enum constant.
     * This method calls the {@link Skin#dispose()} method on the provided {@link Skin} object and removes it from the list of loaded Skins, {@link #loadedSkins}
     *
     * @param asset The {@link Skin} object to be disposed and removed from the list of loaded Skins, {@link #loadedSkins}
     */
    @Override
    public void dispose(@NotNull Skin asset) {
        asset.dispose();
        loadedSkins.remove(asset);
    }

    /**
     * Disposes of all Skins for all enum constants.
     * This static method iterates over all the enum constants of {@link SkinAssets} and calls the {@link #dispose()} method on each of them.
     * This is used to dispose all the {@link Skin} objects associated with each enum constant.
     */
    public static void disposeAll() {
        for (SkinAssets asset : values()) {
            asset.dispose();
        }
    }
}
