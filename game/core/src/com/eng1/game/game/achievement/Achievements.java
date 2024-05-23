package com.eng1.game.game.achievement;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

@Getter
public enum Achievements {
    FITNESS_FANATIC(new FitnessFanaticAchievement(), 4, "Active at least 3 times in the week"),
    DREAMWEAVER(new DreamweaverAchievement(),2, "Daydreamed more than 4 times in the week"),
    SNACK_MASTER(new SnackMasterAchievement(),1, "Snacked 5 out of 7 days in the week"),
    SCHOLARLY_SPRINTER(new ScholarlySprinterAchievement(),6, "Studying once every day in the week"),
    WELL_ROUNDED(new WellRoundedAchievement(),2, "Participated in all activities in the week"),
    TEACHERS_PET(new TeachersPetAchievement(),3, "Went to teaching hours more than twice in a week"),
    SOCIAL_BUTTERFLY(new SocialButterflyAchievement(), 2, "Socialized with friends at least 3 times in the week");

    private final float score;
    private final String description;
    private final Achievement achievementRef;

    Achievements(Achievement achievementRef, @Range(from=0, to=100) float addScorePercent, String description) {
        this.achievementRef = achievementRef;
        this.score = addScorePercent;
        this.description = description;
    }

    public String getName() {
        return Arrays.stream(name().split("_")).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()).reduce((s1, s2) -> s1 + " " + s2).orElse("");
    }

    public boolean hasAchieved() {
        return achievementRef.hasAchieved();
    }

    public static @Nullable Achievements fromString(String string) {
        for (Achievements achievement : values()) {
            if (achievement.name().equalsIgnoreCase(string)) {
                return achievement;
            }
        }
        return null;
    }
}
