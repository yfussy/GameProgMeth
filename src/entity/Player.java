package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.SpriteSheet;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	final int SCREEN_X;
	final int SCREEN_Y;
	
	boolean moving = false;
	int standCounter = 0;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		SCREEN_X = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
		SCREEN_Y = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		speed = 6;
		direction = "down";
	}
	
	public void update() {
		
		if (!moving) {
			if (keyH.upPressed || keyH.downPressed ||
					keyH.leftPressed || keyH.rightPressed ) {
				if (keyH.upPressed == true) {
					direction = "up";
				}
				else if (keyH.downPressed == true) {
					direction = "down";
				}
				else if (keyH.leftPressed == true) {
					direction = "left";
				}
				else if (keyH.rightPressed == true) {
					direction = "right";
				}
				moving = true;
			}
			else { // Return sprite animation to default when standing still;
				standCounter++;
				if (standCounter == 16) {
					spriteNum = 0;
					standCounter = 0;
				}
			}
		}
		
		if (moving) {
			
			// Animate sprite moving
			spriteCounter++;
			if (spriteCounter > 8) {
				if (spriteNum == 0) {
					spriteNum = 1;
				}
				else if (spriteNum == 1) {
					spriteNum = 0;
					
				}
				spriteCounter = 0;
			}
			
			// moving tile by tile
			pixelCounter += speed;
			if (pixelCounter >= 46) {
				moving = false;
				pixelCounter = 0;
			}
		}
	}
	
	public void getPlayerImage() {
		
		BufferedImage playerSheet;
		try {
			playerSheet = ImageIO.read(getClass().getResourceAsStream("/player/player_sprite_sheet.png"));
			ss = new SpriteSheet(48, 96, 24, 24, playerSheet);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage img = null;
		int i;
		
		switch(direction) {
		case "up":
			i = spriteNum;
			img = ss.sprites[i];
			break;
		case "down":
			i = spriteNum + 2;
			img = ss.sprites[i];
			break;
		case "left":
			i = spriteNum + 4;
			img = ss.sprites[i];
			break;
		case "right":
			i = spriteNum + 6;
			img = ss.sprites[i];
			break;
		}
		g2.drawImage(img, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);
	}
}
