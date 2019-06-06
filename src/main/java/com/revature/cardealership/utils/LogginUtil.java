package com.revature.cardealership.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogginUtil {
	
	private static org.apache.logging.log4j.Logger log = LogManager.getRootLogger();
//	private static final Logger log2 = LogManager.getLogger();
	
	public static void debug(String msg) {
		log.debug(msg);
//		log2.debug(msg);
	}

}
