package opengl.textures;

public class TextureAtlasRectangle {
	
	public int top, bottom, left, right;
	
	public TextureAtlasRectangle(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		
		assert(this.getWidth() != 0 && this.getHeight() != 0);
	}
	
	public int getHeight() {
		return bottom - top + 1;
	}
	
	public int getWidth() {
		return right - left + 1;
	}
	
}
