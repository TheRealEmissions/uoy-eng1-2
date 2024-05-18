package com.eng1.game.game;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@UtilityClass
public class Score {

    private static final String SCORE_FILE = "com/eng1/game/game/player/scores.txt";

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
     * @return A list of the top 10 scores in the format Name,Score
     */
    public static List<String> getTop10Scores() {
        List<String> scores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Comparator.comparingDouble(Score::extractScore).reversed());
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }

    /**
     * Saves a new high score to the score file.
     *
     * @param playerName      The name of the player
     * @param scorePercentage The score percentage achieved by the player
     */
    public static void saveScore(String playerName, int scorePercentage) {
        List<String> scores = getTop10Scores();
        scores.add(playerName + "," + scorePercentage);
        scores.sort(Comparator.comparingDouble(Score::extractScore).reversed());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE));
            for (String score : scores) {
                writer.write(score);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double extractScore(String scoreEntry) {
        String[] parts = scoreEntry.split(",");
        return Double.parseDouble(parts[1]);
    }
}
