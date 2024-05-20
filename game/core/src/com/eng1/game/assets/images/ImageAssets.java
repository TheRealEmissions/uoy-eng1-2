package com.eng1.game.assets.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

import static com.eng1.game.assets.images.ImageAssetPaths.*;

public enum ImageAssets implements Assets<Texture> {
    MAIN_MENU_TITLE(() -> new Texture(Gdx.files.internal(MAIN_MENU_TITLE_PATH))),
    NEW_WORLD_MAP_OVERVIEW(() -> new Texture(Gdx.files.internal(NEW_WORLD_MAP_OVERVIEW_PATH))),
    PLAYER_CHARACTER_1(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_1_PATH))),
    PLAYER_CHARACTER_2(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_2_PATH))),
    PLAYER_CHARACTER_3(() -> new Texture(Gdx.files.internal(PLAYER_CHARACTER_3_PATH)));

    private final List<Texture> loadedTextures = new ArrayList<>();
    private final Supplier<Texture> texture;

    ImageAssets(Supplier<Texture> texture) {
        this.texture = texture;
    }

    @Override
    public Texture get() {
        Texture texture = this.texture.get();
        loadedTextures.add(texture);
        return texture;
    }

    @Override
    public void dispose() {
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
        loadedTextures.clear();
    }

    @Override
    public void dispose(@NotNull Texture asset) {
        asset.dispose();
        loadedTextures.remove(asset);
    }

    public static void disposeAll() {
        for (ImageAssets asset : values()) {
            asset.dispose();
        }
    }
}
