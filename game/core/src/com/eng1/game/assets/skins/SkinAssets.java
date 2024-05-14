package com.eng1.game.assets.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.Assets;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum SkinAssets implements Assets<Skin> {
    UI(() -> new Skin(Gdx.files.internal("skin/uiskin.json")));

    private final List<Skin> loadedSkins = new ArrayList<>();
    private final Supplier<Skin> skin;

    SkinAssets(Supplier<Skin> skin) {
        this.skin = skin;
    }

    @Override
    public Skin get() {
        Skin skin = this.skin.get();
        loadedSkins.add(skin);
        return skin;
    }

    @Override
    public void dispose() {
        for (Skin skin : loadedSkins) {
            skin.dispose();
        }
        loadedSkins.clear();
    }

    @Override
    public void dispose(@NotNull Skin asset) {
        asset.dispose();
        loadedSkins.remove(asset);
    }

    public static void disposeAll() {
        for (SkinAssets asset : values()) {
            asset.dispose();
        }
    }
}
