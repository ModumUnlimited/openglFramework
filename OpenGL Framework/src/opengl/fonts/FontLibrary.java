package opengl.fonts;

import java.awt.Font;
import java.util.ArrayList;

import opengl.rendering.RenderUtils;
import opengl.textures.TextureAtlas;

public class FontLibrary {
	
	public ArrayList<GLFont> activeFonts = new ArrayList<>();
	
	private TextureAtlas atlas;
	
	public FontLibrary(TextureAtlas atlas) {
		this.atlas = atlas;
		addFont(new Font("Calibri", Font.PLAIN, 256));
		
		RenderUtils.setFont(activeFonts.get(0));
		RenderUtils.setColor(1, 1, 1, 1);
		RenderUtils.setFontSize(16);
	}

	public GLFont addFont(Font font) {
		GLFont glfont = new GLFont(font, 32, this.atlas);
		activeFonts.add(glfont);
		return glfont;
	}
	
	public GLFont addFont(String fontName, int size, int style) {
		GLFont glfont = new GLFont(fontName, size, style, atlas);
		activeFonts.add(glfont);
		return glfont;
	}
	
	public void removeFont(GLFont font) {
		activeFonts.remove(font);
		font.removeFromAtlas();
	}
	
	

}
