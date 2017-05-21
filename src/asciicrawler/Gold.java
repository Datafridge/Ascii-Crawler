package asciicrawler;

import java.awt.Color;

/**
 * \brief Gold is a board object. The Player have to reach this field one time
 * and then he can go back to the Exit-field.
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Gold extends BoardObject {

	/** Checks if the Mob can access this type of field */
	public boolean canEnter() {
		return true;
	}

	/** Returns the Color of the field */
	public Color getColor() {
		return Color.yellow;
	}

	/** Defines which events (on condition) happens when the field is entered
	* 	by a mob.*/
	public void OnEnter() {
		Board.player.hasItem = true;
		Board.player.standingOn = Board.air;
		Game.addScore(Game.GoldScore);
	}

}
