package opengl.threads;

import opengl.Window;

/**
 * A collection of utility methods for multithreading
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class ThreadUtil {
	
	public static long sync(Window window, long last, long delay) {
		if (delay > 0) try {
			long left = delay - (System.currentTimeMillis() - last);
			if (left > 0) {
				Thread.sleep(left);
			}
		} catch (InterruptedException e) {
			window.error("Could not sync: The wait was interrupted!");
		}
		return System.currentTimeMillis();
	}
	
}
