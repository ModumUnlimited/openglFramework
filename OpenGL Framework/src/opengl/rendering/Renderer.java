/**
 * 
 */
package opengl.rendering;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * Renderer Class. This is used to schedule many renderable objects to be rendered on command by
 * a window trying to refresh.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Renderer {
	
	private HashMap<UUID, IRenderable> attached;
	
	private int clearMask;
	
	/**
	 * Creates a new Renderer with no attached renderables, that has both
	 * GL_COLOR_BUFFER_BIT and GL_DEPTH_BUFFER_BIT set.
	 */
	public Renderer() {
		attached = new HashMap<>();
		this.clearMask = GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT;
	}
	
	/**
	 * Attaches a renderable object to the renderer. This results in
	 * the renderable object to be scheduled for rendering every time
	 * this window refreshes;
	 * @param renderable A renderable object to be attached to this renderer
	 * @return the UUID of the newly attached object, or null if the object couldn't be attached
	 */
	public UUID attachObject(IRenderable renderable) {
		if (!attached.containsKey(renderable.getUUID())) {
			attached.put(renderable.getUUID(), renderable);
			return renderable.getUUID();
		}
		return null;
	}
	
	/**
	 * Detaches a renderable object from this renderer. This result in the 
	 * renderable object no longer being scheduler for rendering when window refreshes.
	 * @param id the UUID of the object to be removed.
	 * @return true if the object was removed, false if there was not such object.
	 */
	public boolean detachObject(UUID id) {
		if (attached.containsKey(id)) return attached.remove(id) != null;
		return false;
	}

	/**
	 * Renders all object that are attached to this renderer.
	 * @param window 
	 */
	public void renderAll(long window) {
		glClear(clearMask);
		
		for (IRenderable r : attached.values()) {
			glfwMakeContextCurrent(window);
			r.render(window);
		}
		
		glfwSwapBuffers(window);
	}
	
}
