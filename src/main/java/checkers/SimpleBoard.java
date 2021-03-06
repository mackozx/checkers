package checkers;

import java.util.ArrayList;
import java.util.Random;
import checkers.Field;

public class SimpleBoard implements Board {

	private ArrayList<Field> fields;
	private int size = 700;
	private ArrayList<BoardCorner> corners;
	private int activePlayerId;
	private int players;
	private ArrayList<Integer> bots;
	private ArrayList<Integer> finished;
	
	public SimpleBoard(int playerSize) {
		activePlayerId = 0;
		players = playerSize;
		bots = new ArrayList<Integer>();
		finished = new ArrayList<Integer>();
		createFields();
		for(int i = 0; i < playerSize; i++) {
			for(Field field : corners.get(i).getCorner()) {
				field.addPiece(new Piece(i));
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
	
	public void addBot(int id) {
		bots.add(id);
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

	public void selectField(int xclick, int yclick, int id, boolean isBot) {
		if(xclick > 556 && xclick < 626 && yclick > 324 && yclick < 370 && id == activePlayerId) {
			moved();
			resetCanMove();
		}
		for(Field field : fields) {
			int n = Math.abs(field.getXCord() - xclick);
			int m = Math.abs(field.getYCord() - yclick);
			int k = (int) (Math.sqrt(((double) (n * n)) + ((double) (m * m))));
			if(k <= field.getRadius()) {
				if(!checkSelect()) {
					handleSelect(field, id);
					if(field.getPiece() != null) {
						if(field.getPiece().getOwnerId() == id) {
							handleCanMove(field, true);
						}
					}
				} else if(checkSelect()) {
			    	handleMove(field, isBot);
				}
			}
		}
	}
	
	protected void handleSelect(Field field, int id) {
		if(!field.isSelected() && field.getPiece() != null) {
			if(field.getPiece().getOwnerId() == id) {
				field.setSelected(true);
				blockAllFields(true);
			}
		} else {
			field.setSelected(false);
			blockAllFields(false);
		}
	}
	
	protected void handleMove(Field field, boolean isbot) {
		for(Field selectedField : fields) {
			if(selectedField.isSelected()) {
				if(selectedField != field && field.getPiece() == null) {
					if(field.getCanMove()) {
						field.addPiece(selectedField.getPiece());
						selectedField.removePiece();
						selectedField.setSelected(false);
						blockAllFields(false);
						setCanMoveAll(false);
						checkFinished();
						if(!isbot) {
							moved();
						}
					}
				} else {
					selectedField.setSelected(false);
					blockAllFields(false);
					setCanMoveAll(false);
				}
			}
		}
	}
	
	protected void checkFinished() {
		if(activePlayerId == 0) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId + 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		} else if(activePlayerId == 1) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId - 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		} else if(activePlayerId == 2) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId + 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		} else if(activePlayerId == 3) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId - 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		} else if(activePlayerId == 4) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId + 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		} else if(activePlayerId == 5) {
			int finishCounter = 0;
			for(Field field : corners.get(activePlayerId - 1).getCorner()) {
				if(field.getPiece() != null) {
					if(field.getPiece().getOwnerId() == activePlayerId) {
						finishCounter++;
					}
				}
			}
			if(finishCounter == 10) {
				finished.add(activePlayerId);
			}
		}
	}
	
	protected void addPlayerId() {
		if(activePlayerId < players - 1) {
			activePlayerId++;
		} else {
			activePlayerId = 0;
		}
		if(finished.size() == players) {
			
		} else if(finished.contains(activePlayerId)) {
			addPlayerId();
		}
	}
	
	protected void moved() {
		addPlayerId();
		if(bots.contains(activePlayerId)) {
			try {
				botTurn(activePlayerId);
			} catch(Exception e) {
				
			}
			moved();
		}
	}
	
	protected void botTurn(int id) {
		Random ran = new Random();
		Field botField;
		ArrayList<Field> withPiece = new ArrayList<Field>();
		for(Field field : fields) {
			if(field.getPiece() != null) {
				if(field.getPiece().getOwnerId() == id) {
					withPiece.add(field);
				}
			}
		}
		botField = withPiece.get(ran.nextInt(withPiece.size() - 1));
		selectField(botField.getXCord(), botField.getYCord(), activePlayerId, true);
		if(getCanMoveList().size() != 0) {
			Field toMove = getCanMoveList().get(ran.nextInt(getCanMoveList().size() - 1));
			selectField(toMove.getXCord(), toMove.getYCord(), activePlayerId, true);
		}
	}
	
	public int getActivePlayerId() {
		return activePlayerId;
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
	
	protected void resetCanMove() {
		for(Field fieldToMove : fields) {
			if(fieldToMove.getCanMove()) {
				fieldToMove.setCanMove(false);
			}
		}
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
					} else if(withPiece.getLinePosition() < field.getLinePosition() && !getIllegalNWFields().contains(withPiece)) {
						getFieldByLinePosition(withPiece.getLinePosition() - 1, withPiece.getColumn() - 1).setCanMove(true);
						int i = 0;
						while(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i, withPiece.getColumn() - 2 * i).getPiece() != null) {
							if(getIllegalNWFields().contains(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i, withPiece.getColumn() - 2 * i))) {
								break;
							}
							if(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() - 2 * i - 1).getPiece() != null) {
								break;
							}
							getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() - 2 * i - 1).setCanMove(true);
							if(getIllegalNWFields().contains(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() - 2 * i - 1))) {
								break;
							}
							i++;
						}
					}
				} else if(withPiece.getColumn() > field.getColumn() && withPiece.getColumn() != 16) {
					if(withPiece.getLinePosition() > field.getLinePosition() && !getIllegalSEFields().contains(withPiece)) {
						getFieldByLinePosition(withPiece.getLinePosition() + 1, withPiece.getColumn() + 1).setCanMove(true);
						int i = 0;
						while(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i, withPiece.getColumn() + 2 * i).getPiece() != null) {
							if(getIllegalSEFields().contains(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i, withPiece.getColumn() + 2 * i))) {
								break;
							}
							if(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() + 2 * i + 1).getPiece() != null) {
								break;
							}
							getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() + 2 * i + 1).setCanMove(true);
							if(getIllegalSEFields().contains(getFieldByLinePosition(withPiece.getLinePosition() + 2 * i + 1, withPiece.getColumn() + 2 * i + 1))) {
								break;
							}
							i++;
						}
					} else if(withPiece.getLinePosition() < field.getLinePosition() && !getIllegalSWFields().contains(withPiece)) {
						getFieldByLinePosition(withPiece.getLinePosition() - 1, withPiece.getColumn() + 1).setCanMove(true);
						int i = 0;
						while(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i, withPiece.getColumn() + 2 * i).getPiece() != null) {
							if(getIllegalSWFields().contains(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i, withPiece.getColumn() + 2 * i))) {
								break;
							}
							if(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() + 2 * i + 1).getPiece() != null) {
								break;
							}
							getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() + 2 * i + 1).setCanMove(true);
							if(getIllegalSWFields().contains(getFieldByLinePosition(withPiece.getLinePosition() - 2 * i - 1, withPiece.getColumn() + 2 * i + 1))) {
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
	
	protected ArrayList<Field> getIllegalNWFields() {
		ArrayList<Field> fieldsList = new ArrayList<Field>();
		fieldsList.add(getFieldByPosition(0, 4));
		fieldsList.add(getFieldByPosition(1, 4));
		fieldsList.add(getFieldByPosition(2, 4));
		fieldsList.add(getFieldByPosition(3, 4));
		fieldsList.add(getFieldByPosition(4, 4));
		fieldsList.add(getFieldByPosition(9, 4));
		fieldsList.add(getFieldByPosition(10, 4));
		fieldsList.add(getFieldByPosition(11, 4));
		fieldsList.add(getFieldByPosition(12, 4));
		fieldsList.add(getFieldByPosition(0, 1));
		fieldsList.add(getFieldByPosition(0, 2));
		fieldsList.add(getFieldByPosition(0, 3));
		fieldsList.add(getFieldByPosition(0, 9));
		fieldsList.add(getFieldByPosition(0, 10));
		fieldsList.add(getFieldByPosition(0, 11));
		fieldsList.add(getFieldByPosition(0, 12));
		return fieldsList;
	}
	
	protected ArrayList<Field> getIllegalSEFields() {
		ArrayList<Field> fieldsList = new ArrayList<Field>();
		fieldsList.add(getFieldByPosition(0, 12));
		fieldsList.add(getFieldByPosition(1, 12));
		fieldsList.add(getFieldByPosition(2, 12));
		fieldsList.add(getFieldByPosition(3, 12));
		fieldsList.add(getFieldByPosition(8, 12));
		fieldsList.add(getFieldByPosition(9, 12));
		fieldsList.add(getFieldByPosition(1, 15));
		fieldsList.add(getFieldByPosition(2, 14));
		fieldsList.add(getFieldByPosition(3, 13));
		fieldsList.add(getFieldByPosition(10, 12));
		fieldsList.add(getFieldByPosition(11, 12));
		fieldsList.add(getFieldByPosition(12, 12));
		fieldsList.add(getFieldByPosition(9, 7));
		fieldsList.add(getFieldByPosition(10, 6));
		fieldsList.add(getFieldByPosition(11, 5));
		fieldsList.add(getFieldByPosition(12, 4));
		return fieldsList;
	}
	
	protected ArrayList<Field> getIllegalSWFields() {
		ArrayList<Field> fieldsList = new ArrayList<Field>();
		fieldsList.add(getFieldByPosition(0, 12));
		fieldsList.add(getFieldByPosition(1, 12));
		fieldsList.add(getFieldByPosition(2, 12));
		fieldsList.add(getFieldByPosition(3, 12));
		fieldsList.add(getFieldByPosition(4, 12));
		fieldsList.add(getFieldByPosition(9, 12));
		fieldsList.add(getFieldByPosition(10, 12));
		fieldsList.add(getFieldByPosition(11, 12));
		fieldsList.add(getFieldByPosition(12, 12));
		fieldsList.add(getFieldByPosition(0, 13));
		fieldsList.add(getFieldByPosition(0, 14));
		fieldsList.add(getFieldByPosition(0, 15));
		fieldsList.add(getFieldByPosition(0, 4));
		fieldsList.add(getFieldByPosition(0, 5));
		fieldsList.add(getFieldByPosition(0, 6));
		fieldsList.add(getFieldByPosition(0, 7));
		return fieldsList;
	}
}
