package com.dcronqvist.engine.utils;

import java.io.FileReader;
import java.util.Random;

import com.dcronqvist.engine.math.Vector2f;

public class Utils {
    private static final Random rand = new Random();

    public static String getTextFromFile(String path) throws Exception {
        try (FileReader fr = new FileReader(path)) {
            StringBuilder fileContent = new StringBuilder();
            int content;
            while ((content = fr.read()) != -1) {
                fileContent.append((char) content);
            }
            return fileContent.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public static float getRandomFloat(float min, float max) {
        return (rand.nextFloat() * (max - min)) + min;
    }

    public static Vector2f getRandomVector2f(float xmin, float xmax, float ymin, float ymax) {
        return new Vector2f(getRandomFloat(xmin, xmax), getRandomFloat(ymin, ymax));
    }
}
