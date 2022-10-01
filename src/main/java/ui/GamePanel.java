package ui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

  public GamePanel() {
    initializeLayout();
  }

  private void initializeLayout() {
    setPreferredSize(new Dimension(300, 600));
//    app focus will be on the panel
    setFocusable(true);
  }
}
