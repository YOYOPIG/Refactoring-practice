/** 
 * This class adds the dialog pop up dialog layer into the game.
 * To show a certain message, please use the showDialog() method, using a string(ur message)
 * as argument.
 * @version 1.0
 * @since   2018-06-10
 */

package ui;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Dialog{
	
	private JLabel textbox; //the text to be shown
	private JLayeredPane frameLayers;
	
	// change position
	public Dialog(){
		System.out.println("Error! Please send a JLayeredPane to the Dialog class.");
	}
	
	public Dialog(JLayeredPane FL)
	{
		frameLayers = FL;
		//initialize the text box to show
		textbox = new JLabel("YEE",JLabel.LEFT);
		textbox.setVerticalAlignment(JLabel.CENTER);
		textbox.setHorizontalAlignment(JLabel.CENTER);
		textbox.setVerticalTextPosition(JLabel.CENTER);
		textbox.setHorizontalTextPosition(JLabel.CENTER);
		textbox.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 30));
		//textbox.setBackground(Color.magenta);
		//textbox.setForeground(Color.black);
		textbox.setOpaque(true);
		// set position and dimension
		textbox.setBounds(25, 780, 1250, 160);
		
		//textbox.setIcon();
		ImageIcon icon = new ImageIcon("res/dialog_box.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(1250, 160, BufferedImage.SCALE_SMOOTH));
		textbox.setIcon(icon);
		textbox.setIconTextGap(-512); // set to -(width of image)
		spawnDialog();
		hideDialog();
	}
	// show
	public void showDialog(String msg)
	{
		textbox.setVisible(true);
		textbox.setText(msg);
	}
	
	// hide
	public void hideDialog()
	{
		textbox.setVisible(false);
	}
	
	public void spawnDialog()
	{
		frameLayers.add(textbox, new Integer(2));
	}
}
