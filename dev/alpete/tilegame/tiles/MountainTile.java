package dev.alpete.tilegame.tiles;

import dev.alpete.tilegame.gfx.Assets;

public class MountainTile extends Tile{

	public MountainTile(int id) {
		super(Assets.mountain, id);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isSolid() {
		return true;
	}

}
