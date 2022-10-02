package ui;

import listener.GameEventListener;
import utils.Constants;
import utils.GameVariables;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Background;
import model.Laser;
import model.Meteor;
import model.Spaceship;
import random.RandomGenerator;

public class GamePanel extends JPanel {

  private Timer timer;
  private transient Spaceship spaceship;
  private transient Background background;
  private transient List<Laser> lasers;
  private transient List<Meteor> meteors;
  private transient RandomGenerator randomGenerator;
  private transient CollisionDetector collisionDetector;

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

    if (GameVariables.IN_GAME) {
      handleBackground(graphics);
      handleSpaceship(graphics);
      handleLaser(graphics);
      handleMeteors(graphics);
      handleScoreAndLives(graphics);
    } else {
      if (timer.isRunning()) {
        timer.stop();
      }
      gameOver(graphics);
    }

//    we want to synchronize the canvas
    Toolkit.getDefaultToolkit().sync();
  }

  private void handleScoreAndLives(Graphics graphics) {

    if(!GameVariables.IN_GAME) {
      return;
    }
    Font font = new Font("Helvetica", Font.BOLD, 20);
    graphics.setColor(Color.WHITE);
    graphics.setFont(font);
    graphics.drawString("Score: " + GameVariables.SCORE, Constants.FRAME_WIDTH-150, 50);
    graphics.drawString("Lives: " + GameVariables.LIVES, 50, 50);
  }

  private void gameOver(Graphics graphics) {
    background.update(graphics);
//    draw game over
    Font font = new Font("Helvetica", Font.BOLD, 50);
    FontMetrics fontMetrics = getFontMetrics(font);
    graphics.setColor(Color.WHITE);
    graphics.setFont(font);
    graphics.drawString(Constants.GAME_OVER, Constants.FRAME_WIDTH/2 - fontMetrics.stringWidth(Constants.GAME_OVER)/2, Constants.FRAME_HEIGHT/2-100);

//    draw score
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);
    graphics.drawString("Score: " + GameVariables.SCORE, Constants.FRAME_WIDTH/2 - fontMetrics.stringWidth("Score: " + GameVariables.SCORE)/2, Constants.FRAME_HEIGHT-300);
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
      if (GameVariables.IN_GAME) {
        int x = spaceship.getX();
        int y = spaceship.getY();

        lasers.add(new Laser(x, y));
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    spaceship.keyReleased(e);
  }

  private void update() {
//    check whether game is over
    if (spaceship.isDead()) {
      GameVariables.IN_GAME = false;
      return;
    }

//    generate random meteors
    if (randomGenerator.isMeteorGenerated()) {
      int randomX = randomGenerator.generateRandomX();
      int randomY = -Constants.METEOR_HEIGHT;
      meteors.add(new Meteor(randomX, randomY));
    }

//    check whether meteor reached the end of the canvas - GAME OVER
    for (Meteor meteor :meteors) {
      if (meteor.isDead()) {
        spaceship.die();
      }
    }

//    check whether to remove dead laser beams
    List<Laser> destroyedLasers = new ArrayList<>();
    for (Laser laser : lasers) {
      if (laser.isDead()) {
        destroyedLasers.add(laser);
      }
    }
    lasers.removeAll(destroyedLasers);

    //    detect laser-meteor collisions
    Meteor destroyedMeteor = null;
    Laser destroyedLaser = null;

    for(Laser laser : lasers) {
      if(!laser.isDead()) {
        for (Meteor meteor : meteors) {
          if (collisionDetector.collisionLaserMeteor(laser, meteor)) {
            destroyedMeteor = meteor;
            destroyedLaser = laser;
            GameVariables.SCORE += 20;
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
        GameVariables.LIVES--;

        if(GameVariables.LIVES < 0 ) {
          spaceship.die();
        }
      }
    }
    meteors.remove(destroyedMeteor);
  }
}
