package objects;

import constants.Constants;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Meteor extends Sprite{

  private List<ImageIcon> frames;
  private int imageIndex;

  public Meteor(int x, int y) {
    this.x = x;
    this.y = y;
    initialize();
  }

  private void initialize() {
    frames = new ArrayList<>();
    frames.add(new ImageIcon("src/main/resources/images/meteor_01.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_02.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_03.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_04.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_05.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_06.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_07.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_08.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_09.png"));
    frames.add(new ImageIcon("src/main/resources/images/meteor_10.png"));
    setImage(frames.get(0).getImage());
  }

  @Override
  protected void update() {

//    move meteor from top to bottom
    y += Constants.METEOR_SPEED;
//    if meteor outside canvas, remove it and game over
    if (y >= Constants.FRAME_HEIGHT) {
      die();
//      update image index
      imageIndex++;
      if(imageIndex > frames.size()-1) {
        imageIndex = 0;
      }
    }
  }

  @Override
  protected void act(Graphics graphics) {
    graphics.drawImage(frames.get(imageIndex).getImage(), x, y, null);
  }
}
