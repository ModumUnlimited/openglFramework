package opengl.fonts;

import java.awt.Font;
import java.util.ArrayList;

import opengl.textures.TextureAtlas;

public class FontLibrary {
	
	public ArrayList<GLFont> activeFonts = new ArrayList<>();
	private ArrayList<GLFont> removed = new ArrayList<>();
	private ArrayList<GLFont> added = new ArrayList<>();
	
	private TextureAtlas atlas;
	
	public FontLibrary(TextureAtlas atlas) {
		this.atlas = atlas;
		addFont(new Font("Calibri", Font.PLAIN, 128));
		insertToAtlas();
	}
	
	public void insertToAtlas() {
		for (GLFont font : removed) {
			activeFonts.remove(font);
			font.removeFromAtlas();
		}
		removed.clear();
		for (GLFont font : added) {
			activeFonts.add(font);
			font.addToAtlas();
		}
		added.clear();
		atlas.createAtlas();
	}

	public GLFont addFont(Font font) {
		GLFont glfont = new GLFont(font, 32, this.atlas);
		added.add(glfont);
		return glfont;
	}
	
	public GLFont addFont(String fontName, int size, int style) {
		GLFont glfont = new GLFont(fontName, size, style, atlas);
		added.add(glfont);
		return glfont;
	}
	
	public void removeFont(GLFont font) {
		removed.add(font);
	}
	
	

}
