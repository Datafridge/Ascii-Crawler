package asciicrawler;

import java.awt.*;
import java.awt.event.*;

/**
 * handles the close request and disposes the window and quit the application
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
class GameWindowAdapter extends WindowAdapter
{
  public void windowClosing(WindowEvent e)
  {
    e.getWindow().dispose();
    System.exit(0);
  }
}
