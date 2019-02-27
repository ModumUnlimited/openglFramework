/**
 * 
 */
package opengl.components;

import opengl.Window;

/**
 * This a the superclass of all container type components.
 * A container is a component that has child-components.
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Container extends Component {

	public abstract void add(Component comp);
	public abstract void remove(Component comp);
	public abstract void renderChildern(Window window);

}
