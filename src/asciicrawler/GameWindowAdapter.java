package asciicrawler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * handles the close request, disposes the window and quits the application
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
class GameWindowAdapter extends WindowAdapter
{
  /** Defines the behavior if the window is closing. */
  public void windowClosing(WindowEvent e)
  {
    e.getWindow().dispose();
    System.exit(0);
  }
}
