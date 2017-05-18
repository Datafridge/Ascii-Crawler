package asciicrawler;

import java.awt.Color;

public class Gold extends BoardObject {

	public boolean canEnter() {
		return true;
	}

	public Color getColor() {
		return Color.yellow;
	}

	public void OnEnter() {
		Board.player.hasItem = true;
		Board.player.standingOn = Board.air;
		Game.addScore(Game.GoldScore);
	}

}
