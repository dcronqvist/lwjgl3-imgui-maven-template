package com.dcronqvist.engine.utils;

public class GameTime {
    public static float deltaTime;
    public static float totalTime;

    public static float getFPS() {
        return 1f / deltaTime;
    }
}
