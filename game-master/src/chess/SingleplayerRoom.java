package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import msg.ClientBackChess;
import msg.ClientGameOver;
import msg.ClientOutRoomMsg;
import net.MyClient;

public class SingleplayerRoom extends Room{
	public SingleplayerRoom(Home home) {
		super();
		this.home = home;
	    init();
	}
	
	@Override
	protected void createChessTable() {
		chessPanel = new ChessTable(this, 0);
	}

	@Override
	protected JButton createReadyButton() {
		JButton refresh = new JButton("重新开始");
	      refresh.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	          getChessPanel().getChessimpl().ResetGame();
	          repaint();
	        }
	      });
	      refresh.setBounds(150, 5, 80, 23);
		return refresh;
	}

	@Override
	protected JButton createExitButton() {
		JButton But_exit = new JButton("退出");
	    But_exit.setBounds(416, 5, 73, 23);
	    But_exit.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
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
		      if (res == 0)
		        chessPanel.unpaintItem();
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
	        String[] options = {"我还可以再战!", "我认怂 T T"};
	        int res = JOptionPane.showOptionDialog(null, "你连机器人都打不过,还好意思认输~~", "这样真的好吗?",
	            JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
	            new ImageIcon("resource/imag/touxiang.png"), options, options[0]);
	        if (res == 1)
	          defeat();
	      }
	    });
	    But_sur.setBounds(328, 5, 78, 23);
		return But_sur;
	}

	@Override
	public void toRoomList() {
		home.setVisible(true);
		this.dispose();
	}
}
