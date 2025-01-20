package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	
	
	int standCounter = 0;
	int standNum = 0;
	int pixelCounter = 0;
	boolean moving = false;
	boolean idle = true;

	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		SCREEN_X = gp.SCREEN_WIDTH/2 - (gp.TILE_SIZE/2);
		SCREEN_Y = gp.SCREEN_HEIGHT/2 - (gp.TILE_SIZE/2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
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
				idle = false;
				standCounter = 0;
				
				// Check Tile Collision
				collisionOn = false;
				gp.cChecker.checkTile(this);
			}
			else { // Return sprite animation to default when standing still;
				standCounter++;
				if (standCounter >= 4 && idle == false) {
					idle = true;
					spriteNum = 0;
					standCounter = 0;
				}
				
				if (idle == true) {
					standNum++;
					if (standNum > 12) {
						spriteNum++;
						if (spriteNum > 3) {
							spriteNum = 0;
						}
						standNum = 0;
					}
				}
			}
		}
		
		if (moving == true) {
			
			if (collisionOn == false) { // Check collision
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
			}
			
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
		
		BufferedImage sheet;
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/player/player_walk.png"));
			walk = new SpriteSheet(sheet.getWidth(), sheet.getHeight(), 4, 8, sheet, 32);
			sheet = ImageIO.read(getClass().getResourceAsStream("/player/player_idle.png"));
			stand = new SpriteSheet(sheet.getWidth(), sheet.getHeight(), 4, 4, sheet, 16);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		try {

			BufferedImage shadowSheet = ImageIO.read(getClass().getResourceAsStream("/textures/Other.png"));
			SpriteSheet tmp = new SpriteSheet(shadowSheet.getWidth(), shadowSheet.getHeight(), 3, 4, shadowSheet, 12);
			BufferedImage shadow = tmp.sprites[4];
			g2.drawImage(shadow, SCREEN_X, SCREEN_Y + (gp.TILE_SIZE/2), gp.TILE_SIZE, gp.TILE_SIZE/2, null);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		BufferedImage img = null;
		
		switch(direction) {
		case "up":
			if (idle == true) {
				img = stand.sprites[12 + spriteNum];
			} else {
				img = walk.sprites[24 + spriteNum];		
			}
			break;
		case "down":
			if (idle == true) {
				img = stand.sprites[8 + spriteNum];
			} else {				
				img = walk.sprites[16 + spriteNum];
			}
			break;
		case "left":
			if (idle == true) {
				img = stand.sprites[spriteNum];
			} else {
				img = walk.sprites[spriteNum];				
			}
			break;
		case "right":
			if (idle == true) {
				img = stand.sprites[4 + spriteNum];
			} else {
				img = walk.sprites[8 + spriteNum];				
			}
			break;
		}
		g2.drawImage(img, SCREEN_X, SCREEN_Y, gp.TILE_SIZE, gp.TILE_SIZE, null);	
	}
}
