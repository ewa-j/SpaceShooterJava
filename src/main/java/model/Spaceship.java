package model;

import utils.Constants;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Spaceship extends Sprite {

  private int dx;
  private final List<ImageIcon> frames;
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
     x += dx;

//     check the boundaries
//     ship cannot go beyond the canvas on the left side
    if (x <= 0) {
      x = 0;
    }
    //     ship cannot go beyond the canvas on the right side
    if (x >= Constants.FRAME_WIDTH-Constants.SHIP_WIDTH) {
      x = Constants.FRAME_WIDTH-Constants.SHIP_WIDTH;
    }

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

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      dx = -3;
    }
    if (key == KeyEvent.VK_RIGHT) {
      dx = 3;
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      dx = 0;
    }
    if (key == KeyEvent.VK_RIGHT) {
      dx = 0;
    }
  }
}
