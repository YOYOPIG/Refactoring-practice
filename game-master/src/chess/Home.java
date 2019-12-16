package chess;

import entity.User;
import msg.ClientLoginMsg;
import msg.ClientLogoutMsg;
import net.MyClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends JFrame {
  private User user = new User("Guest");
  private Home home = this;
  private JButton userButton = new JButton();
  private JButton netButton = new JButton("Multiplayer");
  private JButton robotButton = new JButton("Single player");
  private JButton logoffButton = new JButton("Quit");
  private JPanel contentPane = new JPanel() {
    protected void paintComponent(Graphics g) {
      Image image = new ImageIcon("resource/imag/home.png").getImage();
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

  };

  public Home() {
    setFrameInfo();
    init();
    addAction();
    addComponents();
  }

  private void init() {
    contentPane.setLayout(null);
    netButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 14);
    netButton.setFocusPainted(false);
    userButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 14);
    robotButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.6), this.getWidth() / 6, this.getHeight() / 14);
    logoffButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.7), this.getWidth() / 6, this.getHeight() / 14);
  }

  private void setFrameInfo() {
	  this.setTitle("Chess");
	  this.setSize(1000, 562);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setIconImage(new ImageIcon("resource/imag/logo.png").getImage());
  }
  
  private void addAction() {
    logoffButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        home.dispose();
        new Home();
      }
    });
    netButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new NameDialog(home);
      }
    });

    robotButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toRoom();
      }
    });

    userButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ClientLoginMsg msg = new ClientLoginMsg(user.getName());
        MyClient.getMyClient().trySendMessage(msg);
      }
    });

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("End process");
        ClientLogoutMsg msg = new ClientLogoutMsg();
        MyClient.getMyClient().trySendMessage(msg);
      }
    });
  }

  private void addComponents() {
	  contentPane.add(netButton);
	  contentPane.add(robotButton);
	  this.add(contentPane);
	  this.setVisible(true);
  }
  
  /**
   *
   * @param time   time to repaint
   * @param x      starting point
   * @param y      starting point
   * @param width  window width
   * @param height window height
   */
  public void repaint(long time, int x, int y, int width, int height) {
    netButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.5), this.getWidth() / 6, this.getHeight() / 13);
    robotButton.setBounds((int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.6), this.getWidth() / 6, this.getHeight() / 13);
  }


  public void toRoomList(User user) {
    this.user = user;
    new RoomList(this, user);
    userButton.setText(user.getName());
    contentPane.remove(netButton);
    contentPane.add(userButton);
    contentPane.add(logoffButton);
    this.setVisible(false);
  }

  public void toRoom() {
    new SingleplayerRoom(this);
    this.setVisible(false);
  }
}
