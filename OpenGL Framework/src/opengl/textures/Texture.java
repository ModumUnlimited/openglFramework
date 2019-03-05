package opengl.textures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Texture class. This contains all necessary data needed to render a texture
 * (including the benefit of texture atlas performance).
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Texture implements Comparable<Texture> {
	
	private TextureAtlas atlas;
	
	private final int textureID;
	
	private int width;
	private int height;
	private int hOffset;
	private Image img;
	private String toDraw;
	private Font font;
	
	private boolean hasImage = true;
	
	private TextureAtlasRectangle rectangle;
	
	public Texture(int width, int height, Image img, TextureAtlas atlas) {
		this.width = width;
		this.height = height;
		this.atlas = atlas;
		this.img = img;
		this.textureID = atlas.addTexture(this);
	}
	
	public Texture(int width, int height, TextureAtlas atlas, Image img, Font font, String toDraw, int hOffset, boolean hasImage) {
		this.hasImage = hasImage;
		this.img = img;
		this.toDraw = toDraw;
		this.atlas = atlas;
		this.width = width;
		this.height = height;
		this.hOffset = hOffset;
		this.font = font;
		this.textureID = atlas.addTexture(this);
	}
	
	public Texture(Image img, TextureAtlas atlas) {
		this(img.getWidth(null), img.getHeight(null), img, atlas);
	}
	
	public Texture(File textureFile, TextureAtlas atlas) throws IOException {
		this(ImageIO.read(textureFile), atlas);
	}
	
	public Texture(String path, TextureAtlas atlas) throws IOException {
		this(ImageIO.read(new File(path)), atlas);
	}
	
	public int getTextureID() {
		return this.textureID;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Image getImage() {
		return img;
	}
	
	public double getX1d() {
		return (double) this.getX1i() / (double) this.atlas.getSize();
	}
	
	public double getY1d() {
		return (double) this.getY1i() / (double) this.atlas.getSize();
	}
	
	public double getX2d() {
		return (double) this.getX2i() / (double) this.atlas.getSize();
	}
	
	public double getY2d() {
		return (double) this.getY2i() / (double) this.atlas.getSize();
	}
	
	
	public int getX1i() {
		return this.rectangle.left;
	}

	public int getY1i() {
		return this.rectangle.top;
	}

	public int getX2i() {
		return this.rectangle.right;
	}

	public int getY2i() {
		return this.rectangle.bottom;
	}
	
	public void setRect(TextureAtlasRectangle rect) {
		this.rectangle = rect;
	}

	/**
	 * @param g
	 */
	public void drawToAtlas(Graphics g) {
		if (hasImage) g.drawImage(this.img, this.rectangle.left, this.rectangle.top, null);
		else {
			g.setFont(this.font);
			g.setColor(Color.BLACK);
			g.fillRect(this.rectangle.left, this.rectangle.top, width, height);
			g.setColor(Color.WHITE);
			g.drawString(this.toDraw, this.rectangle.left, this.rectangle.top + this.hOffset);
		}
	}
	
	public int compareTo(Texture o) {
		return getWidth()*getHeight() - o.getWidth()-o.getHeight();
	}
}
