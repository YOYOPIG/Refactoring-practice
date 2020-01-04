package level.tiles;

import gfx.Colours;
import gfx.Screen;
import level.Level;

public abstract class Tile {

	
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1));
	public static final Tile STONE = new BasicSolidTile(1, 1, 0,Colours.get(-1, 333, -1, -1));
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1,	131, 141, -1));
	public static final Tile FLOOR = new BasicTile(3, 3, 0, Colours.get(211,321,211,420));
	public static final Tile NPC11 = new BasicSolidTile(4, 8, 28, Colours.get(111,222,333,444));
	public static final Tile NPC12 = new BasicSolidTile(5, 9, 28, Colours.get(111,222,333,444));
	public static final Tile NPC21 = new BasicSolidTile(6, 8, 29, Colours.get(111,222,333,444));
	public static final Tile NPC22 = new BasicSolidTile(7, 9, 29, Colours.get(111,222,333,444));
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	
	public Tile(int id, boolean isSolid, boolean isEmitter) {
		this.id = (byte)id;
		if(tiles[id] != null)	throw new RuntimeException("Duplicate tile id on" + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
	}
	
	public byte getID() {
		return id;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public boolean isEmitter() {
		return emitter;
	}

	public abstract void render(Screen screen, Level level, int x, int y);
}
