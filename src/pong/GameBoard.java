package pong;

import java.awt.Dimension;

public class GameBoard {
	private int GAME_WIDTH = 1000;
	private final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.5);
	
	public int getHeight() {
		return GAME_HEIGHT;
	}
	
	public int getWidth() {
		return GAME_WIDTH;
	}
	
	public Dimension getBoardSize() {
		return new Dimension(GAME_WIDTH, GAME_HEIGHT);
	}
}
