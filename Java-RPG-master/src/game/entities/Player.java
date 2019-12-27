package game.entities;

import game.InputHandler;
import gfx.Colours;
import gfx.Screen;
import level.Level;

public class Player extends Mob{

	private InputHandler input;
	private int colour = Colours.get(-1, 111, 145, 543);
	
	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "Player", x, y, 1);
		this.input = input;
	}
	
	
	
	public void tick() {
		
		int xa = 0;
		int ya = 0;

		// player moves
		if(input.up.getPressed())	ya--;
		if(input.down.getPressed())	ya++;
		if(input.right.getPressed())xa++;
		if(input.left.getPressed())	xa--;
		
		if(xa != 0 || ya != 0) {
			//System.out.println("condition is good!");
			move(xa, ya);
			isMoving = true;
		}
		else {
			//System.out.println("condition is not good!");
			isMoving = false;
		}
//		this.scale = 2;		//scale up
	}
	
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 3;
		int flipTop = (numSteps >> walkingSpeed) & 1;		//for animation
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if(movingDir == 1) {
			xTile += 2;
		}
		else if(movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);		
	}
	
	public boolean hasCollided(int xa,int ya) {
		// the four corners of the player's collidor box
		int xMin=0;
		int xMax=10;
		int yMin=3;
		int yMax=8;
		
		// check 4 edges of the box
		for(int i=xMin;i<=xMax;i++) {
			if(isSolidTile(xa,ya,i,yMin)) {
				System.out.println(itemID);
				return true;
			}
			
		}
		
		for(int i=xMin;i<=xMax;i++) {
			if(isSolidTile(xa,ya,i,yMax)) {
				System.out.println(itemID);
				return true;
			}
		}
		
		for(int i=yMin;i<=yMax;i++) {
			if(isSolidTile(xa,ya,xMin,i)) {
				System.out.println(itemID);
				return true;
			}
				
		}
		
		for(int i=yMin;i<=yMax;i++) {
			if(isSolidTile(xa,ya,xMax,i)) {
				System.out.println(itemID);
				return true;
			}
		}
		return false;
	}

}
