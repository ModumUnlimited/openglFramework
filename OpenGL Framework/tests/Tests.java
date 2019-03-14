
import java.awt.Image;
import java.util.Comparator;

import opengl.Window;
import opengl.components.Button;

public class Tests {
	
	public static void main(String[] args) {
		Window window = new Window("Some Title", 800, 600);
		Button btn = new Button(25, 25, 50, 50, "blyat");
		window.getContentPane().add(btn);
		window.show();

//		for (Font s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) System.out.println(s.getFontName());
	}
	
	Comparator<Image> comp = new Comparator<Image>() {
		
		@Override
		public int compare(Image o1, Image o2) {
			return o2.getWidth(null)*o2.getHeight(null) - o1.getWidth(null)*o1.getHeight(null);
		}
	};
	
}

