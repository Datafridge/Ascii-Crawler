package asciicrawler;

import java.awt.Color;
import java.util.LinkedList;

public class Enemy extends Mob {

	public static final int ChargeDelayMin = 6;
	public static final int ChargeDelayRandom = 3;
	public static final int WalkDelayMin = 12;
	public static final int WalkDelayRandom = 6;
	public static final int DeathTime = 500;
	public static final int WakingTime = 50;

	public int moveTimer = 50;
	public Direction facing = Direction.UP;

	public boolean isDead = false;

	public void kill() {
		isDead = true;
		moveTimer = DeathTime;
	}

	public Color getColor() {
		return isDead ? Color.green : Color.red;
	}

	private int sign(int n) {
		return n > 0 ? 1 : n < 0 ? -1 : 0;
	}

	public boolean canSeePlayer(Direction d) {
		int ix = Board.getXOffset(d);
		int iy = Board.getYOffset(d);
		int x = getXPosition();
		int y = getYPosition();

		if (sign(Board.player.getXPosition() - getXPosition()) != ix
				|| sign(Board.player.getYPosition() - getYPosition()) != iy) {
			return false;
		}

		x += ix;
		y += iy;
		while (Board.isOnBoard(x, y)
				&& (Game.board.getBoardObject(x, y).canEnter() || Game.board
						.getBoardObject(x, y) == Board.player)) {
			if (Game.board.getBoardObject(x, y) == Board.player) {
				return true;
			}
			x += ix;
			y += iy;
		}
		return false;
	}

	@Override
	public void move() {
		if (moveTimer > 0) {
			moveTimer--;
		} else if (isDead) {
			isDead = false;
			moveTimer = WakingTime;
		} else {
			if (canSeePlayer(facing)) {
				moveInDirection(facing);
				if (standingOn == Board.player) {
					Game.lost();
				}
				moveTimer = ChargeDelayMin
						+ Game.aiRandom.nextInt(ChargeDelayRandom);
			} else {
				Direction choice = Direction.STAY;
				Direction back = Board.getOpposite(facing);
				for (Direction d : Board.directions) {
					if (d != back && canSeePlayer(d)) {
						choice = d;
						break;
					}
				}
				if (choice == Direction.STAY) {
					LinkedList<Direction> possibleMoves = new LinkedList<>();
					for (Direction d : Board.directions) {
						if (canMoveInDirection(d)) {
							possibleMoves.add(d);
						}
					}

					// Prevent turning around if possible
					if (possibleMoves.size() > 1) {
						possibleMoves.remove(Board.getOpposite(facing));
					}

					// Can happen when blocked by other enemy
					if (possibleMoves.size() > 0) {
						choice = possibleMoves.get(Game.aiRandom
								.nextInt(possibleMoves.size()));

					}
				}
				if (choice != Direction.STAY) {
					facing = choice;
					moveInDirection(choice);
					if (standingOn == Board.player) {
						Game.lost();
					}
					moveTimer = WalkDelayMin
							+ Game.aiRandom.nextInt(WalkDelayRandom);

				}
			}
		}
	}
}
