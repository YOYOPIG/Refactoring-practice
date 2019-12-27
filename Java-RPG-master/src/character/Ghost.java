package character;

import game.Game;
import ui.Dialog;

public class Ghost extends NPC{
	
	public Ghost()
	{
		super(0,new String[6],0);
		msg[0] = "Hello, PLAYER";
		msg[1] = "Welcome to THE GAME";
		msg[2] = "Press E to keep talking";
		msg[3] = "But I have nothing to say now";
		msg[4] = "Bye, have a great time!";
		msg[5] = "ENDOFLINE";
	}
	
	
}
