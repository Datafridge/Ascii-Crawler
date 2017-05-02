package asciicrawler;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

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

    Display display = new Display(gamewindowadapter, gamekeylistener);

    Timer timer = new Timer();
    GameTicker gameticker = new GameTicker();
    timer.schedule((TimerTask)gameticker,1000l,50l);
  }

}
