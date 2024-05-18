package com.eng1.game.game.player;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class Score {
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
}
