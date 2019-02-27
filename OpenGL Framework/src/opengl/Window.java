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

/**
 * A window class that uses GLFW to display its contents.
 * @author Linus Vogel <linvogel@student.ethz.ch>
 *
 */
public class Window {
	
	private static long firstWindow = NULL;
	
	public final Object glfwContextLock = new Object();
	
	private long window;
	
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
	
	/**
	 * Creates a new Window object with specified title, width and height. Can be set fullscreen if desired.
	 * @param title
	 * @param width
	 * @param height
	 * @param fullscreen
	 */
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
	
	
	/**
	 * Creates a new non fullscreen window with specified width, height and title.
	 * @param title
	 * @param width
	 * @param height
	 */
	public Window(String title, int width, int height) {
		this(title, width, height, false);
	}
	
	public long getWindowHandle() {
		return Long.valueOf(this.window);
	}
	
	/**
	 * Sets the framerate of this window. A framerate of 0 or above 2000 will result in no limit.
	 * @param fps the taarget framerate to be set for this window.
	 */
	public void setFramerate(float fps) {
		maintainer.setRenderRate(fps);
	}
	
	/**
	 * Sets the tickrate of this window. A tickrate of 0 or above 2000 will result in no limit.
	 * It is recommended to not exceed a tickrate of 128 to keep load on the CPU lower. Higher
	 * tickrates might impact performance of this Program, including some other program or even
	 * the operating system.
	 * @param tps
	 */
	public void setTickrate(float tps) {
		maintainer.setUpdateRate(tps);
	}
	
	/**
	 * This method opens the window. More precisely it starts the maintainer thread
	 * that will keep this window running.
	 */
	public void open() {
		maintainer.start();
	}
	
	/**
	 * Returns the title of this window.
	 * @return the title of this window.
	 */
	public String getTitle() {
		return new String(title);
	}
	
	/**
	 * Sets the title of this window.
	 * @param title the new title for this window.
	 */
	public void setTitle(String title) {
		this.ref.WINDOW_TITLE = title;
		glfwSetWindowTitle(window, title);
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


	/**
	 * Returns true if this window should close.
	 * @return true if the window should close, otherwise false
	 */
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
}