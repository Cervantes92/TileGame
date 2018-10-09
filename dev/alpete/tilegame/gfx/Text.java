package dev.alpete.tilegame.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {
	//Takes a string and draws it to the screen.
	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		
		int x = xPos, y = yPos;
		
		if(center) {
			FontMetrics fm = g.getFontMetrics(font);
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		
		g.drawString(text, x, y);
		
	}
}
