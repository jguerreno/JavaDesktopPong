package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
	
	private static int BALL_DIAMETER = 20;
	private static Random random = new Random();
	private int xVelocity;
	private int yVelocity;
	private int initialSpeed = 4;
	
	
	public Ball(int x, int y, int diameter) {
		super(x, y, diameter, diameter);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		setYDirection(randomYDirection*initialSpeed);
		
		BALL_DIAMETER = diameter;
	}
	
	public Ball(GameBoard board) {		
		super((int)board.getWidthCenter()-(BALL_DIAMETER/2), random.nextInt(board.getHeight()-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

		//ACA MEJORAR
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		setYDirection(randomYDirection*initialSpeed);
	}
	
	
	
	public void setXDirection(int ramdomXDirection) {
		xVelocity = ramdomXDirection;
	}
	
	public void setYDirection(int ramdomYDirection) {
		yVelocity = ramdomYDirection;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;			
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);
	}
	
	
	
	public void checkBounceWithBoard(GameBoard board) {
		final int leftLimit = board.getHeight() - BALL_DIAMETER;
		
		if(y <= 0 || y >= leftLimit) {
			this.boundeY();
		}
	}
	
	public void boundeY() {
		yVelocity *= -1;
	}
	
	public void boundeX() {
		xVelocity *= -1;
	}
	
	
	public void checkBounceWithPaddle(Paddle paddle) {
		if(this.intersects(paddle)) {
			this.boundeX();
			this.increaseSpeed();
			
			this.move();
		}
	}
	
	public void increaseSpeed() {
		xVelocity += Integer.signum(xVelocity);
		yVelocity += Integer.signum(yVelocity);
		
		xVelocity = Math.min(Math.abs(xVelocity), 10) * Integer.signum(xVelocity);
		yVelocity = Math.min(Math.abs(yVelocity), 10) * Integer.signum(yVelocity);
	}
	
	public boolean checkPoint(GameBoard board, Paddle paddle1, Paddle paddle2) {
		if(x <= 0) {
			paddle2.increaseScore();
			
			return true;
		}
		if(x >= (board.getWidth()-BALL_DIAMETER)) {
			paddle1.increaseScore();
			
			return true;
		}
		
		return false;
	}
	
}
