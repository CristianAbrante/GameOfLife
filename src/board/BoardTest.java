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
		assertTrue(fileTestBoard.getCell(1, 1).getCurrentState() == CellState.ALIVE);
		assertTrue(fileTestBoard.getCell(0, 0).getCurrentState() == CellState.DEAD);
		assertNull(fileTestBoard.getCell(-2, -4));
		assertNull(fileTestBoard.getCell(28, 45));
	}
	
	@Test
	public void saveBoardTest() throws FileNotFoundException {
		fileTestBoard.saveAt("src/test/out1.txt");
	}
	
	@Test
	public void updateBoardTest() throws NumberFormatException, IllegalArgumentException, IOException {
		Board testBoard2 = new Board("src/test/board2.txt");
		String expectedBoard = 	"*******\n" + 
							   	"*OO****\n" + 
								"*O*O***\n" + 
								"*******\n" + 
								"***O*O*\n" + 
								"****OO*\n" + 
								"*******\n";
		testBoard2.updateBoard();
		assertTrue(expectedBoard.equals(testBoard2.toString()));
		
		expectedBoard = 	"*******\n" + 
					   	"*OO****\n" + 
						"*O*****\n" + 
						"**O*O**\n" + 
						"*****O*\n" + 
						"****OO*\n" + 
						"*******\n";
		testBoard2.updateBoard();
		assertTrue(expectedBoard.equals(testBoard2.toString()));
		
		Board testBoard3 = new Board("src/test/board3.txt");
		String expectedBoard2 =	"****O*****O****\n" + 
								"****O*****O****\n" + 
								"****OO***OO****\n" + 
								"***************\n" + 
								"OOO**OO*OO**OOO\n" + 
								"**O*O*O*O*O*O**\n" + 
								"****OO***OO****\n" + 
								"***************\n" + 
								"****OO***OO****\n" + 
								"**O*O*O*O*O*O**\n" + 
								"OOO**OO*OO**OOO\n" + 
								"***************\n" + 
								"****OO***OO****\n" + 
								"****O*****O****\n" + 
								"****O*****O****\n";
		testBoard3.updateBoard();
		System.out.println(testBoard3);
		assertTrue(expectedBoard2.equals(testBoard3.toString()));
	}
}
