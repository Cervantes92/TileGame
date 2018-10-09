package dev.alpete.tilegame.states;

import java.awt.Graphics;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.gfx.Assets;
import dev.alpete.tilegame.ui.ClickListener;
import dev.alpete.tilegame.ui.UIImageButton;
import dev.alpete.tilegame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		//BUTTONS
		uiManager.addObject(new UIImageButton(128, 128, 128, 64, Assets.startButton, new ClickListener() {

			@Override
			public void onClick() {
				State.setState((handler.getGame().gameState));
				
			}}));
		
		uiManager.addObject(new UIImageButton(128, 256, 128, 64, Assets.startButton, new ClickListener() {

			@Override
			public void onClick() {
				State.setState((handler.getGame().gameState));
				
			}}));
		
	}

	public void tick() {
		uiManager.tick();

	}

	public void render(Graphics g) {
		uiManager.render(g);
	}
	
	

}
