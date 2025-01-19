package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
	
    public BufferedImage spriteSheet;
    public BufferedImage[] sprites;
    int row, col;
    int tileSize;
    int spriteNum;
    
    public SpriteSheet(int width, int height, int row, int col, BufferedImage spriteSheet, int spriteNum) {
    	
        this.row = row;
        this.col = col;
        this.tileSize = width/col;
        this.spriteNum = spriteNum;       
        this.spriteSheet = spriteSheet;
        sprites = new BufferedImage[spriteNum];
        
        getSheet();
    }
    
    public void getSheet() {
    	
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                sprites[(i * col) + j] = spriteSheet.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }
}

