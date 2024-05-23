package com.eng1.gdxtesting.PlayerClassUnitTests;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.eng1.game.game.player.Player;
import com.eng1.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getField;
import static com.eng1.gdxtesting.ReflectionMethods.GeneralReflectionMethods.getFloat;
import static com.eng1.gdxtesting.ReflectionMethods.PlayerReflectionMethods.*;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class PlayerMovementUnitTests {
    Player player = new Player(new Sprite(), null, null, null);

    @Test
    public void testKeyDownW() {
        player.keyDown(Input.Keys.W);

        assertEquals(
                "Checking that keyDown works as expected when W is pressed",
                getFloat(player, "speed"),
                getVelocity(player).y,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyDownS() {
        player.keyDown(Input.Keys.S);

        assertEquals(
                "Checking that keyDown works as expected when S is pressed",
                -getFloat(player, "speed"),
                getVelocity(player).y,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyDownA() {
        player.keyDown(Input.Keys.A);

        assertEquals(
                "Checking that keyDown works as expected when A is pressed",
                -getFloat(player, "speed"),
                getVelocity(player).x,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyDownD() {
        player.keyDown(Input.Keys.D);

        assertEquals(
                "Checking that keyDown works as expected when D is pressed",
                getFloat(player, "speed"),
                getVelocity(player).x,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyUpW() {
        setVelocity(player, new Vector2(0, 1)); // Set velocity.y = 1

        player.keyUp(Input.Keys.W);
        assertEquals(
                "Checking that keyUp works as expected when W is stopped being pressed",
                0.0f,
                getVelocity(player).y,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyUpS() {
        setVelocity(player, new Vector2(0, -1)); // Set velocity.y = -1

        player.keyUp(Input.Keys.S);
        assertEquals(
                "Checking that keyUp works as expected when S is stopped being pressed",
                0.0f,
                getVelocity(player).y,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyUpA() {
        setVelocity(player, new Vector2(-1, 0)); // Set velocity.x = -1

        player.keyUp(Input.Keys.A);
        assertEquals(
                "Checking that keyUp works as expected when A is stopped being pressed",
                0.0f,
                getVelocity(player).x,
                0.0f // No delta
        );
    }

    @Test
    public void testKeyUpD() {
        setVelocity(player, new Vector2(1, 0)); // Set velocity.x = 1

        player.keyUp(Input.Keys.D);
        assertEquals(
                "Checking that keyUp works as expected when D is stopped being pressed",
                0.0f,
                getVelocity(player).x,
                0.0f // No delta
        );
    }
}
