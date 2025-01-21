package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.SpriteSheet;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	int spriteCount = 10;
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[60];
		mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
		
		getTileImage();
		loadMap("/maps/world01.txt");
		
	}
	
	public void getTileImage() {
		
		try {
			
			// GRASS (10-24)
			getSheet("/tiles/Grass1.png", 3, 5, 15, true);
		
			// DIRT (25)
			tile[spriteCount] = new Tile();
			tile[spriteCount].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));
			spriteCount++;
			
			// PLANTER (26-28)
			getSheet("/tiles/misc.png", 3, 10, 30, false);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void getSheet (String dir, int row, int col, int spriteNum, boolean collision) {
		
		try {
			
			BufferedImage img;
			img = ImageIO.read(getClass().getResourceAsStream(dir));
			SpriteSheet sheet = new SpriteSheet(img.getWidth(), img.getHeight(), row, col, img, spriteNum);
			for (int i = 0; i < sheet.sprites.length; i++) {

				tile[spriteCount] = new Tile();
				tile[spriteCount].image = sheet.sprites[i];
				tile[spriteCount].collision = collision;
				spriteCount++;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath)   {
		
		try {
		
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
				
				String line = br.readLine();
				String numbers[] = line.split(" ");
				
				while (col < gp.MAX_WORLD_COL) {
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if (col == gp.MAX_WORLD_COL) {
					col = 0;
					row++;
				}
			}
		} catch (Exception e) {}
		
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.TILE_SIZE;
			int worldY = worldRow * gp.TILE_SIZE;
			int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X; // pos relative to player
			int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y; // pos relative to player
			
			if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
					worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
					worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
					worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) { // draw only on screen + offset
					g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
				}
				
				worldCol++;
				
				if (worldCol == gp.MAX_WORLD_COL) {
					worldCol = 0;
					worldRow++;
				}
		}
	}
}
 