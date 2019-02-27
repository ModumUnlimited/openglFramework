package opengl.event;

import java.util.LinkedList;

import opengl.Window;

public class EventHandler {
	
	private LinkedList<Event> events = new LinkedList<>();
	private Window window;
	
	public EventHandler(Window window) {
		this.window  = window;
	}
	
	public void attach(Event e) {
		synchronized (events) {
			events.addLast(e);
		}
	}
	
	public void consume() {
		synchronized (events) {
			Event event = events.removeFirst();
			if (event instanceof KeyEvent) if (((KeyEvent) event).isPressed()) window.keyPressed((KeyEvent) event);
									  else if (((KeyEvent) event).isReleased()) window.keyReleased((KeyEvent) event);
									  else if (((KeyEvent) event).isRepeat()) window.keyRepeat((KeyEvent) event);
		}
	}
	
}
