package opengl.updateing;

import opengl.Identifiable;
import opengl.Window;

/**
 * This is the interface of every object that needs to be updated logically.
 * This is completely separate from rendering.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class IUpdatable extends Identifiable {
	
	/**
	 * Updates this object in the scope of the window it belongs to.
	 * 
	 * @param window The window that triggered this updating routine.
	 */
	public abstract void update(Window window);

}
