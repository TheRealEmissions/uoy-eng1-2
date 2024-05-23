package com.eng1.game.utils;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The Pair class represents a pair of objects.
 * It is a generic class that can hold a pair of any two types of objects.
 * The {@link Getter} annotation from the Lombok library generates getter methods for the left and right fields.
 *
 * @param <T> the type of the first object in the pair
 * @param <U> the type of the second object in the pair
 */
@Getter
public class Pair<T, U> {
    // The first object in the pair
    private final T left;
    // The second object in the pair
    private final U right;

    /**
     * Constructor for the Pair class.
     * This constructor initializes the pair with the provided values.
     *
     * @param left the first object in the pair
     * @param right the second object in the pair
     */
    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Static factory method for creating a new Pair.
     *
     * @param left the first object in the pair
     * @param right the second object in the pair
     * @return a new Pair containing the provided objects
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static <T, U> @NotNull Pair<T, U> of(T left, U right) {
        return new Pair<>(left, right);
    }
}
