package dev.alpete.tilegame.world;

import java.awt.Graphics;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.entities.EntityManager;
import dev.alpete.tilegame.entities.creatures.Player;
import dev.alpete.tilegame.entities.statics.FruitTree;
import dev.alpete.tilegame.entities.statics.Tree;
import dev.alpete.tilegame.items.ItemManager;
import dev.alpete.tilegame.tiles.Tile;
import dev.alpete.tilegame.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	//Entities
	private EntityManager entityManager;
	
	//Items
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new FruitTree(handler, 256, 320));
		
		itemManager = new ItemManager(handler);
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		entityManager.tick();
		itemManager.tick();
		
	}
	
	public void render(Graphics g) {
		//Render only tiles that can be seen on screen.
		
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int)Math.max(0,  handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(width,  (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int)(x * Tile.TILEWIDTH -handler.getGameCamera().getxOffset()), 
						(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		itemManager.render(g);
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		//If outside parameters, paint grass tiles.
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {

		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]);
			}
		}
		
		
	}
	
	
	//TODO: see if this needs to be routed through the handler.
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
