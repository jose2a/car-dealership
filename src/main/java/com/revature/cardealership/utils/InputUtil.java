package com.revature.cardealership.utils;

import java.util.Scanner;

public class InputUtil {

	private static final Scanner SCAN = new Scanner(System.in);

	public static int getNumber(int min, int max) {

		System.out.println("Please input a number between " + min + " and " + max);

		int input = SCAN.nextInt();

		return input;
	}

	public static double getDouble() {
		return SCAN.nextDouble();

	}

	public static String getString() {
		return SCAN.nextLine();
	}

}