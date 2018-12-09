package checkers;

import java.awt.Graphics;

public interface CheckersField {

	void draw(Graphics g);
	
	void setSelected(boolean b);
	
	boolean isSelected();
	
	void setBlock(boolean b);
	
	void addPiece(Piece p);
	
	Piece getPiece();
	
	void removePiece();
}
