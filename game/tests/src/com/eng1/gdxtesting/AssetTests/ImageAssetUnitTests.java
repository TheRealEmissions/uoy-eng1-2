package com.eng1.gdxtesting.AssetTests;

import static com.eng1.gdxtesting.AssetTests.AssetUnitTestsUtils.assetTestReflectionStaticPath;
import static com.eng1.gdxtesting.AssetTests.AssetUnitTestsUtils.generateDefaultMessage;
import static org.junit.Assert.assertTrue;

import com.eng1.game.assets.images.ImageAssetPaths;
import com.eng1.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class ImageAssetUnitTests {
    @Test
    public void testMainMenuTitleAssetExists() {
        assetTestReflectionStaticPath(
                ImageAssetPaths.class,
                "MAIN_MENU_TITLE_PATH",
                generateDefaultMessage("the main menu title")
        );
    }

    @Test
    public void testNewWordMapOverviewExists() {
        assetTestReflectionStaticPath(
                ImageAssetPaths.class,
                "NEW_WORLD_MAP_OVERVIEW_PATH",
                generateDefaultMessage("the new world map overview")
        );
    }

    @Test
    public void testCharacter1AssetExists() {
        assetTestReflectionStaticPath(
                ImageAssetPaths.class,
                "PLAYER_CHARACTER_1_PATH",
                generateDefaultMessage("character 1")
        );
    }

    @Test
    public void testCharacter2AssetExists() {
        assetTestReflectionStaticPath(
                ImageAssetPaths.class,
                "PLAYER_CHARACTER_2_PATH",
                generateDefaultMessage("character 2")
        );
    }

    @Test
    public void testCharacter3AssetExists() {
        assetTestReflectionStaticPath(
                ImageAssetPaths.class,
                "PLAYER_CHARACTER_3_PATH",
                generateDefaultMessage("character 3")
        );
    }
}