package model;

import lombok.NoArgsConstructor;
import utils.Constants;
import image.ImageFactory;
import image.ImageType;
import java.awt.Graphics;
import javax.swing.ImageIcon;

@NoArgsConstructor
public class Laser extends Sprite{

  public Laser(int x, int y) {
    this.x = x;
    this.y = y;
    initialize();
  }

  private void initialize() {
    ImageIcon imageIcon = ImageFactory.createImage(ImageType.LASER);
    setImage(imageIcon.getImage());

    setX(x + Constants.SHIP_WIDTH/2 - Constants.LASER_WIDTH/2);
    setY(y - Constants.LASER_HEIGHT);
  }

  @Override
  protected void update() {
    this.y -= Constants.LASER_SPEED;

//    if the laser is outside the canvas, remove it
    if (y < 0 ) {
      die();
    }
  }

  @Override
  protected void act(Graphics graphics) {
    graphics.drawImage(getImage(), x, y, null);
  }
}
