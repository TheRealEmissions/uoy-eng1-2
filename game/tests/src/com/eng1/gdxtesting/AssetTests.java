package com.eng1.gdxtesting;

import static org.junit.Assert.assertTrue;

import com.eng1.game.assets.images.ImageAssets;
import com.eng1.game.game.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testCharacter1AssetExists() {
        assertTrue("Testing whether the asset for character 1 exists", ImageAssets.PLAYER_CHARACTER_1.get() != null);
    }

    @Test
    public void testCharacter2AssetExists() {
        assertTrue("Testing whether the asset for character 2 exists", ImageAssets.PLAYER_CHARACTER_2.get() != null);
    }

    @Test
    public void testCharacter3AssetExists() {
        assertTrue("Testing whether the asset for character 3 exists", ImageAssets.PLAYER_CHARACTER_3.get() != null);
    }
}