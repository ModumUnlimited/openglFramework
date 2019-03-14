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
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import opengl.Window;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

public class GLFont {
	
	private final Font font;
	private final TextureAtlas atlas;
	
	private static final float FONT_SIZE_SCALE_FACTOR = 8;
	private static final int offset = 31;
	private static final BufferedImage sImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	
	private String name;
	
	private Texture[] glyphs = new Texture[256];
	private float fontSize = 16;
	private float glyphPadding = 0.05f;
	
	private float red = 0;
	private float green = 0;
	private float blue = 0;
	private float alpha = 1;
	
	public GLFont(Font font, float size, TextureAtlas atlas) {
		this.font = font;
		this.name = font.getFontName();
		this.atlas = atlas;
		setupTextures();
	}
	
	public GLFont(String name, int size, int style, TextureAtlas atlas) {
		this(new Font(name, style, 256), size, atlas);
		this.name = name;
	}
	
	private GLFont(Font font, TextureAtlas atlas,  Texture[] glyphs, float size) {
		this.font = font;
		this.atlas  = atlas;
		this.glyphs = glyphs;
		this.fontSize = size;
		setupTextures();
	}
	
	public String getName() {
		return this.name;
	}
	
	public GLFont deriveFont(GLFont font, float size) {
		return new GLFont(this.font, this.atlas, this.glyphs, size);
	}
	
	public Texture getGlyph(char c) {
		return glyphs[c];
	}
	
	public void removeFromAtlas() {
		for (int i = 1; i < 256; i++) {
			if (glyphs[i] != glyphs[0]) this.atlas.remove(glyphs[i]);
		}
		this.atlas.remove(glyphs[0]);
	}
	
	private void setupTextures() {
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
		g.setColor(new Color(0, 0, 0, 0));
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
			if ((img.getRGB(x, y) & 0xFF000000) != 0) {
				found = true;
				if (x < left) left = x;
				if (y < top) top = y;
				
				if (x > right) right = x+1;
				if (y > bottom) bottom = y+1;
			}
		}

		g.dispose();
		
		if (c != 0) glyphs[c] = found ? new Texture(img.getSubimage(left, top, right-left, bottom-top), atlas) : glyphs[0];
		else glyphs[c] = new Texture(img, atlas);
	}
	
	public Texture[] getGlyphs() {
		return glyphs;
	}
	
	public void addToAtlas() {
		atlas.addTexture(glyphs[0]);
		for (Texture tex : glyphs) {
			if (tex != glyphs[0]) atlas.addTexture(tex);
		}
	}
	
	public void setSize(float fontSize) {
		this.fontSize = fontSize;
	}
	
	public float getSize() {
		return this.fontSize;
	}
	
	public float getSizeMultiplier() {
		return this.fontSize / FONT_SIZE_SCALE_FACTOR;
	}
	
	public float getPadding() {
		return this.glyphPadding / FONT_SIZE_SCALE_FACTOR;
	}
	
	public void setColor4f(float r, float g, float b, float a) {
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
	}
	
	public void setColor3f(float r, float g, float b) {
		this.setColor4f(r, g, b, 1);
	}
	
	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public float getAlpha() {
		return alpha;
	}
	
}
