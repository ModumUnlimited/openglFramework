package opengl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
	
	public static final int WINDOW_WIDTH = 0;
	public static final int WINDOW_HEIGHT = 1;
	
	private final long window;
	
	private String title;
	private int[] data = new int[64];
	
	static {
		glfwInit();
	}
	
	public Window(String title, int width, int height, boolean fullscreen) {
		window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
		this.title = title;
		this.data[WINDOW_WIDTH] = width;
		this.data[WINDOW_HEIGHT] = height;
	}
	
	public Window(String title, int width, int height) {
		this(title, width, height, false);
	}
	
	public String getTitle() {
		return new String(title);
	}
	
}
