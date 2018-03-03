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
	private CellState currentState; 	// Current state of the cell in this turn.
	private CellState previousState; 	// Previous state of the cell in this turn.
	
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
	 * @see 		CellState
	 */
	public Cell(CellState state) {
		currentState = state;
		previousState = CellState.DEAD;
	}
	
	/**
	 * Method that returns the current state of the cell.
	 * 
	 * @return  current state of the cell.
	 * @see 		CellState
	 */
	public CellState getCurrentState() {
		return currentState;
	}
	
	/**
	 * Method that return the previous state of the cell.
	 * 
	 * @return previous state of the cell.
	 * @see 		CellState
	 */
	public CellState getPreviousState() {
		return previousState;
	}
	
	/**
	 * Method used to convert cell into String.
	 * 
	 * @return "O" if cell is alive and "*" otherwise.
	 */
	public String toString() {
		return currentState.toString();
	}
	
	/**
	 * Method that updates the current state of the cell, given a
	 * certain number of neighbours of the cell.
	 * <p>
	 * The rules for changing the state are:
	 * <ul>
	 * <li>Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.</li>
	 * <li>Any live cell with two or three live neighbours lives on to the next generation.</li>
	 * <li>Any live cell with more than three live neighbours dies, as if by overpopulation.</li>
	 * <li>Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.</li>
	 * </ul>
	 * 
	 * @param numberOfAliveNeighbours
	 */
	public void updateState(int numberOfAliveNeighbours) {
		previousState = currentState;
		if (currentState == CellState.ALIVE) {
			if (numberOfAliveNeighbours < 2 || numberOfAliveNeighbours > 3) {
				currentState = CellState.DEAD;
			}
		} else {
			if (numberOfAliveNeighbours == 3) {
				currentState = CellState.ALIVE;
			}
		}
	}
}
