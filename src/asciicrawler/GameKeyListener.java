package asciicrawler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * save all key events to list
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
class GameKeyListener implements KeyListener {

	long lastEventTime;

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		Game.keys.setKey(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
	}
}
