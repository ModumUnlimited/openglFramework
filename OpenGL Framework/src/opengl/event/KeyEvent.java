package opengl.event;

import org.lwjgl.glfw.GLFW;

public class KeyEvent extends Event {
	
	private int keycode;
	private int scancode;
	private int action;
	private int modifier;
	
	public KeyEvent(int keycode, int scancode, int action, int mods) {
		this.keycode = keycode;
		this.scancode = scancode;
		this.action = action;
		this.modifier = mods;
	}
	
	public int getKeyCode() {
		return this.keycode;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public boolean isPressed() {
		return this.action == GLFW.GLFW_PRESS;
	}
	
	public boolean isReleased() {
		return this.action == GLFW.GLFW_RELEASE;
	}
	
	public boolean isRepeat() {
		return this.action == GLFW.GLFW_REPEAT;
	}
	
	public boolean isShift() {
		return (this.modifier & GLFW.GLFW_MOD_SHIFT) != 0;
	}
	
	public boolean isControl() {
		return (this.modifier & GLFW.GLFW_MOD_CONTROL) != 0;
	}
	
	public boolean isAlt() {
		return (this.modifier & GLFW.GLFW_MOD_ALT) != 0;
	}
	
}
