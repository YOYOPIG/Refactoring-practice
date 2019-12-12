package chess;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 功能: 邮件发送功能，邮件反馈.
 * 作者: 王小明   时间: 2016-09-23
 */
public class SendmailFrame extends JFrame {
	JTextArea textArea = null;
	String r = null;
	String t=null;
	private JTextField textField;

	public SendmailFrame() {
		configureFrame();
		addSendButton();
		configureTextArea();
		addLabel("您希望我们游戏哪里还有待改善", 16, new Bound(76, 25, 263, 31));
		addLabel("收 件 人 ：", 14, new Bound(37, 66, 77, 15));
		addLabel("     五子棋游戏建议反馈部", 14, new Bound(96, 66, 205, 15));
		addLabel("主   题  ：", 14, new Bound(37, 104, 77, 15));
		addLabel("正   文  ：", 14, new Bound(37, 142, 77, 15));
		configureTextField();
		addExitButton();
		addIcon();
	}
	
	private void configureFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resource/imag/Sendmailframe.png"));
		getContentPane().setFont(new Font("宋体", Font.BOLD, 14));
		setVisible(true);
		setBounds(new Rectangle(200, 200, 50, 50));
		setSize(394, 394);

		getContentPane().setBackground(SystemColor.control);
		setTitle("欢迎来到邮件反馈箱");
		getContentPane().setLayout(null);
	}
	
	private void configureTextArea() {
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea.setForeground(Color.BLACK);
		textArea.setBounds(37, 174, 316, 128);
		getContentPane().add(textArea);

		textArea.setLineWrap(true);
	}
	
	private void configureTextField() {
		textField = new JTextField();
		textField.setBounds(109, 101, 244, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	private class Bound {
		int x;
		int y;
		int width;
		int height;
		
		Bound(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
	}
	
	private void addLabel(String str, int fontSize, Bound bound) {
		JLabel lbl = new JLabel(str);
		lbl.setForeground(Color.BLUE);
		lbl.setFont(new Font("宋体", Font.PLAIN, fontSize));
		lbl.setBounds(bound.x, bound.y, bound.width, bound.height);
		getContentPane().add(lbl);
	}
	
	private void addSendButton() {
		JButton sendButton = new JButton("发送");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
    	}
		});
		sendButton.setForeground(Color.BLUE);
		sendButton.setFont(new Font("宋体", Font.BOLD, 18));
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mailEmpty())
					warnEmptyMail();
				else
					trySendMail();
			}
        });
		sendButton.setBounds(99, 312, 93, 23);
		getContentPane().add(sendButton);
	}
	
	private void addExitButton() {
		JButton btnNewButton = new JButton("退出");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disposeFrame();
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		btnNewButton.setBounds(208, 312, 93, 23);
		getContentPane().add(btnNewButton);
	}
	
	private void addIcon() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resource/imag/sendmail.jpg"));
		lblNewLabel.setBounds(0, 0, 384, 361);
		getContentPane().add(lblNewLabel);
	}
	
	/**
	   * 功能: 判定邮件内容是否为空
	   * 作者: 王小明   时间: 2016-09-23
	*/
	private boolean mailEmpty() {
		r = textArea.getText();
	 	t=textField.getText();
	 	int index1 = 0;
	 	int index2 = 0;
	 	for(int i=0;i<r.length();i++)
	    {
	     if((r.charAt(i)!=' '))
	      index1++;
	    }
	    for(int j=0;j<t.length();j++)
	    {
	    	if((t.charAt(j)!=' '))
	    		index2++;
	    }
	    return (index1==0)||(index2==0);
	}
	
	private void trySendMail() {
		try {			
			sendmail();
			disposeFrame();
			JOptionPane.showMessageDialog(null,"已收到您的建议，我们会做最大改善", "邮件发送框",JOptionPane.CLOSED_OPTION);
		} 
	    catch (Exception e1)
	    {
	    	e1.printStackTrace();
		} 
	}
	
	private void warnEmptyMail() {
		disposeFrame();
	    JOptionPane.showMessageDialog(null,"邮件内容或主题不能为空,点击确定返回重新输入", "邮件发送提示框",
				JOptionPane.CLOSED_OPTION);
	    setFrameVisible();
	}
	
	public void disposeFrame() {
		this.dispose();
	}
	
	public void setFrameVisible() {
		new SendmailFrame().setVisible(true);
	}
	  /**
	   * 功能: 邮件发送功能，邮件反馈.
	   * 作者: 王小明   时间: 2016-09-23
	   */
	public void sendmail() throws AddressException, MessagingException{
		Properties prop = initMailProperty();

		//使用JavaMail发送邮件的5个步骤
		Session session = Session.getInstance(prop);
	
		// 创建mime类型邮件 
		Message message = new MimeMessage(session);
	
		// 设置发信人
		message.setFrom(new InternetAddress(1169473174 + "@qq.com"));
		message.setRecipient(RecipientType.TO,new InternetAddress("1169473174@qq.com"));
	
		// 设置主题 
		message.setSubject(textField.getText());
	
		// 设置邮件内容
		message.setText(r);
		Transport trans = session.getTransport();
		session.setDebug(true);

		// 设置收件人们 
		trans.connect("smtp.qq.com", "1169473174","dqpaktkfcgwagabe");
		
		// 发送 
		trans.sendMessage(message, message.getAllRecipients());
	}
	
	private Properties initMailProperty() {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "smtp.qq.com");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.port", "587");
		prop.setProperty("mail.smtp.socketFactory.port", "587");
		return prop;
	}
	
}