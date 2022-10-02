package model;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import utils.Constants;

public class MedicalKit extends Sprite {

  public MedicalKit(int x, int y) {
    this.x = x;
    this.y = y;
    initialize();
  }

  private void initialize() {
    setImage(new ImageIcon(Constants.MEDICAL_KIT_URL).getImage());
  }

  @Override
  protected void update() {

//    move meteor from top to bottom
    y += Constants.MEDICAL_KIT_SPEED;
//    if meteor outside canvas, remove it and game over
    if (y >= Constants.FRAME_HEIGHT) {
      die();
    }
  }

  @Override
  protected void act(Graphics graphics) {
    graphics.drawImage(this.getImage(), x, y, null);
  }

}
