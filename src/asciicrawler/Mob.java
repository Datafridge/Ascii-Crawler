package asciicrawler;

public abstract class Mob extends BoardObject {
	private int xPosition;
	private int yPosition;
	public boolean hasItem = false;
	public BoardObject standingOn;

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public void spawnAt(int x, int y) {
		standingOn = Game.board.getBoardObject(x, y);
		Game.board.setBoardObject(x, y, this);
		xPosition = x;
		yPosition = y;
	}

	public boolean canMoveTo(int x, int y) {
		if (!Board.isOnBoard(x, y)) {
			return false;
		}
		return Game.board.getBoardObject(x, y).canEnter();
	}

	public boolean canMoveInDirection(Direction d) {
		return canMoveTo(xPosition + Board.getXOffset(d),
				yPosition + Board.getYOffset(d));
	}

	public boolean moveTo(int x, int y) {
		if (canMoveTo(x, y)) {
			Game.board.setBoardObject(xPosition, yPosition, standingOn);
			spawnAt(x, y);
			return true;
		}
		return false;
	}

	public boolean moveInDirection(Direction d) {
		return moveTo(xPosition + Board.getXOffset(d),
				yPosition + Board.getYOffset(d));
	}

	@Override
	public boolean canEnter() {
		return false;
	}
	
	public abstract void move();
}
