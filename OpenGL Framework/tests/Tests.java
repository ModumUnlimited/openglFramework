import org.junit.Test;

import opengl.Window;

public class Tests {
	
	public static void main(String[] args) {
		Window window = new Window("Test Window", 800, 600);
		window.open();
	}
	

	@Test
	public void testOpenWindow() {
		Window window = new Window("Test Window", 800, 600);
		window.open();
	}
	
}
