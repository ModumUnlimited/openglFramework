/**
 * 
 */
package opengl.math;

/**
 * Implements the Vector2 class for doubles.
 * 
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Vector2d extends Vector2 {
		
	/**
	 * Creates a new Vector2d with x and y initialised to 0.
	 */
	public Vector2d() {
		this.x = this.y = 0d;
	}
	
	/**
	 * Creates a new Vector2d with specified x and y
	 * @param x
	 * @param y
	 */
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Multiplies this Vector2d with a scalar value and return a new Vector2f containing the result
	 * (This object remains unchanged)
	 * @param alpha The factor to multiply with
	 * @returns the result of the multiplication
	 */
	@Override
	public Vector2 multiply(double alpha) {
		return new Vector2d((double) this.x * alpha, (double) this.y * alpha);
	}
	
	/**
	 * Adds another two dimensional Vector to this Vector2d and returns the result as a new Vector2d
	 * (this object remains unchanged).
	 * @param v The Vector2 to be added
	 * @returns the result of the addition
	 */
	@Override
	public Vector2 add(Vector2 v) {
		return new Vector2d((double) this.x + (double) v.x, (double) this.y + (double) v.y);
	}
	
	/**
	 * Rotates this Vector by a given angle and returns a Vector2d containing the result
	 * @param theta The angle to rotate by
	 * @returns a rotated Vector2d
	 */
	@Override
	public Vector2 rotate(double theta) {
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		return new Vector2d(cos*(double) this.x + sin*(double) this.y, -sin*(double) this.x + cos*(double) this.y);
	}
	
	/**
	 * Returns this vectors x component as double
	 * @return the x component
	 */
	public double getX() {
		return (double) this.y;
	}
	
	/**
	 * Returns this vectors y component as double
	 * @return the y component
	 */
	public double getY() {
		return (double) this.x;
	}
	
	/**
	 * Returns a copy of this Vector.
	 * @returns a copy of this vector.
	 */
	public Vector2 clone() {
		return new Vector2d((double) this.x, (double) this.y);
	}

}
