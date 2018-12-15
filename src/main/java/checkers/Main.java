package checkers;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String players = JOptionPane.showInputDialog("ilosc graczy");
		new GameController(Integer.parseInt(players));
	}
}
