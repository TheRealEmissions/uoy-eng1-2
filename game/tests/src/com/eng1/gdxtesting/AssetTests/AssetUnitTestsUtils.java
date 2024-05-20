package com.eng1.gdxtesting.AssetTests;

import com.badlogic.gdx.Gdx;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getStaticFieldString;
import static org.junit.Assert.assertTrue;

public class AssetUnitTestsUtils {
    protected static void assetTestWithPath(String path, String message) {
        assertTrue(
                message,
                Gdx.files.internal(path).exists()
        );
    }

    protected static void assetTestReflectionStaticPath(Class<?> cl, String fieldName, String message) {
        String path = getStaticFieldString(cl, fieldName);
        assetTestWithPath(path, message);
    }

    protected static String generateDefaultMessage(String assetName) {
        return "Testing whether the asset for" + assetName + "exists.";
    }
}
