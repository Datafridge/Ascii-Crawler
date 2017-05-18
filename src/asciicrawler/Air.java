package asciicrawler;

import java.awt.Color;

public class Air extends BoardObject {

	@Override
	public boolean canEnter() {
		return true;
	}

	@Override
	public Color getColor() {
		return Color.blue;
	}
}
