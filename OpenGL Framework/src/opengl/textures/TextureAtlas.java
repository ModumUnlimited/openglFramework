package opengl.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.glfw.GLFW.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL;

import opengl.Window;

public class TextureAtlas {
	
	private BufferedImage atlas;
	private final int textureHandle;
	private Window window;
	
	private LinkedList<Texture> textures = new LinkedList<>();
	private TopMostLeftFit packer;
	
	private int size;
	
	private int width;
	private int height;
	
	private boolean bound = false;
	
	public TextureAtlas(Window window) {
		this.textureHandle = glGenTextures();
		this.window = window;
	}
	
	public synchronized boolean isBound() {
		return bound;
	}
	
	public synchronized void bind() {
		glBindTexture(GL_TEXTURE_2D, textureHandle);
		bound = true;
	}
	
	public synchronized void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
		bound = false;
	}
	
	public int addTexture(Texture texture) {
		int out;
		synchronized (textures) {
			out = textures.size();
			textures.add(texture);
		}
		return out;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumTextures() {
		return this.textures.size();
	}
	
	public BufferedImage getImage() {
		return atlas;
	}
	
	public void createAtlas() {
		//get the total size of all images
		int size = 0;
		window.debug("Stiching " + textures.size() + " textures into the atlas...");
		for (Texture tex : textures) size += tex.getHeight()*tex.getHeight();
		size = (int) Math.round( Math.sqrt(size) * 1.05d );
		this.size = size;
		
		Collections.sort(textures);
		
		packer = new TopMostLeftFit(size);
		
		for (Texture tex : textures) {
			tex.setRect(packer.insert(tex));
		}
		
		int right = 0;
		int bottom = 0;
		
		for (Texture tex : textures) {
			if (tex.getX2i() > right) right = tex.getX2i();
			if (tex.getY2i() > bottom) bottom = tex.getY2i();
		}
		
		this.atlas = new BufferedImage(right, bottom, BufferedImage.TYPE_INT_ARGB);
		Graphics g = atlas.createGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, right, bottom);
		
		this.width = this.atlas.getWidth();
		this.height = this.atlas.getHeight();
		
		for (Texture tex : textures) {
			tex.drawToAtlas(g);
		}
		
		g.dispose();
		
		try {
			ImageIO.write(atlas, "png", new File("ATLAS-STATE.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		int[] rgb = new int[atlas.getWidth()*atlas.getHeight()];
		atlas.getRGB(0, 0, atlas.getWidth(), atlas.getHeight(), rgb, 0, atlas.getWidth());
		
		bind();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, atlas.getWidth(), atlas.getHeight(), 0, GL_BGRA, GL_UNSIGNED_BYTE, rgb);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		unbind();
	}

	/**
	 * @param texture
	 */
	public void remove(Texture texture) {
		System.out.println("removint");
		synchronized (textures) {
			textures.remove(texture.getTextureID());
		}
	}

	/**
	 * @param texture
	 * @return
	 */
	public boolean contains(Texture texture) {
		return textures.contains(texture);
	}

	/**
	 * @param textureID
	 * @return
	 */
	public boolean containsID(int textureID) {
		for (Texture t : textures) if (t.getTextureID() == textureID) return true;
		return false;
	}

	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	
}