package chess;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;

import entity.User;

public class WinNumFrame extends JFrame{
	static List<User> userlist = null;
	String str = null;
	public WinNumFrame(List<User> userlist) {
		this.userlist = userlist;
		configureContentPane();
		JPanel panel = createPanel();
		configureTextArea(panel);
		configureRankingLabel(panel);
	}
	
	private void configureContentPane() {
		getContentPane().setBounds(new Rectangle(0, 0, 434, 261));
		getContentPane().setLayout(null);
	}
	
	private JPanel createPanel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 261);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		return panel;
	}
	
	private void configureTextArea(JPanel panel) {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 31, 432, 228);
		panel.add(textArea);
		for(int i = 0;i< userlist.size();i++)
			str += userlist.get(i).getName()+"  "+userlist.get(i).getWinNum()+"\n\r";
		textArea.setText(str);
	}
	
	private void configureRankingLabel(JPanel panel) {
		JLabel label = new JLabel("战绩排行");
		label.setBounds(0, 0, 432, 29);
		panel.add(label);
	}
	   
	  
      public static void main(String[] args) {
		new WinNumFrame(userlist).setVisible(true);
	}
}
