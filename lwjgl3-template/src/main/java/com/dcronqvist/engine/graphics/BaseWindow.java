package com.dcronqvist.engine.graphics;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import imgui.ImFontAtlas;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import java.io.PrintStream;
import java.nio.*;

import com.dcronqvist.engine.utils.GameTime;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class BaseWindow {
    public long windowHandle;
    PrintStream errorStream;

    public int width, height;
    String title;

    ImGuiImplGlfw img;
    ImGuiImplGl3 img3;

    public BaseWindow(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    /**
     * Opens the window and begins the common window loop.
     * 
     * @param errorStream A print stream which will be bound to the GLFW error
     *                    stream.
     * @throws Exception
     */
    public void run(PrintStream errorStream, String[] args) throws Exception {
        initialize(args);

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new Exception("Unable to initialize GLFW.");
        }

        // Set default window hints for GLFW.
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, 1);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation

        windowHandle = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (windowHandle == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            keyCallBack(window, key, scancode, action, mods);
        });

        // This centers the window on the screen.
        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(windowHandle, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(windowHandle, (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2);
        } catch (Exception e) {
            throw e;
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);

        // Make the window visible
        glfwShowWindow(windowHandle);
        GL.createCapabilities();

        ImGui.createContext();

        // ImGui stuff
        ImGuiIO io = ImGui.getIO();
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.setConfigViewportsNoTaskBarIcon(true);

        ImFontAtlas fontAtlas = io.getFonts();
        fontAtlas.addFontDefault();

        img = new ImGuiImplGlfw();
        img3 = new ImGuiImplGl3();
        img.init(this.windowHandle, true);
        img3.init("#version 330 core");

        loadContent();

        float previousTime = 0.0f;
        // Input.init(this.windowHandle);

        while (!glfwWindowShouldClose(this.windowHandle)) {
            float currentTime = (float) glfwGetTime();
            GameTime.totalTime = currentTime;
            GameTime.deltaTime = (currentTime - previousTime);
            // Input.begin();
            update();

            img.newFrame();
            ImGui.newFrame();

            render();

            ImGui.render();
            img3.renderDrawData(ImGui.getDrawData());

            glfwSwapBuffers(this.windowHandle);
            // Input.end();

            glfwPollEvents();
            previousTime = currentTime;
        }

        unload();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Is bound during initialisation of window. Is called any time a key is
     * pressed.
     * 
     * @param window
     * @param key
     * @param scancode
     * @param action
     * @param mods
     */
    protected abstract void keyCallBack(long window, int key, int scancode, int action, int mods);

    /**
     * Is run before anything else is run, no OpenGL context exists here.
     * 
     * @param args The arguments which the application was launched with.
     */
    protected abstract void initialize(String[] args);

    /**
     * Is run after initialize(), however here you have access to an OpenGL context,
     * which allows for creating/binding GL buffers and binding data.
     */
    protected abstract void loadContent();

    /**
     * Is run every frame, before render() is called.
     */
    protected abstract void update();

    /**
     * Is run every frame, after update() is called.
     */
    protected abstract void render();

    /**
     * Is run every time the window is closed.
     */
    protected abstract void unload();

}
