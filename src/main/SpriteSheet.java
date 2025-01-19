package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
	
    public BufferedImage spriteSheet;
    public BufferedImage[] sprites;
    int row, col;
    int tileSize;
    
    public SpriteSheet(int width, int height, int row, int col, BufferedImage spriteSheet, int spriteNum) throws IOException {

        this.row = row;
        this.col = col;
        this.tileSize = width/col;
        
        this.spriteSheet = spriteSheet;
        sprites = new BufferedImage[spriteNum];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                sprites[(i * col) + j] = spriteSheet.getSubimage(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }

}

