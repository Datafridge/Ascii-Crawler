package asciicrawler;

import java.awt.Color;

/**
 * \brief Water is a board object. This field can not entered by any mob.
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Water extends BoardObject {

	/** Checks if the Mob can access this type of field */
	@Override
	public boolean canEnter() {
		return false;
	}

	/** Returns the Color of the field */
	@Override
	public Color getColor() {
		return Color.cyan;
	}
}
