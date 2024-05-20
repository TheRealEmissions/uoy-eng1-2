package com.eng1.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@UtilityClass
public class Score {

    private static final String SCORE_FILE = "scores.json"; // Change file extension to .json

    private static final Json json = new Json(); // Create a Json instance

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
     * Retrieves the top 10 scores from the scores file.
     *
     * @return A list of the top 10 scores in the format Name,Score
     */
    public static List<ScoreEntry> getTop10Scores() {
        ArrayList<ScoreEntry> scores = new ArrayList<>();
        FileHandle file = Gdx.files.local(SCORE_FILE);
        if (file.exists()) {
            // Load scores from the JSON file
            scores = json.fromJson(ArrayList.class, Score.ScoreEntry.class, file);
        } else {
            System.out.println("File does not exist: " + SCORE_FILE);
        }
        scores.sort(Comparator.comparingDouble(ScoreEntry::getScore).reversed());
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }


    /**
     * Saves a new high score to the score file.
     *
     * @param playerName      The name of the player
     * @param scorePercentage The score percentage achieved by the player
     */
    public static void saveScore(String playerName, int scorePercentage) {
        List<ScoreEntry> scores = getTop10Scores();
        scores.add(new ScoreEntry(playerName, scorePercentage));
        scores.sort(Comparator.comparingDouble(ScoreEntry::getScore).reversed());
        FileHandle file = Gdx.files.local(SCORE_FILE);
        // Write scores to the JSON file
        file.writeString(json.prettyPrint(scores), false);
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class ScoreEntry {
        private final String playerName;
        private final int score;

        public ScoreEntry() {
            this("", 0);
        }

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }
    }
}
