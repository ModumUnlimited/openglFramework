/**
 * 
 */
package opengl.callbacks;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

import opengl.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class FramebufferSizeCallback extends GLFWFramebufferSizeCallback {
	
	private final Window window;
	
	public FramebufferSizeCallback(Window window) {
		this.window = window;
	}
	
	@Override
	public void invoke(long window, int width, int height) {
		if (window == this.window.getWindowHandle()) {
			synchronized (Window.glfwContextLock) {
				glfwMakeContextCurrent(window);
				
				glMatrixMode(GL_PROJECTION);
				glViewport(0, 0, width, height);
				
				
				glfwMakeContextCurrent(0);
			}
		}
	}

}
