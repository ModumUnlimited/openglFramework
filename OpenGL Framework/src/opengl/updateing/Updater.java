/**
 * 
 */
package opengl.updateing;

import java.util.HashMap;
import java.util.UUID;

import opengl.Window;

/**
 * This is an Updater. It is used to schedule updateable object to be
 * updated on each tick.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Updater {
	
	HashMap<UUID, IUpdatable> attached;
	
	/**
	 * Creates a new Updater object with no attached objects
	 */
	public Updater() {
		attached = new HashMap<>();
	}
	
	/**
	 * Attaches an updatable object to the updater. This results in
	 * the updatable object to be scheduled for updating every time
	 * this window updates;
	 * @param updatable A updatable object to be attached to this updater
	 * @return the UUID of the newly attached object, or null if the object couldn't be attached
	 */
	public UUID attachObject(IUpdatable updatable) {
		if (!attached.containsKey(updatable.getUUID())) {
			attached.put(updatable.getUUID(), updatable);
			return updatable.getUUID();
		}
		return null;
	}
	
	/**
	 * Detaches a updatable object from this updater. This result in the 
	 * updatable object no longer being scheduler for rendering when window updates.
	 * @param id the UUID of the object to be removed.
	 * @return true if the object was removed, false if there was not such object.
	 */
	public boolean detachObject(UUID id) {
		if (attached.containsKey(id)) return attached.remove(id) != null;
		return false;
	}
	
	/**
	 * Updates all objects that are attached to this updater.
	 * 
	 * @param window The window that called this updater belong to.
	 */
	public void updateAll(Window window) {
		
		for (IUpdatable updatable : attached.values()) updatable.update(window);
		
	}
	
}
