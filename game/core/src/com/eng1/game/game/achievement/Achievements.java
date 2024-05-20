package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

@Getter
public enum Achievements {
    FITNESS_FANATIC(4, "Active at least 3 times in the week"),
    DREAMWEAVER(2, "Daydreamed more than 4 times in the week"),
    SNACK_MASTER(1, "Snacked 5 out of 7 days in the week"),
    SCHOLARLY_SPRINTER(6, "Studying once every day in the week"),
    WELL_ROUNDED(2, "Completed one of every activity every day"),
    TEACHERS_PET(3, "Went to teaching hours more than twice in a week");

    private final float score;
    private final String description;
    @Setter
    @SuppressWarnings({"NonFinalFieldInEnum"})
    private boolean achieved = false;

    Achievements(@Range(from=0, to=100) float addScorePercent, String description) {
        this.score = addScorePercent;
        this.description = description;
    }

    public String getName() {
        return Arrays.stream(name().split("_")).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()).reduce((s1, s2) -> s1 + " " + s2).orElse("");
    }
}
