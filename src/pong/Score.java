package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle{

	private final int GAME_WIDTH;
	private final int GAME_HEIGHT;
	int player1;
	int player2;
	
	public Score(int width, int height) {
		GAME_WIDTH = width;
		GAME_HEIGHT = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
	}
	
}
