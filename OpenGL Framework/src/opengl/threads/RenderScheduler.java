package opengl.threads;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;
import java.io.IOException;

import org.lwjgl.opengl.GL;

import opengl.Window;
import opengl.components.Panel;
import opengl.fonts.FontLibrary;
import opengl.textures.Texture;
import opengl.textures.TextureAtlas;

/**
 * This is a scheduler that will take care of all rendering of its window.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class RenderScheduler extends Thread implements Runnable {
	
	private Window window;
	private int delay;
	private long last;
	
	public static Texture smile;
	
	public RenderScheduler(Window window, int delay) {
		this.window = window;
		this.delay = delay;
		setName("SCHEDULER-RENDER");
	}
	
	@Override
	public void run() {
		last = System.currentTimeMillis();
		window.debug("Renderer started.");
		window.open();
		synchronized (Window.glfwContextLock) {
			glfwMakeContextCurrent(window.getWindowHandle());
			GL.createCapabilities();
			glMatrixMode(GL_TEXTURE);
			glPushMatrix();
			glEnable(GL_ALPHA_TEST);
			glAlphaFunc(GL_GREATER, 0.0f);
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glPopMatrix();
			
			glMatrixMode(GL_PROJECTION);
			glOrtho(0, window.ref.WINDOW_WIDTH, window.ref.WINDOW_HEIGHT, 0, -1, 1);
			glViewport(0, 0, window.ref.WINDOW_WIDTH, window.ref.WINDOW_HEIGHT);
			
			window.setTextureAtlas(new TextureAtlas(window));
			
			window.setFontLibrary(new FontLibrary(window.getTextureAtlas()));
			
			window.setContentPane(new Panel(0, 0, window.ref.WINDOW_WIDTH, window.ref.WINDOW_HEIGHT));
			window.getContentPane().setWindow(window);
			window.getTextureAtlas().createAtlas();
			try {
				smile = new Texture(new File("smile.png"), window.getTextureAtlas());
			} catch (IOException e) {
				e.printStackTrace();
			}
			glfwMakeContextCurrent(NULL);
		}
		
		// Incase someone already waits for this window to open, notify him
		synchronized (window.windowInitLock) {
			window.info("Window ready!");
			window.setInit();
			window.windowInitLock.notifyAll();
		}
		glfwSwapInterval(0);
		while (!window.shouldClose()) {
			glfwPollEvents();

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			render();
			
			synchronized (Window.glfwContextLock) {
				glfwMakeContextCurrent(window.getWindowHandle());
				glfwSwapBuffers(window.getWindowHandle());
				glfwMakeContextCurrent(NULL);
			}
			ThreadUtil.sync(window, last, delay);
		}
	}
	
	
	
	public void render() {
		synchronized (Window.glfwContextLock) {
			glfwMakeContextCurrent(window.getWindowHandle());
			window.getContentPane().render(window, 0, 0);
			glfwMakeContextCurrent(NULL);
		}
	}
	
	public synchronized void setDelay(int renderDelay) {
		this.delay = renderDelay;
	}
	
}
