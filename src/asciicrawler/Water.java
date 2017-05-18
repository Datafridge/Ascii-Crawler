package asciicrawler;

import java.awt.Color;

public class Water extends BoardObject {

	@Override
	public boolean canEnter() {
		return false;
	}

	@Override
	public Color getColor() {
		return Color.cyan;
	}
}
