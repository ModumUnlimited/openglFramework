package opengl;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.system.MemoryUtil.NULL;
import static opengl.Reference.*;
import static opengl.Module.CORE;
import static opengl.errors.Errors.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import opengl.debug.Logger;
import opengl.debug.LoggerLevel;

public class Window {
	
	private final long window;
	
	private static String title;
	
	public static Logger stdOut;
	public static Logger fileOut;
	
	private static boolean init;
	
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
		Window.title = title;
		state[WINDOW_WIDTH] = width;
		state[WINDOW_HEIGHT] = height;
		stdOut = new Logger(System.out);
		stdOut.setLevel(LoggerLevel.INFO);
		try {
			fileOut = new Logger(new PrintStream(new File("log-" +
										new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".log")));
			fileOut.setLevel(LoggerLevel.VERBOSE);
		} catch (FileNotFoundException e) {
			error(Module.CORE, "Could not create logfile: " + e.getMessage());
		}
	}
	
	public Window(String title, int width, int height) {
		this(title, width, height, false);
	}
	
	
	public static String getTitle() {
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
