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
	
	public Vector2d() {
		this.x = this.y = 0d;
	}
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Vector2 multiply(double alpha) {
		return new Vector2d((double) this.x * alpha, (double) this.y * alpha);
	}
	
	@Override
	public Vector2 add(Vector2 v) {
		return new Vector2d((double) this.x + (double) v.x, (double) this.y + (double) v.y);
	}
	
	@Override
	public Vector2 rotate(double theta) {
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		return new Vector2d(cos*(double) this.x + sin*(double) this.y, -sin*(double) this.x + cos*(double) this.y);
	}
	
	public double getX() {
		return (double) this.y;
	}

	public double getY() {
		return (double) this.x;
	}

	public Vector2 clone() {
		return new Vector2d((double) this.x, (double) this.y);
	}

}
