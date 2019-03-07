/**
 * 
 */
package opengl.textures;

/**
 * Packs rectangles according to a modified first fit algorithm, that packs every single rectangle
 * as far to the top as possible, possibly leaving some space that will not be considered again.
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class TopMostLeftFit {
	
	private final int width;
	private static int[] topMostFreePixel;
	
	public TopMostLeftFit(int width) {
		this.width = width;
		topMostFreePixel = new int[width];
	}
	
	public TopMostLeftFit(int[] data) {
		this.width = data.length;
		topMostFreePixel = data;
	}
	
	public int topMostSpaceOfWidth(int width) {
		int min = 0;
		for (int i = 1; i+width-1 < this.width; i++)
			if (topMostFreePixel[max(topMostFreePixel, i, i+width)] < topMostFreePixel[max(topMostFreePixel, min, min+width)])
				min = i;
		return min;
	}
	
	public Rectangle insert(Texture tex) {
		int x = topMostSpaceOfWidth(tex.getWidth()+2);
		int y = topMostFreePixel[max(topMostFreePixel, x, x+tex.getWidth()+2)];
		for (int i = 0; i < tex.getWidth()+1; i++) {
			topMostFreePixel[x+i] = y+tex.getHeight()+2;
		}
		return new Rectangle(x+1, y+1, tex.getWidth(), tex.getHeight());
	}
	
	private int max(int[] data, int from, int to) {
		int max = from;
		for (int i = from+1; i < to; i++) {
			if (data[i] > data[max]) max = i;
		}
		return max;
	}
	
}
