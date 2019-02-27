/**
 * 
 */
package opengl.threads;

import opengl.Window;

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
	
	public WindowMaintainerThread(Window window) {
		this.window = window;
		setName("CORE");
	}
	
	@Override
	public void run() {
		window.checkGLFW();
		window.createWindow();
		
	}
	
	public void setRenderRate(float fps) {
		this.renderDelay = Math.round(1000f / fps);
	}
	
	public void setUpdateRate(float ups) {
		this.updateDelay = Math.round(1000f / ups);
	}
	
}
