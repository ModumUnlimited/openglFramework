/**
 * 
 */
package opengl.callbacks;

import org.lwjgl.glfw.GLFWErrorCallback;

import opengl.Window;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class ErrorCallback extends GLFWErrorCallback {

	private Window window;
	
	public ErrorCallback(Window window) {
		this.window = window;
	}
	
	@Override
	public void invoke(int error, long description) {
		window.error(GLFWErrorCallback.getDescription(description));
	}

}
