package com.eng1.game.game.activity;

import com.eng1.game.game.player.Statistics;
import com.eng1.game.utils.Pair;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * This enum represents different activities that a player can engage in.
 * Each activity has a set of effects on the player's statistics, which can either increase, decrease, or reset certain statistics.
 * The effects of each activity are represented as {@link Pair} of {@link Statistics.PlayerStatistics} and {@link Statistics.Effect}.
 *
 * The enum provides methods to get the effect of an activity on a specific statistic, and to get the index of a specific statistic in the effects array.
 * It also provides a method to get an activity from a string representation.
 */
@Getter
public enum Activities {
    STUDY(
        Pair.of(Statistics.PlayerStatistics.STUDY, Statistics.Effect.INCREASE),
        Pair.of(Statistics.PlayerStatistics.ENERGY, Statistics.Effect.DECREASE),
        Pair.of(Statistics.PlayerStatistics.HAPPINESS, Statistics.Effect.DECREASE)
    ),
    SLEEP(
        Pair.of(Statistics.PlayerStatistics.ENERGY, Statistics.Effect.RESET),
        Pair.of(Statistics.PlayerStatistics.STUDY, Statistics.Effect.RESET)
    ),
    NAP(
        Pair.of(Statistics.PlayerStatistics.ENERGY, Statistics.Effect.INCREASE)
    ),
    EAT(
        Pair.of(Statistics.PlayerStatistics.ENERGY, Statistics.Effect.INCREASE)
    ),
    RELAX(
        Pair.of(Statistics.PlayerStatistics.ENERGY, Statistics.Effect.DECREASE),
        Pair.of(Statistics.PlayerStatistics.HAPPINESS, Statistics.Effect.INCREASE)
    );

    /**
     * An array of {@link Pair} representing the effects of an activity on player's statistics.
     * Each pair consists of a {@link Statistics.PlayerStatistics} and an {@link Statistics.Effect}.
     * The player statistic is the statistic that the activity affects, and the effect is how the activity affects the statistic.
     * For example, the {@link #STUDY} activity has an effect of {@link Statistics.Effect#INCREASE} on {@link Statistics.PlayerStatistics#STUDY}, and an effect of {@link Statistics.Effect#DECREASE} on the {@link Statistics.PlayerStatistics#ENERGY} and {@link Statistics.PlayerStatistics#HAPPINESS} statistics.
     */
    private final Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects;

    /**
     * Constructor for the {@link Activities} enum.
     *
     * This constructor takes in an array of {@link Pair}, where each pair represents the effect of an activity on a player's statistic.
     * Each pair consists of a {@link Statistics.PlayerStatistics} and an {@link Statistics.Effect}.
     * The player statistic is the statistic that the activity affects, and the effect is how the activity affects the statistic.
     *
     * @param effects An array of {@link Pair} representing the effects of an activity on player's statistics.
     */
    @SafeVarargs
    Activities(Pair<Statistics.PlayerStatistics, Statistics.Effect>... effects) {
        this.effects = effects;
    }

    /**
     * Converts a string representation of an activity into an {@link Activities} enum.
     *
     * This method takes a string as input and checks if it matches the name of any of the {@link Activities} enums (case insensitive).
     * If a match is found, the corresponding {@link Activities} enum is returned.
     * If the input string is null, empty, or does not match any {@link Activities} enum, null is returned.
     *
     * @param string The string representation of an activity.
     * @return The corresponding {@link Activities} enum if a match is found, null otherwise.
     */
    public static @Nullable Activities fromString(String string) {
        if (string == null || string.isEmpty()) return null;
        for (Activities activity : values()) {
            if (activity.name().equalsIgnoreCase(string)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * Retrieves the effect of the activity on a specific player statistic.
     *
     * This method iterates over the effects array and checks if the player statistic of each effect matches the input statistic.
     * If a match is found, the effect of the activity on the matched statistic is returned.
     * If no match is found, null is returned.
     *
     * @param statistic The player statistic to get the effect for.
     * @return The effect of the activity on the input statistic if a match is found, null otherwise.
     */
    public @Nullable Statistics.Effect getEffect(Statistics.PlayerStatistics statistic) {
        for (Pair<Statistics.PlayerStatistics, Statistics.Effect> effect : effects) {
            if (effect.getLeft().equals(statistic)) {
                return effect.getRight();
            }
        }
        return null;
    }

    /**
     * Retrieves the index of a specific player statistic in the effects array.
     *
     * This method iterates over the effects array and checks if the player statistic of each effect matches the input statistic.
     * If a match is found, the index of the matched statistic in the effects array is returned.
     * If no match is found, -1 is returned.
     *
     * @param statistic The player statistic to get the index for.
     * @return The index of the input statistic in the effects array if a match is found, -1 otherwise.
     */
    public int indexOf(Statistics.PlayerStatistics statistic) {
        for (int i = 0; i < effects.length; i++) {
            if (effects[i].getLeft().equals(statistic)) {
                return i;
            }
        }
        return -1;
    }
}
