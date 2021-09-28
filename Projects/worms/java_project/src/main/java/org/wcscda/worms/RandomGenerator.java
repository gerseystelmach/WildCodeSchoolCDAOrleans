package org.wcscda.worms;

import java.util.Random;

public class RandomGenerator {
  private static Random instance;

  public static Random getInstance() {
    if (instance == null) {
      long seed = new Random().nextLong();
      System.err.println("Using random seed " + seed);
      instance = new Random(seed);
    }

    return instance;
  }
  
  public static double nextDouble() {
	return getInstance().nextDouble();
  }
}
