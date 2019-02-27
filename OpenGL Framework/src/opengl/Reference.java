package opengl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a collection of all state relevant information of a window
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Reference {
	
	public static ConcurrentHashMap<Long, Window> windows = new ConcurrentHashMap<>();

	public int WINDOW_WIDTH = 800;
	public int WINDOW_HEIGHT = 600;
	public String WINDOW_TITLE;
	
}
