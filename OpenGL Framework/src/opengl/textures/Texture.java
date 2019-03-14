package opengl.textures;

import java.awt.Color;
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
	
	private static final boolean drawDebug = true;
	
	private TextureAtlas atlas;
	
	private final int textureID;
	
	private int width;
	private int height;
	private int hOffset;
	private Image img;
	
	private boolean render = true;
	
	private Rectangle rectangle;
	
	/*private Texture(TextureAtlas atlas, int textureID, int width, int height, int hOffset, Image img, String toDraw, Font font, boolean render, Rectangle rectangle) {
		this.atlas = atlas;
		this.textureID = textureID;
		this.width = width;
		this.height = height;
		this.hOffset = hOffset;
		this.img = img;
		this.toDraw = toDraw;
		this.font = font;
		this.render = render;
		this.rectangle = rectangle;
	}*/
	
	public Texture(int width, int height, Image img, TextureAtlas atlas) {
		this.width = width;
		this.height = height;
		this.atlas = atlas;
		this.img = img;
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

	public double getWidthD() {
		return (double) this.width / (double) this.atlas.getWidth();
	}
	
	public double getHeightD() {
		return (double) this.height / (double) this.atlas.getHeight();
	}
	
	public Image getImage() {
		return img;
	}
	
	public void setHOffset(int hOffset) {
		this.hOffset = hOffset;
	}
	
	public int getHOffset() {
		return this.hOffset;
	}
	
	public double getX1d() {
		return (double) this.getX1i() / (double) this.atlas.getWidth();
	}
	
	public double getY1d() {
		return (double) this.getY1i() / (double) this.atlas.getHeight();
	}
	
	public double getX2d() {
		return (double) this.getX2i() / (double) this.atlas.getWidth();
	}
	
	public double getY2d() {
		return (double) this.getY2i() / (double) this.atlas.getHeight();
	}
	
	
	public int getX1i() {
		if (this.rectangle == null) atlas.createAtlas();
		return this.rectangle.x;
	}

	public int getY1i() {
		if (this.rectangle == null) atlas.createAtlas();
		return this.rectangle.y;
	}

	public int getX2i() {
		if (this.rectangle == null) atlas.createAtlas();
		return this.rectangle.x + this.rectangle.width-1;
	}

	public int getY2i() {
		if (this.rectangle == null) atlas.createAtlas();
		return this.rectangle.y + this.rectangle.height-1;
	}
	
	public void setRect(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public void setFontBaseline(int hOffset) {
		this.hOffset = hOffset;
	}
	
	public int getFontBaseline() {
		return this.hOffset;
	}

	/**
	 * @param g
	 */
	public void drawToAtlas(Graphics g) {
		if (render) {
			g.drawImage(this.img, this.getX1i(), this.getY1i(), null);
			if (drawDebug) {
				g.setColor(Color.RED);
				g.drawRect(this.getX1i(), this.getY1i(), this.getWidth(), this.getHeight());
			}
		}
	}
	
	public int compareTo(Texture o) {
		return o.getWidth() - getWidth();
	}
}
