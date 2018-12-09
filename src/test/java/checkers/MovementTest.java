package checkers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MovementTest {

	private SimpleBoard board;
	
	@Before
	public void createNewBoard() {
		board = new SimpleBoard();
	}
	
	@Test
	public void selectionWithoutPieceTest() {
		Field field = board.getFields().get(55);
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		assertFalse(field.isSelected());
	}
	
	@Test
	public void SelectionWithPieceTest() {
		Field field = board.getFields().get(55);
		field.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		assertTrue(field.isSelected());
	}
	
	@Test
	public void simpleMovementTest() {
		Field field = board.getFieldByPosition(4, 9);
		field.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		assertTrue(board.getFieldByPosition(3, 9).getCanMove());
		assertTrue(board.getFieldByPosition(5, 9).getCanMove());
		assertTrue(board.getFieldByPosition(3, 8).getCanMove());
		assertTrue(board.getFieldByPosition(4, 8).getCanMove());
		assertTrue(board.getFieldByPosition(4, 10).getCanMove());
		assertTrue(board.getFieldByPosition(5, 10).getCanMove());
	}
	
	@Test
	public void simpleMovementWithPieceTest() {
		Field field = board.getFieldByPosition(4, 9);
		Field fieldWithPiece = board.getFieldByPosition(5, 9);
		field.addPiece(new Piece());
		fieldWithPiece.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		assertTrue(board.getFieldByPosition(3, 9).getCanMove());
		assertFalse(board.getFieldByPosition(5, 9).getCanMove());
		assertTrue(board.getFieldByPosition(3, 8).getCanMove());
		assertTrue(board.getFieldByPosition(4, 8).getCanMove());
		assertTrue(board.getFieldByPosition(4, 10).getCanMove());
		assertTrue(board.getFieldByPosition(5, 10).getCanMove());
	}
	
	@Test
	public void movementWithSimpleJumpTest() {
		Field field = board.getFieldByPosition(4, 9);
		Field fieldWithPiece = board.getFieldByPosition(5, 9);
		field.addPiece(new Piece());
		fieldWithPiece.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		assertTrue(board.getFieldByPosition(6, 9).getCanMove());
	}
	
	@Test
	public void movementWithComplexJumpTest() {
		Field field = board.getFieldByPosition(10, 11);
		Field fieldWithPiece1 = board.getFieldByPosition(9, 10);
		Field fieldWithPiece2 = board.getFieldByPosition(7, 8);
		Field fieldWithPiece3 = board.getFieldByPosition(7, 6);
		
		field.addPiece(new Piece());
		fieldWithPiece1.addPiece(new Piece());
		fieldWithPiece2.addPiece(new Piece());
		fieldWithPiece3.addPiece(new Piece());
		
		int x = field.getXCord();
		int y = field.getYCord();
		board.selectField(x, y);
		
		assertTrue(board.getFieldByPosition(8, 9).getCanMove());
		assertTrue(board.getFieldByPosition(7, 7).getCanMove());
		assertTrue(board.getFieldByPosition(7, 5).getCanMove());
	}
	
	@Test
	public void movePieceTest() {
		Field field = board.getFieldByPosition(4, 9);
		Field fieldToMove = board.getFieldByPosition(5, 9);
		
		field.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		int x2 = fieldToMove.getXCord();
		int y2 = fieldToMove.getYCord();
		
		board.selectField(x, y);
		board.selectField(x2, y2);
		
		assertTrue(field.getPiece() == null);
		assertFalse(fieldToMove.getPiece() == null);
	}
	
	@Test
	public void movePieceWithJumpTest() {
		Field field = board.getFieldByPosition(2, 9);
		Field fieldWithPiece = board.getFieldByPosition(3, 9);
		Field fieldToMove = board.getFieldByPosition(4, 9);
		
		field.addPiece(new Piece());
		fieldWithPiece.addPiece(new Piece());
		int x = field.getXCord();
		int y = field.getYCord();
		int x2 = fieldToMove.getXCord();
		int y2 = fieldToMove.getYCord();
		
		board.selectField(x, y);
		board.selectField(x2, y2);
		
		assertTrue(field.getPiece() == null);
		assertFalse(fieldToMove.getPiece() == null);
	}
}
