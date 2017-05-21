package asciicrawler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * \brief save all key events to list
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
class GameKeyListener implements KeyListener {

	long lastEventTime;

	/** Defines what should be done if a key is typed (keyboard input to OS) */
	public void keyTyped(KeyEvent e) {
	}

	/** The keycode of the pressed key is saved to a list of keys in
	* 	the Game obejct */
	public void keyPressed(KeyEvent e) {
		Game.keys.setKey(e.getKeyCode());
	}

	/** Defines what should be done if a pressed key is released */
	public void keyReleased(KeyEvent e) {
	}
}
