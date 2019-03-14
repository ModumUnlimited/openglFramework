package opengl.components;

import opengl.Window;
import opengl.math.Vector2d;

import opengl.rendering.RenderUtils;
import opengl.textures.Texture;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
	Texture t, t1, t2, t3, t4;
	
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
		
		//RenderUtils.renderTexture(RenderScheduler.smile, 250, 250, 150, 150);
		
		RenderUtils.renderText(window.getWindowHandle(), "Hello There", 50, 50);
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
