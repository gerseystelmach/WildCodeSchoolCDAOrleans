package org.wcscda.worms;

import java.util.Random;
/*This class is used to create the seeds, which is used to generate the asymmetric fields where the worms play */
public class RandomGenerator {
  private static Random instance;
  private static Integer seed;

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
      RandomGenerator.seed = seed;
      instance = new Random(seed);
    }

    return instance;
  }

  public static Integer getSeed() {
    return seed;
  }

  public static double nextDouble() {
    return getInstance().nextDouble();
  }
}
