package checkers;

import java.awt.Color;

public class Piece {

	private int ownerId;
	
	private Color ownerColor;
	
	public Piece(int id) {
		ownerId =  id;
		if(id == 0) {
			ownerColor = Color.RED;
		} else if(id == 1) {
			ownerColor = Color.YELLOW;
		} else if(id == 2) {
			ownerColor = Color.PINK;
		} else if(id == 3) {
			ownerColor = Color.GREEN;
		} else if(id == 4) {
			ownerColor = Color.ORANGE;
		} else if(id == 5) {
			ownerColor = Color.MAGENTA;
		}
	}
	
	public Color getColor() {
		return ownerColor;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	
}
