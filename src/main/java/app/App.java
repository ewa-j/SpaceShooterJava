package app;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.GameMainFrame;

public class App {

  public static void main(String[] args)
      throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//    app will use the underlying OS related look and feel
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

//    start the app on a distinct thread as Swing objects are not multi-thread safe
//    app is run on the Event Dispatch Thread
    EventQueue.invokeLater(() -> {
      new GameMainFrame();
    });
  }

}
