package com.sample.model2;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import sun.java2d.opengl.OGLContext;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

public class Engine implements Runnable{

    public static long windowId = 0;
    private boolean running = false;

    public void start(){
        this.running = true;
        Thread t1 = new Thread(this, "t1");
        t1.start();
    }

    public void run(){
        Engine mainEngine = new Engine();
        mainEngine.init();
        while (running){
            glfwPollEvents();
            glfwSwapBuffers(windowId);
            running = !glfwWindowShouldClose(windowId);
        }
        System.out.println("Exiting game");
    }

    private void init(){
        if (!glfwInit()){
            throw new RuntimeException("Error when initiating game");
        }
        System.out.println("Starting game");

        windowId = glfwCreateWindow(800, 600, "Display", 0, 0);

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowId, 0,100);

        glfwSetKeyCallback(windowId, new Input());
        glfwMakeContextCurrent(windowId);
        glfwShowWindow(windowId);

        GL.createCapabilities();
        glClearColor(1.0f,0.0f,1.0f,1.0f);
        glEnable(GL_DEPTH_TEST);
    }

}
