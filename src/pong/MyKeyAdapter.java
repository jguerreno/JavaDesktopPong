package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class MyKeyAdapter extends KeyAdapter{
	private ArrayList<Paddle> paddles;
	
	
	public MyKeyAdapter() {
		paddles = new ArrayList<Paddle>();
	}
	
	public void addNewPaddle(Paddle paddle) {
		paddles.add(paddle);
	}
	
	public void keyPressed(KeyEvent e) {
		for(Paddle paddle : paddles) {
			paddle.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		for(Paddle paddle : paddles) {
			paddle.keyReleased(e);
		}
	}

}
