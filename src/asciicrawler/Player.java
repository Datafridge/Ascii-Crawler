package asciicrawler;

import java.awt.Color;

/**
 * \brief Player is class derived from mob. It specifices the abilities and
 * and actions of a player.
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Player extends Mob {

	/** returns the color of the player*/
	public Color getColor() {
		return Color.black;
	}

	/** set that the enemies can enter a player*/
	@Override
	public boolean canEnter() {
		return true;
	}

	/** defines the movements of the player on a specific key event*/
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
