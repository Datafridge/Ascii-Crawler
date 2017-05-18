package asciicrawler;

import java.awt.Color;

public class Player extends Mob {

	public Color getColor() {
		return Color.black;
	}
	
	//Enemies can enter the player
	@Override
	public boolean canEnter() {
		return true;
	}

	@Override
	public void move() {
		Direction requestedMove = Game.keys.consumeMoveRequest();
		int scoreGain = 0;
		if (requestedMove != Direction.STAY
				&& Board.player.moveInDirection(requestedMove)) {
			Board.player.standingOn.OnEnter();
			scoreGain += Game.MoveScore;
		}

		boolean attackRequest = Game.keys.consumeAttack();
		if (attackRequest) {
			scoreGain += Game.AttackScore;
			for (Direction d : Board.directions) {
				int x = getXPosition() + Board.getXOffset(d);
				int y = getYPosition() + Board.getYOffset(d);
				if (Board.isOnBoard(x, y)) {
					BoardObject b = Game.board.getBoardObject(x, y);
					if (b instanceof Enemy) {
						Enemy e = (Enemy) b;
						if (e.canSeePlayer(Board.getOpposite(e.facing))) {
							e.kill();
							scoreGain += Game.KillScore;
						}
					}
				}
			}
		}
		Game.addScore(scoreGain);
	}

}
