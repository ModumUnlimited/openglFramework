/**
 * 
 */
package opengl.errors;

import opengl.math.Matrix;

/**
 * @author Linus
 *
 */
public class MatrixDimensionError extends Exception {
	
	private static final long serialVersionUID = 1576365987274139877L;
	private String msg;
	
	public MatrixDimensionError(Matrix m, int w, int h) {
		msg = "Dimension were expected as: " + w + ", " + h + ", but were : " + m.getWidth() + ", " + m.getWidth();
		initCause(this);
	}
	
	public MatrixDimensionError(Matrix m, int elements) {
		msg = m.getWidth()*m.getHeight() + " elements were expected, but " + elements + " were provided";
		initCause(this);
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + msg;
	}
	
}
