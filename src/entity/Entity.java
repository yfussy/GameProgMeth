package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.SpriteSheet;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;	
	
	int spriteCounter = 0;
	int spriteNum = 3;
	SpriteSheet ss;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
}
