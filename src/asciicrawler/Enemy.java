package asciicrawler;

import java.awt.Color;
import java.util.LinkedList;

public class Enemy extends Mob {
	
	public static final int ChargeDelayMin = 6;
	public static final int ChargeDelayRandom = 3;
	public static final int WalkDelayMin = 12;
	public static final int WalkDelayRandom = 6;
	

	public int moveTimer = 50;
	public Direction facing = Direction.UP;
	
	public Color getColor() {
		return Color.red;
	}
	
	private boolean canSeePlayer(Direction d) {
		int ix = Board.getXOffset(d);
		int iy = Board.getYOffset(d);
		int x = getXPosition() + ix;
		int y = getYPosition() + iy;
		int i = 1;
		while (Board.isOnBoard(x, y)
				&& (Game.board.getBoardObject(x, y).canEnter() || Game.board
						.getBoardObject(x, y) == Board.player)) {
			if (Game.board.getBoardObject(x, y) == Board.player) {
				if (i == 1) {
					Game.lost();
				}
				return true;
			}
			x += ix;
			y += iy;
			i++;
		}
		return false;
	}

	@Override
	public void move() {
		if (moveTimer > 0) {
			moveTimer--;
		} else {
			if (canSeePlayer(facing)) {
				moveInDirection(facing);
				moveTimer = Enemy.ChargeDelayMin
						+ Game.aiRandom.nextInt(Enemy.ChargeDelayRandom);
			} else {
				LinkedList<Direction> possibleMoves = new LinkedList<>(
						Board.directions);
				for (Direction d : Board.directions) {
					if (!canMoveInDirection(d)) {
						possibleMoves.remove(d);
					}
				}
				// Prevent turning around
				if (possibleMoves.size() > 1) {
					possibleMoves.remove(Board.getOpposite(facing));
				}
				Direction choice = Direction.STAY;
				for (Direction d : possibleMoves) {
					if (canSeePlayer(d)) {
						choice = d;
						break;
					}
				}
				if (choice == Direction.STAY) {
					choice = possibleMoves.get(Game.aiRandom
							.nextInt(possibleMoves.size()));
				}
				facing = choice;
				moveInDirection(choice);
				moveTimer = Enemy.WalkDelayMin
						+ Game.aiRandom.nextInt(Enemy.WalkDelayRandom);
			}
		}
	}


}
