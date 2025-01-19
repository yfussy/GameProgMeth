package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
	
    public BufferedImage spriteSheet;
    public BufferedImage[] sprites;
    int width;
    int height;
    int rows;
    int columns;
    
    public SpriteSheet(int width, int height, int rows, int columns, BufferedImage spriteSheet) throws IOException {
        
    	this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
        this.spriteSheet = spriteSheet;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                sprites[(i * columns) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
            }
        }
    }

}

