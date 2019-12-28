package game;

import java.awt.event.KeyEvent;

public class MoveRight implements InputCommand{
	private Key key = new Key();
	public void setKeyStatus(boolean pressStatus) {
		key.setPressed(pressStatus);
	}
	public Key getKey() {
		return key;
	}
	
	public boolean checkKeyCode(int keyCode) {
		if(keyCode == KeyEvent.VK_D||keyCode==KeyEvent.VK_RIGHT)
			return true;
		else
			return false;
	}
}