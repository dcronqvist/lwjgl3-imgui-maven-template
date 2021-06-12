package com.dcronqvist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.dcronqvist.engine.math.Vector2f;
import com.dcronqvist.engine.utils.Utils;

import org.junit.Test;

/**
 * Unit tests for trig math :)
 */
public class MathTests {

    @Test
    public void testVector2fClonedShouldBeEqual() {
        Vector2f vec2 = new Vector2f(2, 5);
        Vector2f cloned = new Vector2f(vec2);
        assertTrue("Cloned Vector2f are not considered equal.", vec2.equals(cloned) && cloned.equals(vec2));
    }

    @Test
    public void testVector2fPerpendicularShouldBeZeroDot() {
        Vector2f right = new Vector2f(1, 0);
        Vector2f up = new Vector2f(0, 1);
        assertTrue("Hard coded perpendicular Vector2fs do not give 0 dot product.", right.dot(up) == 0.0f);
    }

    @Test
    public void testVector2fCalculatedPerpendicularShouldBeZeroDot() {
        Vector2f right = new Vector2f(1, 0);
        Vector2f perp = right.perpendicular();
        assertTrue("Calculated perpendicular Vector2fs do not give 0 dot product.", right.dot(perp) == 0.0f);
    }

    @Test
    public void testSubtractVector2f() {
        Vector2f v1 = new Vector2f(5, 7);
        Vector2f v2 = new Vector2f(1, 3);
        Vector2f eq = new Vector2f(4, 4);
        assertTrue("Subtracted Vector2f yields incorrect vector.", v1.sub(v2).equals(eq));
    }

    @Test
    public void testNormalizedParallellVector2fShouldBeOneDot() {
        Vector2f v1 = new Vector2f(1, 1).normalize();
        Vector2f v2 = new Vector2f(2, 2).normalize();
        float dot = v1.dot(v2);
        assertEquals("Normalized Parallell Vector2fs did not yield dot product equal to 1.", 1.0f, dot, 0.0001f);
    }

    @Test
    public void testNormalizedRandomVector2fLengthAlwaysOne() {
        int amount = 100;
        for (int i = 0; i < amount; i++) {
            Vector2f randVector2 = Utils.getRandomVector2f(-100.0f, 100.0f, -100.0f, 100.0f);
            float length = randVector2.normalize().length();
            assertEquals("Random Normalized Vector2f length was not 1.", 1.0f, length, 0.0001f);
        }
    }
}
