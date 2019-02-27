package opengl.math;

/**
 * This is the superclass of all two-dimensional vectors.
 * It contains signatures for all necessary methods in a abstract fashion such that
 * this class can be implemented for every primitive number.
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public abstract class Vector2 {
	
	protected Number x, y;
	
	public abstract Vector2 multiply(double alpha);
	public abstract Vector2 add(Vector2 v);
	public abstract Vector2 rotate(double theta);
	
}
