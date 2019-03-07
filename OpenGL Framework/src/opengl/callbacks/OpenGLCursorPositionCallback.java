/**
 * 
 */
package opengl.callbacks;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import opengl.Window;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class OpenGLCursorPositionCallback extends GLFWCursorPosCallback {

	private Window window;
	
	public OpenGLCursorPositionCallback(Window window) {
		this.window = window;
	}
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		if (window == this.window.getWindowHandle()) {
			this.window.mousedx = xpos - this.window.mousex;
			this.window.mousedy = ypos - this.window.mousey;
			
			this.window.mousex = xpos;
			this.window.mousey = ypos;
		}
	}

}
