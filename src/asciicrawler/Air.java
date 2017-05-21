package asciicrawler;

import java.awt.Color;

/**
 * \brief Air is a board object. On this fields of the board the player and the
 * enemys are walking along the dungeon.
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Air extends BoardObject {

	/** Checks if the Mob can access this type of field */
	@Override
	public boolean canEnter() {
		return true;
	}

	/** Returns the Color of the field */
	@Override
	public Color getColor() {
		return Color.blue;
	}
}
