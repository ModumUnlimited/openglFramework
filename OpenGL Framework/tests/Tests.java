
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.Test;

import opengl.Window;
import opengl.textures.TextureAtlasNode;

public class Tests {
	
	public static void main(String[] args) {
		Window window = new Window("Test Window", 800, 600);
		window.show();
	}
	
	
	@Test
	public void testAtlas() throws IOException {
		
		
		File[] icons = new File("icons/png").listFiles();
		System.out.println("Found " + icons.length + " textures...");
		Random r = new Random();
		int area = 0;
		
		Image[] imgs = new Image[icons.length];
		for (int i = 0; i < icons.length; i++) {
			imgs[i] = ImageIO.read(icons[i]).getScaledInstance(4*(r.nextInt(56)+8), 4*(r.nextInt(56)+8), BufferedImage.SCALE_SMOOTH);
			area += imgs[i].getWidth(null) * imgs[i].getHeight(null);
			System.out.println("Processing: " + i + "/" + imgs.length);
		}
		System.out.println("Sorting...");
		Arrays.sort(imgs, comp);
		System.out.println("Done.");
		
		
		int size = (int) Math.round(Math.sqrt(area) * 1.05d);
		System.out.println("Total size of all images: " + area + " <= " + size + "^2");

		BufferedImage atlas = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		
		TextureAtlasNode node = new TextureAtlasNode(atlas);
		
		int i = 1;
		
		for (Image img : imgs) {
			//if (i > 11) break;
			
			System.out.print("Image (" + i++ + "): " + img.getWidth(null) + "x" + img.getHeight(null) + ":\t");
			TextureAtlasNode t = node.insert(img);
			System.out.println(t != null ? "Done." : "ERROR");
		}
		
		ImageIO.write(atlas, "png", new File("ATLAS.png"));
		
	}
	
	Comparator<Image> comp = new Comparator<Image>() {
		
		@Override
		public int compare(Image o1, Image o2) {
			return o2.getWidth(null)*o2.getHeight(null) - o1.getWidth(null)*o1.getHeight(null);
		}
	};
	
}

