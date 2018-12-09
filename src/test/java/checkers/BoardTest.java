package checkers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;
	
	@Before
	public void createNewBoard() {
		board = new SimpleBoard();
	}
	
	@Test
	public void boardCornerTest() {
		BoardCorner bc = new BoardCorner(0, (SimpleBoard) board);
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(0, 0)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(0, 1)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(1, 1)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(0, 2)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(1, 2)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(2, 2)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(0, 3)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(1, 3)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(2, 3)));
		assertTrue(bc.getCorner().contains(board.getFieldByPosition(3, 3)));
	}
}
