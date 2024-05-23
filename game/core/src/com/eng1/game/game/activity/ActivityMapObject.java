package com.eng1.game.game.activity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.eng1.game.game.achievement.Achievements;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents an activity object on the map in the game.
 *
 * This class holds all the information related to an activity that can be performed in the game, including the text description of the activity,
 * the type of activity, the time it takes to complete the activity, the changes it makes to player's statistics, and the achievements that can be unlocked by performing the activity.
 *
 * The class also provides a static method to convert a {@link MapObject} into an {@link ActivityMapObject}.
 */
@Getter
public final class ActivityMapObject {
    /**
     * A static {@link Map} that stores the association between {@link MapObject} instances and their corresponding {@link ActivityMapObject} instances.
     *
     * This map is used to ensure that each {@link MapObject} is associated with exactly one {@link ActivityMapObject}, and that the same {@link ActivityMapObject} is returned every time a {@link MapObject} is converted into an {@link ActivityMapObject}.
     * The map is initialized as an empty {@link HashMap}, and is populated as MapObjects are converted into ActivityMapObjects using the {@link #fromMapObject(MapObject)} method.
     */
    private static final Map<MapObject, ActivityMapObject> activityMap = new HashMap<>();

    /**
     * The text description of the activity.
     */
    private final String text;

    /**
     * The type of activity.
     */
    private final Activities activity;

    /**
     * The time it takes to complete the activity.
     */
    private final int advanceTimeBy;

    /**
     * The changes the activity makes to player's statistics.
     * This is a {@link List} of {@link Float} where each float represents the change in a specific player statistic.
     */
    private final @Unmodifiable List<Float> changeStats;

    /**
     * The achievements that can be unlocked by performing the activity.
     * This is a {@link List} of {@link Achievements} enums.
     */
    private final List<Achievements> achievements;

    /**
     * Constructor for the {@link ActivityMapObject} class.
     *
     * This constructor takes a {@link MapObject} as input and initializes the fields of the {@link ActivityMapObject} instance based on the properties of the input {@link MapObject}.
     * The properties of the {@link MapObject} are retrieved using the {@link MapObject#getProperties()} method, and are then used to initialize the fields of the {@link ActivityMapObject}.
     * The {@link #text}, {@link #activity}, {@link #advanceTimeBy}, {@link #changeStats}, and {@link #achievements} fields are initialized using the "activity_str", "activity_type", "activity_time", "change_stats", and "activity_achievements" properties of the {@link MapObject}, respectively.
     * The {@link #changeStats} field is a {@link List} of {@link Float}, and is initialized by splitting the "change_stats" property of the {@link MapObject} into a string array, converting each string in the array into a float, and then collecting the floats into a {@link List}.
     * The {@link #achievements} field is a list of {@link Achievements} enums, and is initialized by splitting the "activity_achievements" property of the {@link MapObject} into a string array, converting each string in the array into an {@link Achievements} enum using the {@link Achievements#fromString(String)} method, filtering out any null values, and then collecting the {@link Achievements} enums into a {@link List}.
     *
     * @param object The {@link MapObject} to initialize the {@link ActivityMapObject} from.
     */
    private ActivityMapObject(@NotNull MapObject object) {
        // Retrieves the properties of the input MapObject.
        MapProperties properties = object.getProperties();

        // Initializes the text field with the "activity_str" property of the MapObject.
        this.text = properties.get("activity_str", String.class);

        // Initializes the activity field with the "activity_type" property of the MapObject.
        // The "activity_type" property is converted into an Activities enum using the fromString method.
        this.activity = Activities.fromString(properties.get("activity_type", String.class));

        // Initializes the advanceTimeBy field with the "activity_time" property of the MapObject.
        this.advanceTimeBy = properties.get("activity_time", Integer.class);

        // Initializes the changeStats field with the "change_stats" property of the MapObject.
        // The "change_stats" property is split into a string array, each string in the array is converted into a float, and the floats are collected into a list.
        this.changeStats = Arrays.stream(properties.get("change_stats", String.class).split(",", -1))
            .map(x -> x.isEmpty() ? 0 : Float.parseFloat(x))
            .collect(Collectors.toUnmodifiableList());

        // Initializes the achievements field with the "activity_achievements" property of the MapObject.
        // The "activity_achievements" property is split into a string array, each string in the array is converted into an Achievements enum using the fromString method, any null values are filtered out, and the Achievements enums are collected into a list.
        this.achievements = Arrays.stream(properties.get("activity_achievements", "", String.class).split(","))
            .map(Achievements::fromString)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * Converts a {@link MapObject} into an {@link ActivityMapObject}.
     *
     * This method takes a {@link MapObject} as input and returns the corresponding {@link ActivityMapObject}.
     * If the input {@link MapObject} has already been converted into an {@link ActivityMapObject}, the existing {@link ActivityMapObject} is returned.
     * If the input {@link MapObject} has not yet been converted into an {@link ActivityMapObject}, a new {@link ActivityMapObject} is created using {@link #ActivityMapObject(MapObject)}, and the new {@link ActivityMapObject} is added to the {@link #activityMap}.
     * The {@link #activityMap} is a static {@link Map} that stores the association between {@link MapObject} instances and their corresponding {@link ActivityMapObject} instances.
     *
     * @param object The {@link MapObject} to convert into an {@link ActivityMapObject}.
     * @return The {@link ActivityMapObject} corresponding to the input {@link MapObject}.
     */
    public static ActivityMapObject fromMapObject(@NotNull MapObject object) {
        return activityMap.computeIfAbsent(object, ActivityMapObject::new);
    }
}
