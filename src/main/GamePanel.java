package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	// SCREEN SETTINGS
	final int ORIG_TILE_SIZE = 16; //16x16 px
	final int SCALE = 3;
	
	final int TILE_SIZE = ORIG_TILE_SIZE * SCALE; // 48x48 px
	final int MAX_SCREEN_COL = 20;
	final int MAX_SCREEN_ROW = 15;
	final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 960px
	final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 720px
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
}
