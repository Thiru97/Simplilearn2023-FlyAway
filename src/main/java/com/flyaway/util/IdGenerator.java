package com.flyaway.util;

import java.util.Random;

public class IdGenerator {

	public String flightNumberGenerator(String airlineName) {
		Random random = new Random();
		String flightNumber = "";
		int randomNumber = random.nextInt(10000);
		String capitalizedWord = airlineName.toUpperCase();
		String airlineSubString = capitalizedWord.substring(0, 2);

		flightNumber = airlineSubString + randomNumber;
		System.out.println(flightNumber);
		return flightNumber;
	}

}
