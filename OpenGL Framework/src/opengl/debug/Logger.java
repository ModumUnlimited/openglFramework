package opengl.debug;

import static opengl.debug.LoggerLevel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
	
	private static LoggerLevel level = DEBUG;
	
	private static SimpleDateFormat format = new SimpleDateFormat("-dd/MM/yyyy-HH:mm:ss-", Locale.GERMAN);

	private static PrintWriter verboseOut;
	private static PrintWriter standardOut = new PrintWriter(System.out);
	
	static {
		try {
			verboseOut = new PrintWriter(new File("logs/log" + format.format(new Date()) + ".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setVerboseOutput(PrintWriter writer) {
		verboseOut = writer;
	}
	
	public void setStandardOut(PrintWriter writer) {
		standardOut = writer;
	}
	
	private void log(LoggerLevel level, String module, String msg) {
		verboseOut.println(level + module + " " + msg);
		if (level.ordinal() >= Logger.level.ordinal()) standardOut.println(level + "[" + module + "] " + msg);
	}
	
	public void fatal(String module, String msg) {
		log(FATAL, "[" + module + "]", msg);
	}
	
	public void fatal(String msg) {
		log(FATAL, "", msg);
	}
	
	public void error(String module, String msg) {
		log(ERROR, "[" + module + "]", msg);
	}
	
	public void error(String msg) {
		log(ERROR, "", msg);
	}
	
	public void warning(String module, String msg) {
		log(WARNING, "[" + module + "]", msg);
	}
	
	public void warning(String msg) {
		log(WARNING, "", msg);
	}
	
	public void info(String module, String msg) {
		log(INFO, "[" + module + "]", msg);
	}
	
	public void info(String msg) {
		log(INFO, "", msg);
	}
	
	public void debug(String module, String msg) {
		log(DEBUG, "[" + module + "]", msg);
	}
	
	public void debug(String msg) {
		log(DEBUG, "", msg);
	}
	
	public void verbose(String module, String msg) {
		log(VERBOSE, "[" + module + "]", msg);
	}
	
	public void verbose(String msg) {
		log(VERBOSE, "", msg);
	}
	
}
