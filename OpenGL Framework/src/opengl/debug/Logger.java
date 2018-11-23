package opengl.debug;

import static opengl.debug.LoggerLevel.DEBUG;
import static opengl.debug.LoggerLevel.ERROR;
import static opengl.debug.LoggerLevel.FATAL;
import static opengl.debug.LoggerLevel.INFO;
import static opengl.debug.LoggerLevel.VERBOSE;
import static opengl.debug.LoggerLevel.WARNING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
	
	private static LoggerLevel level = INFO;
	
	private static SimpleDateFormat format = new SimpleDateFormat("-dd-MM-yyyy_HH-mm-ss", Locale.GERMAN);

	private static PrintStream verboseOut;
	private static PrintStream standardOut = System.out;
	
	static {
		try {
			if (!new File("logs").isDirectory()) new File("logs").mkdir();
			verboseOut = new PrintStream(new File("logs/log" + format.format(new Date()) + "-full.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void setVerboseOutput(PrintStream writer) {
		verboseOut = writer;
	}
	
	public static void setStandardOut(PrintStream writer) {
		standardOut = writer;
	}
	
	private static void log(LoggerLevel level, String module, String msg) {
		verboseOut.println(level + module + " " + msg);
		if (level.ordinal() >= Logger.level.ordinal()) {
			standardOut.println(level + module + " " + msg);
		}
	}
	
	public static void fatal(String module, String msg) {
		log(FATAL, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void fatal(String msg) {
		log(FATAL, "", msg);
	}
	
	public static void error(String module, String msg) {
		log(ERROR, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void error(String msg) {
		log(ERROR, "", msg);
	}
	
	public static void warning(String module, String msg) {
		log(WARNING, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void warning(String msg) {
		log(WARNING, "", msg);
	}
	
	public static void info(String module, String msg) {
		log(INFO, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void info(String msg) {
		log(INFO, "", msg);
	}
	
	public static void debug(String module, String msg) {
		log(DEBUG, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void debug(String msg) {
		log(DEBUG, "", msg);
	}
	
	public static void verbose(String module, String msg) {
		log(VERBOSE, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public static void verbose(String msg) {
		log(VERBOSE, "", msg);
	}
	
}
