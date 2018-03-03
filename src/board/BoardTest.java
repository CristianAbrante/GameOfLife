package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		String expectedString = "***\n*O*\n***";
		assertTrue(expectedString == fileTestBoard.toString());
	}
}
