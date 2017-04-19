import java.awt.*;
import java.awt.event.*;


/**
 * Main class of the game
 *
 * @author Leon Hansen, Felix Schmidt
 * @version 1.0
 */
public class Game {
  public Game () {
    GameListener gl = new GameListener();

    Display display = new Display(gl);
  }

}
