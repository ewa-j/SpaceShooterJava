package app;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.GameMainFrame;

public class Application {

  public static void main(String[] args)
      throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

    EventQueue.invokeLater(GameMainFrame::new);
  }

}
