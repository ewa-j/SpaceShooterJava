package model;

import java.awt.Graphics;
import java.awt.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Sprite {

  protected int x;
  protected int y;
  protected Image image;
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

}
