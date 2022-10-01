package ui;

import constants.Constants;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

  private Timer timer;

  public GamePanel() {
    initializeLayout();
    startAnimation();
  }

  private void startAnimation() {
//    update and re-paint the canvas in every N seconds
    timer = new Timer(100, new GameLoop(this));
    timer.start();
  }

  private void initializeLayout() {
    setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
//    app focus will be on the panel
    setFocusable(true);
  }

  public void loop() {
  }
}
