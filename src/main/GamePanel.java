package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int ORIG_TILE_SIZE = 16; //16x16 px
	final int SCALE = 3;
	
	public final int TILE_SIZE = ORIG_TILE_SIZE * SCALE; // 48x48 px
	final int MAX_SCREEN_COL = 20;
	final int MAX_SCREEN_ROW = 15;
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 960px
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 720px
	
	// FPS
	final int FPS = 60;
	
	// SYSTEM
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// ENTITY
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start(); // calls "run"
	}
	
	@Override // Runnable interface
	public void run() {
		
		// Limit game loop to FPS
		double drawInterval = 1000000000/FPS; // draw time in 1 frame (nanoseconds)
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		// Show FPS
		long timer = 0;
		long drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint(); // calls "paintComponent"
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				timer = 0;
				drawCount = 0;
			}
			
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// PLAYER
		player.draw(g2);
		
		g2.dispose();
		
	}


}
