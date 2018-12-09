package checkers;

import java.util.ArrayList;

import checkers.Field;

public class SimpleBoard implements Board {

	private ArrayList<Field> fields;
	private int size = 700;
	private ArrayList<BoardCorner> corners;
	
	public SimpleBoard() {
		createFields();
		for(int i = 0; i < 2; i++) {
			for(Field field : corners.get(i).getCorner()) {
				field.addPiece(new Piece());
			}
		}
	}
	
	public void createFields() {
		fields = new ArrayList<Field>();
		for(int i = 0; i < 17; i++) {
			if(i < 4 || (i >= 9 && i < 13)) {
				for(int j = 0; j < i + 1; j++) {
					fields.add(new Field(j, i));
				}
			} else if((i >= 4 && i < 9) || (i >= 13)) {
				for(int j = 0; j < 17 - i; j++) {
					fields.add(new Field(j, i));
				}
			}
		}
		corners = new ArrayList<BoardCorner>();
		for(int i = 0; i < 6; i++) {
			corners.add(new BoardCorner(i, this));
		}
		initFieldValues();
	}
	
	public void initFieldValues() {
		for(Field field : fields) {
			int ccount = 0;
			for(Field cfield : fields) {
				if(field.getColumn() == cfield.getColumn()) {
					ccount ++;
				}
			}
			field.setinitValues(size / 2, ccount);
		}
	}
	
	public void onStart() {
	
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}

    public Field getFieldByPosition(int line, int column) {
    	for(Field field : fields) {
    		if(field.getLine() == line && field.getColumn() == column) {
                return field;
            }
        }
        return null;
    }
    
    public Field getFieldByLinePosition(int linePosition, int column) {
    	for(Field field : fields) {
    		if(field.getLinePosition() == linePosition && field.getColumn() == column) {
    			return field;
    		}
    	}
    	return null;
    }

	public int getSize() {
		return size;
	}

	public void selectField(int xclick, int yclick) {
		for(Field field : fields) {
			int n = Math.abs(field.getXCord() - xclick);
			int m = Math.abs(field.getYCord() - yclick);
			int k = (int) (Math.sqrt(((double) (n * n)) + ((double) (m * m))));
			if(k <= field.getRadius()) {
				if(!checkSelect()) {
					handleSelect(field);
					handleCanMove(field, true);
				} else if(checkSelect()) {
			    	handleMove(field);
				}
			}
		}
	}
	
	protected void handleSelect(Field field) {
		if(!field.isSelected() && field.getPiece() != null) {
			field.setSelected(true);
			blockAllFields(true);
		} else {
			field.setSelected(false);
			blockAllFields(false);
		}
	}
	
	protected void handleMove(Field field) {
		for(Field selectedField : fields) {
			if(selectedField.isSelected()) {
				if(selectedField != field && field.getPiece() == null) {
					if(field.getCanMove()) {
						field.addPiece(selectedField.getPiece());
						selectedField.removePiece();
						selectedField.setSelected(false);
						blockAllFields(false);
						setCanMoveAll(false);
					}
				} else {
					selectedField.setSelected(false);
					blockAllFields(false);
					setCanMoveAll(false);
				}
			}
		}
	}
	
	protected void canMoveToNext(Field field, boolean b) {	
		for(Field fieldToMove : getNextList(field)) {
			fieldToMove.setCanMove(b);
		}
	}
	
	protected ArrayList<Field> getNextList(Field field) {
		ArrayList<Field> nextFieldsList = new ArrayList<Field>();
		for(Field fieldToMove : fields) {
			boolean checkCols = Math.abs(fieldToMove.getColumn() - field.getColumn()) == 1;
			boolean checkLine = Math.abs(fieldToMove.getLinePosition() - field.getLinePosition()) == 1;
			boolean checkCCols = fieldToMove.getColumn() == field.getColumn();
			boolean checkCLines = Math.abs(field.getLinePosition() - fieldToMove.getLinePosition()) == 2;
			if((checkCols && checkLine) || (checkCCols && checkCLines)) {
				nextFieldsList.add(fieldToMove);
			}
		}
		return nextFieldsList;
	}
	
	protected ArrayList<Field> getCanMoveList() {
		ArrayList<Field> canMoveFieldsList = new ArrayList<Field>();
		for(Field fieldToMove : fields) {
			if(fieldToMove.getCanMove()) {
				canMoveFieldsList.add(fieldToMove);
			}
		}
		return canMoveFieldsList;
	}
	
	protected void handleCanMove(Field field, boolean b) {
		if(field.getPiece() != null) {
			ArrayList<Field> nextFieldsWithPiece = new ArrayList<Field>();
			for(Field toMove : getNextList(field)) {
				if(toMove != field && toMove.getPiece() == null) {
					toMove.setCanMove(true);
				} else if(toMove != field && toMove.getPiece() != null) {
					nextFieldsWithPiece.add(toMove);
				}
			}

			for(Field withPiece : nextFieldsWithPiece) {
				if(withPiece.getColumn() == field.getColumn() && withPiece.getLine() != 0 && withPiece.getLine() != withPiece.getColCount() - 1) {
					if(withPiece.getLine() > field.getLine()) {
						if(getFieldByPosition(withPiece.getLine() + 1, withPiece.getColumn()).getPiece() == null) {
							getFieldByPosition(withPiece.getLine() + 1, withPiece.getColumn()).setCanMove(true);
							int i = 0;
							while(getFieldByPosition(withPiece.getLine() + 2 * i, withPiece.getColumn()).getPiece() != null) {
								if(getFieldByPosition(withPiece.getLine() + 2 * i, withPiece.getColumn()).getLine() < (getFieldByPosition(withPiece.getLine() + 2 * i, withPiece.getColumn()).getColCount()) - 2) {
									getFieldByPosition(withPiece.getLine() + 2 * i + 1, withPiece.getColumn()).setCanMove(true);
									i++;
								} else {
									getFieldByPosition(withPiece.getColCount() - 1, withPiece.getColumn()).setCanMove(true);
									break;
								}
							}
						}
					} else {
						if(getFieldByPosition(withPiece.getLine() - 1, withPiece.getColumn()).getPiece() == null) {
							getFieldByPosition(withPiece.getLine() - 1, withPiece.getColumn()).setCanMove(true);
							int i = 0;
							while(getFieldByPosition(withPiece.getLine() + 2 * i, withPiece.getColumn()).getPiece() != null) {
								if(getFieldByPosition(withPiece.getLine() + 2 * i, withPiece.getColumn()).getLine() > 1) {
									getFieldByPosition(withPiece.getLine() + 2 * i - 1, withPiece.getColumn()).setCanMove(true);
									i--;
								} else {
									getFieldByPosition(0, withPiece.getColumn()).setCanMove(true);
									break;
								}
							}
						}
					}
				} else if(withPiece.getColumn() < field.getColumn() && withPiece.getColumn() != 0) {
					if(withPiece.getLinePosition() > field.getLinePosition() && !getIllegalNEFields().contains(withPiece)) {
						getFieldByLinePosition(withPiece.getLinePosition() + 1, withPiece.getColumn() - 1).setCanMove(true);
						int i = 0;
						while(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i, withPiece.getColumn() - 2 * i).getPiece() != null) {
							if(getIllegalNEFields().contains(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i, withPiece.getColumn() - 2 * i))) {
								break;
							}
							if(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() - 2 * i - 1).getPiece() != null) {
								break;
							}
							getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() - 2 * i - 1).setCanMove(true);
							if(getIllegalNEFields().contains(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() - 2 * i - 1))) {
								break;
							}
							i++;
						}
					}
				}
			}
			for(Field fieldToMove : fields) {
				if(fieldToMove.getPiece() != null && fieldToMove.getCanMove()) {
					fieldToMove.setCanMove(false);
				}
			}
		}
	}
	
	protected void blockAllFields(boolean block) {
		for(Field field: fields) {
			field.setBlock(block);
		}
	}
	
	protected void setCanMoveAll(boolean b) {
		for(Field field: fields) {
			field.setCanMove(b);
		}
	}
	
	protected boolean checkSelect() {
		for(Field field : fields) {
			if(field.isSelected()) {
				return true;
			}
		}
		return false;
	}
	
	protected ArrayList<Field> getIllegalNEFields() {
		ArrayList<Field> fieldsList = new ArrayList<Field>();
		fieldsList.add(getFieldByPosition(0, 4));
		fieldsList.add(getFieldByPosition(1, 4));
		fieldsList.add(getFieldByPosition(2, 4));
		fieldsList.add(getFieldByPosition(3, 4));
		fieldsList.add(getFieldByPosition(8, 4));
		fieldsList.add(getFieldByPosition(9, 4));
		fieldsList.add(getFieldByPosition(10, 4));
		fieldsList.add(getFieldByPosition(11, 4));
		fieldsList.add(getFieldByPosition(12, 4));
		fieldsList.add(getFieldByPosition(1, 1));
		fieldsList.add(getFieldByPosition(2, 2));
		fieldsList.add(getFieldByPosition(3, 3));
		fieldsList.add(getFieldByPosition(9, 9));
		fieldsList.add(getFieldByPosition(10, 10));
		fieldsList.add(getFieldByPosition(11, 11));
		fieldsList.add(getFieldByPosition(12, 12));
		return fieldsList;
	}
}
