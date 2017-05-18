package asciicrawler;

import java.awt.event.KeyEvent;

public class KeyManager {

	private Direction moveRequest = Direction.STAY;
	private boolean isAttacking = false;

	synchronized public void setKey(int key) {
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			moveRequest = Direction.UP;
		}
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			moveRequest = Direction.LEFT;
		}
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			moveRequest = Direction.DOWN;
		}
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			moveRequest = Direction.RIGHT;
		}
		if (key == KeyEvent.VK_CONTROL || key == KeyEvent.VK_SPACE) {
			isAttacking = true;
		} 
	}

	synchronized public Direction getMoveRequest() {
		return moveRequest;
	}

	synchronized public Direction consumeMoveRequest() {
		Direction move = moveRequest;
		moveRequest = Direction.STAY;
		return move;
	}
	
	synchronized public boolean consumeAttack() {
		boolean wasAttacking = isAttacking;
		isAttacking = false;
		return wasAttacking;
	}
}