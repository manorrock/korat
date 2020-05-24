/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.random;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final Random RANDOM = new Random();

	public static final String DIGITS = "0123456789";
	public static final String MIXED_CASE_CHARACTERS_AND_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
	
	@Deprecated
	public static String getRandomString(int minLength, int maxLength) {

		return getRandomString(maxLength, LOWER_CASE_CHARACTERS);
	}

	public static int getRandomInt(int min, int max) {

		if (max < Integer.MAX_VALUE) {

			max++;
		}

		return RANDOM.nextInt(max) + min;
	}

	public static boolean getRandomBoolean() {

		return getRandomInt(0, 2) == 1;
	}

	public static String getRandomHexColor() {

		return "#" + String.format("%02X", getRandomInt(0, 255)) + String.format("%02X", getRandomInt(0, 255)) + String.format("%02X", getRandomInt(0, 255));
	}

	public static String getRandomString(int length, String chars) {

		StringBuilder stringBuilder = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			
			stringBuilder.append(chars.charAt(SECURE_RANDOM.nextInt(chars.length())));
		}
		
		return stringBuilder.toString();
	}
}
