package objects;

import constants.Constants;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Spaceship extends Sprite{

  private int dx;
  private List<ImageIcon> frames;
  private int imageIndex;

  public Spaceship() {
    this.frames = new ArrayList<>();
    frames.add(new ImageIcon("src/main/resources/images/spaceship_0.png"));
    frames.add(new ImageIcon("src/main/resources/images/spaceship_1.png"));
    frames.add(new ImageIcon("src/main/resources/images/spaceship_2.png"));
    frames.add(new ImageIcon("src/main/resources/images/spaceship_3.png"));

//    set the first frame as the initial image
    setImage(frames.get(0).getImage());
//    set the initial coordinates of the spaceship
    int initialX = Constants.FRAME_WIDTH/2 - Constants.SHIP_WIDTH/2;
    int initialY = Constants.FRAME_HEIGHT - Constants.SHIP_HEIGHT - 10;

    setX(initialX);
    setY(initialY);
  }

  @Override
  protected void update() {
//    update the frame (image)
    imageIndex++;
    if (imageIndex > frames.size()-1) {
      imageIndex = 0;
    }
  }

  @Override
  protected void act(Graphics graphics) {
//    pain on the canvas
    setImage(frames.get(imageIndex).getImage());
    graphics.drawImage(getImage(), x, y, null);
  }
}
