package ui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import gfx.Font;

public class Hint {
	private JLabel textbox; //the text to be shown
	
	public Hint()
	{
		System.out.println("Error! Please send a JLayeredPane to the Hint class.");
	}
	
	public Hint(JLayeredPane layerPane)
	{
		//initialize the text box to show
		textbox = new JLabel("E : Interact",JLabel.LEFT);
		textbox.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 30));
		textbox.setVerticalAlignment(JLabel.CENTER);
		textbox.setHorizontalAlignment(JLabel.CENTER);
		//textbox.setBackground(new Color(0, 0, 0, 0));
		//textbox.setForeground(Color.black);
		//textbox.setOpaque(false);
		// set position and dimension
		textbox.setBounds(500, 820, 250, 80);
		textbox.setVisible(true);
		layerPane.add(textbox, new Integer(1));
		hideHint();
	}
	
	// show
	public void showHint(String msg)
	{
		textbox.setVisible(true);
		textbox.setText(msg);
	}
		
	// hide
	public void hideHint()
	{
		textbox.setVisible(false);
	}
		
}
