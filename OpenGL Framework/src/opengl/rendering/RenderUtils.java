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
			
			double w = g.getWidth() * ((fontSize / 30) / wWidth[0]);
			double h = g.getHeight() * ((fontSize / 30) / wHeight[0]);
			double b = g.getFontBaseline() * ((fontSize / 30) / wHeight[0]);
			
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
				
				glColor4f(1, 1, 1, 1);
			glEnd();
			
			xOff += w + activeFont.getPadding();
		}
	}
	
	public static void renderTexture(Texture t) {
		
		double x1 = t.getX1d();
		double x2 = t.getX2d();
		double y1 = t.getY1d();
		double y2 = t.getY2d();
		
		glBegin(GL_QUADS);
			glTexCoord2d(x1, y1);
			glVertex2f(-0.5f, 0.5f);
			
			glTexCoord2d(x2, y1);
			glVertex2f(0.5f, 0.5f);
			
			glTexCoord2d(x2, y2);
			glVertex2f(0.5f, -0.5f);
			
			glTexCoord2d(x1, y2);
			glVertex2d(-0.5f, -0.5f);
			glColor4f(0, 0, 0, 0);
		glEnd();
		
	}
	
}
