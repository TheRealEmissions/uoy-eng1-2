package com.eng1.game.assets;

import org.jetbrains.annotations.NotNull;

/**
 * This interface defines the methods that must be implemented by all asset managers, which in this game are enums.
 * The type of asset is represented by the generic parameter {@link T}.
 *
 * The interface includes methods for getting an instance of the asset {@link #get()}, disposing of all instances of the asset {@link #dispose()},
 * and disposing of a specific instance of the asset {@link #dispose(T)}.
 *
 * @param <T> The type of asset that the implementing class represents.
 */
public interface Assets<T> {
    T get();
    void dispose();
    void dispose(@NotNull T asset);
}
