package asciicrawler;

import java.awt.Color;

/**
 * \brief Exit is a board object. It is the field in the game where the player
 * starts and the ends the level.
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Exit extends BoardObject {

	/** Checks if the Mob can access this type of field */
	public boolean canEnter() {
		return true;
	}

	/** Returns the Color of the field */
	public Color getColor() {
		return Color.white;
	}

	/** Defines which events (on condition) happens when the field is entered
	* 	by a mob.*/
	public void OnEnter() {
		if (Board.player.hasItem) {
			Game.won();
		}
	}



}
