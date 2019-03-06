
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Test;

import opengl.Window;
import opengl.fonts.GLFont;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

public class Tests {
	
	public static void main(String[] args) {
		Window window = new Window("Some Title", 800, 600);

//		for (Font s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) System.out.println(s.getFontName());
	}
	
	Comparator<Image> comp = new Comparator<Image>() {
		
		@Override
		public int compare(Image o1, Image o2) {
			return o2.getWidth(null)*o2.getHeight(null) - o1.getWidth(null)*o1.getHeight(null);
		}
	};
	
}

