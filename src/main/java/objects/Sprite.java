package objects;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Sprite {

  protected int x;
  protected int y;

  protected Image image;

//   is object dead or not
  protected boolean dead;

  protected abstract void update();
//  paint the sprite onto the canvas
  protected abstract void act(Graphics graphics);

  public void update(Graphics graphics) {
    update();
    act(graphics);
  }

  public void die() {
    this.dead = true;
  }

  public boolean isDead() {
    return this.dead;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
