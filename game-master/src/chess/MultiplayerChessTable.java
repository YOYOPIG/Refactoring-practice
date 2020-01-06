package chess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import msg.ClientGameOver;
import msg.ClientMovePieces;
import net.MyClient;
import util.ChessImpl;

public class MultiplayerChessTable extends ChessTable{

	public MultiplayerChessTable(Room room) {
		super(room);
	    System.out.println("这个房间是网络对战模式");
	    this.addMouseListener(new MouseHandler());
	}
	
	public class MouseHandler extends MouseAdapter {
	    public void mousePressed(MouseEvent event) {

	      synchronized (chessTable) {
	        int x = event.getX();
	        int y = event.getY();

	        if (x > 30 && x < 535 && y > 30 && y < 535) {
	          mouseClickX = (x - 21) / 34;
	          mouseClickY = (y - 21) / 34;

	            if(room.isCanplay()) {
	              if (paintItem(mouseClickX, mouseClickY)) {
	                room.backGame=true;
	                Moves++;
	                if (Moves == 225)
	                  room.drawGame();
	                room.setCanplay(false);
	                System.out.println("kjdhasjdakdhads+==========" + ChessImpl.chess[0][0]);
	                ClientMovePieces msg = new ClientMovePieces(
	                    room.getRoomID(), room.isleft, ChessImpl.chess, false, mouseClickX, mouseClickY);
	                MyClient.getMyClient().trySendMessage(msg);
	                room.getChessPanel().setMark(mouseClickX, mouseClickY);
	                room.repaint();
	                audioPlayer.run();
	                if(room.isleft) {
	                  if (chessimpl.compare(mouseClickX, mouseClickY, 2)) {//黑棋赢了，发送游戏结束报文
	                    ClientGameOver msg1 = new ClientGameOver(room.getRoomID(), room.isleft);
	                    MyClient.getMyClient().trySendMessage(msg1);
	                  }
	                }else{
	                  if (chessimpl.compare(mouseClickX, mouseClickY, 1)) {//白棋赢了
	                    ClientGameOver msg1 = new ClientGameOver(room.getRoomID(), room.isleft);
	                    MyClient.getMyClient().trySendMessage(msg1);
	                  }
	                }
	              }
	              else
	                audioStopPlayer.run();
	            }

	        } else {
	          System.out.println("请将棋子放进棋盘内");
	        }
	        System.out.println("x:" + x + "y:" + y);
	      }
	    }
	  }
	
	boolean paintItem(int i, int j) {// 落子
	    boolean succeed = false;
	    if (i < 15 && j < 15) {
	        if (room.isleft) {// 黑棋玩家
	          Moves++;
	          succeed = chessimpl.add(i, j, 2);
	        } else {// 白棋玩家
	          Moves++;
	          succeed = chessimpl.add(i, j, 1);
	        }
	        return succeed;
	    } else {
	      System.out.println("棋子没添加成功");
	    }
	    return false;
	  }
	
	public void unpaintItem() {
	    if (room.isleft) {
	      chessimpl.delete(2);//黑方悔棋
	    } else {
	      chessimpl.delete(1);//白方悔棋
	    }
	    repaint();
	  }
}
