package com.eng1.game.assets.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.function.Supplier;

public enum ImageAssets {
    MAIN_MENU_TITLE(() -> new Texture(Gdx.files.internal("images/main_menu_title.png")));

    private final Supplier<Texture> texture;

    ImageAssets(Supplier<Texture> texture) {
        this.texture = texture;
    }

    public Texture get() {
        return texture.get();
    }
}
