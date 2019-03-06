package opengl.fonts;

import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import opengl.Window;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

public class GLFont {
	
	private final Font font;
	private final TextureAtlas atlas;
	
	private static final int offset = 31;
	private static final BufferedImage sImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	
	private Texture[] glyphs = new Texture[256];
	private float fontSize = 16;
	
	public GLFont(Font font, float size, TextureAtlas atlas) {
		this.font = font;
		this.atlas = atlas;
		setupTextures();
	}
	
	public GLFont(String name, int size, int style, TextureAtlas atlas) {
		this(new Font(name, style, 128), size, atlas);
	}
	
	private GLFont(Font font, TextureAtlas atlas,  Texture[] glyphs, float size) {
		this.font = font;
		this.atlas  = atlas;
		this.glyphs = glyphs;
		this.fontSize = size;
		setupTextures();
	}
	
	public GLFont deriveFont(GLFont font, float size) {
		return new GLFont(this.font, this.atlas, this.glyphs, size);
	}
	
	public void removeFromAtlas() {
		for (int i = 1; i < 256; i++) {
			if (glyphs[i] != glyphs[0]) this.atlas.remove(glyphs[i]);
		}
		this.atlas.remove(glyphs[0]);
	}
	
	private void setupTextures() {
		System.out.println("Setting up font textures");
		createGlyph('\0');
		for (char c = 31; c < 128; c++) createGlyph(c);
		for (char c = 161; c < 256; c++) createGlyph(c);
		
		for (int i = 0; i < glyphs.length; i++) if (glyphs[i] == null) glyphs[i] = glyphs['\0'];
		
	}
	
	private void createGlyph(char c) {
		FontRenderContext frc = sImg.createGraphics().getFontRenderContext();
		LineMetrics metrics = font.getLineMetrics("" + c, frc);
		int height = (int) Math.round(font.getStringBounds("" + c, frc).getHeight());
		int width = (int) Math.round(font.getStringBounds("" + c, frc).getWidth());
		
		BufferedImage img = new BufferedImage(width > 0 ? width : 16, height > 0 ? height : 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.createGraphics();
		g.setFont(this.font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.drawString("" + c, 0, Math.round(height - metrics.getDescent() - 3));
		
		
		int[] rgb = new int[width*height];
		img.getRGB(0, 0, width, height, rgb, 0, width);
		
		
		int top, bottom, left, right;
		top = left = Integer.MAX_VALUE;
		bottom = right = 0;
		
		boolean found = false;
		
		for (int x = 0; x < width; x++) for (int y = 0; y < height; y++) {
			if (img.getRGB(x, y) != Color.BLACK.getRGB()) {
				found = true;
				if (x < left) left = x;
				if (y < top) top = y;
				
				if (x > right) right = x+1;
				if (y > bottom) bottom = y+1;
			}
		}

		g.dispose();
		
		if (c != 0) glyphs[c] = found ? new Texture(img.getSubimage(left, top, right-left, bottom-top), atlas) : glyphs[0].nonRenderCopy();
		else glyphs[c] = new Texture(img, atlas);
	}

	/**
	 * 
	 */
	public void addToAtlas() {
		atlas.addTexture(glyphs[0]);
		for (Texture tex : glyphs) {
			if (tex != glyphs[0]) atlas.addTexture(tex);
		}
	}
	
}
