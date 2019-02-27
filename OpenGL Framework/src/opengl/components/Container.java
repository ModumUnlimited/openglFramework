/**
 * 
 */
package opengl.components;

import java.util.LinkedList;
import java.util.List;

import opengl.Window;

/**
 * This a the superclass of all container type components.
 * A container is a component that has child-components.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Container extends Component {
	
	protected List<Component> children = new LinkedList<>();
	
	
	/**
	 * Adds a component to this container.
	 * @param comp The component to be added.
	 */
	public void add(Component comp) {
		synchronized (children) {
			children.add(comp);
		}
	}
	
	/**
	 * Removes a specific child from this container.
	 * @param comp the component to be removed.
	 */
	public void remove(Component comp) {
		synchronized (children) {
			children.remove(comp);
		}
	}
	
	@Override
	public void render(Window window) {
		renderSelf(window);
		renderChildren(window);
	}
	
	/**
	 * This method renders the container itself. This method is called by the render
	 * method of this container before renderChildren is called.
	 * @param window
	 */
	public abstract void renderSelf(Window window);
	
	/**
	 * Renders all children of this container to the specified window.
	 * This method is called by the render method of this container after renderSelf
	 * is called.
	 * @param window The window that should be rendered to.
	 */
	public void renderChildren(Window window) {
		synchronized (children) {
			for (Component c : children) c.render(window);
		}
	};

}
