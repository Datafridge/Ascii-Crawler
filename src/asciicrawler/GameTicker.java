package asciicrawler;

import java.util.TimerTask;
/**
 * this will handle all game ticks and the movement of player/enemy
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class GameTicker extends TimerTask{
  public void run() {
    System.out.println(Game.keylist);
  }
}
