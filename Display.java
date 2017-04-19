import java.awt.*;
import java.awt.event.*;

/**
 * Class is the display of the game and handles the rendering
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Display extends Frame {
     /**
     * Constructor of the Display class
     * set the frame title and size
     *
     * @param a adapter which get the window events
     */
  public Display (WindowAdapter a) {
    setTitle("Ascii-Crawler");
    setSize(400,100);
    addWindowListener(a);

    setVisible(true);
  }
}
