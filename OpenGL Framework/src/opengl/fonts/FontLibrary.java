package opengl.fonts;

import java.awt.Font;

import opengl.textures.TextureAtlas;

public class FontLibrary {
	
	public static GLFont timesNewRoman;

	public static void setup(TextureAtlas atlas) {
		timesNewRoman = new GLFont(new Font("Times New Roman", Font.PLAIN, 64), atlas);
	}

}
