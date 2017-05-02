package asciicrawler;

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
  public Display (WindowAdapter a, KeyListener b) {
    setTitle("Ascii-Crawler");
    setSize(200,300);
    addWindowListener(a);
    addKeyListener(b);
    setFocusTraversalKeysEnabled(false);

    setVisible(true);
  }
}
