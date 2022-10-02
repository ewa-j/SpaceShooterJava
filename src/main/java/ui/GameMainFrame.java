package ui;

import utils.Constants;
import image.ImageFactory;
import image.ImageType;
import javax.swing.JFrame;

public class GameMainFrame extends JFrame {

  public GameMainFrame() {
    initialize();
  }

  private void initialize() {

    add(new GamePanel());
//    set the frame size to fit the preferred components' size
    pack();
//    define frame title
    setTitle(Constants.GAME_TITLE);
//    icon/logo
    setIconImage(ImageFactory.createImage(ImageType.LOGO).getImage());
//    default close operation
    setDefaultCloseOperation(EXIT_ON_CLOSE);
//    window will open in the center of the screen
    setLocationRelativeTo(null);
//    user will not be able to resize window
    setResizable(false);
    setVisible(true);
  }
}
