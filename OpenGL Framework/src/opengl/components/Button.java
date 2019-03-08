package opengl.components;

import static org.lwjgl.opengl.GL11.*;

import opengl.Window;
import opengl.rendering.RenderUtils;

public class Button extends Component {
	
	private boolean hovered = false;
	private String text;
	
	public Button(double x, double y, double w, double h, String text) {
		super(x, y, w, h);
		this.text = text;
	}

	@Override
	public void render(Window window, double xOff, double yOff) {
		super.render(window, xOff, yOff);
		
		if (hovered) {
			glColor4f(0.75f, 0.75f, 1f, 1f);
			glBegin(GL_QUADS);
				glVertex2d(xOff + position.getX(), yOff + position.getY());
				glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
				glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
				glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
			glEnd();
		}
		
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
		
		RenderUtils.setColor(0, 1, 0, 1);
		RenderUtils.renderText(window.getWindowHandle(), text, xOff + position.getX()+200, yOff + position.getY()+200);
	}

	@Override
	public void update(Window window) {
		hovered = window.mousex >= position.getX() && window.mousex <= position.getX() + dimension.getX() &&
				  window.mousey >= position.getY() && window.mousey <= position.getY() + dimension.getY();
	}

}
