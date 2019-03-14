package opengl.components;

import opengl.Window;
import opengl.math.Vector2d;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
	public Panel() {
		super();
	}
	
	public Panel(double x, double y) {
		super(x, y);
	}
	
	public Panel(double x, double y, double w, double h) {
		super(x, y, w, h);
	}
	
	@Override
	public void renderSelf(Window window, double xOff, double yOff) {
		//glColor4f(red, green, blue, alpha);
		
		glBegin(GL_QUADS);
			glVertex2d(xOff + position.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
		glEnd();
		
	}

	@Override
	public void update(Window window) {
		synchronized (children) {
			for (Component comp : children) comp.update(window);
		}
	}
	
	
	
}
