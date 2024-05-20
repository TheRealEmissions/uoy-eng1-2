package com.eng1.game.utils;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
public class Pair<T, U> {
    private final T left;
    private final U right;

    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static <T, U> @NotNull Pair<T, U> of(T left, U right) {
        return new Pair<>(left, right);
    }
}
