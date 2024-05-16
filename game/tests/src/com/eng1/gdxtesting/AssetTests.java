package com.eng1.gdxtesting;

import static org.junit.Assert.assertTrue;

import com.eng1.game.game.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

@RunWith(GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testCharacter1AssetExists() {
        assertTrue("Testing whether the asset for character 1 exists", Gdx.files.internal(Player.CHAR1).exists());
    }

    @Test
    public void testCharacter2AssetExists() {
        assertTrue("Testing whether the asset for character 2 exists", Gdx.files.internal(Player.CHAR2).exists());
    }

    @Test
    public void testCharacter3AssetExists() {
        assertTrue("Testing whether the asset for character 3 exists", Gdx.files.internal(Player.CHAR3).exists());
    }
}