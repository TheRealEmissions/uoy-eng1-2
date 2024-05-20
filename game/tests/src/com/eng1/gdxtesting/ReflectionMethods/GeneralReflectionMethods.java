package com.eng1.gdxtesting.ReflectionMethods;

import java.lang.reflect.Field;

public class GeneralReflectionMethods {
    public static String getFieldString(Object object, String fieldName) {
        Class cl = object.getClass();
        Field field;
        try {
            field = cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
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
        Field field;
        try {
            field = cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);

        String fieldStr;
        try {
            fieldStr = (String) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return fieldStr;
    }

}
