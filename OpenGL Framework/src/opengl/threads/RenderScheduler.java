package opengl.threads;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import opengl.Window;

/**
 * This is a scheduler that will take care of all rendering of its window.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class RenderScheduler extends Thread implements Runnable {
	
	private Window window;
	private int delay;
	private long last;
	
	public RenderScheduler(Window window, int delay) {
		this.window = window;
		this.delay = delay;
	}
	
	
	@Override
	public void run() {
		last = System.currentTimeMillis();
		while (!window.shouldClose()) {
			
		}
	}
	
	
	
	public void render() {
		synchronized (window.glfwContextLock) {
			glfwMakeContextCurrent(window.getWindowHandle());
			
			glfwMakeContextCurrent(NULL);
		}
	}
	
}
