package opengl.components;

import opengl.Window;
import opengl.math.Vector2d;
<<<<<<< HEAD
import opengl.rendering.RenderUtils;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;
import opengl.threads.RenderScheduler;
=======
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
<<<<<<< HEAD
	private Vector2d position;
	private Vector2d dimension;
	Texture t, t1, t2, t3, t4;
	
=======
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
	public Panel() {
		super();
	}
	
	public Panel(double x, double y) {
		super(x, y);
	}
	
	public Panel(double x, double y, double w, double h) {
<<<<<<< HEAD
		position = new Vector2d(x, y);
		dimension = new Vector2d(w, h);
=======
		super(x, y, w, h);
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
	}
	
	@Override
	public void renderSelf(Window window, double xOff, double yOff) {
<<<<<<< HEAD
=======
		//glColor4f(red, green, blue, alpha);
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
		
<<<<<<< HEAD
		if (!window.getTextureAtlas().isBound()) window.getTextureAtlas().bind();
		
		//RenderUtils.renderTexture(RenderScheduler.smile, 250, 250, 150, 150);
		
		RenderUtils.renderText(window.getWindowHandle(), "Hello There", 50, 50);
=======
		glBegin(GL_QUADS);
			glVertex2d(xOff + position.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
		glEnd();
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
		
	}

	@Override
	public void update(Window window) {
<<<<<<< HEAD
		
=======
		synchronized (children) {
			for (Component comp : children) comp.update(window);
		}
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
	}
	
	
	
}
