package dev.alpete.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile BrickTile = new BrickTile(2);
	public static Tile stoneTile = new StoneTile(3);
	public static Tile forestTile = new ForestTile(4);
	public static Tile mountainTile = new MountainTile(5);
	
	//CLASS
	public static final int TILEWIDTH = 64;
	public static final int TILEHEIGHT = 64; 
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	//Defines if you can walk on it.
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return id;
	}

}
