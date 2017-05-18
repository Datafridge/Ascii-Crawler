package asciicrawler;

import java.awt.Color;

public class Player extends Mob {

	public Color getColor() {
		return Color.black;
	}

	@Override
	public void move() {
		Direction requestedMove = Game.keys.consumeMoveRequest();

		if (requestedMove != Direction.STAY
				&& Board.player.moveInDirection(requestedMove)) {
			Board.player.standingOn.OnEnter();
		}		
	}


}
