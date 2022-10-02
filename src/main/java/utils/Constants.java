package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final int FRAME_WIDTH = 420;
  public static final int FRAME_HEIGHT = 746;

  public static final int SHIP_WIDTH = 75;
  public static final int SHIP_HEIGHT = 104;

  public static final String GAME_TITLE = "Space Shooter";
  public static final String GAME_OVER = "GAME OVER";
  public static final String SCORE_STRING = "Score: ";
  public static final String LIVES_STRING = "Lives: ";

  public static final String LOGO_URL = "src/main/resources/images/meteor.png";
  public static final String BACKGROUND_URL = "src/main/resources/images/background.jpg";
  public static final String LASER_URL = "src/main/resources/images/laser_shot.png";

  public static final int GAME_SPEED = 15; //approximately 67 FPS

  public static final int LASER_SPEED = 4;
  public static final int LASER_WIDTH = 12;
  public static final int LASER_HEIGHT = 62;

  public static final int METEOR_SPEED = 2;
  public static final double METEOR_PROBABILITY = 0.007;
  public static final int METEOR_WIDTH = 48;
  public static final int METEOR_HEIGHT = 82;
}
