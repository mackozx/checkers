package checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerGUI extends JFrame implements ActionListener {

	private JButton newBot;
	private GameController con;
	
	public ServerGUI(GameController gc) {
		con = gc;
		newBot = new JButton("dodaj bota");
		newBot.addActionListener(this);
		add(newBot);
		setBounds(100, 100, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String botid = JOptionPane.showInputDialog("Id bota");
		con.addBot(Integer.parseInt(botid));
	}
}
