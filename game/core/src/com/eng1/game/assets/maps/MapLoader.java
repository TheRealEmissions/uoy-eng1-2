package com.eng1.game.assets.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class MapLoader {
    private static final TmxMapLoader mapLoader = new TmxMapLoader();

    public static TmxMapLoader get() {
        return mapLoader;
    }
}
