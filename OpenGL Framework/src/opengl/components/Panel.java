/**
 * 
 */
package opengl.components;

import java.io.IOException;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.textures.Texture;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
	private Vector2d position;
	private Vector2d dimension;
	Texture t;
	
	public Panel() {
		this(0, 0, 0, 0);
	}
	
	public Panel(double x, double y) {
		this(x, y, 0, 0);
	}
	
	public Panel(double x, double y, double w, double h) {
		position = new Vector2d(x, y);
		dimension = new Vector2d(w, h);
		try {
			t = new Texture("smile.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void renderSelf(Window window) {
		t.bind();
		
	

		glBegin(GL_QUADS);
			//glTexCoord2f(0, 0);
			glColor4d(1, 0, 0, 1);
			glVertex2f(-0.5f, 0.5f);
			//glTexCoord2f(1, 0);
			glColor4d(0, 1, 0, 1);
			glVertex2f(0.5f, 0.5f);
			//glTexCoord2f(1, 1);
			glColor4d(0, 0, 1, 1);
			glVertex2f(0.5f, -0.5f);
			//glTexCoord2f(0, 1);
			glColor4d(1, 1, 1, 1);
			glVertex2f(-0.5f, -0.5f);
		glEnd();
		
		t.unbind();
	}

	@Override
	public void update() {
		
	}
	
	
	
}
