/**
 * 
 */
package opengl.components;

import static opengl.components.Component.*;

import opengl.Window;

/**
 * This is a representaion of a GUI component, much like the ones you find in
 * this swing library. The main difference is, that these components are drawn
 * using the OpenGL interfacce on the systems GPU.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Component {

	private static int components = 0;
	private final int id;
	
	public Component() {
		this.id = getNewID();
	}
	
	public abstract void render(Window window);
	public abstract void update();
	
	public final int getID() {
		return id;
	}
	
	private static synchronized final int getNewID() {
		int out = components;
		components++;
		return out;
	}
	
}
