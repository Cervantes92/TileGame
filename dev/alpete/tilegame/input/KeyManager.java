package dev.alpete.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	
	private boolean[] keys, justPressed,  cantPress;
	public boolean up, down, left, right, space, enter, shift, q, e;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick() {
		//Detects if key has been pressed but not released.
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] == true && !keys[i]) {
				cantPress[i] = false;
			}else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		space = keys[KeyEvent.VK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];
		shift = keys[KeyEvent.VK_SHIFT];
		
		q = keys[KeyEvent.VK_Q];
		e = keys[KeyEvent.VK_E];
	}


	public void keyPressed(KeyEvent e) {
		//Make sure keypress is within bounds of 256
		if(e.getKeyCode() < 0 || e.getKeyCode() > 256) {
			return;
		}
		
		//Checks and sees if key is pressed.
		keys[e.getKeyCode()] = true;
		//System.out.println("KEY IS PRESSED!");

	}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}

	public void keyReleased(KeyEvent e) {
		//Make sure keypress is within bounds of 256
		if(e.getKeyCode() < 0 || e.getKeyCode() > 256) {
			return;
		}
		
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
