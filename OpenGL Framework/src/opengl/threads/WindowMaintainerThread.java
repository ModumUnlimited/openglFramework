/**
 * 
 */
package opengl.threads;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

import opengl.Window;
import opengl.components.Panel;

/**
 * This is a maintainer thread, that will keep track of all objects
 * associated with a Window object and will take care of its
 * render and update schedulers and the input callbacks and events.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class WindowMaintainerThread extends Thread implements Runnable {
	
	private Window window;
	
	// State variables following:
	
	private int renderDelay = Math.round(1000f/60f);
	private int updateDelay = Math.round(1000f/24f);
	
	private RenderScheduler renderer;
	private UpdateScheduler updater;
	
	public WindowMaintainerThread(Window window) {
		this.window = window;
		setName("CORE");
	}
	
	@Override
	public void run() {
		window.debug("Starting RenderScheduler");
		renderer = new RenderScheduler(this.window, this.renderDelay);
		updater = new UpdateScheduler(this.window, this.updateDelay);
		
		renderer.start();
		updater.start();
	}
	
	public void setRenderRate(float fps) {
		this.renderDelay = Math.round(1000f / fps);
		renderer.setDelay(this.renderDelay);
	}
	
	public void setUpdateRate(float ups) {
		this.updateDelay = Math.round(1000f / ups);
		updater.setDelay(this.updateDelay);
	}
	
}
