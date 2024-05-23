package com.eng1.gdxtesting;

import com.eng1.game.game.Score;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreUnitTests {
    private String generateMessage(Float score) {
        return "Checks Score.getClassification() works as expected when score = "
                + score;
    }

    private void testGetClassification(Float score, String expectedResult) {
        assertEquals(
                    generateMessage(score),
                    expectedResult,
                    Score.getClassification(score)
        );
    }

    @Test
    public void testGetClassificationScore0 () {
        testGetClassification(0.0f, "Fail");
    }

    @Test
    public void testGetClassificationScore40 () {
        testGetClassification(40.0f, "Third Class");
    }

    @Test
    public void testGetClassificationScore49 () {
        testGetClassification(49.0f, "Third Class");
    }

    @Test
    public void testGetClassificationScore50 () {
        testGetClassification(50.0f, "Lower Second Class");
    }

    @Test
    public void testGetClassificationScore59 () {
        testGetClassification(59.0f, "Lower Second Class");
    }

    @Test
    public void testGetClassificationScore60 () {
        testGetClassification(60.0f, "Upper Second Class");
    }

    @Test
    public void testGetClassificationScore69 () {
        testGetClassification(69.0f, "Upper Second Class");
    }

    @Test
    public void testGetClassificationScore70 () {
        testGetClassification(70.0f, "First Class");
    }

    @Test
    public void testGetClassificationScore79 () {
        testGetClassification(79.0f, "First Class");
    }

    @Test
    public void testGetClassificationScore80 () {
        testGetClassification(80.0f, "First Class with Distinction");
    }

}
