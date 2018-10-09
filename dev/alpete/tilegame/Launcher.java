package dev.alpete.tilegame;

public class Launcher {
	public static void main(String[] args) {
		String title = "Title";
		int width = 640;
		int height = 480;
		
		Game game = new Game(title, width, height);
		game.start();
		
	}

}
