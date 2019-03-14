/**
 * 
 */
package opengl.components;

import java.io.IOException;

import opengl.Window;
import opengl.math.Vector2d;
import opengl.rendering.RenderUtils;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;
import opengl.threads.RenderScheduler;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Panel extends Container {
	
	private Vector2d position;
	private Vector2d dimension;
	Texture t, t1, t2, t3, t4;
	
	public Panel() {
		this(0, 0, 0, 0);
	}
	
	public Panel(double x, double y) {
		this(x, y, 0, 0);
	}
	
	public Panel(double x, double y, double w, double h) {
		position = new Vector2d(x, y);
		dimension = new Vector2d(w, h);
	}
	
	@Override
	public void renderSelf(Window window, double xOff, double yOff) {
		
		if (!window.getTextureAtlas().isBound()) window.getTextureAtlas().bind();
		
		//RenderUtils.renderTexture(RenderScheduler.smile, 250, 250, 150, 150);
		
		RenderUtils.renderText(window.getWindowHandle(), "Hello There", 50, 50);
		
	}

	@Override
	public void update(Window window) {
		
	}
	
	
	
}
