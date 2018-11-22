package opengl.debug;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public enum LoggerLevel {
	
	VERBOSE,
	DEBUG,
	INFO,
	WARNING,
	ERROR,
	FATAL;
	
	SimpleDateFormat format = new SimpleDateFormat("[dd/MM/yyyy][HH:mm:ss][", Locale.GERMAN);
	
	@Override
	public String toString() {
		return format.format(new Date()) + name() + "]";
	}
	
}
