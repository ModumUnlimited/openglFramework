package opengl.threads;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import opengl.Window;

public class UpdateScheduler extends Thread implements Runnable {

	private Window window;
	private int delay;
	private long last;
	
	public UpdateScheduler(Window window, int delay) {
		this.window = window;
		this.delay = delay;
		setName("SCHEDULER-UPDATE");
	}
	
	@Override
	public void run() {
		
		// Incase someone already waits for this window to open, notify him
		synchronized (window.windowInitLock) {
			try {
				window.debug("Waiting for the window to be open");
				window.windowInitLock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		window.verbose("Starting updates...");
		while (!window.shouldClose()) {
			
			window.getContentPane().update(window);
			
			last = ThreadUtil.sync(window, last, delay);
		}
		
	}

	public synchronized void setDelay(int renderDelay) {
		this.delay = renderDelay;
	}
	
}
