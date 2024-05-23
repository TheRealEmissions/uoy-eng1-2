package com.eng1.game.game.achievement;

import java.util.*;

public class ScholarlySprinterAchievement implements Achievement {
    //Scholarly Sprinter
    //Achieve by studying once every day
    //(Study at desk, Study)
    //6%

    private final Set<Integer> daysStudied = new HashSet<>();

    @Override
    public boolean hasAchieved() {
        return getDaysStudied() >= 7;
    }

    public void study(int day) {
        daysStudied.add(day);
    }

    public void setDaysStudied(List<Integer> daysStudied) {
        this.daysStudied.clear();
        this.daysStudied.addAll(daysStudied);
    }

    public void reset() {
        daysStudied.clear();
    }

    public int getDaysStudied() {
        return daysStudied.size();
    }
}
