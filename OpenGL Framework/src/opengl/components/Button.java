package opengl.components;

import static org.lwjgl.opengl.GL11.*;

import opengl.Window;
import opengl.rendering.RenderUtils;

public class Button extends Component {
	
	private boolean hovered = false;
	
	public Button(double x, double y, double w, double h) {
		super(x, y, w, h);
	}

	@Override
	public void render(Window window, double xOff, double yOff) {
		super.render(window, xOff, yOff);
		glColor4f(0.25f, 0.25f, 0.25f, 1);
		/*
		glBegin(GL_LINES);
			glVertex2d(xOff + position.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY());
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
	
			glVertex2d(xOff + position.getX() + dimension.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
	
			glVertex2d(xOff + position.getX(), yOff + position.getY() + dimension.getY());
			glVertex2d(xOff + position.getX(), yOff + position.getY());
		glEnd();*/
		
		RenderUtils.setColor(1, 0, 0, 1);
		RenderUtils.setFontSize(32);
		RenderUtils.renderText(window.getWindowHandle(), "Hello There", 150, 150);
	}

	@Override
	public void update(Window window) {
	}

}
