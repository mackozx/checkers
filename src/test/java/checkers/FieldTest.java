package checkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	private Field field;
	
	@Before
	public void createNewField() {
		field = new Field(4, 9);
	}
	
	@Test
	public void initFieldTest() {
		field.setinitValues(700 / 2, 10);
		assertEquals(10, field.getColCount());
		assertEquals(11, field.getLinePosition());
	}
	
	@Test
	public void selectWithoutBlockTest() {
		field.setBlock(false);
		field.setSelected(true);
		assertTrue(field.isSelected());
	}

	@Test
	public void unselectWithoutBlockTest() {
		field.setBlock(false);
		field.setSelected(true);
		field.setSelected(false);
		assertFalse(field.isSelected());
	}
	
	@Test
	public void selectWithBlockTest() {
		field.setBlock(true);
		field.setSelected(true);
		assertFalse(field.isSelected());
	}

	@Test
	public void unselectWithBlockTest() {
		field.setBlock(false);
		field.setSelected(true);
		field.setBlock(true);
		field.setSelected(false);
		assertFalse(field.isSelected());
	}
	
	@Test
	public void fieldToStringTest() {
		field.setinitValues(700 / 2, 10);
		String fieldAsString = field.toString();
		String expected = "4 9 10 11 312 361 false false false 0";
		assertEquals(expected, fieldAsString);
	}
	
	@Test
	public void stringToFieldTest() {
		field.setinitValues(700 / 2, 10);
		String fieldAsString = "4 9 10 11 312 361 false false false 0";
		Field fieldFromString = Field.buildFromString(fieldAsString);
		assertEquals(fieldFromString.toString(), fieldAsString);
		assertEquals(field.toString(), fieldAsString);
	}
}
