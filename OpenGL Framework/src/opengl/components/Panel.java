package opengl.components;

import java.io.IOException;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.rendering.RenderUtils;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
	private Vector2d position;
	private Vector2d dimension;
	Texture t, t1, t2, t3, t4;
	TextureAtlas atlas;
	
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
			atlas = Window.textures;
			t = new Texture("smile.png", atlas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void renderSelf(Window window) {
		
		if (!atlas.isBound()) atlas.bind();
		
		RenderUtils.renderTexture(t);
		
		if (Math.abs(window.getNormalizedMouseX()) <= 0.5f && Math.abs(window.getNormalizedMouseY()) <= 0.5f) {
			RenderUtils.setColor(0.5f, 0.5f, 1, 1);
		} else {
			RenderUtils.setColor(1, 1, 1, 1);
		}
		
		RenderUtils.renderText(window.getWindowHandle(), "Hello There", -0.6d, -0.6d);
		
		
	}

	@Override
	public void update() {
		
	}
	
	
	
}
