package chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import msg.*;
import net.MyClient;
import net.MyServer;
import util.AudioPlayer;
import util.ChessImpl;
import util.IChess;
import entity.RoomPojo;
import entity.User;

public abstract class Room extends JFrame {
  public boolean gameStart;
  public boolean backGame;
  protected RoomList roomList;
  protected Home home;
  protected int roomID;
  private User leftPlayer;
  private User rightPlayer;
  private static boolean canplay = false;
  protected static boolean beforeRegret = false;
  public boolean visible = false;
  JPanel gamer1 = new JPanel();
  JLabel jLabellll = new JLabel();
  JLabel jLabelll = new JLabel();
  JLabel ready = new JLabel();
  JLabel ready1 = new JLabel();
  JLabel label_4;
  JLabel label = new JLabel();
  JLabel lblNewLabel = new JLabel();
  JLabel label_3;
  private int status;// 房间的状态
  protected ChessTable chessPanel;
  public static boolean isleft;
  protected User user;
  
  public Room() {
	  return;
  }
  
  public Room(boolean isleft) {
	  this.isleft = isleft;
  }

  public boolean isCanplay() {
    return canplay;
  }

  public void setCanplay(boolean canplay) {
    this.canplay = canplay;
  }

  public ChessTable getChessPanel() {
    return chessPanel;
  }

  public void setChessPanel(ChessTable chessPanel) {
    this.chessPanel = chessPanel;
  }

  public RoomList getRoomList() {
    return roomList;
  }

  public void setRoomList(RoomList roomList) {
    this.roomList = roomList;
  }

  public int getRid() {
    return roomID;
  }

  public void setRid(int rid) {
    this.roomID = rid;
  }

  public User getLeftPlayer() {
    return leftPlayer;
  }

  public void setLeftPlayer(User leftPlayer) {
    this.leftPlayer = leftPlayer;
  }

  public User getRightPlayer() {
    return rightPlayer;
  }

  public void setRightPlayer(User rightPlayer) {
    this.rightPlayer = rightPlayer;
  }

  public int getStatus() {
    return status;
  }


  public void setStatus(int status) {
    this.status = status;
  }

  
  /**
   * 功能：初始化房间、棋盘 作者：林珊珊
   */
  public void init(final int model) {// 联网对战0 人机对战1
	configureFrameInfo();
	createChessTable();
    configureGamer2();
    configureGamer1();
    configureChat();
    JPanel UIPanel = configureUIPanel();
    addButtons(UIPanel);
    configureWindowClose();
  }

  private void configureFrameInfo() {
	  this.setIconImage(new ImageIcon("resource/imag/logo.png").getImage());
	  this.setTitle("五子棋");
	  this.setSize(1000, 800);
	  this.setResizable(false);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setVisible(true);
	  getContentPane().setLayout(null);
  }
  
  abstract protected void createChessTable();
  
  private void configureGamer2() {
	  JPanel gamer2 = new JPanel();
	  gamer2.setBounds(10, 408, 180, 290);
	  getContentPane().add(gamer2);
	  gamer2.setLayout(null);
	  gamer2.setOpaque(false);
	  if (user != null) {
	    JLabel lblNewLabel_1 = new JLabel(user.getName());
	    lblNewLabel_1.setBounds(85, 110, 130, 45);
	    gamer2.add(lblNewLabel_1);
	    JLabel label_2 = new JLabel();
      	label_2.setIcon(new ImageIcon(user.getFileName()));
	    label_2.setBounds(62, 35, 70, 70);
	    label_2.setOpaque(false);
	    gamer2.add(label_2);

	    label_3 = new JLabel(user.getWinNum() + "");
	    label_3.setBounds(85, 190, 45, 45);
	    gamer2.add(label_3);

	    label_4 = new JLabel(user.getLoseNum() + "");
	    label_4.setBounds(85, 240, 45, 45);
	    gamer2.add(label_4);
	  }
	  ready.setBounds(135, 53, 40, 40);
	  ready.setIcon(new ImageIcon("resource/imag/ready.png"));
	  ready.setVisible(false);
	  gamer2.add(ready);
  }
  
  private void configureGamer1() {
	  gamer1.setBounds(10, 78, 180, 290);

	  getContentPane().add(gamer1);
	  gamer1.setLayout(null);
	  gamer1.setOpaque(false);
	  ready1.setBounds(135, 53, 40, 40);
	  ready1.setIcon(new ImageIcon("resource/imag/ready.png"));
	  ready1.setVisible(false);
	  gamer1.add(ready1);
	  jLabelll.setBounds(85, 190, 45, 45);
	  jLabelll.setOpaque(false);
	  gamer1.add(jLabelll);

	  jLabellll.setBounds(85, 240, 45, 45);
	  jLabellll.setOpaque(false);
	  gamer1.add(jLabellll);
	  lblNewLabel.setBounds(85, 110, 130, 45);
	  lblNewLabel.setOpaque(false);
	  gamer1.add(lblNewLabel);

	  label.setBounds(62, 35, 70, 70);
	  label.setOpaque(false);
	  gamer1.add(label);
  }
  
  private void configureChat() {
	  JPanel chatRoom = new JPanel();
	  chatRoom.setBounds(778, 100, 222, 658);
	  getContentPane().add(chatRoom);
	  chatRoom.setLayout(null);
	  chatRoom.setOpaque(false);

	  JLabel label_1 = new JLabel("聊天室");
	  label_1.setBounds(71, 147, 60, 348);
	  chatRoom.add(label_1);
	  label_1.setOpaque(false);
  }
  
  private JPanel configureUIPanel() {
	  JPanel logoPanel = new JPanel() {
	      protected void paintComponent(Graphics g) {
	        Image image = new ImageIcon("resource/imag/room.png").getImage();
	        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	      }
	    };
	    logoPanel.setBounds(0, 0, 1000, 800);
	    getContentPane().add(logoPanel);
	    logoPanel.setLayout(null);
	    logoPanel.setOpaque(false);

	    chessPanel.setBounds(215, 100, 545, 545);
	    logoPanel.add(chessPanel);
	    
	    JPanel UIPanel = new JPanel();
	    UIPanel.setBounds(173, 670, 515, 33);
	    logoPanel.add(UIPanel);
	    UIPanel.setLayout(null);
	    UIPanel.setOpaque(false);
	    return UIPanel;
  }
  
  private void addButtons(JPanel UIPanel) {
	  JButton But_ready = createReadyButton();
	  JButton But_exit = createExitButton();
	  JButton But_reg = createRegretButton();
	  JButton But_sur = createSurrenderButton();
	  
	  UIPanel.add(But_ready);
	  UIPanel.add(But_exit);
	  UIPanel.add(But_reg);
	  UIPanel.add(But_sur);
  }
  
  abstract protected JButton createReadyButton();
  abstract protected JButton createExitButton();
  abstract protected JButton createRegretButton();
  abstract protected JButton createSurrenderButton();
  
  protected void configureWindowClose() {
	  addWindowListener(new WindowAdapter() {
		  
	  });
  }
  
  public void setAnotherPlayer(RoomPojo roomPojo) {
    System.out.println(roomPojo);
    if (roomPojo.getRid() != roomID) return;
    User user;
    boolean flag;
    if (isleft) {
      if (roomPojo.isLeftReady() == false) {
        ready.setVisible(false);
        visible = false;
      }
      user = roomPojo.getRightPlayer();
      flag = roomPojo.isRightReady();
    } else {
      if (roomPojo.isRightReady()) {
        ready.setVisible(false);
        visible = false;
      }
      user = roomPojo.getLeftPlayer();
      flag = roomPojo.isLeftReady();
    }
    if (user != null) {
      ready1.setVisible(flag);
      label.setIcon(new ImageIcon(user.getFileName()));
      lblNewLabel.setText(user.getName());
      jLabelll.setText(user.getWinNum() + "");
      jLabellll.setText(user.getLoseNum() + "");
    } else {
      if (gameStart == true) {
        ready.setIcon(new ImageIcon("resource/imag/ready.png"));
        ready.setVisible(false);
        gameStart = false;
        backGame = false;
        resetGame();
        repaint();
      }
      ready1.setIcon(new ImageIcon("resource/imag/ready.png"));
      ready1.setVisible(false);
      label.setIcon(null);
      lblNewLabel.setText("");
      jLabelll.setText("");
      jLabellll.setText("");
    }
  }

  public void setReady(RoomPojo roomPojo) {
    if (roomPojo.getRid() != roomID) return;
    if (isleft) {
      ready1.setVisible(roomPojo.isRightReady());
    } else
      ready1.setVisible(roomPojo.isLeftReady());
  }

  public void resetReady(RoomPojo roomPojo) {
    if (roomPojo.getRid() != roomID) return;
    ready1.setVisible(false);
  }

  /**
   * 功能：跳转至房间列表页面 作者：林珊珊
   */
  public void toRoomList() {
    if (home == null)
      roomList.setVisible(true);
    else {
      home.setVisible(true);
    }
    this.dispose();
  }


  public static void main(String[] args) {
    Room r = new SingleplayerRoom(new Home());
  }

  public void gameStart() {
    gameStart = true;
    new AudioPlayer("resource/audio/start.wav").run();
    if (isleft) {
      ready.setIcon(new ImageIcon("resource/imag/black.png"));
      ready1.setIcon(new ImageIcon("resource/imag/white.png"));
      setCanplay(true);
    } else {
      ready.setIcon(new ImageIcon("resource/imag/white.png"));
      ready1.setIcon(new ImageIcon("resource/imag/black.png"));
    }

    //if((isLeftPlay()==false)&&(isRightPlay()==false)){
    //setLeftPlay(true);//黑棋先手
    //}
  }

  public void decide() {
    boolean result;
    String[] options = {"收", "滚"};
    int res = JOptionPane.showOptionDialog(this, "收不收", "对方为了悔棋认你做爸爸",
        JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_OPTION,
        null, options, options[0]);

    if (res == 0) {
      result = true;
    } else {
      result = false;
    }
    if (result) {
      setCanplay(false);
      backGame = false;
    }
    ClientBackResult msg = new ClientBackResult(result, roomID, isleft);
    MyClient.getMyClient().sendMsg(msg);
  }

  public void BackFail() {
    if (beforeRegret) {
      setCanplay(true);
    }
    JOptionPane.showMessageDialog(this,
        "对方儿子太多", "对方拒绝了你的请求", JOptionPane.ERROR_MESSAGE);


  }

  public void BackSucceed() {
    backGame = false;
    setCanplay(true);
    chessPanel.unpaintItem();//本身面板
    ClientMovePieces msg = new ClientMovePieces(roomID, isleft, ChessImpl.chess, true, 0, 0);
    MyClient.getMyClient().sendMsg(msg);
    JOptionPane.showMessageDialog(this,
        "乖儿子", "对方同意了你的请求", JOptionPane.ERROR_MESSAGE);
  }

  private void resetGame() {
    gameStart = false;
    setCanplay(false);
    ready.setIcon(new ImageIcon("resource/imag/ready.png"));
    ready1.setIcon(new ImageIcon("resource/imag/ready.png"));
    ready.setVisible(false);
    ready1.setVisible(false);
    chessPanel.getChessimpl().ResetGame();
    repaint();
    visible = false;
    chessPanel.Moves = 0;
  }

  public void win() {
    if(chessPanel.model==0) {
      user = MyServer.getMyServer().findUser(user.getName());
      label_3.setText(user.getWinNum() + "");
      label_4.setText(user.getLoseNum()+"");
    }
    new Thread(new AudioPlayer("resource/audio/winner.wav")).start();
    JOptionPane.showMessageDialog(this,
        "大侠，在下甘拜下风！！", "你赢了！", JOptionPane.ERROR_MESSAGE, new ImageIcon("resource/imag/winner.png"));
    resetGame();
  }

  public void deafeat() {
    if(chessPanel.model==0) {
      jLabelll.setText("" + (Integer.parseInt(jLabelll.getText()) + 1));
      jLabellll.setText(""+(Integer.parseInt(jLabellll.getText())+1));
    }
    new Thread(new AudioPlayer("resource/audio/loser.wav")).start();
    JOptionPane.showMessageDialog(this,
        "胜败乃兵家常事，壮士请重新来过", "你输了！", JOptionPane.ERROR_MESSAGE, new ImageIcon("resource/imag/loser.png"));
    resetGame();
  }

  public void pingju() {
    new Thread(new AudioPlayer("resource/audio/pingju.wav")).start();
    JOptionPane.showMessageDialog(this,
        "相亲相爱一家人~~", "平局！", JOptionPane.ERROR_MESSAGE);
    resetGame();
  }
}
