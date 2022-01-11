package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle{

	private final GameBoard BOARD;
	
	
	public Score(GameBoard board) {
		BOARD = board;
	}
	
	
	public void draw(Graphics g, int paddle1Score, int paddle2Score) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		
		g.drawLine(BOARD.getWidthCenter(), 0, BOARD.getWidthCenter(), BOARD.getHeight());
		
		g.drawString(String.valueOf(paddle1Score/10)+String.valueOf(paddle1Score%10), BOARD.getWidthCenter()-85, 50);
		g.drawString(String.valueOf(paddle2Score/10)+String.valueOf(paddle2Score%10), BOARD.getWidthCenter()+20, 50);
	}
}
