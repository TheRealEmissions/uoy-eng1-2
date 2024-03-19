package com.eng1.game;

import com.badlogic.gdx.math.Interpolation;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Activity {
    private static Map<String, Map<String, Activity>> activities; // Map of the activity types
    // each activity type containing a map of the locations and activities within those locations
    // Activities can be called using the type then the location
    private LocalTime timeNeeded; // Time required to complete the activity
    private int energyNeeded; // Energy required to complete the activity
    private int timesCompletedWeek; // Times the activity has been completed in the week (can be used to decrese the reward of an activity if it is completed multiple times)
    private int timesCompletedDay; // Times the activity has been completed that day (can be used to stop activites being completed too many times or to increase the reward i.e. eating 3 meals)
    private int reward; // Score the activity gives for being completed

    public Activity(LocalTime timeNeeded, int energyNeeded, int reward) {
        // Constructor for activities
        this.timeNeeded = timeNeeded;
        this.energyNeeded = energyNeeded;
        this.timesCompletedWeek = 0;
        this.timesCompletedDay = 0;
        this.reward = reward;
    }

    public static void createActivities() {
        // Create all activities here
        // Call this method at the start of a new game

        activities = new HashMap<>();
        // Add in each type of activity
        activities.put("Study", new HashMap<>());
        activities.put("Relax", new HashMap<>());
        activities.put("Eat", new HashMap<>());
        activities.put("Sleep", new HashMap<>());

        // Add activties to their activity type
        // Study: CompSci Building
        activities.get("Study").put("CompSci Building", new Activity(LocalTime.of(1,0), 10, 0));

        // Relax: Gym
        activities.get("Relax").put("Gym", new Activity(LocalTime.of(1,0), 10, 0));

        // Eat: Piazza Building
        activities.get("Eat").put("Piazza", new Activity(LocalTime.of(1,0), 10, 0));

        // Sleep: Home
        activities.get("Sleep").put("Home", new Activity(LocalTime.of(0,0),0, 0));
    }

    public static void completeActivity(String activityIdentifier) {
        // Takes a string to indicate the activity being completed. i.e. "Relax,Gym"
        String[] activityLocator = activityIdentifier.split(",");
        String type = activityLocator[0];
        String location = activityLocator[1];
        System.out.println(type);
        System.out.println(location);

        activities.get(type).get(location).complete();
        if (type == "Sleep") {
            Activity.sleep();
        }
    }

    public String complete() {
        // Returns whether the activity has been completed
        // Checks whether there is enough time left in the day to complete the activity
        LocalTime tempTime = GameStats.getTime().plusHours(this.timeNeeded.getHour());
        tempTime = tempTime.plusMinutes(this.timeNeeded.getMinute());
        if (tempTime.isAfter(GameStats.DAY_END) && tempTime.isBefore(GameStats.DAY_START)) {
            return "Insufficient Time";
        }

        // Checks whether there is enough energy left to complete the activity
        if (GameStats.getEnergy() - this.energyNeeded < 0) {
            return "Insufficient Energy";
        }

        // Increase the relevent trackers
        this.timesCompletedDay++;
        this.timesCompletedWeek++;
        GameStats.increaseTime(this.timeNeeded);
        GameStats.decreaseEnergy(this.energyNeeded);
        return "Activity Completed";
    }


    public static void sleep() {
        // Calculate the days score and add to the total score
        GameStats.increaseScore(calculateDayScore());

        // Reset activity day counts
        for (Map<String, Activity> typeActivities : activities.values()) {
            // typeActivities is the map for each type of activity
            for (Activity activity : typeActivities.values()) {
                activity.timesCompletedDay = 0;
            }
        }

        //Reset stats
        GameStats.newDay();

        if (GameStats.getDay() == 8) {
            //Call end screen
        }
    }

    public static int countCompletedActivities() {
        //Count the completed activites for the end screen
        int count = 0;
        for (Map<String, Activity> typeActivities : activities.values()) {
            // typeActivities is the map for each type of activity
            for (Activity activity : typeActivities.values()) {
                count += activity.timesCompletedWeek;
            }
        }

        return count;
    }

    public static int calculateDayScore() {
        // Score to be calculated here
        // Can be completed by iterating through the activites and checking if their timeCompletedDay is > 0
        return  0;
    }

    public static void main(String[] args) {

    }
}
