package ui;

import callbacks.GameEventListener;
import constants.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import objects.Background;
import objects.Laser;
import objects.Meteor;
import objects.Spaceship;
import random.RandomGenerator;

public class GamePanel extends JPanel {

  private Timer timer;
  private Spaceship spaceship;
  private Background background;
  private List<Laser> lasers;
  private List<Meteor> meteors;
  private RandomGenerator randomGenerator;
  private CollisionDetector collisionDetector;

  public GamePanel() {
    initializeVariables();
    initializeLayout();
    startAnimation();
  }

  private void initializeVariables() {
    spaceship = new Spaceship();
    addKeyListener(new GameEventListener(this));
    background = new Background(0,0);
    lasers = new ArrayList<>();
    meteors = new ArrayList<>();
    randomGenerator = new RandomGenerator();
    collisionDetector = new CollisionDetector();
  }

  private void startAnimation() {
//    update and re-paint the canvas in every N seconds
    timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
    timer.start();
  }

  private void initializeLayout() {
    setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
//    app focus will be on the panel
    setFocusable(true);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    handleCanvas(graphics);
  }

  private void handleCanvas(Graphics graphics) {
    graphics.setColor(Color.ORANGE);
    graphics.fillRect(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    handleBackground(graphics);
    handleSpaceship(graphics);
    handleLaser(graphics);
    handleMeteors(graphics);
  }

  private void handleBackground(Graphics graphics) {
    background.update(graphics);
  }

  private void handleSpaceship(Graphics graphics) {
    spaceship.update(graphics);
  }

  private void handleLaser(Graphics graphics) {
    for(Laser laser : lasers) {
      if(!laser.isDead()) {
        laser.update(graphics);
      }
    }
  }

  private void handleMeteors(Graphics graphics) {
    for(Meteor meteor : meteors) {
      if(!meteor.isDead()) {
        meteor.update(graphics);
      }
    }
  }

  public void loop() {
    update();
    repaint();
  }

  public void keyPressed(KeyEvent e) {
    spaceship.keyPressed(e);

//    generate laser beam when space key is hit
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_SPACE) {
      int x = spaceship.getX();
      int y = spaceship.getY();

      lasers.add(new Laser(x, y));
    }
  }

  public void keyReleased(KeyEvent e) {
    spaceship.keyReleased(e);
  }

  private void update() {
//    check whether game is over
//    generate random meteors
    if (randomGenerator.isMeteorGenerated()) {
      int randomX = randomGenerator.generateRandomX();
      int randomY = 0 - Constants.METEOR_HEIGHT;
      meteors.add(new Meteor(randomX, randomY));
    }

    //    detect laser-meteor collisions
    Meteor destroyedMeteor = null;
    Laser destroyedLaser = null;

    for(Laser laser : lasers) {
      if(!laser.isDead()) {
        for (Meteor meteor : meteors) {
          if (collisionDetector.collisionLaserMeteor(laser, meteor)) {
            destroyedMeteor = meteor;
            destroyedLaser = laser;
          }
        }
        meteors.remove(destroyedMeteor);
      }
    }
    lasers.remove(destroyedLaser);

    //    detect spaceship-meteor collisions
    destroyedMeteor = null;
    for(Meteor meteor : meteors) {
      if (collisionDetector.collisionMeteorSpaceship(spaceship, meteor)) {
        destroyedMeteor = meteor;
      }
    }
    meteors.remove(destroyedMeteor);
  }
}
