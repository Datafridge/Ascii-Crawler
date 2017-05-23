package asciicrawler;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

/**
 * \brief Class is the display of the game and handles the rendering
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Display extends Frame {
	/**
	 * This number is needed for javas serialization
	 */
	private static final long serialVersionUID = 1214189760697788025L;

	private Panel[][] boardFrames;

	/**
	 * Constructor of the Display class set the frame title and size
	 *
	 * @param a
	 *            adapter which get the window events and one to listen to key
	 *            presses
	 *
	 * @param b
	 *						listener to recognize if any key was pressed
	 */
	public Display(WindowAdapter a, KeyListener b) {
		updateTitle();

		addWindowListener(a);
		addKeyListener(b);
		setFocusTraversalKeysEnabled(false);

		Dimension size = new Dimension(40,40);

		GridLayout boardLayout = new GridLayout(Board.height, Board.width);
		boardFrames = new Panel[Board.width][Board.height];
		for (int i = 0; i < Board.height; i++) {
			for (int j = 0; j < Board.width; j++) {
				Panel currentFrame = new Panel(boardLayout);
				currentFrame.setPreferredSize(size);
				currentFrame.setFocusable(false);
				add(currentFrame);
				boardFrames[j][i] = currentFrame;
			}
		}
		setLayout(boardLayout);
		pack();
		setVisible(true);
	}

	/** provides information about the level and score on the updatetitle */
	public void updateTitle() {
		setTitle(String.format("Ascii-Crawler in Dugeon %d with %d Points",Game.level,Game.score));
	}

	/** renders the actual state of the board */
	public void renderBoard() {
		for (int i = 0; i < Board.width; i++) {
			for (int j = 0; j < Board.height; j++) {
				boardFrames[i][j].setBackground(Game.board.getBoardObject(i, j).getColor());
			}
		}
	}
}
