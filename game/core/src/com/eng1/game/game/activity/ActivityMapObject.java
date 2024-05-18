package com.eng1.game.game.activity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class ActivityMapObject {
    private final String text;
    private final Activities activity;
    private final int advanceTimeBy;
    private final @Unmodifiable List<Float> changeStats;

    public ActivityMapObject(String text, Activities activity, int advanceTimeBy, List<Float> changeStats) {
        this.text = text;
        this.activity = activity;
        this.advanceTimeBy = advanceTimeBy;
        this.changeStats = changeStats;
    }

    public ActivityMapObject(@NotNull MapObject object) {
        MapProperties properties = object.getProperties();
        this.text = properties.get("activity_str", String.class);
        this.activity = Activities.fromString(properties.get("activity_type", String.class));
        this.advanceTimeBy = properties.get("activity_time", Integer.class);
        this.changeStats = Arrays.stream(properties.get("change_stats", String.class).split(","))
                .map(Float::parseFloat)
                .collect(Collectors.toUnmodifiableList());
    }
}
