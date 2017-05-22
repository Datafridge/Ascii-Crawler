package asciicrawler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * \brief Board stores all configurations of the current dungeon
 * 
 * Contains a two dimensional array of BoardObjects Offers helper functions for
 * handling the Board, Positions and Directions
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Board {
	// Constants

	/** number of horizontal BoardObjects on the Board */
	public static final int width = 30;
	/** number of vertical BoardObjects on the Board */
	public static final int height = 20;
	/** has to be smaller than width * height / 4 - 2 */
	public static final int numberOfEnemies = 5;
	/** every wall has a chance to be water from 0-1 */
	public static final double waterChance = 0.02;

	// Reference objects

	/** static instance of the wall class */
	public static final Wall wall = new Wall();
	/** static instance of the air class */
	public static final Air air = new Air();
	/** static instance of the water class */
	public static final Water water = new Water();
	/** static instance of the gold class */
	public static final Gold gold = new Gold();
	/** static instance of the exit class */
	public static final Exit exit = new Exit();

	// Variables

	/**
	 * contains all enemies so that the board does not have to be searched for
	 * them
	 */
	public static Enemy[] enemies;
	/** static instance of the Player */
	public static final Player player = new Player();

	/** Contains all BoardObjects */
	public BoardObject boardObjects[][];

	private Random random;

	/** Contains all directions a mob can move in. Clockwise starting with UP */
	public static final List<Direction> directions = Arrays.asList(
			Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);;

	/**
	 * Constructor of the Board. Initializes non static variables. Does not fill
	 * in BoardObjects
	 */
	public Board() {
		boardObjects = new BoardObject[width][height];
		random = new Random();
		enemies = new Enemy[numberOfEnemies];
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy();
		}
	}

	/** Returns to a new dungeon */
	public void reset() {
		player.hasItem = false;
		for (int i = 0; i < numberOfEnemies; i++) {
			enemies[i].isDead = false;
		}
		generateDungeon();
	}

	/** Setter for the Elements of the BoardObject array */
	public void setBoardObject(int x, int y, BoardObject Item) {
		boardObjects[x][y] = Item;
	}

	/** Getter for the Elements of the BoardObject array */
	public BoardObject getBoardObject(int x, int y) {
		return boardObjects[x][y];
	}

	/** Checks whether given coordinates are in the range of the Board */
	public static boolean isOnBoard(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	/** A Cell is open when an adjacent block is air */
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

	/** Returns the difference of X when moving into direction d */
	public static int getXOffset(Direction d) {
		return d == Direction.RIGHT ? 1 : d == Direction.LEFT ? -1 : 0;
	}

	/** Returns the difference of Y when moving into direction d */
	public static int getYOffset(Direction d) {
		return d == Direction.DOWN ? 1 : d == Direction.UP ? -1 : 0;
	}

	private static Direction turnBy(Direction d, int RightTurns) {
		int index = Board.directions.indexOf(d);
		return Board.directions.get((index + RightTurns) % 4);
	}

	/** Returns the opposing Direction */
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

	/** Fills the BoardObject array with a new Dungeon */
	public void generateDungeon() {
		// Fill everything with walls or water
		for (int i = 0; i < Board.width; i++) {
			for (int j = 0; j < Board.height; j++) {
				if (random.nextDouble() > waterChance) {
					setBoardObject(i, j, Board.wall);
				} else {
					setBoardObject(i, j, Board.water);
				}

			}
		}

		boolean allOpen = false;
		setBoardObject(2, 1, Board.air); // open first cell
		while (!allOpen) {
			allOpen = true;
			for (int i = 0; i < Board.width; i += 2) {
				for (int j = 0; j < Board.height; j += 2) {
					// spread from open to closed cells
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
