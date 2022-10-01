package ui;

import callbacks.GameEventListener;
import constants.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import objects.Background;
import objects.Spaceship;

public class GamePanel extends JPanel {

  private Timer timer;
  private Spaceship spaceship;
  private Background background;

  public GamePanel() {
    initializeVariables();
    initializeLayout();
    startAnimation();
  }

  private void initializeVariables() {
    spaceship = new Spaceship();
    addKeyListener(new GameEventListener(this));
    background = new Background(0,0);
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
  }

  private void handleBackground(Graphics graphics) {
    background.update(graphics);
  }

  private void handleSpaceship(Graphics graphics) {
    spaceship.update(graphics);
  }

  public void loop() {
    repaint();
  }

  public void keyPressed(KeyEvent e) {
    spaceship.keyPressed(e);
  }

  public void keyReleased(KeyEvent e) {
    spaceship.keyReleased(e);
  }
}
