package opengl.components;

import static org.lwjgl.opengl.GL11.*;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.rendering.RenderUtils;
import opengl.threads.RenderScheduler;

public class Button extends Component {
	
	private boolean hovered = false;
	private String text;
	
	public Button(double x, double y, double w, double h, String text) {
		this.text = text;
		this.position = new Vector2d(x, y);
		this.dimension = new Vector2d(w, h);
	}

	@Override
	public void render(Window window, double xOff, double yOff) {
		if (hovered) {
			glColor4f(0.75f, 0.75f, 1f, 1f);
		} else {
			glColor4f(0.8f, 0.8f, 0.8f, 1);
		}
		System.out.println(hovered);
		
		glBegin(GL_QUADS);
			glVertex2d(xOff + position.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
		glEnd();
		
		glColor4f(0.25f, 0.25f, 0.25f, 1);
		glBegin(GL_LINES);
			glVertex2d(xOff + position.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
	
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
	
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY());
		glEnd();
		
		//RenderUtils.setColor(0, 1, 0, 1);
		//RenderUtils.renderText(window.getWindowHandle(), text, xOff + position.getX()+200, yOff + position.getY()+200);
		
		RenderUtils.setColor(1, 1, 1, 1);
		RenderUtils.renderTexture(RenderScheduler.smile, (float) (xOff + position.getX()+200), (float) (yOff + position.getY()+400), 250, 250);
	}

	@Override
	public void update(Window window) {
		hovered = window.mousex >= position.getX() && window.mousex <= position.getX() + dimension.getX() &&
				  window.mousey >= position.getY() && window.mousey <= position.getY() + dimension.getY();
	}

}
