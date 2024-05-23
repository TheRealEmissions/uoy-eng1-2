package com.eng1.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.eng1.game.game.achievement.Achievements;
import com.eng1.game.game.player.Statistics;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is a utility class that handles the game's scoring system.
 * It provides methods for calculating, saving, and retrieving scores.
 * It also classifies scores into different categories based on the score percentage.
 * This class is not meant to be instantiated.
 */
@UtilityClass
public final class Score {

    /**
     * The name of the file where scores are stored. The file is in JSON format.
     */
    private static final String SCORE_FILE = "scores.json";

    /**
     * An instance of the Json class from the libGDX library. This is used for reading and writing the scores file.
     */
    private static final Json json = new Json();

    /**
     * This method classifies the score percentage into different categories.
     * The categories are as follows:
     * - Less than 40: Fail
     * - 40 to less than 50: Third Class
     * - 50 to less than 60: Lower Second Class
     * - 60 to less than 70: Upper Second Class
     * - 70 to less than 80: First Class
     * - 80 and above: First Class with Distinction
     *
     * @param scorePercentage The score percentage to be classified
     * @return The classification of the score percentage
     */
    @Contract(pure = true)
    public static @NotNull String getClassification(float scorePercentage) {
        if (scorePercentage < 40) {
            return "Fail";
        } else if (scorePercentage >= 40 && scorePercentage < 50) {
            return "Third Class";
        } else if (scorePercentage >= 50 && scorePercentage < 60) {
            return "Lower Second Class";
        } else if (scorePercentage >= 60 && scorePercentage < 70) {
            return "Upper Second Class";
        } else if (scorePercentage >= 70 && scorePercentage < 80) {
            return "First Class";
        } else {
            return "First Class with Distinction";
        }
    }

    /**
     * This method retrieves the top 10 scores from the scores file.
     * It first checks if the scores file exists. If it does, it reads the file and deserializes the JSON content into an ArrayList of ScoreEntry objects.
     * If the file does not exist, it prints a message to the console and continues with an empty ArrayList.
     * The scores are then sorted in descending order.
     * If there are more than 10 scores, it returns only the top 10. Otherwise, it returns all the scores.
     *
     * @return A list of the top 10 scores, or all scores if there are less than 10. Each score is represented by a ScoreEntry object.
     */
    public static @NotNull List<ScoreEntry> getTop10Scores() {
        ArrayList<ScoreEntry> scores = new ArrayList<>();
        FileHandle file = Gdx.files.local(SCORE_FILE);
        // Checks if the score file exists
        if (file.exists()) {
            // If the file exists, it reads the file and deserializes the JSON content into an ArrayList of ScoreEntry objects.
            scores = json.fromJson(ArrayList.class, Score.ScoreEntry.class, file);
        } else {
            // If the file does not exist, it prints a message to the console
            System.out.println("File does not exist: " + SCORE_FILE);
        }
        // The scores are then sorted in descending order.
        scores.sort(Comparator.comparingDouble(ScoreEntry::getScore).reversed());
        // If there are more than 10 scores, it returns only the top 10. Otherwise, it returns all the scores.
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }

    /**
     * This method retrieves the last score from the top 10 scores.
     * It first gets the top 10 scores. If the size of the top scores is less than 10, it returns 0.
     * Otherwise, it returns the score of the last entry in the top scores.
     *
     * @return The last score from the top 10 scores, or 0 if there are less than 10 scores.
     */
    public static int getLastScore() {
        List<Score.ScoreEntry> topScores = Score.getTop10Scores();
        if (topScores.size() < 10) {
            return 0;
        } else {
            return topScores.get(topScores.size() - 1).getScore();
        }
    }

    /**
     * This method saves a new high score to the score file.
     * It first retrieves the top 10 scores and adds the new score to the list.
     * The scores are then sorted in descending order.
     * The updated list of scores is then written back to the score file in JSON format.
     *
     * @param playerName The name of the player who achieved the new high score
     * @param scorePercentage The score percentage achieved by the player
     */
    public static void saveScore(String playerName, int scorePercentage) {
        // Retrieve the top 10 scores
        List<ScoreEntry> scores = getTop10Scores();
        // Add the new score to the list
        scores.add(new ScoreEntry(playerName, scorePercentage));
        // Sort the scores in descending order
        scores.sort(Comparator.comparingDouble(ScoreEntry::getScore).reversed());
        // Get a handle to the score file
        FileHandle file = Gdx.files.local(SCORE_FILE);
        // Write the updated list of scores back to the score file in JSON format
        file.writeString(json.prettyPrint(scores), false);
    }

    /**
     * This method calculates the final score percentage based on player statistics.
     * It first gets the total score from all player statistics and calculates the score percentage based on the maximum possible score.
     * The score percentage is then increased by the score of each achieved achievement.
     * The final score percentage is capped at 100%.
     *
     * @return The final score percentage, capped at 100%
     */
    public static int calculateScorePercentage() {
        // Get the total score from all player statistics
        float scoreTotal = Arrays.stream(Statistics.PlayerStatistics.values())
            .map(Statistics.PlayerStatistics::getTotal)
            .reduce(0f, Float::sum);
        // Get the maximum possible score
        float maxTotal = Statistics.MAX_SCORE;
        // Calculate the score percentage based on the total score and the maximum possible score
        // Capped at 80% of the total score
        float score = (scoreTotal / maxTotal) * 0.8f;

        // Get all achievements
        Achievements[] achievements = Achievements.values();
        // For each achieved achievement, increase the score percentage by the score of the achievement
        for (Achievements achievement : achievements) {
            if (!achievement.hasAchieved()) continue;
            score += achievement.getScore() / 100;
        }

        // Cap the final score percentage at 100%
        score = Math.min(score, 1.0f);

        // Return the final score percentage
        return (int) Math.floor(score * 100);
    }

    /**
     * This is a static inner class that represents a score entry.
     * Each score entry consists of a player's name and their score.
     * It provides a getter method for both the player's name and their score.
     */
    @Getter
    public static class ScoreEntry {
        /**
         * The name of the player who achieved the score.
         */
        private final String playerName;
        /**
         * The score achieved by the player.
         */
        private final int score;

        /**
         * Default constructor that initializes the player's name to an empty string and the score to 0.
         */
        public ScoreEntry() {
            this("", 0);
        }

        /**
         * Constructor that initializes the player's name and score to the provided values.
         *
         * @param playerName The name of the player who achieved the score
         * @param score The score achieved by the player
         */
        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }
    }
}
