package dev.alpete.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index;
	private BufferedImage[] frames;
	
	private long lastTime, timer;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public BufferedImage getStillFrame() {
		return frames[0];
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}
	

}
