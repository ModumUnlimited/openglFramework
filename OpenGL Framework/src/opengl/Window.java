package opengl;

import static opengl.errors.Errors.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import opengl.debug.Logger;
import opengl.debug.LoggerLevel;
import opengl.threads.WindowMaintainerThread;

public class Window {
	
	private static long firstWindow = NULL;
	
	protected long window;
	
	private String title;
	
	public Logger stdOut;
	public Logger fileOut;
	
	protected static boolean init;
	protected boolean running;
	protected boolean fullscreen;
	
	private WindowMaintainerThread maintainer;
	
	public Reference ref;
	
	static {
		System.out.println("Startup sequence: initializng GLFW...");
		init = glfwInit();
	}
	
	public Window(String title, int width, int height, boolean fullscreen) {
		this.fullscreen = fullscreen;
		stdOut = new Logger(System.out);
		stdOut.setLevel(LoggerLevel.VERBOSE);
		try {
			File logs = new File("logs");
			if (!logs.exists()) logs.mkdir();
			fileOut = new Logger(new PrintStream(new File("logs/log-" +
										new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date()) + ".log")));
			fileOut.setLevel(LoggerLevel.VERBOSE);
		} catch (FileNotFoundException e) {
			error("Could not create logfile: " + e.getMessage());
		}
		debug("Setting up state...");
		this.ref = new Reference();
		this.title = title;
		this.ref.WINDOW_WIDTH = width;
		this.ref.WINDOW_HEIGHT = height;
		this.ref.WINDOW_TITLE = title;
		info("Successfully created Window Object!");
		this.maintainer = new WindowMaintainerThread(this);
	}
	
	public Window(String title, int width, int height) {
		this(title, width, height, false);
	}
	
	
	public void setFramerate(float fps) {
		if (fps == 0) this.ref.FRAMERATE = 0;
		else this.ref.FRAMERATE = Math.round(1000f/fps);
	}
	
	/**
	 * This method opens the window. More precisely it starts the maintainer thread
	 * that will keep this window running.
	 */
	public void open() {
		maintainer.start();
	}
	
	public String getTitle() {
		return new String(title);
	}
	
	public void fatal(String msg) {
		if (fileOut != null) fileOut.fatal(Thread.currentThread().getName(), msg);
		stdOut.fatal(Thread.currentThread().getName(), msg);
	}
	
	public void error(String msg) {
		if (fileOut != null) fileOut.error(Thread.currentThread().getName(), msg);
		stdOut.error(Thread.currentThread().getName(), msg);
	}
	
	public void warning(String msg) {
		if (fileOut != null) fileOut.warning(Thread.currentThread().getName(), msg);
		stdOut.warning(Thread.currentThread().getName(), msg);
	}
	
	public void info(String msg) {
		if (fileOut != null) fileOut.info(Thread.currentThread().getName(), msg);
		stdOut.info(Thread.currentThread().getName(), msg);
	}
	
	public void debug(String msg) {
		if (fileOut != null) fileOut.debug(Thread.currentThread().getName(), msg);
		stdOut.debug(Thread.currentThread().getName(), msg);
	}
	
	public void verbose(String msg) {
		if (fileOut != null) fileOut.verbose(Thread.currentThread().getName(), msg);
		stdOut.verbose(Thread.currentThread().getName(), msg);
	}

	/**
	 * This method checks the state of the GLFW engine and will create an error if
	 * the glfw is not initialized and terminate the program.
	 */
	public void checkGLFW() {
		if (!init) {
			fatal("GLFW could not be initialized. The Program will terminate...");
			System.exit(ERR_GLFW_INIT);
		}
	}

	/**
	 * This method creates the Window and makes it visible. If an error occurs,
	 * this method will throw an error message and terminate the program.
	 */
	public void createWindow() {
		this.window = glfwCreateWindow(ref.WINDOW_WIDTH, ref.WINDOW_HEIGHT, ref.WINDOW_TITLE,
				fullscreen ? glfwGetPrimaryMonitor() : NULL,  firstWindow == 0 ? NULL : firstWindow);
		if (firstWindow == 0) firstWindow = this.window;
		if (window == NULL) {
			fatal("The window could not be created. The Program will terminate...");
			glfwTerminate();
			System.exit(ERR_WINDOW_CREATE);
		}
	}
	
}