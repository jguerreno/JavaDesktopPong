package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Paddle extends Rectangle {
	
	private static int PADDLE_WIDTH = 25;
	private static int PADDLE_HEIGHT = 100;
	
	private static Color color = Color.WHITE;
	
	private int id;
	private int yVelocity;
	private int speed = 8;
	
	public Paddle(int x, int y, int id) {
		super(x, y, PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = id; 
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
			case 1:
				if(e.getKeyCode() == KeyEvent.VK_W) {
					setYDirection(-speed);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					setYDirection(speed);
					move();
				}
			break;
			
			case 2:
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					setYDirection(-speed);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					setYDirection(speed);
					move();
				}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
				this.stopPaddle();
			}
		break;
		
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
				this.stopPaddle();
			}
		break;
		}
	}
	
	public void setYDirection(int yDirection) {
		this.yVelocity = yDirection;
	}
	
	public void move() {
		y = y + yVelocity;
	}
	
	
	public void draw(Graphics g) {
		if(id == 1) {
			g.setColor(color);
		}
		else {
			g.setColor(color);
		}
		
		g.fillRect(x, y, width, height);
	}


	public void movePaddle() {
		setYDirection(-speed);
		move();
	}
	
	public void stopPaddle() {
		setYDirection(0);
		move();
	}
}
