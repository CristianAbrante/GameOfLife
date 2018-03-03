package cell;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class cellTest {

	@Before
	public void setUp() throws Exception {
		// Creation of an empty cell.
		Cell emptyTestCell = new Cell();

		// Creation of an alive cell.
		Cell aliveTestCell = new Cell(ALIVE);
		
		// Creation of a dead cell.
		Cell deadTestCell = new Cell(DEAD);
	}

	@Test
	public void currentStateTest() {
		assertEqual(emptyTestCell.getCurrentState(), DEAD);
		assertEqual(aliveTestCell.getCurrentState(), ALIVE);
		assertEqual(deadTestCell.getCurrentState(), DEAD);
	}
	
	@Test
	public void previousStateTest() {
		assertEqual(emptyTestCell.getPreviousState(), DEAD);
		assertEqual(aliveTestCell.getPreviousState(), DEAD);
		assertEqual(deadTestCell.getPreviousState(), DEAD);
	}
}
