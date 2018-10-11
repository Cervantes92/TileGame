package dev.alpete.tilegame.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.gfx.Assets;
import dev.alpete.tilegame.gfx.Text;
import dev.alpete.tilegame.items.Item;

public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	//Inventory Screen Size (Centered).
	private int invX = 64, invY = 48, invWidth = 512, invHeight = 384,
			
			//Center of inventory screen.
			invListCenterX = invX +  171,
			invListCenterY = invY + invHeight / 2 + 5,
			
			//Spacing between items in the inventory screen
			invListSpacing = 30,
			
			//Position of item image
			invImageX = 452, invImageY =82,
			invImageWidth = 64, invImageHeight = 64,
			
			//Position of item counter
			invCountX = 484, invCountY = 172;
	
	//What Item is selected in the inventory
	private int selectedItem = 0;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		//misc items here, start with 5 apples for testing purposes.
		addItem(Item.appleItem.createNew(5));
	}
	
	public void tick() {
		//Activate inventory
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		
		if(!active)
			return;
		
		//Inventory User Interface
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			selectedItem--;
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
			selectedItem++;
		}
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
		
		//Inventory Test No Apple?
		//System.out.println("Inventory: ");
		//for(Item i : inventoryItems) {
		//	System.out.println(i.getName() + "  " + i.getCount()); }
	}
		
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		//Draw screen
		g.drawImage(Assets.inventoryScreen, invX , invY, invWidth, invHeight, null);
		
		//Draw items in screen
		//TODO: Fix .ttf import bug so we can draw text to the screen
		int len  = inventoryItems.size();
		if(len == 0) 
			return;
		
		//Fills inventory slots
		for(int i = -5; i < 6; i++) {
			//Check and see if item index is outside field
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
					invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.timesRoman);
			
			
		}
	}
	//Inventory Methods
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	
	//GETTERS AND SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
}
