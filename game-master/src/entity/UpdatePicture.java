package entity;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chess.Home;
import chess.RoomList;
import dao.IUserDao;
import dao.IUserDaoImp;
import msg.ClientLoginMsg;
import net.MyClient;
import net.MyServer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdatePicture extends JFrame {
  public UpdatePicture u = this;
  private MyServer idi = MyServer.getMyServer();
  private User user;
  private UpdatePictureButton buttons[] = new UpdatePictureButton[19];
  
  private class UpdatePictureButton extends JLabel{
	  
	  UpdatePictureButton(ImageIcon img) {
		  super(img);
	  }
	  
		public void configureButton(final int imgID, final int op) {
			this.addMouseListener(new MouseAdapter() {
			      @Override
			      public void mouseClicked(MouseEvent e) {
			    	String root = "resource/photo/" + Integer.toString(imgID) + ".jpg";
			        idi.updateUserImag(root, user.getName());
			        user.setFileName(root);
			        if(op==1) login();
			        else updatePhoto();
			      }
			    });
		}
	}

  public UpdatePicture(User user,int flag) {
	configureFrame(user);
    createButtons();
    frameInit(flag);
  }

  public void frameInit(final int flag) {
	  for(int i=0;i<19;++i) {
	    	buttons[i].configureButton(i+1, flag);
	    	getContentPane().add(buttons[i]);
	  }
  }
  
  public void login(){
    dispose();
    ClientLoginMsg msg = new ClientLoginMsg(user.getName());
    MyClient.getMyClient().trySendMessage(msg);
  }
  
  public void updatePhoto(){
    dispose();
  }

  private void configureFrame(User user) {
	  	this.user = user;
	    idi.insertUser(user);
	    this.setBounds(100, 100, 400, 400);
	    this.setResizable(false);
	    setVisible(true);
	    getContentPane().setLayout(new GridLayout(5, 10));
  }
  
  private void createButtons() {
	  for(int i=0;i<19;++i) {
	    	String imgUrl ="resource/photo/" + Integer.toString(i+1) + ".jpg";
	    	buttons[i] = new UpdatePictureButton(new ImageIcon(imgUrl));
	    	getContentPane().add(buttons[i]);
	  }
  }

}