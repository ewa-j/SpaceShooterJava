package ui;

import utils.Constants;
import model.Sprite;

public class CollisionDetector {

  public boolean collisionLaserMeteor(Sprite laser, Sprite meteor) {

    int laserX = laser.getX();
    int laserY = laser.getY();
    int meteorX = meteor.getX();
    int meteorY = meteor.getY();

    return laserX < meteorX + Constants.METEOR_WIDTH &&
        meteorX < laserX + Constants.LASER_WIDTH &&
        laserY < meteorY + Constants.METEOR_HEIGHT &&
        meteorY < laserY + Constants.LASER_HEIGHT;
  }

  public boolean collisionMeteorSpaceship(Sprite spaceship, Sprite meteor) {

    int spaceshipX = spaceship.getX();
    int spaceshipY = spaceship.getY();
    int meteorX = meteor.getX();
    int meteorY = meteor.getY();

    return spaceshipX < meteorX + Constants.METEOR_WIDTH &&
        meteorX < spaceshipX + Constants.SHIP_WIDTH &&
        spaceshipY < meteorY + Constants.METEOR_HEIGHT &&
        meteorY < spaceshipY + Constants.SHIP_HEIGHT;
  }

  public boolean collisionMedicalKitSpaceship(Sprite spaceship, Sprite medicalKit) {

    int spaceshipX = spaceship.getX();
    int spaceshipY = spaceship.getY();
    int medicalKitX = medicalKit.getX();
    int medicalKitY = medicalKit.getY();

    return spaceshipX < medicalKitX + Constants.MEDICAL_KIT_WIDTH &&
        medicalKitX < spaceshipX + Constants.SHIP_WIDTH &&
        spaceshipY < medicalKitY + Constants.MEDICAL_KIT_HEIGHT &&
        medicalKitY < spaceshipY + Constants.SHIP_HEIGHT;
  }
}
