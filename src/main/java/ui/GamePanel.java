package ui;

import constants.Constants;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

  public GamePanel() {
    initializeLayout();
  }

  private void initializeLayout() {
    setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
//    app focus will be on the panel
    setFocusable(true);
  }
}
