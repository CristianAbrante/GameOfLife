package cell;

/**
 * Class that simulates a cell on the Game of Life.
 * <p>Each Cell on the Game Of life is represented by a  position
 * into a board, that could have two possible states, alive or dead.</p>
 * <p>Each state of the cell is recalculated in every single turn, 
 * and depends on the live state of the neighbor cells.</p>
 * 
 * <h3>Important methods</h3>
 * <p>updateState calculate new state depending on the state of the
 * neighbors</p>
 * 
 * @author 	Cristian Abrante Dorta
 * @company University of La Laguna
 * @date 	03-03-2018
 * @version 	1.0.0
 */
public class Cell {
	public CellState currentState; 	// Current state of the cell in this turn.
	public CellState previousState; 	// Previous state of the cell in this turn.
	
	/**
	 * Default constructor of the cell class.
	 * <p>
	 * Constructor sets the current state to DEAD. 
	 * Previous State is set to DEAD too.
	 */
	public Cell() {
		this(CellState.DEAD);
	}
	
	/**
	 * Constructor of the class with specification of current
	 * state. Previous State is set to DEAD too.
	 * 
	 * @param state current state of the cell.
	 */
	public Cell(CellState state) {
		currentState = state;
		previousState = CellState.DEAD;
	}
	
	/**
	 * Method that returns the current state of the cell.
	 * 
	 * @return current state of the cell.
	 */
	public CellState getCurrentState() {
		return currentState;
	}
	
	/**
	 * Method that return the previous state of the cell.
	 * 
	 * @return previous state of the cell.
	 */
	public CellState getPreviousState() {
		return previousState;
	}
}
