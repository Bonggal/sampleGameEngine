package com.sample.model;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

public class SampleDisplayManager {

    private long windowId = 0;

    public void run() {
        System.out.println("Start engine, version: " + Version.getVersion());

        init();
        loop();

        Callbacks.glfwFreeCallbacks(windowId);
        GLFW.glfwDestroyWindow(windowId);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();

    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new RuntimeException("Failed to start GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // create window
        windowId = GLFW.glfwCreateWindow(300, 300, "Sample Display Test", 0, 0);
        if (windowId == 0) {
            throw new RuntimeException("Failed to create window");
        }

        System.out.println("Window created, windowId: " + windowId);

        GLFW.glfwSetKeyCallback(windowId, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE){
                System.out.println("ESC key presses, and quit");
                GLFW.glfwSetWindowShouldClose(window, true);
            }

            if (key == GLFW.GLFW_KEY_ENTER && action == GLFW.GLFW_PRESS){
                System.out.println("ENTER key pressed");
            }
        });

        try{
            MemoryStack stack = MemoryStack.stackPush();
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(windowId, pWidth, pHeight);
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(windowId, (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2);
        } catch (Throwable e ){
            throw new RuntimeException("Failed when initiate window");
        }

        GLFW.glfwMakeContextCurrent(windowId);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(windowId);
    }

    private void loop() {
        GL.createCapabilities();

        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        while (!GLFW.glfwWindowShouldClose(windowId)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(windowId);
            GLFW.glfwPollEvents();
        }
    }
}
