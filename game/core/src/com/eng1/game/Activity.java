package com.eng1.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Interpolation;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.eng1.game.HeslingtonHustle;


public class Activity {

    private static Map<String, Map<String, Activity>> activities; // Map of the activity types
    // each activity type containing a map of the locations and activities within those locations
    // Activities can be called using the type then the location
    private final LocalTime timeNeeded; // Time required to complete the activity
    private final int energyNeeded; // Energy required to complete the activity
    private int timesCompletedWeek; // Times the activity has been completed in the week (can be used to decrese the reward of an activity if it is completed multiple times)
    private int timesCompletedDay; // Times the activity has been completed that day (can be used to stop activites being completed too many times or to increase the reward i.e. eating 3 meals)
    private final int reward; // Score the activity gives for being completed

    private static HeslingtonHustle gameInstance; // Reference to the HeslingtonHustle instance

    private static int finalScore;

    public Activity(LocalTime timeNeeded, int energyNeeded, int reward) {
        // Constructor for activities
        this.timeNeeded = timeNeeded;
        this.energyNeeded = energyNeeded;
        this.timesCompletedWeek = 0;
        this.timesCompletedDay = 0;
        this.reward = reward;
    }

    // Method to set the game instance
    public static void setGameInstance(HeslingtonHustle game) {
        gameInstance = game;
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
        activities.get("Study").put("CompSci", new Activity(LocalTime.of(3,0), 30, 0));

        // Relax: Gym
        activities.get("Relax").put("Gym", new Activity(LocalTime.of(1,0), 20, 0));

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
        if (type.equals("Sleep")) {
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

        // Debugging
        // ---
        System.out.println("Current time: " + GameStats.getTime());
        System.out.println("Current energy: " + GameStats.getEnergy());
        System.out.println("Current score: " + GameStats.getScore());
        // ---

        return "Activity Completed";
    }


    public static void sleep() {
        // Debugging
        // ---
        System.out.println("Sleeping");
        // ---

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

        System.out.println(Activity.countCompletedActivities());

        if (GameStats.getDay() > 7) {
            // Pass the score and activities completed to EndScreen
            Map<String, Integer> activitiesCompleted = Activity.countCompletedActivities();
            int score = calculateDayScore();
            gameInstance.changeScreen(HeslingtonHustle.ENDGAME);
        }
    }

    public static Map<String, Integer> countCompletedActivities() {
        // Count the completed activites for the end screen
        Map<String, Integer> counts = new HashMap<>();
        // Add in each type of activity
        counts.put("Study", 0);
        counts.put("Relax", 0);
        counts.put("Eat", 0);
        counts.put("Sleep", 0);
        for (String type : activities.keySet()) {
            // typeActivities is the map for each type of activity
            for (Activity activity : activities.get(type).values()) {
                counts.put(type, counts.get(type) + activity.timesCompletedWeek);
            }
        }

        return counts;
    }

    public static int calculateDayScore() {
        // Score to be calculated here
        // Can be completed by iterating through the activites and checking if their timeCompletedDay is > 0
        return  0;
    }

    public static int getFinalScore() {
        return finalScore;
    }

    public static void setFinalScore(int score) {
        finalScore = score;
    }
}
