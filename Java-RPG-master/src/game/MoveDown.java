package game;

import java.awt.event.KeyEvent;

public class MoveDown implements InputCommand{
	private Key key = new Key();
	public void setKeyStatus(boolean pressStatus) {
		key.setPressed(pressStatus);
	}
	public Key getKey() {
		return key;
	}
	
	public boolean checkKeyCode(int keyCode) {
		if(keyCode == KeyEvent.VK_S||keyCode==KeyEvent.VK_DOWN)
			return true;
		else
			return false;
	}
}