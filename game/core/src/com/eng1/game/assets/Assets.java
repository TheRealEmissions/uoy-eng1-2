package com.eng1.game.assets;

import org.jetbrains.annotations.NotNull;

public interface Assets<T> {
    T get();
    void dispose();
    void dispose(@NotNull T asset);
}
