package cell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class cellTest {
	
	Cell emptyTestCell;
	Cell aliveTestCell;
	Cell deadTestCell;
	
	@Before
	public void setUp() throws Exception {
		// Creation of an empty cell.
		emptyTestCell = new Cell();

		// Creation of an alive cell.
		aliveTestCell = new Cell(CellState.ALIVE);
		
		// Creation of a dead cell.
		deadTestCell = new Cell(CellState.DEAD);
	}
	
	@Test
	public void cellStateTest() {
		assertTrue(CellState.ALIVE.toString().equals("alive"));
		assertTrue(CellState.ALIVE.toBool());
		
		assertTrue(CellState.DEAD.toString().equals("dead"));
		assertFalse(CellState.DEAD.toBool());
	}
	
	@Test
	public void currentStateTest() {
		assertTrue(emptyTestCell.getCurrentState() == CellState.DEAD);
		assertTrue(aliveTestCell.getCurrentState() == CellState.ALIVE);
		assertTrue(deadTestCell.getCurrentState() == CellState.DEAD);
	}
	
	@Test
	public void previousStateTest() {
		assertTrue(emptyTestCell.getPreviousState() == CellState.DEAD);
		assertTrue(aliveTestCell.getPreviousState() == CellState.DEAD);
		assertTrue(deadTestCell.getPreviousState() == CellState.DEAD);
	}
	
	@Test
	public void toStringTest() {
		assertTrue(aliveTestCell.toString() == "O");
		assertTrue(deadTestCell.toString() == "*");
	}
}
