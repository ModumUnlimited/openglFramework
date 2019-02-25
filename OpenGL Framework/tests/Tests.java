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
					texture = new Texture("smile.jpg");
					window.getRenderer().attachObject(texture);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	@Test
	public void testOpenWindow() {
		Window window = new Window("Test Window", 800, 600);
		window.open();
	}
	
}

