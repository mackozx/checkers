package checkersclient;

import javax.swing.JFrame;

public class CheckersFrame extends JFrame {

	private CheckersPanel gamePanel;

	public CheckersFrame(CheckersClient c) {
		setBounds(500, 50, 700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel = new CheckersPanel(c);
		add(gamePanel);
		setVisible(true);
	}
	
	public CheckersPanel getPanel() {
		return gamePanel;
	}
}
