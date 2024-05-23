package com.eng1.gdxtesting.ReflectionMethods;

import java.lang.reflect.Field;

public class GeneralReflectionMethods {
    public static Field getField(Class<?> cl, String fieldName) {
        try {
            return cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFieldString(Object object, String fieldName) {
        Class cl = object.getClass();
        Field field = getField(cl, fieldName);
        field.setAccessible(true);

        String fieldStr;
        try {
            fieldStr = (String) field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return fieldStr;
    }

    public static String getStaticFieldString(Class<?> cl, String fieldName) {
        Field field = getField(cl, fieldName);
        field.setAccessible(true);

        String fieldStr;
        try {
            fieldStr = (String) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return fieldStr;
    }

    public static float getFloat(Object obj, String fieldName) {
        Class cl = obj.getClass();
        Field field = getField(cl, fieldName);
        field.setAccessible(true);

        float fieldFloat;
        try {
            fieldFloat = field.getFloat(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return fieldFloat;
    }

}
