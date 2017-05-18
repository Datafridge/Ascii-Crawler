package asciicrawler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

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
	
	public static int score = 0;
	public static int level = 1;
	
	public static final int AttackScore = -40;
	public static final int MoveScore = -1;
	public static final int LevelWin = 700;
	public static final int KillScore = 60;
	public static final int GoldScore = 300;
	
	public static LinkedList<Integer> scoreBoard;
	

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
	
	public static void addScore(int n) {
		if (n != 0) {
			score += n;
			if (score < 0) {
				score = 0;
			}
			display.updateTitle();
		}		
	}

	public static void won() {
		level++;
		addScore(LevelWin);
		board.reset();
	}

	public static void lost() {
		scoreBoard.add(score);
		Collections.sort(scoreBoard);
		int place = scoreBoard.size() - scoreBoard.indexOf(score);
		JOptionPane.showMessageDialog(display, String.format("You are on place %d with %d points! You have reached level %d.", place, score, level));
		
		level = 1;
		score = 0;
		display.updateTitle();
		board.reset();
	}

}
