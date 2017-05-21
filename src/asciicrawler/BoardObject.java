package asciicrawler;

import java.awt.Color;

/**
 * \brief Is the abstract class of several board objects
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public abstract class BoardObject {

	/** abstract method to check if a mob can enter the field*/
	public abstract boolean canEnter();

	/** abstract method to get the color of the field*/
	public abstract Color getColor();

	/** abstract method to define what happens if a mob enter the field*/
	public void OnEnter(){};

}
