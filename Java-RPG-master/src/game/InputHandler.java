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
	
	public InputCommand cmds[] = new InputCommand[5];
	
	/** 
	 * The Constructor simply connects this class to the main game,
	 * making the class able to handle user input in the game. 
	 * @param game The main game
	 */
	public InputHandler(Game game) {
		game.addKeyListener(this);
		setupCommands();
	}
	
	public void keyPressed(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), true);
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
		for(int i=0;i<5;++i) {
			if(cmds[i].checkKeyCode(keyCode))
				cmds[i].setKeyStatus(pressStatus);
		}
	}
	
	private void setCommand(int index, InputCommand cmd) {
		cmds[index] = cmd;
	}
	
	private void setupCommands() {
		setCommand(0, new MoveForward());
		setCommand(1, new MoveDown());
		setCommand(2, new MoveLeft());
		setCommand(3, new MoveRight());
		setCommand(4, new InteractCommand());
	}
}
