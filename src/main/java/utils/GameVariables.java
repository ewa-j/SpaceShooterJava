package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameVariables {

  public static volatile boolean IN_GAME = true;
  public static volatile int LIVES = 10;
  public static volatile int SCORE = 0;
  public static int meteorSpeed = 2;
  public static double meteorProbability = 0.01;
}
