package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FitnessFanaticAchievement implements Achievement {
    //Fitness Fanatic
    //Achieve by being active at least 3 times a week.
    //(Do cardio, lift some weights, play football, play basketball)
    //4%

    private int total = 0;

    @Override
    public boolean hasAchieved() {
        return total >= 3;
    }

    public void exercise() {
        total++;
    }

    public void reset() {
        total = 0;
    }

}
