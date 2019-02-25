package opengl.debug;

import static opengl.debug.LoggerLevel.DEBUG;
import static opengl.debug.LoggerLevel.ERROR;
import static opengl.debug.LoggerLevel.FATAL;
import static opengl.debug.LoggerLevel.INFO;
import static opengl.debug.LoggerLevel.VERBOSE;
import static opengl.debug.LoggerLevel.WARNING;

import java.io.PrintStream;

public class Logger {
	
	private LoggerLevel level = INFO;

	private PrintStream standardOut = System.out;
	
	public Logger(PrintStream writer) {
		standardOut = writer;
	}
	
	public void setLevel(LoggerLevel level) {
		this.level = level;
	}
	
	public LoggerLevel getLevel() {
		return this.level;
	}
	
	public void setStandardOut(PrintStream writer) {
		standardOut = writer;
	}
	
	private void log(LoggerLevel level, String module, String msg) {
		if (level.ordinal() >= this.level.ordinal()) {
			standardOut.println(level + module + " " + msg);
		}
	}
	
	public PrintStream getStream() {
		return standardOut;
	}
	
	public void fatal(String module, String msg) {
		log(FATAL, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void fatal(String msg) {
		log(FATAL, "", msg);
	}
	
	public void error(String module, String msg) {
		log(ERROR, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void error(String msg) {
		log(ERROR, "", msg);
	}
	
	public void warning(String module, String msg) {
		log(WARNING, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void warning(String msg) {
		log(WARNING, "", msg);
	}
	
	public void info(String module, String msg) {
		log(INFO, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void info(String msg) {
		log(INFO, "", msg);
	}
	
	public void debug(String module, String msg) {
		log(DEBUG, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void debug(String msg) {
		log(DEBUG, "", msg);
	}
	
	public void verbose(String module, String msg) {
		log(VERBOSE, module != "" ? "[" + module + "]" : "", msg);
	}
	
	public void verbose(String msg) {
		log(VERBOSE, "", msg);
	}
	
}
