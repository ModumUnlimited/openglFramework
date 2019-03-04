package opengl.fonts;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

public class GLFont {
	
	private final Font font;
	private final TextureAtlas atlas;
	
	private static final int offset = 31;
	private static final BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	
	private Texture[] glyphs = new Texture[256];
	
	public GLFont(Font font, TextureAtlas atlas) {
		this.font = font;
		this.atlas = atlas;
		setupTextures();
	}
	
	public GLFont(String name, int size, int style, TextureAtlas atlas) {
		this(new Font(name, style, size), atlas);
	}
	
	private void setupTextures() {
		createGlyph('\0');
		for (char c = 31; c < 128; c++) {
			createGlyph(c);
		}
		
		for (int i = 0; i < glyphs.length; i++) if (glyphs[i] == null) glyphs[i] = glyphs['\0'];
		
		atlas.createAtlas();
		
		try {
			ImageIO.write(atlas.getImage(), "png", new File("charset.png"));
		} catch(IOException e) {}
	}
	
	private void createGlyph(char c) {
		FontRenderContext frc = img.createGraphics().getFontRenderContext();
		LineMetrics metrics = font.getLineMetrics("" + c, frc);
		int height = (int) Math.round(Math.ceil(metrics.getAscent() + metrics.getDescent()));
		int width = (int) Math.round(font.getStringBounds("" + c, frc).getWidth());
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setFont(this.font);
		g.drawString("" + c, 0, 0);
		glyphs[c] = new Texture(img, this.atlas);
	}
	
}
