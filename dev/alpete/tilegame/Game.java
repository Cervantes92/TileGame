package dev.alpete.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;

import dev.alpete.tilegame.display.Display;
import dev.alpete.tilegame.gfx.*;
import dev.alpete.tilegame.input.KeyManager;
import dev.alpete.tilegame.input.MouseManager;
import dev.alpete.tilegame.states.GameState;
import dev.alpete.tilegame.states.MenuState;
import dev.alpete.tilegame.states.SettingsState;
import dev.alpete.tilegame.states.State;

public class Game implements Runnable {
	private boolean running = false;
	
	private Display display;
	private Thread thread;
	
	private int width, height;
	public String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//Declare state variables.
	public State menuState;
	public State settingsState;
	public State gameState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.height = height;
		this.width = width;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title,width,height);
		
		
		//Initialize input
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init(); //Pull assets from sprite sheet.
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		//Declare game state objects:
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		
		//Set the initial state of the program:
		State.setState(gameState);
		

	}
	
	private void tick() {
		keyManager.tick();
		
		//Check the game state every tick.
		if(State.getState() != null) 
			State.getState().tick();	
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0,0,width,height);
		
		//Things can be drawn here.
		//Run the render method for whatever state our game is in.
		if(State.getState() != null) 
			State.getState().render(g);

		//g.drawImage(Assets.dirt, 48, 64, null);
		
		//End draw
		
		bs.show();
		g.dispose();
		
	}
	
	public void run( ) {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		//long timer = 0;
		//int ticks = 0;
		
		while(running) {
			
			//Follows ensures that the game tries to run at the speed specified above.
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			//timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				//ticks++;
				delta--;
			}
			
			//CHECK FPS BELOW (Uncomment lines above in this method.
			//if(timer >= 1000000000)
			//{
				//System.out.println("Frames per second: " + ticks); //check FPS
				//ticks = 0;
				//timer = 0;
			//}
		}
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	//Ensures that the game can be started and stopped properly.
	public synchronized void start() {
		if(running) 
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
