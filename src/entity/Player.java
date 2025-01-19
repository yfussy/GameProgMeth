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
	
	public final int SCREEN_X;
	public final int SCREEN_Y;
	
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
		
		worldX = gp.TILE_SIZE * 14;
		worldY = gp.TILE_SIZE * 8;
		speed = 6;
		direction = "down";
	}
	
	public void update() {
		
		if (moving == false) {
			
			if (keyH.upPressed == true || keyH.downPressed == true || 
					keyH.leftPressed == true || keyH.rightPressed == true) {
				
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
				if (standCounter == 4) {
					spriteNum = 3;
					standCounter = 0;
				}
			}
		}
		
		if (moving == true) {
			
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
			
			System.out.println("sCount: " + spriteCounter);
			System.out.println("sNum: " + spriteNum);
			// Animate sprite moving
			spriteCounter++;
			if (spriteCounter > 3) {
				spriteNum++;
				if (spriteNum > 7) {
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
			ss = new SpriteSheet(playerSheet.getWidth(), playerSheet.getHeight(), 4, 8, playerSheet, 32);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage img = null;
		
		switch(direction) {
		case "up":
			img = ss.sprites[24 + spriteNum];
			break;
		case "down":
			img = ss.sprites[16 + spriteNum];
			break;
		case "left":
			img = ss.sprites[spriteNum];
			break;
		case "right":
			img = ss.sprites[8 + spriteNum];
			break;
		}
		g2.drawImage(img, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);
	}
}
