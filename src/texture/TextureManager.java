package texture;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.SpriteSheet;
import tile.Tile;

public class TextureManager extends Texture {
	
	GamePanel gp;
	public Texture[] texture;
	public int mapTextureNum[][];
	int textureCount = 10;
	
	public TextureManager(GamePanel gp) {
		
		this.gp = gp;

		texture = new Texture[90];
		mapTextureNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
		
		getTextureImage();
		loadMap("/maps/texture01.txt");
	}

	public void getTextureImage() {
		
		// BUSH (10-13)
		getSheet("/textures/Bush.png", 1, 4, 4);
		
		// SMALL BUSH (14-25)
		getSheet("/textures/Small_Bush.png", 3, 4, 12);
		
		// GRASS & DIRT TEXTURE (26-29)
		getSheet("/textures/Grass_and_Dirt_Texture.png", 1, 4, 4);
		
		// TREE (30-41)
		getSheet("/textures/Tree.png", 3, 4, 12);
		
		// FLOWER (42-65)
		getSheet("/textures/Flower.png", 4, 6, 24);
		
		// STONE & ROCK (66-73)
		getSheet("/textures/Stone_and_Rock.png", 2, 4, 8);
		
		// OTHER (74-85)
		getSheet("/textures/Other.png", 3, 4, 12);
		
	}
	
	public void getSheet (String dir, int row, int col, int spriteNum) {
		
		try {
			
			BufferedImage img;
			img = ImageIO.read(getClass().getResourceAsStream(dir));
			SpriteSheet sheet = new SpriteSheet(img.getWidth(), img.getHeight(), row, col, img, spriteNum);
			for (int i = 0; i < sheet.sprites.length; i++) {

				texture[textureCount] = new Texture();
				texture[textureCount].img = sheet.sprites[i];
				textureCount++;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath); // import text file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // read content in text file
			
			int col = 0;
			int row = 0;
			
			while(col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
				
				String line = br.readLine();
				String numbers[] = line.split(" "); // split and put each element to list
				
				while (col < gp.MAX_WORLD_COL) {
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTextureNum[col][row] = num;
					col++;
					
				}
				
				if (col == gp.MAX_WORLD_COL) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
			
			int tileNum = mapTextureNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.TILE_SIZE;
			int worldY = worldRow * gp.TILE_SIZE;
			
			// Set tile offset
			int offsetX;
			int offsetY;
			int tile = gp.tileM.mapTileNum[worldCol][worldRow];
			
			
			int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X; // pos relative to player
			int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y; // pos relative to player
			
			if (tileNum != 0) {
				if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
					worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
					worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
					worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) { // draw only on screen + offset
					g2.drawImage(texture[tileNum].img, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
				}
			}
				
			worldCol++;	
			if (worldCol == gp.MAX_WORLD_COL) {
				worldCol = 0;
				worldRow++;
			}
			
		}
	}
	
}
