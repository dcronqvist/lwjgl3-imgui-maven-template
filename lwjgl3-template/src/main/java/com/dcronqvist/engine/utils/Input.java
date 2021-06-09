package com.dcronqvist.engine.utils;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetKeyScancode;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.nio.DoubleBuffer;
import java.util.HashMap;

import com.dcronqvist.engine.math.Vector2f;

import org.lwjgl.system.MemoryStack;

public class Input {
    static long windowHandle;
    static HashMap<Integer, Boolean> currentKeyState;
    static HashMap<Integer, Boolean> previousKeyState;
    static HashMap<Integer, Boolean> currentMouseState;
    static HashMap<Integer, Boolean> previousMouseState;
    static float currentMouseWheel;
    static float previousMouseWheel;

    public static void init(long handle) {
        windowHandle = handle;
        glfwSetScrollCallback(windowHandle, (window, x, y) -> {
            currentMouseWheel += y;
        });
    }

    public static void setKey(int key, boolean value) {
        currentKeyState.put(key, value);
    }

    private static boolean getKeyCurrent(int key) {
        return currentKeyState.get(key);
    }

    private static boolean getKeyPrevious(int key) {
        return previousKeyState.get(key);
    }

    public static boolean isKeyDown(int key) {
        return getKeyCurrent(key);
    }

    public static boolean isKeyPressed(int key) {
        return getKeyCurrent(key) && !getKeyPrevious(key);
    }

    public static boolean isMouseDown(int button) {
        return currentMouseState.get(button);
    }

    public static boolean isMousePressed(int button) {
        return currentMouseState.get(button) && !previousMouseState.get(button);
    }

    public static Vector2f getMousePosition() {
        double x = 0, y = 0;
        try (MemoryStack ms = stackPush()) {
            DoubleBuffer dbx = ms.callocDouble(1);
            DoubleBuffer dby = ms.callocDouble(1);

            glfwGetCursorPos(windowHandle, dbx, dby);
            x = dbx.get(0);
            y = dby.get(0);
        } catch (Exception e) {
        }
        return new Vector2f((float) x, (float) y);
    }

    public static float getMouseWheelMove() {
        return currentMouseWheel - previousMouseWheel;
    }

    public static void begin() {
        currentKeyState = new HashMap<>();
        for (int i = 32; i < 348; i++) {
            if (glfwGetKeyScancode(i) != -1) {
                setKey(i, glfwGetKey(windowHandle, i) == GLFW_PRESS);
            }
        }

        currentMouseState = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            currentMouseState.put(i, glfwGetMouseButton(windowHandle, i) == GLFW_PRESS);
        }
    }

    public static void end() {
        previousKeyState = currentKeyState;
        previousMouseState = currentMouseState;
        previousMouseWheel = currentMouseWheel;
    }
}
