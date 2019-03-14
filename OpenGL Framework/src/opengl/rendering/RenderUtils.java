package opengl.rendering;

import opengl.fonts.GLFont;
import opengl.textures.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;


/**
 * This is a collection of utility
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class RenderUtils {
	
	private static GLFont activeFont;
	private static final float[] rgb = new float[4];
	private static float fontSize = 16;
	
	public static void setColor(float r, float g, float b, float a) {
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
			
<<<<<<< HEAD
			double w = g.getWidth() * (fontSize * activeFont.getSizeMultiplier());
			double h = g.getHeight() * (fontSize * activeFont.getSizeMultiplier());
			double b = g.getFontBaseline() * (fontSize * activeFont.getSizeMultiplier());
=======
			double w = g.getWidth() * (fontSize / (wWidth[0] / 8));
			double h = g.getHeight() * (fontSize / (wHeight[0] / 8));
			double b = g.getFontBaseline() * (fontSize / ( wHeight[0] / 8));
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
			
			glBegin(GL_QUADS);
				//glColor4f(rgb[0], rgb[1], rgb[2], rgb[3]);
				//top left
				glTexCoord2d(tx1, ty1);
				glVertex2d(xOff + x, y - h + b);
				
				//top right
				glTexCoord2d(tx2, ty1);
				glVertex2d(xOff + x + w, y - h + b);
				
				//bottom right
				glTexCoord2d(tx2, ty2);
				glVertex2d(xOff + x + w, y + b);
				
				//bottom left
				glTexCoord2d(tx1, ty2);
				glVertex2d(xOff + x, y + b);
				
				//glColor4f(1, 1, 1, 1);
			glEnd();
			
			xOff += w + activeFont.getPadding();
		}
	}
	
<<<<<<< HEAD
	public static void renderTexture(Texture t, double x, double y, double w, double h) {
=======
	public static void renderTexture(Texture t, float x, float y, float w, float h) {
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
		
		double x1 = t.getX1d();
		double x2 = t.getX2d();
		double y1 = t.getY1d();
		double y2 = t.getY2d();
		
		glBegin(GL_QUADS);
			//glColor4f(rgb[0], rgb[1], rgb[2], rgb[3]);
			
			glTexCoord2d(x1, y1);
<<<<<<< HEAD
			glVertex2d(x, y);
=======
			glVertex2f(x, y);
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
			
			glTexCoord2d(x2, y1);
<<<<<<< HEAD
			glVertex2d(x+w, y);
=======
			glVertex2f(x+w, y);
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
			
			glTexCoord2d(x2, y2);
<<<<<<< HEAD
			glVertex2d(x+w, y+h);
=======
			glVertex2f(x+w, y+h);
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
			
			glTexCoord2d(x1, y2);
			glVertex2d(x, y+h);
<<<<<<< HEAD
			glColor4f(0, 0, 0, 0);
=======
>>>>>>> refs/remotes/origin/refactoring/rewriting_for_component_based_approach
		glEnd();
		
	}
	
}
