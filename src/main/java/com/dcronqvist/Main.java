package com.dcronqvist;

import com.dcronqvist.engine.graphics.BaseWindow;

public class Main {
    public static void main(String[] args) throws Exception {
        BaseWindow window = new GameWindow(1280, 720, "lwjgl3-imgui-maven-template by @dcronqvist");
        window.run(System.err, args);
    }
}
