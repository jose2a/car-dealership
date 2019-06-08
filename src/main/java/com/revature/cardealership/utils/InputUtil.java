package com.revature.cardealership.utils;

import java.util.Scanner;

public class InputUtil {
	
	private static final Scanner SCAN = new Scanner(System.in);
	
	public static int getNumber(int min, int max) {
		
//		System.out.println("Please input a number between " + min + " and " + max);
		
		int input = SCAN.nextInt();
		
		if (input < min || input > max) {
			return getNumber(min, max);
		}
		
		return input;
		
	}
	
	public static double getDouble(double min, double max) {
		
//		System.out.println("Please input a number between " + min + " and " + max);
		
		double input = SCAN.nextDouble();
		
		if (input < min || input > max) {
			return getDouble(min, max);
		}
		
		return input;
		
	}
	
	public static String getString() {
		return SCAN.nextLine();
	}

}