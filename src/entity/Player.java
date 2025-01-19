package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	final int SCREEN_X;
	final int SCREEN_Y;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		SCREEN_X = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
		SCREEN_Y = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);
		
		
	}
	
	public void setDefaultValues() {
		
		speed = 6;
		direction = "down";
	}
}
