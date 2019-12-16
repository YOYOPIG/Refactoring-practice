package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.User;
import msg.ClientBackChess;
import msg.ClientBeReady;
import msg.ClientGameOver;
import msg.ClientOffMsg;
import msg.ClientOutRoomMsg;
import net.MyClient;

public class MultiplayerRoom extends Room{

	public MultiplayerRoom(int roomid, boolean isleft, RoomList roomList, User user) {
		super(isleft);
		this.user = user;
	    this.roomList = roomList;
	    MyClient.getMyClient().setRoom(this);
	    System.out.println("网络对战");
	    this.roomID = roomid;
	    //Room.isleft = isleft;
	    init();
	}
	
	@Override
	protected void createChessTable() {
		chessPanel = new MultiplayerChessTable(this);
	}
	
	@Override
	protected JButton createReadyButton() {
		JButton But_ready = new JButton("准备");
	    But_ready.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	        if (gameStart == false) {
	          visible = !visible;
	          ready.setVisible(visible);
	          ClientBeReady msg = new ClientBeReady(roomID, isleft);
	          MyClient.getMyClient().trySendMessage(msg);//发给服务器
	        }
	      }
	    });
	    But_ready.setBounds(157, 5, 73, 23);
	    
		return But_ready;
	}

	@Override
	protected JButton createExitButton() {
		JButton But_exit = new JButton("退出");
	    But_exit.setBounds(416, 5, 73, 23);
	    But_exit.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	        if (gameStart == true) {
	          String[] options = {"我还可以再战!", "我认怂 T T"};
	          int res = JOptionPane.showOptionDialog(null, "对方把你吓尿了~~", "这样真的好吗?",
	              JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
	              null, options, options[0]);
	          if (res == 0) return;
	          ClientGameOver msg = new ClientGameOver(getRoomID(), !isleft);
	          MyClient.getMyClient().trySendMessage(msg);
	        }
	        ClientOutRoomMsg msg1 = new ClientOutRoomMsg(roomID, isleft);
	        MyClient.getMyClient().trySendMessage(msg1);
	        getChessPanel().getChessimpl().ResetGame();
	        toRoomList();
	      }
	    });
		return But_exit;
	}

	
	@Override
	protected JButton createRegretButton() {
		JButton But_regret = new JButton("悔棋");
	    But_regret.setBounds(240, 5, 78, 23);
	    But_regret.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	        if (backGame == true) {
	          String[] options = {"爸爸", "不叫"};
	          int res = JOptionPane.showOptionDialog(null, "叫爸爸", "还想悔棋?",
	              JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
	              new ImageIcon("resource/imag/back.png"), options, options[0]);
	          if (res == 0) {
	            beforeRegret = isCanplay();
	            setCanplay(false);
	            ClientBackChess msg = new ClientBackChess(roomID, isleft);
	            MyClient.getMyClient().trySendMessage(msg);//发给服务器
	          }
	        }
	      }
	    });
		return But_regret;
	}


	@Override
	protected JButton createSurrenderButton() {
		JButton But_sur = new JButton("认输");
	    But_sur.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	          if (gameStart == true) {
	            String[] options = {"我还可以再战!", "我认怂 T T"};
	            int res = JOptionPane.showOptionDialog(null, "对方把你吓尿了~~", "这样真的好吗?",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
	                new ImageIcon("resource/imag/touxiang.png"), options, options[0]);
	            if (res == 1) {
	              ClientGameOver msg = new ClientGameOver(getRoomID(), !isleft);
	              MyClient.getMyClient().trySendMessage(msg);
	            }
	          }
	      }
	    });
	    But_sur.setBounds(328, 5, 78, 23);
		return But_sur;
	}
	
	protected void configureWindowClose() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ClientOutRoomMsg msg = new ClientOutRoomMsg(roomID, isleft);
				MyClient.getMyClient().trySendMessage(msg);
				ClientOffMsg msg1 = new ClientOffMsg();
				MyClient.getMyClient().trySendMessage(msg1);
			}
		});
	  }

	@Override
	public void toRoomList() {
		roomList.setVisible(true);
		this.dispose();
	}
}
