package com.eng1.game.game.achievement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeachersPetAchievement implements Achievement {
    //Teacher’s Pet
    //Achieve by going to Teaching Hours more than twice in a week
    //(Attend Teaching Hours)
    //3%

    private int total = 0;

    @Override
    public boolean hasAchieved() {
        return total > 2;
    }

    public void attendTeachingHours() {
        total++;
    }

    public void reset() {
        total = 0;
    }
}
