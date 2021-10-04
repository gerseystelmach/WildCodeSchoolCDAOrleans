package org.wcscda.worms;

import java.util.Random;

public class RandomGenerator {
  private static Random instance;

  public static Random getInstance() {
    if (instance == null) {
      int seed;

      if (Config.getRandomSeed() == null) {
        seed = new Random().nextInt();
        System.err.println("Using random seed " + seed);
      } else {
        seed = Config.getRandomSeed();
        System.err.println("Using provided random seed " + seed);
      }

      instance = new Random(seed);
    }

    return instance;
  }

  public static double nextDouble() {
    return getInstance().nextDouble();
  }
}
