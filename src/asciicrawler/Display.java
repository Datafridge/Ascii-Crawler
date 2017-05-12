package asciicrawler;

import java.awt.Frame;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

/**
 * Class is the display of the game and handles the rendering
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Display extends Frame {
     /**
	 * This number is needed for javas serialization
	 */
	private static final long serialVersionUID = 1214189760697788025L;

	/**
     * Constructor of the Display class
     * set the frame title and size
     *
     * @param a adapter which get the window events and one to listen to key presses
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
