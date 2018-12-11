package checkers;

import java.awt.Color;
import java.awt.Graphics;

public class Field implements CheckersField {

	private int radius = 19;

	private Piece piece;
	
	private int line;
	private int column;
	private int colCount;
	private int linePosition;
	
	private int xcord;
	private int ycord;
	
	private boolean selected = false;
	private boolean selectorBlock = false;
	private boolean canMove = false;
	
	public Field(int l, int c) {
		line = l;
		column = c;
	}
	
	public void setinitValues(int halfbsize, int colCount) {
		int x = halfbsize - (colCount * radius) + (line * radius * 2);
		int y = column * radius * 2 + radius;
		this.colCount = colCount;
		setLinePosition();
		xcord = x;
		ycord = y;
	}

	public void draw(Graphics g) {
		if(!selected) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.CYAN);
		}
		
		if(canMove) {
			g.setColor(Color.BLUE);
		}
		
		g.fillOval(xcord - radius, ycord - radius, 2 * radius, 2 * radius);
		if(piece != null) {
			g.setColor(piece.getColor());
			g.fillOval(xcord - (radius - 10), ycord - (radius - 10), 2 * (radius - 10), 2 * (radius - 10));
		}
	}
	
	public String toString() {
		String tostring = "";
		
		tostring += String.valueOf(line) + " ";
		tostring += String.valueOf(column) + " ";
		tostring += String.valueOf(colCount) + " ";
		tostring += String.valueOf(linePosition) + " ";
		tostring += String.valueOf(xcord) + " ";
		tostring += String.valueOf(ycord) + " ";
		tostring += String.valueOf(selected) + " ";
		tostring += String.valueOf(selectorBlock) + " ";
		tostring += String.valueOf(canMove) + " ";
		if(piece != null) {
			tostring += String.valueOf(piece.getOwnerId());
		} else {
			tostring += "6";
		}
						
		return tostring;
	}
	
	public static Field buildFromString(String s) {
		String[] splited = s.split("\\s+");
		Field field = new Field(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
		field.setColCount(Integer.parseInt(splited[2]));
		field.setLinePosition(Integer.parseInt(splited[3]));
		field.setXCord(Integer.parseInt(splited[4]));
		field.setYCord(Integer.parseInt(splited[5]));
		field.setSelected(Boolean.valueOf(splited[6]));
		field.setBlock(Boolean.valueOf(splited[7]));
		field.setCanMove(Boolean.valueOf(splited[8]));
		if(Integer.valueOf(splited[9]) != 6) {
			field.addPiece(new Piece(Integer.parseInt(splited[9])));
		}
		
		return field;
	}
	
	public void setSelected(boolean b) {
		if(b) {
			if(!selectorBlock) {
				selected = b;
			}
		} else {
			selected = b;
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setBlock(boolean block) {
		selectorBlock = block;
	}
	
	public void addPiece(Piece p) {
		piece = p;
	}
	
	public void removePiece() {
		piece = null;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getXCord() {
		return xcord;
	}
	
	public int getYCord() {
		return ycord;
	}
	
	public int getLinePosition() {
		return linePosition;
	}
	
	public boolean getCanMove() {
		return canMove;
	}
	
	public void setCanMove(boolean b) {
		canMove = b;
	}
	
	private void setLinePosition() {
		if(colCount % 2 == 1) {
			linePosition = line * 2 + (13 - colCount);
		} else {
			linePosition = line * 2 + (13 - colCount);
		}
	}
	
	public void setColCount(int c) {
		colCount = c;
	}

	public void setLinePosition(int l) {
		linePosition = l;
	}
	
	public void setXCord(int x) {
		xcord = x;
	}
	
	public void setYCord(int y) {
		ycord = y;
	}
	
	public int getColCount() {
		return colCount;
	}
}
