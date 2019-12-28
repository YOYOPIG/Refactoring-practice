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
	
	private JLabel textShown;
	private JLayeredPane frameLayers;
	
	public Dialog(){
		System.out.println("Error! Please send a JLayeredPane to the Dialog class.");
	}
	
	public Dialog(JLayeredPane FL)
	{
		frameLayers = FL;
		initText();
		initIcon();
		spawnDialog();
	}
	
	public void showDialog(String msg)
	{
		textShown.setVisible(true);
		textShown.setText(msg);
	}
	
	public void hideDialog()
	{
		textShown.setVisible(false);
	}
	
	private void spawnDialog()
	{
		frameLayers.add(textShown, new Integer(2));
		hideDialog();
	}
	
	private void initText() {
		textShown = new JLabel("YEE",JLabel.LEFT);
		textShown.setVerticalAlignment(JLabel.CENTER);
		textShown.setHorizontalAlignment(JLabel.CENTER);
		textShown.setVerticalTextPosition(JLabel.CENTER);
		textShown.setHorizontalTextPosition(JLabel.CENTER);
		textShown.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 30));
		textShown.setOpaque(true);
		textShown.setBounds(25, 780, 1250, 160);
		textShown.setIconTextGap(-512); // set to -(width of image)
	}
	
	private void initIcon() {
		ImageIcon icon = new ImageIcon("res/dialog_box.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(1250, 160, BufferedImage.SCALE_SMOOTH));
		textShown.setIcon(icon);
	}
}
