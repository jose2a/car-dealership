package com.revature.cardealership.utils;

import org.apache.logging.log4j.LogManager;

public class LogginUtil {
	
	private static org.apache.logging.log4j.Logger log = LogManager.getRootLogger();
	
	public static void debug(String msg) {
		log.debug(msg);
	}

}
