package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import direction.*;


public class Paddle extends Rectangle {
	
	private static int PADDLE_WIDTH = 25;
	private static int PADDLE_HEIGHT = 100;
	
	private static Color color = Color.WHITE;
	
	private int yVelocity;
	private int speed = 8;
	
	// Direction currentDirection;
	//Controller controller;
	//	|-> Controller(up, down);
	//	|-> addUpKey() y addDownKey()
	//	|-> nextMove(idKey) un if que devuelva un delta Variacion o un 0
	HashMap<Integer, Move> controller = new HashMap<Integer, Move>();
	//private int keyUp;
	//private int keyDown;
	
	// Score
	private int score = 0;
	
	
	public Paddle(int x, int y, int keyUp, int keyDown) {
		super(x, y, PADDLE_WIDTH,PADDLE_HEIGHT);
		
		//this.keyUp = keyUp;
		//this.keyDown = keyDown;
		
		controller.put(keyUp, new Up(speed));
		controller.put(keyDown, new Down(speed));
	}

	public void keyPressed(KeyEvent e) {
		final Move direction = controller.get(e.getKeyCode());
		if(direction != null) {
			setYDirection(direction.nextMove());
			move();
		}
		/*
		if(e.getKeyCode() == keyUp) {
			setYDirection(-speed);
			move();
		}
		
		if(e.getKeyCode() == keyDown) {
			setYDirection(speed);
			move();
		}
		*/
	}
	
	public void keyReleased(KeyEvent e) {
		final Move direction = controller.get(e.getKeyCode());
		if(direction != null) {
			this.stopPaddle();
		}
		/*
		if(e.getKeyCode() == keyDown || e.getKeyCode() == keyUp) {
			this.stopPaddle();
		}
		*/
	}
	
	public void setYDirection(int yDirection) {
		this.yVelocity = yDirection;
	}
	
	public void move() {
		// y = direction.move(y) // Por Ahora no
		y = y + yVelocity;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

/*
	public void movePaddle() {
		setYDirection(-speed);
		move();
	}
*/	
	public void stopPaddle() {
		setYDirection(0);
		move();
	}
	
	public void checkCollisionWithBoard(GameBoard board) {
		final int bottomLimit = board.getHeight() - PADDLE_HEIGHT;
		
		if(y<=0) {
			y=0;
		}
		if(y >= bottomLimit) {
			y = bottomLimit;
		}
	}
	
	
	public void increaseScore() {
		score++;
	}
	
	public int currentScore() {
		return score;
	}
}
