package ui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import gfx.Font;

public class Hint {
	private JLabel textShown;
	
	public Hint()
	{
		System.out.println("Error! Please send a JLayeredPane to the Hint class.");
	}
	
	public Hint(JLayeredPane layerPane)
	{
		initText();
		layerPane.add(textShown, new Integer(1));
		hideHint();
	}
	
	public void showHint(String msg)
	{
		textShown.setVisible(true);
		textShown.setText(msg);
	}
		
	public void hideHint()
	{
		textShown.setVisible(false);
	}
	
	private void initText() {
		textShown = new JLabel("E : Interact",JLabel.LEFT);
		textShown.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 30));
		textShown.setVerticalAlignment(JLabel.CENTER);
		textShown.setHorizontalAlignment(JLabel.CENTER);
		textShown.setBounds(500, 820, 250, 80);
		textShown.setVisible(true);
	}
}
