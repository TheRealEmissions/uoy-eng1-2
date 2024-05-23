package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DreamweaverAchievement implements Achievement {
    //Dreamweaver
    //Achieve by daydreaming more than 4 times a week
    //(Daydream)
    //2%

    private int total = 0;

    @Override
    public boolean hasAchieved() {
        return total > 4;
    }

    public void daydream() {
        total++;
    }

    public void reset() {
        total = 0;
    }
}
