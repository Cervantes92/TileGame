

package dev.alpete.tilegame.states;

import java.awt.Graphics;

import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.entities.creatures.Player;
import dev.alpete.tilegame.entities.statics.FruitTree;
import dev.alpete.tilegame.entities.statics.Tree;
import dev.alpete.tilegame.world.World;

public class GameState extends State {

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}

	public void tick() {
		world.tick();		
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
