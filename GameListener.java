import java.awt.*;
import java.awt.event.*;

/**
 * handles the close request and disposes the window and quit the application
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
class GameListener extends WindowAdapter
{
  public void windowClosing(WindowEvent e)
  {
    e.getWindow().dispose();
    System.exit(0);
  }
}
