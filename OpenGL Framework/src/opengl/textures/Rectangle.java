/**
 * 
 */
package opengl.textures;

/**
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Rectangle {
	
	public int x, y, width, height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(int x, int y) {
		return  x >= this.x && x < this.x + this.width &&
				y >= this.y && y < this.y + this.height;
	}
	
	
	@Override
	public String toString() {
		return "Rectangle: (" + x + ", " + y + ", " + width + ", " + height + ")";
	}
}
