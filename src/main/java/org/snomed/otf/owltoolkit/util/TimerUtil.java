package org.snomed.otf.owltoolkit.util;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TimerUtil {

	private final String timerName;
	private final long start;
	private long lastCheck;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final Level loggingLevel;

	public TimerUtil(String timerName) {
		this(timerName, Level.INFO);
	}

	public TimerUtil(String timerName, Level loggingLevel) {
		this.loggingLevel = loggingLevel;
		this.timerName = timerName;
		this.start = new Date().getTime();
		lastCheck = start;
		log("Timer {}: started", timerName);
	}

	public void checkpoint(String name) {
		final long now = new Date().getTime();
		float millisTaken = now - lastCheck;
		lastCheck = now;
		log("Timer {}: {} took {} seconds", timerName, name, millisTaken / 1000f);
	}

	public void finish() {
		final long now = new Date().getTime();
		float millisTaken = now - start;
		log("Timer {}: total took {} seconds", timerName, millisTaken / 1000f);
	}

	private void log(String s, Object... o) {
		switch (loggingLevel.toString()) {
			case "TRACE":
				logger.trace(s, o);
				break;
			case "DEBUG":
				logger.debug(s, o);
				break;
			case "WARN":
				logger.warn(s, o);
				break;
			case "ERROR":
				logger.error(s, o);
				break;
			case "OFF":
				break;
			default:
				logger.info(s, o);
		}
	}

}
