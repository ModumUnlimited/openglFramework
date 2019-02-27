package opengl.threads;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import opengl.Window;
import opengl.components.Panel;

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
		setName("SCHEDULER-RENDER");
	}
	
	
	@Override
	public void run() {
		last = System.currentTimeMillis();
		window.debug("Renderer started.");
		window.open();
		synchronized (window.glfwContextLock) {
			glfwMakeContextCurrent(window.getWindowHandle());
			GL.createCapabilities();
			glEnable(GL_TEXTURE_2D);
			window.setContentPane(new Panel(0, 0, window.ref.WINDOW_WIDTH, window.ref.WINDOW_HEIGHT));
			glfwMakeContextCurrent(NULL);
		}
		
		// Incase someone already waits for the window to open, notify him
		synchronized (window.windowInitLock) {
			window.info("Window ready!");
			window.setInit();
			window.windowInitLock.notifyAll();
		}
		glfwSwapInterval(0);
		while (!window.shouldClose()) {
			synchronized (this) {
				glfwPollEvents();
				
				render();
				
				glfwSwapBuffers(window.getWindowHandle());
				ThreadUtil.sync(window, last, delay);
			}
		}
	}
	
	
	
	public void render() {
		synchronized (window.glfwContextLock) {
			glfwMakeContextCurrent(window.getWindowHandle());
			window.getContentPane().render(window);
			glfwMakeContextCurrent(NULL);
		}
	}


	/**
	 * @param renderDelay
	 */
	public synchronized void setDelay(int renderDelay) {
		this.delay = renderDelay;
	}
	
}