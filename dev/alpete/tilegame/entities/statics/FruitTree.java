package dev.alpete.tilegame.entities.statics;

import java.awt.Graphics;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.entities.Entity;
import dev.alpete.tilegame.gfx.Assets;
import dev.alpete.tilegame.items.Item;
import dev.alpete.tilegame.tiles.Tile;

public class FruitTree extends StaticEntity {
	
	public FruitTree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 24;
		bounds.y = 44;
		bounds.width = 12;
		bounds.height = 20;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.fruitTree, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override  
	public void die() {
		//I want to create a tree when a fruit tree dies.
		//handler.getWorld().getEntityManager().addEntity(new Tree(handler, x, y));
		handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int)x + 64, (int)y));
		handler.getWorld().getItemManager().addItem(Item.logItem.createNew((int)x + 32, (int)y));
	}

}
