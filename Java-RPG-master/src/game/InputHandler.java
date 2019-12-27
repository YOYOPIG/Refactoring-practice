package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** 
 * The InputHandler class implements the KeyListener class.
 * This class can handle w,a,s,d inputs to the game.
 * 
 * @version 1.0
 * @since   2018-05-26
 */

public class InputHandler implements KeyListener{
	
	/** 
	 * The Constructor simply connects this class to the main game,
	 * making the class able to handle user input in the game. 
	 * @param game The main game
	 */
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public Key up = new Key();
	public Key left = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key interact = new Key();
	
	public void keyPressed(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), true);
		//System.out.println(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), false);
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	/** 
	 * This method sets each keys' status(pressed or not)
	 */
	public void toggleKey(int keyCode, boolean pressStatus)
	{
		if(keyCode == KeyEvent.VK_W||keyCode==KeyEvent.VK_UP)
		{
			up.setPressed(pressStatus);
		}
		if(keyCode == KeyEvent.VK_A||keyCode==KeyEvent.VK_LEFT)
		{
			left.setPressed(pressStatus);
		}
		if(keyCode == KeyEvent.VK_S||keyCode==KeyEvent.VK_DOWN)
		{
			down.setPressed(pressStatus);
		}
		if(keyCode == KeyEvent.VK_D||keyCode==KeyEvent.VK_RIGHT)
		{
			right.setPressed(pressStatus);
		}
		if(keyCode == KeyEvent.VK_E)
		{
			interact.setPressed(pressStatus);
		}
	}
}
