package pong;

import java.awt.Dimension;

public class GameBoard {
	private int GAME_WIDTH = 1000;
	private int GAME_HEIGHT = (int)(GAME_WIDTH * 0.5);
	
	
	public int getHeight() {
		return GAME_HEIGHT;
	}
	
	public int getWidth() {
		return GAME_WIDTH;
	}
	
	public int getWidthCenter() {
		return Math.round(GAME_WIDTH/2);
	}
	
	public Dimension getBoardDimension() {
		return new Dimension(GAME_WIDTH, GAME_HEIGHT);
	}
	
}
