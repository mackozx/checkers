package checkers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckersTest {

	private Board board;
	
	@Before
	public void createNewBoard() {
		board = new SimpleBoard(6);
	}
	
	@Test
	public void boardFieldsNumberTest() {
		int expected = 121;
		int actual = board.getFields().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getFieldByPositionTest() {
		Field expected = new Field(10, 10);
		Field actual = board.getFieldByPosition(10, 10);
		assertEquals(expected.getLine(), actual.getLine());
		assertEquals(expected.getColumn(), actual.getColumn());
	}
	
	@Test
	public void getFieldByLinePositionTest() {
		Field expected = new Field(3, 3);
		expected.setinitValues(board.getSize() / 2, 4);
		Field actual = board.getFieldByLinePosition(15, 3);
		assertEquals(expected.getLine(), actual.getLine());
		assertEquals(expected.getColumn(), actual.getColumn());
	}
}
