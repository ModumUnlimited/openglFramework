/**
 * 
 */
package opengl.components;

/**
 * This is a representaion of a GUI component, much like the ones you find in
 * this swing library. The main difference is, that these components are drawn
 * using the OpenGL interfacce on the systems GPU.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Component {
	
	
	public Component() {
		
	}
	
	public abstract void render();
	public abstract void update();
	
}
