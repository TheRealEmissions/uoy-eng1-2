package com.eng1.gdxtesting;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getStaticFieldString;
import static org.junit.Assert.assertTrue;

import com.eng1.game.assets.images.ImageAssetPaths;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class ImageAssetTests {
    @Test
    public void testMainMenuTitleAssetExists() {
        String mainMenuTitlePath = getStaticFieldString(ImageAssetPaths.class, "MAIN_MENU_TITLE_PATH");
        assertTrue("Testing whether the asset for the main menu title exists",
                    Gdx.files.internal(mainMenuTitlePath).exists()
                    );
    }

    @Test
    public void testNewWordMapOverviewExists() {
        String newWorldMapOverviewPath = getStaticFieldString(ImageAssetPaths.class, "NEW_WORLD_MAP_OVERVIEW_PATH");
        assertTrue("Testing whether the asset for the new world map overview exists",
                Gdx.files.internal(newWorldMapOverviewPath).exists()
        );
    }

    @Test
    public void testCharacter1AssetExists() {
        String char1AssetPath = getStaticFieldString(ImageAssetPaths.class, "PLAYER_CHARACTER_1_PATH");
        assertTrue("Testing whether the asset for character 1 exists", Gdx.files.internal(char1AssetPath).exists());
    }

    @Test
    public void testCharacter2AssetExists() {
        String char2AssetPath = getStaticFieldString(ImageAssetPaths.class, "PLAYER_CHARACTER_2_PATH");
        assertTrue("Testing whether the asset for character 1 exists", Gdx.files.internal(char2AssetPath).exists());
    }

    @Test
    public void testCharacter3AssetExists() {
        String char3AssetPath = getStaticFieldString(ImageAssetPaths.class, "PLAYER_CHARACTER_3_PATH");
        assertTrue("Testing whether the asset for character 1 exists", Gdx.files.internal(char3AssetPath).exists());
    }
}