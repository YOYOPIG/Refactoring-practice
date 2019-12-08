package chess;

public class SingleplayerRoom extends Room{
	public SingleplayerRoom(Home home) {
		super();
		this.home = home;
	    init(1);// 人机
	}
}
