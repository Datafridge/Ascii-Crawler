package asciicrawler;

import java.awt.Color;
import java.util.LinkedList;

/**
 * \brief Contains the Enemy AI
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Enemy extends Mob {
	// Constants

	/**
	 * When Player is in direct sight, this is the number of minimal ticks to
	 * the next move
	 */
	public static final int ChargeDelayMin = 6;
	/** Difference in ticks of charging moves. Maximum delay is Min + Random - 1 */
	public static final int ChargeDelayRandom = 3;
	/**
	 * When wandering around, this is the number of minimal ticks to the next
	 * move
	 */
	public static final int WalkDelayMin = 12;
	/** Difference in ticks of walking moves. Maximum delay is Min + Random - 1 */
	public static final int WalkDelayRandom = 6;
	/** Number of ticks it take an enemy to resurrect */
	public static final int DeathTime = 500;
	/** Number of ticks the enemy does not move after resurrection */
	public static final int WakingTime = 50;

	// Runtime settings of the enemy

	/** Number of ticks until the next move */
	public int moveTimer = 50;
	/**
	 * Directions the enemy is looking at. It can only walk back when there is
	 * no alternative
	 */
	public Direction facing = Direction.UP;
	/** Whether the Enemy is alive and can move */
	public boolean isDead = false;

	/** Set the resurrection timer and marks the Enemy as dead */
	public void kill() {
		isDead = true;
		moveTimer = DeathTime;
	}

	/** Returns the Color based on isDead */
	public Color getColor() {
		return isDead ? Color.green : Color.red;
	}

	private int sign(int n) {
		return n > 0 ? 1 : n < 0 ? -1 : 0;
	}

	/**
	 * Returns true when the enemy has no obstacles to the player in a given
	 * direction
	 */
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

	/** Called every game tick to move the enemy when necessary */
	@Override
	public void move() {
		if (moveTimer > 0) {
			moveTimer--;
		} else if (isDead) {
			isDead = false;
			moveTimer = WakingTime;
		} else {
			// Can charge towards player?
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
