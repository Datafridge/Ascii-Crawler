package asciicrawler;

import java.awt.Color;

public abstract class BoardObject {
	
	public abstract boolean canEnter();
	
	public abstract Color getColor();
	
	public void OnEnter(){};

}
