package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
	// abstraer la logica del colition
	private static final int GAME_WIDTH = 1000;
	private static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.5);
	// ACA EsTO SE VA
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	// Mejorar
	private static final int PADDLE_WIDTH = 25;
	private static final int PADDLE_HEIGHT = 100;
	private Thread gameThread;
	private Graphics graphics;
	
	private Paddle paddle1;
	private Paddle paddle2;
	private Ball ball;
	private Score score;
	
	private MyKeyAdapter adapter = new MyKeyAdapter();
	private GameBoard board = new GameBoard();	// !!!
	
	public GamePanel() {
		newPaddles();
		ball = new Ball(board);
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		
		this.setFocusable(true);
		this.addKeyListener(adapter);
		//this.setPreferredSize(board.getBoardDimension());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), KeyEvent.VK_W, KeyEvent.VK_S);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		
		adapter.addNewPaddle(paddle1);
		adapter.addNewPaddle(paddle2);
	}
	
	public void paint(Graphics g) {
		Image image = createImage(getWidth(), getHeight());
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
			ball = new Ball(board);
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
