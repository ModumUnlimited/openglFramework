package opengl.textures;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class TextureAtlasNode {
	
	private BufferedImage enclosing;
	
	private TextureAtlasNode[] children = new TextureAtlasNode[2];
	private TextureAtlasRectangle rect;
	private Texture image;
	
	private TextureAtlasNode(BufferedImage img, boolean bool) {
		this.enclosing = img;
		if (bool) this.rect = new TextureAtlasRectangle(0, 0, img.getWidth(), img.getHeight());
	}
	
	public TextureAtlasNode(BufferedImage img) {
		this(img, true);
	}
	
	public TextureAtlasNode insert(Texture img) {
		if (children[0] != null || children[1] != null) { // if this is not a leaf
			TextureAtlasNode node = children[0].insert(img);
			if (node != null) return node;
			node = children[1].insert(img);
			return node;
		} else {
			if (this.image != null) return null;
			if (rect.getWidth() < img.getWidth() || rect.getHeight() < img.getHeight()) return null;
			if (rect.getWidth() == img.getWidth() && rect.getHeight() == img.getHeight()) {
				this.image = img;
				
				Graphics g = enclosing.createGraphics();
				img.setRect(rect);
				img.drawToAtlas(g);
				g.dispose();
				
				return this;
			}

			children[0] = new TextureAtlasNode(enclosing, false);
			children[1] = new TextureAtlasNode(enclosing, false);

			int dw = rect.getWidth() - img.getWidth();
			int dh = rect.getHeight() - img.getHeight();
			
			if (dw > dh) {
				children[0].rect = new TextureAtlasRectangle(rect.left, rect.top, rect.right - dw, rect.bottom);
				children[1].rect = new TextureAtlasRectangle(rect.right - dw+1, rect.top, rect.right, rect.bottom);
			} else {
				children[0].rect = new TextureAtlasRectangle(rect.left, rect.top, rect.right, rect.bottom - dh);
				children[1].rect = new TextureAtlasRectangle(rect.left, rect.bottom - dh+1, rect.right, rect.bottom);
			}
			
			if (children[0].rect.getWidth() == 0 || children[0].rect.getHeight() == 0 || children[1].rect.getWidth() == 0 || children[1].rect.getHeight() == 0)
				System.out.println("This should never happen: " + dw + ", " + dw + ", " + rect.getWidth() + ", " + rect.getHeight() + ", " + children[0].rect.getWidth() + ", " + children[0].rect.getHeight() + ", " + children[1].rect.getWidth() + ", " + children[1].rect.getHeight());
			
			return children[0].insert(img);
		}
	}
	
	public TextureAtlasRectangle getRect() {
		return rect;
	}
	
}
