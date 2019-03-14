/**
 * 
 */
package opengl.components;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.textures.TextureAtlas;

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
	
	protected Window window;
	
	protected Vector2d position;
	protected Vector2d dimension;
	
	public Component() {
		this.id = getNewID();
	}
	
	public abstract void render(Window window, double xOff, double yOff);
	public abstract void update(Window window);
	
	public void setAtlas(TextureAtlas atlas) {}
	public void setupTextures() {}
	
	public final void setWindow(Window window) {
		this.window = window;
	}
 	
	public final int getID() {
		return id;
	}
	
	private static synchronized final int getNewID() {
		int out = components;
		components++;
		return out;
	}
	
}
