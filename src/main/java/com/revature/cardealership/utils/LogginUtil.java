package com.revature.cardealership.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogginUtil {

	private static org.apache.logging.log4j.Logger log = LogManager.getRootLogger();
	private static final Logger log2 = LogManager.getLogger();

	public static void trace(String msg) {
		log.trace(msg);
		log2.trace(msg);
	}

	public static void debug(String msg) {
		log.debug(msg);
		log2.debug(msg);
	}

	public static void info(String msg) {
		log.info(msg);
		log2.info(msg);
	}

	public static void warn(String msg) {
		log.warn(msg);
		log2.warn(msg);
	}

	public static void error(String msg) {
		log.error(msg);
		log2.error(msg);
	}

	public static void fatal(String msg) {
		log.fatal(msg);
		log2.fatal(msg);
	}

}
