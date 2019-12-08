package chess;

import entity.User;
import net.MyClient;

public class MultiplayerRoom extends Room{

	public MultiplayerRoom(int roomid, boolean isleft, RoomList roomList, User user) {
		super(isleft);
		this.user = user;
	    this.roomList = roomList;
	    MyClient.getMyClient().setRoom(this);
	    System.out.println("网络对战");
	    this.rid = roomid;
	    //Room.isleft = isleft;
	    init(0);
	}
}
