package entity;

import java.awt.image.BufferedImage;

import main.SpriteSheet;

public class Entity {
	
	public int worldX, worldY;
	int speed;
	
	BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	String direction;	
	
	int spriteCounter = 0;
	int spriteNum = 1;
	SpriteSheet ss;
	
}
