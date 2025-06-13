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
  public static final String BACKGROUND_URL = "src/main/resources/images/outer-space.jpg";
  public static final String LASER_URL = "src/main/resources/images/laser_shot.png";
  public static final String LASER_UPGRADED_URL = "src/main/resources/images/laser_sprite.png";
  public static final String MEDICAL_KIT_URL = "src/main/resources/images/medical_kit.png";
  public static final String CRATE_URL = "src/main/resources/images/crate.jpg";

  public static final int GAME_SPEED = 15; //approximately 67 FPS

  public static final int LASER_SPEED = 4;
  public static final int LASER_WIDTH = 12;
  public static final int LASER_HEIGHT = 62;

  public static final int METEOR_WIDTH = 48;
  public static final int METEOR_HEIGHT = 82;

  public static final double MEDICAL_KIT_PROBABILITY = 0.001;
  public static final int MEDICAL_KIT_WIDTH = 35;
  public static final int MEDICAL_KIT_HEIGHT = 35;
  public static final int MEDICAL_KIT_SPEED = 8;

  public static final double CRATE_PROBABILITY = 0.001;
  public static final int CRATE_WIDTH = 40;
  public static final int CRATE_HEIGHT = 40;
  public static final int CRATE_SPEED = 7;
}
