package opengl.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.glfw.GLFW.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import opengl.Window;

public class TextureAtlas {
	
	private BufferedImage atlas;
	private final int textureHandle;
	
	private ArrayList<Texture> textures = new ArrayList<>();
	private TextureAtlasNode root;
	
	private int size;
	
	private boolean bound = false;
	
	public TextureAtlas() {
		this.textureHandle = glGenTextures();
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
	
	public BufferedImage getImage() {
		return atlas;
	}
	
	public void createAtlas() {
		//get the total size of all images
		int size = 0;
		for (Texture tex : textures) size += tex.getHeight()*tex.getHeight();
		size = (int) Math.round( Math.sqrt(size) * 1.05d );
		this.size = size;
		
		atlas = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		root = new TextureAtlasNode(atlas);
		
		for (Texture tex : textures) {
			tex.setRect(root.insert(tex.getImage()).getRect());
		}
		
		int right = 0;
		int bottom = 0;
		
		for (Texture tex : textures) {
			if (tex.getX2i() > right) right = tex.getX2i();
			if (tex.getY2i() > bottom) bottom = tex.getY2i();
		}
		
		try {
			ImageIO.write(atlas, "png", new File("ATLAS-STATE.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int[] rgb = new int[atlas.getWidth()*atlas.getHeight()];
		atlas.getRGB(0, 0, atlas.getWidth(), atlas.getHeight(), rgb, 0, atlas.getWidth());
		
		bind();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, atlas.getWidth(), atlas.getHeight(), 0, GL_BGRA, GL_UNSIGNED_BYTE, rgb);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		unbind();
	}
	
	
}