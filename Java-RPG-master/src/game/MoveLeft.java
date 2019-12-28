package game;

import java.awt.event.KeyEvent;

public class MoveLeft implements InputCommand{
	private Key key = new Key();
	public void setKeyStatus(boolean pressStatus) {
		key.setPressed(pressStatus);
	}
	public Key getKey() {
		return key;
	}
	
	public boolean checkKeyCode(int keyCode) {
		if(keyCode == KeyEvent.VK_A||keyCode==KeyEvent.VK_LEFT)
			return true;
		else
			return false;
	}
}
