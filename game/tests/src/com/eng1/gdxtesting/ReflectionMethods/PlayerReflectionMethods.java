package com.eng1.gdxtesting.ReflectionMethods;

import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Field;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getField;

public class PlayerReflectionMethods {
    public static Vector2 setVelocity(Object obj, Vector2 velocity) {
        Class cl = obj.getClass();
        Field field = getField(cl, "velocity");
        field.setAccessible(true);

        try {
            field.set(obj, velocity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return velocity;
    }

    public static Vector2 getVelocity(Object obj) {
        Class cl = obj.getClass();
        Field field = getField(cl, "velocity");
        field.setAccessible(true);

        Vector2 velocity;
        try {
            velocity = (Vector2) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return velocity;
    }
}
