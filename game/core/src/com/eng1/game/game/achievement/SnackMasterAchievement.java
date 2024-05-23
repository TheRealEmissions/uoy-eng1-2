package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SnackMasterAchievement implements Achievement {
    //Snack Master
    //Achieve by snacking 5 out of 7 days.
    //(Have a snack, Get a snack)
    //1%

    private int total = 0;

    @Override
    public boolean hasAchieved() {
        return total >= 5;
    }

    public void snack() {
        total++;
    }

    public void reset() {
        total = 0;
    }
}
