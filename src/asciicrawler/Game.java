package asciicrawler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

/**
 * Main class of the game, Creates all components Stores components as static
 * variables for easy access
 * 
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Game {
	// Game components

	/** static instance of the Board class */
	public static Board board;
	/** static instance of the KeyManager class */
	public static KeyManager keys;
	/** static instance of the Display class */
	public static Display display;

	/** Random Object for movements of the enemies */
	public static Random aiRandom;
	/** The players score */
	public static int score = 0;
	/** The current level */
	public static int level = 1;

	// Score Values

	/** Score gain (or cost if negativ) for pressing the attack key */
	public static final int AttackScore = -40;
	/** Score gain (or cost if negativ) for every step made */
	public static final int MoveScore = -1;
	/** Score for leaving a dungeon with the gold */
	public static final int LevelWin = 700;
	/** Score for successfully attacking an enemy */
	public static final int KillScore = 60;
	/** Score for collecting the gold of the current level */
	public static final int GoldScore = 300;

	/** sorted List of previously archived points */
	public static LinkedList<Integer> scoreBoard;

	/**
	 * Creates Instances of all game components, create and renders the first
	 * dungeon and starts the GameTicker in a new Thread.
	 */
	public Game() {
		GameWindowAdapter gameWindowAdapter = new GameWindowAdapter();
		GameKeyListener gameKeyListener = new GameKeyListener();

		board = new Board();
		keys = new KeyManager();
		aiRandom = new Random();
		scoreBoard = new LinkedList<>();

		display = new Display(gameWindowAdapter, gameKeyListener);

		board.generateDungeon();
		display.renderBoard();

		Timer timer = new Timer();
		GameTicker gameticker = new GameTicker();
		timer.schedule((TimerTask) gameticker, 100l, 50l);
	}

	/** manipulates and displays the score */
	public static void addScore(int n) {
		if (n != 0) {
			score += n;
			if (score < 0) {
				score = 0;
			}
			display.updateTitle();
		}
	}

	/** Is called when a dungeon is completed. Generates the next one */
	public static void won() {
		level++;
		addScore(LevelWin);
		board.reset();
	}

	/** Is called when the player died. Shows the score and restarts */
	public static void lost() {
		scoreBoard.add(score);
		Collections.sort(scoreBoard);
		int place = scoreBoard.size() - scoreBoard.indexOf(score);
		JOptionPane
				.showMessageDialog(
						display,
						String.format(
								"You are on place %d with %d points! You have reached level %d.",
								place, score, level));

		level = 1;
		score = 0;
		display.updateTitle();
		board.reset();
	}

}
