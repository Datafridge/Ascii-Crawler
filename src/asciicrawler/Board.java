package asciicrawler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * \brief Board stores all configurations of the current dungeon
 * 
 * Contains a two dimensional array of BoardObjects
 * Offers helper functions for handling the Board, Positions and Directions
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Board {
	/**  */
	public static final int width = 30;
	public static final int height = 20;
	public static final int numberOfEnemies = 5;
	public static final double waterChance = 0.02;

	public static final Wall wall = new Wall();
	public static final Air air = new Air();
	public static final Water water = new Water();
	public static final Gold gold = new Gold();
	public static final Exit exit = new Exit();

	public static Enemy[] enemies;
	public static final Player player = new Player();

	public BoardObject boardObjects[][];

	private Random random;
	public static final List<Direction> directions = Arrays.asList(
			Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);;

	public Board() {
		boardObjects = new BoardObject[width][height];
		random = new Random();
		enemies = new Enemy[numberOfEnemies];
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy();
		}
	}
	
	public void reset() {
		player.hasItem = false;
		for (int i = 0; i < numberOfEnemies; i++) {
			enemies[i].isDead = false;
		}
		generateDungeon();
	}

	public void setBoardObject(int x, int y, BoardObject Item) {
		boardObjects[x][y] = Item;
	}

	public BoardObject getBoardObject(int x, int y) {
		return boardObjects[x][y];
	}

	public static boolean isOnBoard(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	private boolean isCellOpen(int x, int y) {
		for (Direction d : directions) {
			int entryX = x + getXOffset(d);
			int entryY = y + getYOffset(d);
			if (isOnBoard(entryX, entryY)
					&& getBoardObject(entryX, entryY) == Board.air) {
				return true;
			}
		}
		return false;
	}

	public static int getXOffset(Direction d) {
		return d == Direction.RIGHT ? 1 : d == Direction.LEFT ? -1 : 0;
	}

	public static int getYOffset(Direction d) {
		return d == Direction.DOWN ? 1 : d == Direction.UP ? -1 : 0;
	}

	private static Direction turnBy(Direction d, int RightTurns) {
		int index = Board.directions.indexOf(d);
		return Board.directions.get((index + RightTurns) % 4);
	}

	public static Direction getOpposite(Direction d) {
		return turnBy(d, 2);
	}

	private void openNeighbor(int x, int y) {
		setBoardObject(x, y, Board.air);
		int choice = random.nextInt(directions.size());
		Direction nextCell = directions.get(choice);
		int nextX = x + getXOffset(nextCell) * 2;
		int nextY = y + getYOffset(nextCell) * 2;

		if (isOnBoard(nextX, nextY) && !isCellOpen(nextX, nextY)) {
			setBoardObject(x + getXOffset(nextCell), y + getYOffset(nextCell),
					Board.air);
			openNeighbor(nextX, nextY);
		}
	}

	private void spawnMobAtEmptyCell(Mob m) {
		int x, y;
		do {
			x = random.nextInt(width / 2) * 2;
			y = random.nextInt(height / 2) * 2;
			if (m instanceof Player) { // entrance on side of the Board
				if (random.nextBoolean()) {
					x = 0;
				} else {
					y = 0;
				}
			}
		} while (getBoardObject(x, y) != Board.air);
		m.spawnAt(x, y);
	}

	public void generateDungeon() {
		for (int i = 0; i < Board.width; i++) {
			for (int j = 0; j < Board.height; j++) {
				if (random.nextDouble() > waterChance) {
					setBoardObject(i, j, Board.wall);
				} else {
					setBoardObject(i, j, Board.water);
				}

			}
		}

		// make cells and connect every cell to any other
		boolean allOpen = false;
		setBoardObject(2, 1, Board.air);
		while (!allOpen) {
			allOpen = true;
			for (int i = 0; i < Board.width; i += 2) {
				for (int j = 0; j < Board.height; j += 2) {
					if (isCellOpen(i, j)) {
						openNeighbor(i, j);
					} else {
						allOpen = false;
					}
				}
			}
		}
		setBoardObject(random.nextInt(width / 2) * 2,
				random.nextInt(height / 2) * 2, Board.gold);

		spawnMobAtEmptyCell(player);
		player.standingOn = Board.exit;

		for (int i = 0; i < Board.numberOfEnemies; i++) {
			spawnMobAtEmptyCell(enemies[i]);
		}
	}
}
