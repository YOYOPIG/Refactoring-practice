package game;

import java.awt.event.KeyEvent;

public class MoveForward implements InputCommand{
	private Key key = new Key();
	public void setKeyStatus(boolean pressStatus) {
		key.setPressed(pressStatus);
	}
	public Key getKey() {
		return key;
	}
	
	public boolean checkKeyCode(int keyCode) {
		if(keyCode == KeyEvent.VK_W||keyCode==KeyEvent.VK_UP)
			return true;
		else
			return false;
	}
}
