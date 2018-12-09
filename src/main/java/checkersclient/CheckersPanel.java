package checkersclient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import checkers.Field;

import javax.swing.JPanel;

public class CheckersPanel extends JPanel implements MouseListener {

	private CheckersClient client;

	private ArrayList<Field> fieldslist;
	
	public CheckersPanel(CheckersClient c) {
		setBackground(Color.GRAY);
		addMouseListener(this);
		client = c;
		try {
			fieldslist = client.connect(0, 0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawBoard(g);
	}

	protected void drawBoard(Graphics g) {
		for(Field field : fieldslist) {
			field.draw(g);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		try {
			fieldslist = client.connect(x, y);
		} catch(Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
}
