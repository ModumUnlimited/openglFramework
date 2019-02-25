package opengl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static opengl.Reference.*;
import static opengl.Module.*;
import static opengl.errors.Errors.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import opengl.debug.Logger;
import opengl.debug.LoggerLevel;
import opengl.rendering.Renderer;
import opengl.updateing.Updater;

public class Window {
	
	private final long window;
	
	private String title;
	
	public Logger stdOut;
	public Logger fileOut;
	
	private static boolean init;
	protected boolean running;
	private long lastSync;
	
	public Reference ref;
	
	private Renderer renderer;
	private Updater updater;
	
	static {
		init = glfwInit();
	}
	
	public Window(String title, int width, int height, boolean fullscreen) {
		if (!init) {
			fatal(CORE, "Could not initialize GLFW! Exiting...");
			System.exit(ERR_GLFW_INIT);
		}
		window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
		if (window == NULL) {
			fatal(CORE, "Could not create window!");
			System.exit(ERR_WINDOW_CREATE);
		}
		this.ref = new Reference();
		this.title = title;
		this.ref.state[WINDOW_WIDTH] = width;
		this.ref.state[WINDOW_HEIGHT] = height;
		stdOut = new Logger(System.out);
		stdOut.setLevel(LoggerLevel.INFO);
		try {
			fileOut = new Logger(new PrintStream(new File("log-" +
										new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".log")));
			fileOut.setLevel(LoggerLevel.VERBOSE);
		} catch (FileNotFoundException e) {
			error(Module.CORE, "Could not create logfile: " + e.getMessage());
		}
		lastSync = System.currentTimeMillis();
		renderer = new Renderer();
		updater = new Updater();
		this.ref = new Reference();
		running = true;
	}
	
	public Window(String title, int width, int height) {
		this(title, width, height, false);
	}
	
	public void setFramerate(float fps) {
		if (fps == 0) this.ref.state[FRAMERATE] = 0;
		else this.ref.state[FRAMERATE] = Math.round(1000.0f/fps);
	}
	
	private void syncRenderer() {
		long now = System.currentTimeMillis();
		long d = this.ref.state[FRAMERATE] - (now - this.lastSync);
		if (d > 0) try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			error(CORE, "Frame syncing failed: " + e.getMessage());
		}
		this.lastSync = now;
	}
	
	public void render() {
		renderer.renderAll(window);
	}
	
	public String getTitle() {
		return new String(title);
	}
	
	public void fatal(IModule module, String msg) {
		if (fileOut != null) fileOut.fatal(module.name(), msg);
		stdOut.fatal(module.name(), msg);
	}
	
	public void error(IModule module, String msg) {
		if (fileOut != null) fileOut.error(module.name(), msg);
		stdOut.error(module.name(), msg);
	}
	
	public void warning(IModule module, String msg) {
		if (fileOut != null) fileOut.warning(module.name(), msg);
		stdOut.warning(module.name(), msg);
	}
	
	public void info(IModule module, String msg) {
		if (fileOut != null) fileOut.info(module.name(), msg);
		stdOut.info(module.name(), msg);
	}
	
	public void debug(IModule module, String msg) {
		if (fileOut != null) fileOut.debug(module.name(), msg);
		stdOut.debug(module.name(), msg);
	}
	
	public void verbose(IModule module, String msg) {
		if (fileOut != null) fileOut.verbose(module.name(), msg);
		stdOut.verbose(module.name(), msg);
	}
	
}

class UpdaterThread extends Thread implements Runnable {
	
	private Window window;
	private Updater updater;
	
	public UpdaterThread(Window window, Updater updater) {
		this.window = window;
		this.updater = updater;
	}
	
	@Override
	public void run() {
		
		while (window.running) {
			updater.
		}
		
	}
	
}
