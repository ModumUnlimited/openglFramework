package opengl.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TextureAtlas {
	
	private BufferedImage atlas;
	private final int textureHandle;
	
	private ArrayList<Texture> textures = new ArrayList<>();
	private TextureAtlasNode root;
	
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
	
	public void createAtlas() {
		//get the total size of all images
		int size = 0;
		for (Texture tex : textures) size += tex.getHeight()*tex.getHeight();
		size = (int) Math.round( Math.sqrt(size) * 1.05d );
		
		atlas = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		root = new TextureAtlasNode(atlas);
		
		for (Texture tex : textures) {
			tex.setRect(root.insert(tex.getImage()).getRect());
		}
		
		bind();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, atlas.getWidth(), atlas.getHeight(), 0, GL_BGRA, GL_UNSIGNED_BYTE, atlas.getRGB(0, 0, atlas.getWidth(), atlas.getHeight(), null, 0, atlas.getWidth()));
		unbind();
	}
	
	
}