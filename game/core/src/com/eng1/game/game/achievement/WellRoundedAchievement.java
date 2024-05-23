package com.eng1.game.game.achievement;

import com.eng1.game.game.activity.Activities;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Links with {@link Achievements#WELL_ROUNDED}
 *
 * This class represents the Well-Rounded achievement in the game.
 * The Well-Rounded achievement is achieved by doing one of each type of activity a day.
 * The class keeps track of the activities done each day.
 */
public class WellRoundedAchievement implements Achievement {
    //Well-Rounded
    //Achieve by doing one of each type of activity a day
    //Eat / Study / Relax
    //2%

    /**
     * A {@link HashMap} that stores the activities done each day.
     *
     * The key of the map is the day, and the value is a {@link HashSet} of {@link Activities} done on that day.
     * This map is used to track the progress towards the Well-Rounded achievement.
     * The Well-Rounded achievement is achieved by doing one of each type of activity a day.
     */
    private final HashMap<Integer, HashSet<Activities>> daysActivities = new HashMap<>();

    /**
     * Checks if the Well-Rounded achievement has been achieved.
     *
     * The Well-Rounded achievement is achieved when the player has done one of each type of activity for at least 7 days.
     * This method checks if the total number of days with all activities is greater than or equal to 7.
     *
     * @return true if the player has done one of each type of activity for at least 7 days, false otherwise.
     */
    @Override
    public boolean hasAchieved() {
        int activitiesLength = Activities.values().length;
        return getDaysActivities() >= 7 && daysActivities.values().stream().mapToInt(HashSet::size).allMatch(size -> size == activitiesLength);
    }

    /**
     * Adds an activity to the set of activities done on a specific day.
     *
     * This method is called when the player does an activity.
     * The activity is added to the set of activities done on the specified day.
     *
     * @param day the day on which the activity was done.
     * @param activity the activity that was done.
     */
    public void addActivity(int day, Activities activity) {
        daysActivities.computeIfAbsent(day, d -> new HashSet<>()).add(activity);
    }

    /**
     * Resets the map of activities done each day.
     */
    public void reset() {
        daysActivities.clear();
    }

    /**
     * Gets the total number of days with activities.
     *
     * This method returns the size of the map that stores the activities done each day.
     * The size of the map is equal to the total number of days with activities.
     *
     * @return the total number of days with activities.
     */
    public int getDaysActivities() {
        return daysActivities.size();
    }

    /**
     * Gets the number of activities done on a specific day.
     *
     * This method returns the size of the set of activities done on the specified day.
     * The size of the set is equal to the number of activities done on that day.
     *
     * @param day the day for which the number of activities is to be returned.
     * @return the number of activities done on the specified day.
     */
    public int getDaysActivities(int day) {
        return daysActivities.get(day).size();
    }
}
