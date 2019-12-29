package game;
/** 
 * This class represents a key on the keyboard.
 * It will record the basic status of the key, 
 * such as if it was pressed and the times being pressed.
 * 
 * @version 1.2
 * @since   2018-05-26
 */

public class Key{
	private boolean isPressed; //Status of the key
	private int timesPressed; //times of this key pressed
	private int pressChanged; // a counter to detect key down
	public Key() {
		isPressed = false;
		timesPressed = 0;
		pressChanged = 1;
	}
	
	public void setPressed(boolean bePressed)
	{
		if(isPressed != bePressed)
			pressChanged++;
		isPressed = bePressed;
		if(isPressed)
		{
			timesPressed++;
		}
	}
	
	public boolean getPressed() {
		return isPressed;
	}
	
	public int getTimesPressed() {
		return timesPressed;
	}
	
	public boolean getKeyDown()
	{
		if(pressChanged%3==2)
		{
			pressChanged++;
			if(pressChanged==3)
				pressChanged = 0;
			return true;
		}
		else
			return false;
	}
}