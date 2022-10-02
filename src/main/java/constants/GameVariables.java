package constants;

import ui.GamePanel;

public class GameVariables {

  private GameVariables() {}

//  Java forces the OS not to cache this variable
//  it is always read from the memory
  public static volatile boolean IN_GAME = true;
  public static volatile int SHIELDS = 10;
  public static volatile int SCORE = 0;
}
