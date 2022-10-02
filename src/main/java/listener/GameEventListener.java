package listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ui.GamePanel;

public class GameEventListener extends KeyAdapter {

  private final GamePanel gamePanel;

  public GameEventListener(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    gamePanel.keyPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    gamePanel.keyReleased(e);
  }
}
