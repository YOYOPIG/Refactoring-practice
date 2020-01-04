package game.entities;


import character.NPC;
import level.Level;
import level.tiles.Tile;

public abstract class Mob extends Entity{

	public static int itemID;
	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;
	
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move(int xa, int ya) {
		if(!NPC.isTalking) {
			if(xa != 0 && ya != 0) {
				move(xa, 0);
				move(0, ya);
				numSteps--;
				return;
			}
			numSteps++;
			
			if(!hasCollided(xa,ya)) {
				if(ya < 0)	movingDir = 0;
				if(ya > 0)	movingDir = 1;
				if(xa < 0)	movingDir = 2;
				if(xa > 0)	movingDir = 3;
				x += xa * speed;
				y += ya * speed;
			}
		}
		
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	public boolean isSolidTile(int xa,int ya,int x,int y) {
		if(level == null ) return false;
		Tile lastTile = level.getTile((this.x+x)>>3 , (this.y+y)>>3);
		Tile newTile = level.getTile((this.x+x+xa)>>3 , (this.y+y+ya)>>3);
		// get what kind of Tile is the player on
		// Solid or not solid
		itemID=newTile.getID();
		// if the lastTile is not equal to newTile a.k.a u did move && newTile is a solid 
		if(!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
		
	}
	
	public String getName() {
		return name;
	}

}
