package com.eng1.game.game.activity;

import com.eng1.game.game.player.Statistics;
import com.eng1.game.utils.Pair;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

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

    private final Pair<Statistics.PlayerStatistics, Statistics.Effect>[] effects;

    @SafeVarargs
    Activities(Pair<Statistics.PlayerStatistics, Statistics.Effect>... effects) {
        this.effects = effects;
    }

    public static @Nullable Activities fromString(String string) {
        for (Activities activity : values()) {
            if (activity.name().equalsIgnoreCase(string)) {
                return activity;
            }
        }
        return null;
    }

    public @Nullable Statistics.Effect getEffect(Statistics.PlayerStatistics statistic) {
        for (Pair<Statistics.PlayerStatistics, Statistics.Effect> effect : effects) {
            if (effect.getLeft().equals(statistic)) {
                return effect.getRight();
            }
        }
        return null;
    }

    public int indexOf(Statistics.PlayerStatistics statistic) {
        for (int i = 0; i < effects.length; i++) {
            if (effects[i].getLeft().equals(statistic)) {
                return i;
            }
        }
        return -1;
    }
}
