package checkers;

import java.util.ArrayList;

public class BoardCorner {

	private ArrayList<Field> fields;
	
	private SimpleBoard board;
	
	public BoardCorner(int i, SimpleBoard b) {
		this.board = b;
		fields = new ArrayList<Field>();
		if(i == 0) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(l, c));
    			}
    		}
    	} else if(i == 2) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(l, 7 - c));
    			}
    		}
    	} else if(i == 4) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(9 + l, 7 - c));
    			}
    		}
    	} else if(i == 5) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(l, 9 + c));
    			}
    		}
    	} else if(i == 3) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(9 + l, 9 + c));
    			}
    		}
    	} else if(i == 1) {
    		for(int c = 0; c <= 3; c++) {
    			for(int l = 0; l <= c; l++) {
    				fields.add(board.getFieldByPosition(l, 16 - c));
    			}
    		}
    	}
	}
	
	public ArrayList<Field> getCorner() {
		return fields;
	}
}
