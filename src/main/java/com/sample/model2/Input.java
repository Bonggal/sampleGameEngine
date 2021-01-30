package com.sample.model2;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
    @Override
    public void invoke(long window, int key, int scanCode, int action, int mode) {
        if (action == GLFW_PRESS){
            switch (key){
                case GLFW_KEY_ENTER:
                    System.out.println("Enter key pressed");
                    break;
            }
        } else if (action == GLFW_RELEASE){
            switch (key){
                case GLFW_KEY_ESCAPE:
                    glfwSetWindowShouldClose(window, true);
                    break;
            }
        } else if (action == GLFW_REPEAT){
            switch (key){
                case GLFW_KEY_UP:
                    System.out.println("UP");
                    break;
                case GLFW_KEY_DOWN:
                    break;
                case GLFW_KEY_LEFT:
                    break;
                case GLFW_KEY_RIGHT:
                    break;
            }
        }
    }
}
