package asciicrawler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
  public static ArrayList<KeyEvent> keylist;

  public Game () {
    GameWindowAdapter gamewindowadapter = new GameWindowAdapter();
    GameKeyListener   gamekeylistener   = new GameKeyListener();

    board = new Board();
    keylist = new ArrayList<KeyEvent>();

    @SuppressWarnings("unused")
	Display display = new Display(gamewindowadapter, gamekeylistener);

    Timer timer = new Timer();
    GameTicker gameticker = new GameTicker();
    timer.schedule((TimerTask)gameticker,1000l,50l);
  }

}
