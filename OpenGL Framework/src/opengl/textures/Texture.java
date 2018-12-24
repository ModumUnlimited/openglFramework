/**
 * 
 */
package opengl.textures;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Texture {
	
	private int id;
	
	public Texture(int width, int height, int[] pixels) {
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_INT, pixels);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public Texture(BufferedImage img) {
		this(img.getWidth(), img.getHeight(), img.getRGB(0, 0, img.getWidth(), img.getHeight(), new int[img.getWidth()*img.getHeight()], 0, img.getWidth()));
	}
	
	public Texture(File textureFile) throws IOException {
		this(ImageIO.read(textureFile));
	}
	
	public Texture(String path) throws IOException {
		this(ImageIO.read(new File(path)));
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
}
