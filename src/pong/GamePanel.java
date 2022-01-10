package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
	// abstraer la logica del colition
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.5);
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	MyKeyAdapter adapter = new MyKeyAdapter();
	GameBoard board = new GameBoard();	// !!!
	
	public GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(adapter);
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), KeyEvent.VK_W, KeyEvent.VK_S);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		
		adapter.addNewPaddle(paddle1);
		adapter.addNewPaddle(paddle2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		
		ball.draw(g);
		
		score.draw(g, paddle1.currentScore(), paddle2.currentScore());
	}
	
	
	public void move() {
		paddle1.move();
		paddle2.move();
		
		ball.move();
	}
	
	
	public void checkCollision() {
		// Bounce ball off top and bottom window edges
		ball.checkBounceWithBoard(board);

		
		// Bounce ball off paddles
		ball.checkBounceWithPaddle(paddle1);
		ball.checkBounceWithPaddle(paddle2);
		
				
		// Stops paddles at window edges
		paddle1.checkCollisionWithBoard(board);
		paddle2.checkCollisionWithBoard(board);

				
		// Give a player 1 point and creates new paddles and ball
		if(ball.checkPoint(board, paddle1, paddle2))
			this.newBall();
		
		// paddle1.checkPoint(ball);
		// paddle2.checkPoint(ball);
		
		//mirar ahora porque vamos a necesitar un getScore
		/*
		if(ball.x <= 0) {
			score.player2++;
			//newPaddles();
			newBall();
		}
		if(ball.x >= (GAME_WIDTH-BALL_DIAMETER)) {
			score.player1++;
			//newPaddles();
			newBall();
		}
		*/
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
}
