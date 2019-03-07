package opengl.rendering;

import opengl.fonts.GLFont;
import opengl.textures.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;


/**
 * This is a collection of utility
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class RenderUtils {
	
	private static GLFont activeFont;
	private static float[] rgb = new float[4];
	private static float fontSize = 16;
	
	public static void setFontColor(float r, float g, float b, float a) {
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
		rgb[3] = a;
	}
	
	public static void setFont(GLFont font) {
		activeFont = font;
	}
	
	public static void setFontSize(float size) {
		fontSize = size;
	}
	
	public static void renderText(long window, String string, double x, double y) {
		float xOff = 0;

		int[] wWidth = new int[1];
		int[] wHeight = new int[1];
		
		glfwGetFramebufferSize(window, wWidth, wHeight);
		
		char[] chars = string.toCharArray();
		Texture[] glyphs = activeFont.getGlyphs();
		
		for (int i = 0; i < chars.length; i++) {
			Texture g = glyphs[chars[i]];

			double tx1 = g.getX1d();
			double ty1 = g.getY1d();
			double tx2 = g.getX2d();
			double ty2 = g.getY2d();
			
			double w = g.getWidth() * ((fontSize / 10) / wWidth[0]);
			double h = g.getHeight() * ((fontSize / 10) / wHeight[0]);
			double b = g.getFontBaseline() * ((fontSize / 10) / wHeight[0]);
			
			glBegin(GL_QUADS);
				glColor4f(rgb[0], rgb[1], rgb[2], rgb[3]);
				//top left
				glTexCoord2d(tx1, ty1);
				glVertex2d(xOff + x, -(y - h + b));
				
				//top right
				glTexCoord2d(tx2, ty1);
				glVertex2d(xOff + x + w, -(y - h + b));
				
				//bottom right
				glTexCoord2d(tx2, ty2);
				glVertex2d(xOff + x + w, -(y + b));
				
				//bottom left
				glTexCoord2d(tx1, ty2);
				glVertex2d(xOff + x, -(y + b));
				
				glColor4f(0, 0, 0, 0);
			glEnd();
			
			xOff += w + activeFont.getPadding();
		}
	}

	/**
	 * @param windowHandle
	 * @param t
	 * @param f
	 * @param g
	 * @param d
	 * @param e
	 */
	public static void renderTexture(long window, Texture t, float x, float y, double w, double h) {

		int[] wWidth = new int[1];
		int[] wHeight = new int[1];
		
		glfwGetFramebufferSize(window, wWidth, wHeight);

		double tx1 = t.getX1d();
		double ty1 = t.getY1d();
		double tx2 = t.getX2d();
		double ty2 = t.getY2d();

		double x1 = (x / (double) wWidth[0])*2 - 1;
		double y1 = (y / (double) wHeight[0])*2 - 1;
		double x2 = ((x+w) / (double) wWidth[0])*2 - 1;
		double y2 = ((y+h) / (double) wHeight[0])*2 - 1;
		
		System.out.println("(" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")");
		
		glBegin(GL_QUADS);
			//top left
			glTexCoord2d(tx1, ty1);
			glVertex2d(x1, y1);
			
			//top right
			glTexCoord2d(tx2, ty1);
			glVertex2d(x2, y1);
			
			//bottom right
			glTexCoord2d(tx2, ty2);
			glVertex2d(x2, y2);
			
			//bottom left
			glTexCoord2d(tx1, ty2);
			glVertex2d(x1, y2);
			
			glColor4f(0, 0, 0, 0);
		glEnd();
		
	}
	
}
