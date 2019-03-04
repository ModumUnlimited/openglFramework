package opengl.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Texture class. This contains all necessary data needed to render a texture
 * (including the benefit of texture atlas performance).
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Texture {
	
	private TextureAtlas atlas;
	
	private int width;
	private int height;
	private Image img;
	
	private TextureAtlasRectangle rectangle;
	
	public Texture(int width, int height, Image img, TextureAtlas atlas) {
		this.width = width;
		this.height = height;
		this.atlas = atlas;
		this.img = img;
		atlas.addTexture(this);
	}
	
	public Texture(BufferedImage img, TextureAtlas atlas) {
		this(img.getWidth(), img.getHeight(), img, atlas);
	}
	
	public Texture(File textureFile, TextureAtlas atlas) throws IOException {
		this(ImageIO.read(textureFile), atlas);
	}
	
	public Texture(String path, TextureAtlas atlas) throws IOException {
		this(ImageIO.read(new File(path)), atlas);
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
		return (double) this.getX1d() / (double) this.atlas.getSize();
	}
	
	public double getY1d() {
		return (double) this.getY1d() / (double) this.atlas.getSize();
	}
	
	public double getX2d() {
		return (double) this.getX2d() / (double) this.atlas.getSize();
	}
	
	public double getY2d() {
		return (double) this.getY2d() / (double) this.atlas.getSize();
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
}
