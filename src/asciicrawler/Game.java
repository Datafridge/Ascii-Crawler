package asciicrawler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main class of the game, Creates all components
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Game {
	public static Board board;
	public static KeyManager keys;
	public static Display display;
	public static Random aiRandom;

	public Game() {
		GameWindowAdapter gameWindowAdapter = new GameWindowAdapter();
		GameKeyListener gameKeyListener = new GameKeyListener();

		board = new Board();
		keys = new KeyManager();
		aiRandom = new Random();

		display = new Display(gameWindowAdapter, gameKeyListener);

		board.generateDungeon();
		display.renderBoard();

		Timer timer = new Timer();
		GameTicker gameticker = new GameTicker();
		timer.schedule((TimerTask) gameticker, 1000l, 50l);
	}

	public static void won() {
		System.out.println("You won!");
	}

	public static void lost() {
		System.out.println("You lost!");
	}

}
