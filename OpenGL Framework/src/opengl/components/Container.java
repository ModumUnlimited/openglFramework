package opengl.components;

import java.util.LinkedList;
import java.util.List;

import opengl.Window;
import opengl.math.Vector2d;

/**
 * This a the superclass of all container type components.
 * A container is a component that has child-components.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Container extends Component {
	
	protected List<Component> children = new LinkedList<>();
	
	public Container() {
		super();
	}
	
	public Container(double x, double y) {
		super(x, y);
	}
	
	public Container(double x, double y, double w, double h) {
		super(x, y, w, h);
	}
	
	/**
	 * Adds a component to this container.
	 * @param comp The component to be added.
	 */
	public void add(Component comp) {
		synchronized (children) {
			children.add(comp);
		}
		comp.setWindow(this.window);
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
	
	public Component getChild(int index) {
		synchronized (children) {
			return children.get(index);
		}
	}
	
	/**
	 * Packs this Container. This means, that its dimensions are minimised while still containing all
	 * children completely inside said dimensions.
	 */
	public void pack() {
		synchronized (children) {

			for (Component comp : children) {
				if (comp instanceof Container) ((Container) comp).pack(); 
			}
			
			double w = 0;
			double h = 0;
			
			for (Component comp : children) {
				double x = comp.dimension.getX() + comp.position.getX();
				double y = comp.dimension.getY() + comp.position.getY();
				
				if (x > w) w = x;
				if (y > h) h = y;
			}
			
			this.dimension = new Vector2d(w, h);
		}
	}
	
	@Override
	public void render(Window window, double xOff, double yOff) {
		renderSelf(window, xOff, yOff);
		renderChildren(window, xOff, yOff);
	}
	
	/**
	 * This method renders the container itself. This method is called by the render
	 * method of this container before renderChildren is called.
	 * @param window The Window object that this container resides in
	 * @param xOff The x offset inherited of this container and its parents positions
	 * @param yOff The y offset inherited of this container and its parents positions
	 */
	public abstract void renderSelf(Window window, double xOff, double yOff);
	
	/**
	 * Renders all children of this container to the specified window.
	 * This method is called by the render method of this container after renderSelf
	 * is called.
	 * @param window The window that should be rendered to.
	 * @param xOff The x offset inherited of this container and its parents positions
	 * @param yOff The y offset inherited of this container and its parents positions
	 */
	public void renderChildren(Window window, double xOff, double yOff) {
		synchronized (children) {
			for (Component c : children) c.render(window, xOff, yOff);
		}
	};

}
