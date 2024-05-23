package com.eng1.game.game.achievement;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

/**
 * This enum represents the different achievements that can be accomplished in the game.
 * Each achievement has a score, a description, and a reference to an {@link Achievement} object.
 * This enum provides methods to get the name of the achievement, check if it has been achieved,
 * and get an achievement from a string.
 */
@Getter
public enum Achievements {
    /**
     * Represents the achievement of being active at least 3 times in the week.
     */
    FITNESS_FANATIC(new FitnessFanaticAchievement(), 4, "Active at least 3 times in the week"),
    /**
     * Represents the achievement of being active at least 5 times in the week.
     */
    DREAMWEAVER(new DreamweaverAchievement(),2, "Daydreamed more than 4 times in the week"),
    /**
     * Represents the achievement of being active at least 7 times in the week.
     */
    SNACK_MASTER(new SnackMasterAchievement(),1, "Snacked 5 out of 7 days in the week"),
    /**
     * Represents the achievement of being active at least 10 times in the week.
     */
    SCHOLARLY_SPRINTER(new ScholarlySprinterAchievement(),6, "Studying once every day in the week"),
    /**
     * Represents the achievement of being active at least 15 times in the week.
     */
    WELL_ROUNDED(new WellRoundedAchievement(),2, "Participated in all activities in the week"),
    /**
     * Represents the achievement of being active at least 20 times in the week.
     */
    TEACHERS_PET(new TeachersPetAchievement(),3, "Went to teaching hours more than twice in a week"),
    /**
     * Represents the achievement of being active at least 25 times in the week.
     */
    SOCIAL_BUTTERFLY(new SocialButterflyAchievement(), 2, "Socialized with friends at least 3 times in the week");

    /**
     * Floating point number representing the percentage added on top of the players score at the end of the game
     * if they have achieved this achievement.
     */
    private final float score;

    /**
     * A brief description of the achievement.
     * This is a string that provides a short explanation of what the achievement is about.
     */
    private final String description;

    /**
     * A reference to the {@link Achievement} object associated with this enum constant.
     * This is used to check if the achievement has been achieved and to perform any other operations related to the achievement.
     */
    private final Achievement achievementRef;

    /**
     * Constructor for the {@link Achievements} enum.
     *
     * @param achievementRef  The {@link Achievement} object associated with this enum constant.
     * @param addScorePercent The percentage added on top of the player's score at the end of the game if they have achieved this achievement.
     * @param description     A brief description of the achievement.
     */
    Achievements(Achievement achievementRef, @Range(from=0, to=100) float addScorePercent, String description) {
        this.achievementRef = achievementRef;
        this.score = addScorePercent;
        this.description = description;
    }

    /**
     * Returns the name of the achievement in a human-readable format.
     *
     * This method takes the enum constant name, splits it by underscores, and capitalises the first letter of each word.
     * The words are then joined together with spaces to form a string that represents the name of the achievement.
     *
     * @return A string representing the name of the achievement in a human-readable format.
     */
    public String getName() {
        return Arrays.stream(name().split("_")).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase()).reduce((s1, s2) -> s1 + " " + s2).orElse("");
    }

    /**
     * Checks if the achievement has been achieved.
     *
     * This method calls {@link Achievement#hasAchieved()} on the {@link Achievement} object associated with this enum constant.
     * The method returns true if the achievement has been achieved, and false otherwise.
     *
     * @return A boolean indicating whether the achievement has been achieved.
     */
    public boolean hasAchieved() {
        return achievementRef.hasAchieved();
    }

    /**
     * Converts a string to an {@link Achievements} enum constant.
     *
     * This method takes a string as input and checks if it matches the name of any of the enum constants in the {@link Achievements} enum.
     * The comparison is case-insensitive.
     * If a match is found, the corresponding enum constant is returned.
     * If the input string is null or empty, or if no match is found, the method returns null.
     *
     * @param string The string to be converted to an {@link Achievements} enum constant.
     * @return The {@link Achievements} enum constant that matches the input string, or null if the input string is null, empty, or does not match any enum constant.
     */
    public static @Nullable Achievements fromString(String string) {
        if (string == null || string.isEmpty()) return null;
        for (Achievements achievement : values()) {
            if (achievement.name().equalsIgnoreCase(string)) {
                return achievement;
            }
        }
        return null;
    }
}
