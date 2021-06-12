package com.dcronqvist.engine.graphics;

import static org.lwjgl.opengl.GL33C.*;

public class Shader {
    String vertexCode;
    String fragmentCode;
    int programID;

    /**
     * To use this Shader class, you simply supply the shader code to a new shader
     * instance, for example using {@link Utils.getTextFromFile()}, and you simply
     * have to run init() on that shader instance to have it load up! In case an
     * error occurs during initialization, the init() method will throw and
     * exception with a readable error message.
     */
    public Shader(String vertexCode, String fragmentCode) {
        this.vertexCode = vertexCode;
        this.fragmentCode = fragmentCode;
    }

    public void init() throws Exception {
        StringBuffer sb = new StringBuffer();

        // Create vertex shader
        int vs = createShader(GL_VERTEX_SHADER, vertexCode, sb, "Vertex Shader: ");
        // Create fragment shader
        int fs = createShader(GL_FRAGMENT_SHADER, fragmentCode, sb, "Fragment Shader: ");

        if (vs != -1 && fs != -1) {
            // Create shader program
            this.programID = createProgram(vs, fs, sb, "Linking: ");

            if (this.programID == -1) {
                throw new Exception(sb.toString());
            }
        } else {
            throw new Exception(sb.toString());
        }
    }

    private int createShader(int shaderType, String code, StringBuffer error, String errorPrefix) {
        int shaderID = glCreateShader(shaderType);

        // Attach the code to the shader and attempt compilation.
        glShaderSource(shaderID, code);
        glCompileShader(shaderID);

        // Check if the compilation was successful.
        int success = glGetShaderi(shaderID, GL_COMPILE_STATUS);
        if (success == 0) {
            // If the compilation failed, then populate the error string with an error
            // message.
            error.append(errorPrefix + glGetShaderInfoLog(shaderID));
            glDeleteShader(shaderID);
            return -1;
        }

        // If successful, set id to the generated shader id.
        return shaderID;
    }

    private int createProgram(int vertexShaderID, int fragmentShaderID, StringBuffer error, String errorPrefix) {
        int programID = glCreateProgram();

        // Attach both shaders to the program.
        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);

        // Attempt to link the shaders.
        glLinkProgram(programID);

        // Check for success
        int success = glGetProgrami(programID, GL_LINK_STATUS);
        if (success == 0) {
            // If the linking was not successful
            String info = glGetProgramInfoLog(programID);
            error.append(errorPrefix + info);
            glDeleteProgram(programID);
            return -1;
        }

        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        return programID;
    }
}
