package ui;

import listener.GameEventListener;
import model.MedicalKit;
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
  private transient Background backgroundSprite;
  private transient List<Laser> lasers;
  private transient List<Meteor> meteors;
  private transient List<MedicalKit> medicalKits;
  private transient RandomGenerator randomGenerator;
  private transient CollisionDetector collisionDetector;

  public GamePanel() {
    initializeVariables();
    initializeLayout();
    startAnimation();
  }

  public void loop() {
    update();
    repaint();
  }

  public void keyPressed(KeyEvent keyEvent) {
    spaceship.keyPressed(keyEvent);

    int key = keyEvent.getKeyCode();

    if (key == KeyEvent.VK_SPACE && GameVariables.inGame) {
      int x = spaceship.getX();
      int y = spaceship.getY();

      lasers.add(new Laser(x, y));
    }
  }

  public void keyReleased(KeyEvent e) {
    spaceship.keyReleased(e);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    handleCanvas(graphics);
  }

  private void initializeVariables() {
    spaceship = new Spaceship();
    addKeyListener(new GameEventListener(this));
    backgroundSprite = new Background(0,0);
    lasers = new ArrayList<>();
    meteors = new ArrayList<>();
    medicalKits = new ArrayList<>();
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

  private void handleCanvas(Graphics graphics) {

    if (GameVariables.inGame) {
      handleBackground(graphics);
      handleSpaceship(graphics);
      handleLaser(graphics);
      handleMeteors(graphics);
      handleMedicalKit(graphics);
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

    if(!GameVariables.inGame) {
      return;
    }
    setFontForScoreAndLives(graphics);
    drawScoreOnCanvas(graphics);
    drawLivesOnCanvas(graphics);
  }

  private void drawLivesOnCanvas(Graphics graphics) {
    graphics.drawString(Constants.LIVES_STRING + GameVariables.lives, 50, 50);
  }

  private void drawScoreOnCanvas(Graphics graphics) {
    graphics.drawString(Constants.SCORE_STRING + GameVariables.score, Constants.FRAME_WIDTH-150, 50);
  }

  private void setFontForScoreAndLives(Graphics graphics) {
    Font font = new Font("Helvetica", Font.BOLD, 20);
    graphics.setColor(Color.WHITE);
    graphics.setFont(font);
  }

  private void gameOver(Graphics graphics) {
    backgroundSprite.update(graphics);
//    draw game over
    Font font = new Font("Helvetica", Font.BOLD, 50);
    FontMetrics fontMetrics = getFontMetrics(font);
    graphics.setColor(Color.WHITE);
    graphics.setFont(font);
    graphics.drawString(Constants.GAME_OVER, Constants.FRAME_WIDTH/2 - fontMetrics.stringWidth(Constants.GAME_OVER)/2, Constants.FRAME_HEIGHT/2-100);

//    draw score
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);
    graphics.drawString(Constants.SCORE_STRING + GameVariables.score, Constants.FRAME_WIDTH/2 - fontMetrics.stringWidth(Constants.SCORE_STRING + GameVariables.score)/2, Constants.FRAME_HEIGHT-300);
  }

  private void handleBackground(Graphics graphics) {
    backgroundSprite.update(graphics);
  }

  private void handleSpaceship(Graphics graphics) {
    spaceship.update(graphics);
  }

  private void handleLaser(Graphics graphics) {
    lasers.forEach(laser -> {
      if(!laser.isDead()) {
        laser.update(graphics);
      }
    });
  }

  private void handleMedicalKit(Graphics graphics) {
    medicalKits.forEach(medicalKit -> {
      if(!medicalKit.isDead()) {
        medicalKit.update(graphics);
      }
    });
  }

  private void handleMeteors(Graphics graphics) {
    meteors.forEach(meteor -> {
      if(!meteor.isDead()) {
        meteor.update(graphics);
      }
    });
  }

  private void update() {
    isGameOver();
    updateMeteorSpeed();
    updateMeteorProbability();
    generateRandomMeteors();
    generateRandomMedicalKit();
    checkWhetherMeteorReachedTheEndOfCanvas();
    removeDeadLaserBeams();
    removeDeadMedicalKit();
    detectLaserMeteorCollision();
    detectSpaceshipMeteorCollision();
    detectSpaceshipMedicalKitCollision();
  }

  private void detectSpaceshipMeteorCollision() {
    final Meteor[] destroyedMeteor = new Meteor[1];
    destroyedMeteor[0] = null;

    meteors.forEach(meteor -> {
      if (collisionDetector.collisionBetweenSprites(spaceship, meteor)) {
        destroyedMeteor[0] = meteor;
        GameVariables.lives--;

        if(GameVariables.lives < 1 ) {
          spaceship.die();
        }
      }
    });
    meteors.remove(destroyedMeteor[0]);
  }

  private void detectLaserMeteorCollision() {
    final Meteor[] destroyedMeteor = {null};
    final Laser[] destroyedLaser = {null};

    lasers.forEach(laser -> {
      if(!laser.isDead()) {
        for (Meteor meteor : meteors) {
          if (collisionDetector.collisionBetweenSprites(laser, meteor)) {
            destroyedMeteor[0] = meteor;
            destroyedLaser[0] = laser;
            GameVariables.score += 20;
          }
        }
        meteors.remove(destroyedMeteor[0]);
      }
    });
    lasers.remove(destroyedLaser[0]);
  }

  private void detectSpaceshipMedicalKitCollision() {
    final MedicalKit[] destroyedMedicalKit = new MedicalKit[1];
    destroyedMedicalKit[0] = null;

    medicalKits.forEach(medicalKit -> {
      if (collisionDetector.collisionBetweenSprites(spaceship, medicalKit)) {
        destroyedMedicalKit[0] = medicalKit;
        GameVariables.lives++;
      }
    });
    medicalKits.remove(destroyedMedicalKit[0]);
  }

  private void removeDeadLaserBeams() {
    List<Laser> destroyedLasers = new ArrayList<>();

    lasers.forEach(laser -> {
      if (laser.isDead()) {
        destroyedLasers.add(laser);
      }
    });
    lasers.removeAll(destroyedLasers);
  }

  private void removeDeadMedicalKit() {
    List<MedicalKit> destroyedMedicalKits = new ArrayList<>();

    medicalKits.forEach(medicalKit -> {
      if (medicalKit.isDead()) {
        destroyedMedicalKits.add(medicalKit);
      }
    });
    medicalKits.removeAll(destroyedMedicalKits);
  }

  private void checkWhetherMeteorReachedTheEndOfCanvas() {

    meteors.forEach(meteor -> {
      if (meteor.isDead()) {
        spaceship.die();
      }
    });
  }

  private void generateRandomMeteors() {
    if (randomGenerator.isMeteorGenerated()) {
      int randomX = randomGenerator.generateRandomX();
      int randomY = -Constants.METEOR_HEIGHT;
      meteors.add(new Meteor(randomX, randomY));
    }
  }

  private void generateRandomMedicalKit() {
    if (GameVariables.lives <= 2 && randomGenerator.isMedicalKitGenerated() && medicalKits.isEmpty()) {
      int randomX = randomGenerator.generateRandomX();
      int randomY = -Constants.MEDICAL_KIT_HEIGHT;
      medicalKits.add(new MedicalKit(randomX, randomY));
    }
  }

  private void isGameOver() {
    if (spaceship.isDead()) {
      GameVariables.inGame = false;
    }
  }

  private static void updateMeteorSpeed() {
    if (GameVariables.score >= 200) {
      GameVariables.meteorSpeed = 3;
    } else if (GameVariables.score >= 400) {
      GameVariables.meteorSpeed = 5;
    } else if (GameVariables.score >= 700) {
      GameVariables.meteorSpeed = 6;
    } else if (GameVariables.score >= 1300) {
      GameVariables.meteorSpeed = 7 ;
    } else if (GameVariables.score >= 2000) {
      GameVariables.meteorSpeed = 9;
    } else if (GameVariables.score >= 3500) {
      GameVariables.meteorSpeed = 11;
    }
  }

  private static void updateMeteorProbability() {
    if (GameVariables.score >= 300) {
      GameVariables.meteorProbability = 0.015;
    } else if (GameVariables.score >= 500) {
      GameVariables.meteorProbability = 0.020;
    } else if (GameVariables.score >= 900) {
      GameVariables.meteorProbability = 0.025;
    } else if (GameVariables.score >= 1100) {
      GameVariables.meteorProbability = 0.040;
    } else if (GameVariables.score >= 1400) {
      GameVariables.meteorProbability = 0.043;
    } else if (GameVariables.score >= 1600) {
      GameVariables.meteorProbability = 0.060;
    } else if (GameVariables.score >= 2300) {
      GameVariables.meteorProbability = 0.085;
    } else if (GameVariables.score >= 2600) {
      GameVariables.meteorProbability = 0.090;
    } else if (GameVariables.score >= 3000) {
      GameVariables.meteorProbability = 0.100;
    }
  }
}
