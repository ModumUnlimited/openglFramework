package opengl.rendering;

import java.util.UUID;

import opengl.Identifiable;

/**
 * This is the superclass of every object that can be rendered to a window.
 * It contains a collection of methods that make all necessary operations
 * possible.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class IRenderable extends Identifiable {
	
	public abstract void render();
	
}
