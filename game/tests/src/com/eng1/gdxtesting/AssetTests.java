package com.eng1.gdxtesting;

import static org.junit.Assert.assertTrue;

import com.eng1.game.Play;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import com.eng1.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testCharacter1AssetExists() {
        assertTrue("Testing whether the asset for character 1 exists", Gdx.files.internal(Play.CHAR1).exists());
    }

    @Test
    public void testCharacter2AssetExists() {
        assertTrue("Testing whether the asset for character 2 exists", Gdx.files.internal(Play.CHAR2).exists());
    }

    @Test
    public void testCharacter3AssetExists() {
        assertTrue("Testing whether the asset for character 3 exists", Gdx.files.internal(Play.CHAR3).exists());
    }
}