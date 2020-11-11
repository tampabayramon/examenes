/*
 * RandomNumbers.java
 *
 * Created on December 25, 2002, 7:37 PM
 */

package org.kp4tr.exams.util;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 * @author Ramon Gonzalez
 */
public class RandomNumbers {

	public RandomNumbers() {
	}

	public int randomNumber(int rangeTo) {

		return randomize(rangeTo);
	}

	public int randomNumber(int rangeTo, String skipit) {

		int aRandomNumber = randomize(rangeTo);
		while (this.skipIt(Integer.toString(aRandomNumber), skipit)) {
			aRandomNumber = randomize(rangeTo);
		}
		return aRandomNumber;
	}

	private int randomize(int rangeTo) {
		int aRandomNumber = 0;

		try {
			Thread.sleep(9, 3); // sleep for 1 millis, 2 nanos to
			// generate a more random seed
			long seed = System.currentTimeMillis();
			Random randomNumber = new Random(seed);
			rangeTo--;
			aRandomNumber = randomNumber.nextInt(rangeTo);
			aRandomNumber++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aRandomNumber;
	}

	private boolean skipIt(String value, String skipit) {

		StringTokenizer st = new StringTokenizer(skipit, ",");
		while (st.hasMoreTokens()) {
			if (st.nextToken().equals(value)) {
				return true;
			}
		}

		return false;
	}
}
