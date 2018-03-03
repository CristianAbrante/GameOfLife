package board;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cell.CellState;

public class BoardTest {
	
	Board emptyTestBoard;
	Board fileTestBoard;
	
	@Before
	public void setUp() throws Exception {
		emptyTestBoard = new Board();
		fileTestBoard = new Board("src/test/board1.txt");
	}
	
	@Test
	public void toStringTest() {
		String expectedString = "***\n*O*\n***\n";
		assertTrue(expectedString.equals(fileTestBoard.toString()));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void errorFileTest() throws IllegalArgumentException, IOException {
		new Board("fail file");
	}
	
	// This file contains no number of rows and columns.
	@Test(expected = NumberFormatException.class)
	public void errorFileConfigTest() throws NumberFormatException, IOException {
		new Board("src/test/fail1.txt");
	}
	
	// This file contains an erroneous character.
	@Test(expected = IllegalArgumentException.class)
	public void errorCharFileConfigTest() throws NumberFormatException, IOException {
		new Board("src/test/fail2.txt");
	}
	
	// This file contains fewer columns as expected.
	@Test(expected = IllegalArgumentException.class)
	public void errorColumnsFileConfigTest() throws NumberFormatException, IOException {
		new Board("src/test/fail3.txt");
	}
	
	// This file contains fewer rows as expected.
	@Test(expected = IllegalArgumentException.class)
	public void errorRowsFileConfigTest() throws NumberFormatException, IOException {
		new Board("src/test/fail4.txt");
	}
	
	@Test
	public void cellGetterTest() {
		assertEquals(fileTestBoard.getCell(1, 1).getCurrentState() == CellState.ALIVE);
		assertEquals(fileTestBoard.getCell(0, 0).getCurrentState() == CellState.DEAD);
		assertNull(fileTestBoard.getCell(-2, -4));
	}
	
	@Test
	public void saveBoardTest() {
		fileTestBoard.saveAt("src/test/out1.txt");
	}
}
