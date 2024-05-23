package com.eng1.game.game.achievement;

import com.eng1.game.game.activity.Activities;

import java.util.HashMap;
import java.util.HashSet;

public class WellRoundedAchievement implements Achievement {
    //Well-Rounded
    //Achieve by doing one of each type of activity a day
    //Eat / Study / Relax
    //2%

    private final HashMap<Integer, HashSet<Activities>> daysActivities = new HashMap<>();

    @Override
    public boolean hasAchieved() {
        int activitiesLength = Activities.values().length;
        return getDaysActivities() >= 7 && daysActivities.values().stream().mapToInt(HashSet::size).allMatch(size -> size == activitiesLength);
    }

    public void addActivity(int day, Activities activity) {
        daysActivities.computeIfAbsent(day, d -> new HashSet<>()).add(activity);
    }

    public void reset() {
        daysActivities.clear();
    }

    public int getDaysActivities() {
        return daysActivities.size();
    }

    public int getDaysActivities(int day) {
        return daysActivities.get(day).size();
    }
}
