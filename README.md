# ☕ lwjgl3-imgui-maven-template

A template for getting started with [LWJGL3](https://www.lwjgl.org/) in a maven environment. Includes a working implementation of [imgui](https://github.com/ocornut/imgui) aswell, ready to be used - however not necessary.

Contains some of the most basic things needed to get started. Includes a `GameWindow` class which has the common `initialize/loadContent/update/render/unload` game loop. Also has a wrapper class for GLSL shaders which should be quite straight forward to use.

In addition to that, it also has some utility methods (like reading text from files quickly), and linear algebra classes like `Vector3f` and `Matrix4f` complete with common methods.

## Getting started

1. Create a new repo by clicking `Use this template` up to the right!
2. Open this repo/folder in your favorite maven environment (IntelliJ/VS Code)
3. Go ahead and run the project and you should be ready to go!
4. `GameWindow.java` is probably where you want to start!