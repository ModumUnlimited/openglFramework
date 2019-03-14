package opengl.rendering;

import opengl.fonts.GLFont;
import opengl.textures.Texture;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {
	
	public static void renderText(float x, float y, GLFont font, String string) {
		char[] chars = string.toCharArray();
		float xOff = 0;
		for (int i = 0; i < chars.length; i++) {
			Texture g = font.getGlyph(chars[i]);
			float w = g.getWidth() * font.getSize();
			float o = g.getHOffset();
			
			glBegin(GL_QUADS);{
				glTexCoord2i(g.getX1i(), g.getY1i());
				glVertex2f(xOff + x, y-o);

				glTexCoord2i(g.getX1i() + g.getWidth(), g.getY1i());
				glVertex2f(xOff + x + w, y-o);

				glTexCoord2i(g.getX1i() + g.getWidth(), g.getY1i() + g.getHeight());
				glVertex2f(xOff + x + w, y - o + g.getHeight()*font.getSize());

				glTexCoord2i(g.getX1i(), g.getY1i() + g.getHeight());
				glVertex2f(xOff + x, y - o + g.getHeight()*font.getSize());
			}glEnd();
			
			xOff += w*font.getSize();
		}
	}
	
}
