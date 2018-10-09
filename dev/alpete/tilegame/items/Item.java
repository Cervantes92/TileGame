package dev.alpete.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.gfx.Assets;

public class Item {
	
	//HANDLER STUFF
	public static Item[] items = new Item[256];
	public static Item appleItem  = new Item(Assets.apple, "Apple", 0);
	public static Item logItem  = new Item(Assets.log, "Log", 1);
	public static Item stoneItem  = new Item(Assets.stoneItem, "Stone", 2);
	
	
	//CLASS STUFF
	//Vars for all items
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	//Vars that change from item to item
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	//Bounding rectangle
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
		
	}
	
	public void tick() {
		if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g) {
		if(handler == null) {
			return;
		}
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture,  x,  y,  ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	//Overloaded testing method.
	public Item createNew(int count) {
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
		//Have to update the x and y for the bounding box as well.
		bounds.x = x;
		bounds.y = y;
	}
	
	//GETTERS and SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}
