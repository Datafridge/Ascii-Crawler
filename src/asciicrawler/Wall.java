package asciicrawler;

import java.awt.Color;

public class Wall extends BoardObject {

	@Override
	public boolean canEnter() {
		return false;
	}

	@Override
	public Color getColor() {
		return Color.gray;
	}

}
