package asciicrawler;

/**
 * \brief Is the abstract class of several mob objects
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public abstract class Mob extends BoardObject {
	private int xPosition;
	private int yPosition;
	public boolean hasItem = false; /**< state if mob has an item */
	public BoardObject standingOn;  /**< type of the board object the mob is 
																			standing on*/

	/** returns the actual x Position of a mob*/
	public int getXPosition() {
		return xPosition;
	}

	/** returns the actual x Position of a mob*/
	public int getYPosition() {
		return yPosition;
	}

	/** sets the spawn point of a mob*/
	public void spawnAt(int x, int y) {
		standingOn = Game.board.getBoardObject(x, y);
		Game.board.setBoardObject(x, y, this);
		xPosition = x;
		yPosition = y;
	}

	/** checks if a mob can move to a specific field*/
	public boolean canMoveTo(int x, int y) {
		if (!Board.isOnBoard(x, y)) {
			return false;
		}
		return Game.board.getBoardObject(x, y).canEnter();
	}

	/** checks if a mob can move to a specific direction*/
	public boolean canMoveInDirection(Direction d) {
		return canMoveTo(xPosition + Board.getXOffset(d),
				yPosition + Board.getYOffset(d));
	}

	/** moves a mob to specific field*/
	public boolean moveTo(int x, int y) {
		if (canMoveTo(x, y)) {
			Game.board.setBoardObject(xPosition, yPosition, standingOn);
			spawnAt(x, y);
			return true;
		}
		return false;
	}

	/** sets the walking direction of a mob*/
	public boolean moveInDirection(Direction d) {
		return moveTo(xPosition + Board.getXOffset(d),
				yPosition + Board.getYOffset(d));
	}

	/** sets if an other mob can access the field*/
	@Override
	public boolean canEnter() {
		return false;
	}

	/** abstract method to define the movement of a mob*/
	public abstract void move();
}
