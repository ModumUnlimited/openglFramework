/**
 * 
 */
package opengl.textures;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import opengl.rendering.IRenderable;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Texture extends IRenderable {
	
	private int id;
	
	private int width;
	private int height;
	
	public Texture(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public Texture(BufferedImage img) {
		this(img.getWidth(), img.getHeight(), img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth()));
	}
	
	public Texture(File textureFile) throws IOException {
		this(ImageIO.read(textureFile));
	}
	
	public Texture(String path) throws IOException {
		this(ImageIO.read(new File(path)));
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	@Override
	public void render(long window) {
		bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(-0.8f, 0.8f);
			
			glTexCoord2f(1, 0);
			glVertex2f(0.8f, 0.8f);
			
			glTexCoord2f(1, 1);
			glVertex2f(0.8f, -0.8f);
			
			glTexCoord2f(0, 1);
			glVertex2f(-0.8f, -0.8f);
		glEnd();
		
		unbind();
	}
	
}
