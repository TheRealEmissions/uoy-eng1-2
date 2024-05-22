package com.eng1.game.game.activity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.eng1.game.game.achievement.Achievements;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public final class ActivityMapObject {
    private static final Map<MapObject, ActivityMapObject> activityMap = new HashMap<>();

    private final String text;
    private final Activities activity;
    private final int advanceTimeBy;
    private final @Unmodifiable List<Float> changeStats;
    private final List<Achievements> achievements;

    private ActivityMapObject(@NotNull MapObject object) {
        MapProperties properties = object.getProperties();
        this.text = properties.get("activity_str", String.class);
        this.activity = Activities.fromString(properties.get("activity_type", String.class));
        this.advanceTimeBy = properties.get("activity_time", Integer.class);
        this.changeStats = Arrays.stream(properties.get("change_stats", String.class).split(",", -1))
                .map(x -> x.isEmpty() ? 0 : Float.parseFloat(x))
                .collect(Collectors.toUnmodifiableList());
        this.achievements = Arrays.stream(properties.get("activity_achievements", String.class).split(","))
                .map(Achievements::fromString)
                .collect(Collectors.toList());
    }

    public static ActivityMapObject fromMapObject(@NotNull MapObject object) {
        return activityMap.computeIfAbsent(object, ActivityMapObject::new);
    }
}
