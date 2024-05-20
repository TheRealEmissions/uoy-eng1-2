package com.eng1.gdxtesting.AssetTests;

import com.eng1.game.assets.skins.SkinAssetPaths;
import com.eng1.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.eng1.gdxtesting.AssetTests.AssetUnitTestsUtils.assetTestReflectionStaticPath;

@RunWith(GdxTestRunner.class)
public class SkinAssetUnitTests {
    @Test
    public void testUISkinAssetExists() {
        assetTestReflectionStaticPath(
                SkinAssetPaths.class,
                "UISKIN_JSON_PATH",
                "Testing whether the json file for skins exists"
        );
    }
}
