package asciicrawler;

import java.util.TimerTask;

/**
 * \brief this will handle all game ticks and the movement of player/enemy
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class GameTicker extends TimerTask {

	/** handles the movement of all mobs in a defined frequence */
	public void run() {
		Board.player.move();
		for (int i = 0; i < Board.numberOfEnemies; i++) {
			Board.enemies[i].move();
		}

		Game.display.renderBoard();
	}
}
