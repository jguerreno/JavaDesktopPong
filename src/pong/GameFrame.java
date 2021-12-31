package pong;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame() {
		this.setTitle("Pong");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.add(new GamePanel());
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
}
