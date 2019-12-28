package game;

public interface InputCommand {
	abstract void setKeyStatus(boolean pressStatus);
	abstract boolean checkKeyCode(int keyCode);
	abstract Key getKey();
}
