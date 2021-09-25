package org.wcs_cda.worms;

import java.util.Random;

public class RandomGenerator {
	private static Random instance;
	
	public static Random getInstance() {
		if(instance == null) {
			instance = new Random();
		}
		
		return instance;
	}
}
