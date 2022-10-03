package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameVariables {

  public static volatile boolean inGame = true;
  public static volatile int lives = 10;
  public static volatile int score = 0;
  public static int meteorSpeed = 2;
  public static double meteorProbability = 0.01;
  public static boolean isCrateCollected = false;
}
