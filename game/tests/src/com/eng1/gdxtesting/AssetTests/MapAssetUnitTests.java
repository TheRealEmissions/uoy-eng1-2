package com.eng1.gdxtesting.AssetTests;

import com.eng1.game.assets.maps.MapAssetPaths;
import com.eng1.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.eng1.gdxtesting.AssetTests.AssetTestsUtils.assetTestReflectionStaticPath;
import static com.eng1.gdxtesting.AssetTests.AssetTestsUtils.generateDefaultMessage;

@RunWith(GdxTestRunner.class)
public class MapAssetUnitTests {
    @Test
    public void testHomeMapAssetExists() {
        assetTestReflectionStaticPath(
                MapAssetPaths.class,
                "HOME_MAP_PATH",
                generateDefaultMessage("the home map")
        );
    }

    @Test
    public void testGymMapAssetExists() {
        assetTestReflectionStaticPath(
                MapAssetPaths.class,
                "GYM_MAP_PATH",
                generateDefaultMessage("the gym map")
        );
    }

    @Test
    public void testCSBuildingMapAssetExists() {
        assetTestReflectionStaticPath(
                MapAssetPaths.class,
                "CS_BUILDING_MAP_PATH",
                generateDefaultMessage("the CS building map")
        );
    }

    @Test
    public void testPiazzaMapAssetExists() {
        assetTestReflectionStaticPath(
                MapAssetPaths.class,
                "PIAZZA_MAP_PATH",
                generateDefaultMessage("the piazza map")
        );
    }

    @Test
    public void testNewWorldMapAssetExists() {
        assetTestReflectionStaticPath(
                MapAssetPaths.class,
                "NEW_WORLD_MAP_PATH",
                generateDefaultMessage("the new world map")
        );
    }
}
