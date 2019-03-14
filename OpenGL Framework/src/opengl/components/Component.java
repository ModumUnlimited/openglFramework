/**
 * 
 */
package opengl.components;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.textures.TextureAtlas;

import static org.lwjgl.opengl.GL11.*;

/**
 * This is a representation of a GUI component, much like the ones you find in
 * this swing library. The main difference is, that these components are drawn
 * using the OpenGL interface on the systems GPU.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Component {

	private static int components = 0;
	private final int id;
	private TextureAtlas atlas = null;
	
	protected Window window;

	protected Vector2d position;
	protected Vector2d dimension;

	protected float red = 0.7f;
	protected float green = 0.7f;
	protected float blue = 0.7f;
	protected float alpha = 0.7f;
	
	public Component() {
		this(0, 0, 0, 0);
	}
	
	public Component(double x, double y) {
		this(x, y, 0, 0);
	}
	
	public Component(double x, double y, double w, double h) {
		this.id = getNewID();
		position = new Vector2d(x, y);
		dimension = new Vector2d(w, h);
	}
	
	public void setBackgroundColor(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public void render(Window window, double xOff, double yOff) {
		/*glColor4f(red, green, blue, alpha);
		
		glBegin(GL_QUADS);
			glVertex2d(position.getX(), position.getY());
			glVertex2d(position.getX() + dimension.getX(), position.getY());
			glVertex2d(position.getX() + dimension.getX(), position.getY() + dimension.getY());
			glVertex2d(position.getX(), position.getY() + dimension.getY());
		glEnd();*/
	}
	
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
