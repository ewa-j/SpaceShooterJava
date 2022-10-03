package ui;

import model.Sprite;

public class CollisionDetector {

  public boolean collisionBetweenSprites(Sprite spriteOne, Sprite spriteTwo) {

    int spriteOneX = spriteOne.getX();
    int spriteOneY = spriteOne.getY();
    int spriteTwoX = spriteTwo.getX();
    int spriteTwoY = spriteTwo.getY();

    return spriteOneX < spriteTwoX + spriteTwo.getImage().getWidth(null) &&
        spriteTwoX < spriteOneX + spriteOne.getImage().getWidth(null) &&
        spriteOneY < spriteTwoY + spriteTwo.getImage().getHeight(null) &&
        spriteTwoY < spriteOneY + spriteOne.getImage().getHeight(null);
  }

}
