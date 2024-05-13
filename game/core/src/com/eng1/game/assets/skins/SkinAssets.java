package com.eng1.game.assets.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.function.Supplier;

public enum SkinAssets {
    UI(() -> new Skin(Gdx.files.internal("skin/uiskin.json")));

    private final Supplier<Skin> skin;

    SkinAssets(Supplier<Skin> skin) {
        this.skin = skin;
    }

    public Skin get() {
        return skin.get();
    }
}
