package opengl.rendering;

import opengl.Identifiable;

/**
 * This is the interface of every object that can be rendered to a window.
 * It contains a collection of methods that make all necessary operations
 * possible.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class IRenderable extends Identifiable {
	
	/**
	 * This method is the entry point of all rendering/drawing operations needed to
	 * display this object
	 * @param window The glfwWindow that this object is drawn into.
	 */
	public abstract void render(long window);
	
}
