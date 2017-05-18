package asciicrawler;

import java.awt.Color;

public class Exit extends BoardObject {

	public boolean canEnter() {
		return true;
	}

	public Color getColor() {
		return Color.white;
	}

	public void OnEnter() {
		if (Board.player.hasItem) {
			Game.won();
		}
	}
	
	

}
