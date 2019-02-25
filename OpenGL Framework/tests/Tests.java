import java.io.IOException;

import org.junit.Test;

import opengl.Initializable;
import opengl.Window;
import opengl.textures.Texture;

public class Tests {
	
	static Texture texture;
	
	public static void main(String[] args) {
		Window window = new Window("Test Window", 800, 600);
		window.open();
		window.addInit(new Initializable() {
			
			@Override
			public void init() {
				try {
					texture = new Texture("C:\\Users\\Linus\\Pictures\\funny\\19511254_1662880293725594_9039944942219137906_n.jpg");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		window.getRenderer().attachObject(texture);
	}
	

	@Test
	public void testOpenWindow() {
		Window window = new Window("Test Window", 800, 600);
		window.open();
	}
	
}

