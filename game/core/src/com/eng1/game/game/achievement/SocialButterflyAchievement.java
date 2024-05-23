package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SocialButterflyAchievement implements Achievement {
    //Social Butterfly
    //Achieve by hanging out with friends or going to the pub at least 3 times in a week
    //(Hang out with friends, Go to courtyard)
    //2%

    private int total = 0;

    @Override
    public boolean hasAchieved() {
        return total >= 3;
    }

    public void socialise() {
        total++;
    }

    public void reset() {
        total = 0;
    }

}
