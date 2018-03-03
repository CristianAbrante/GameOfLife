package cell;

/**
 * Enum used to represent all possible states of
 * a Cell in the Game of Live.
 * <p>It provides a couple of methods to convert enum into 
 * String and into boolean</p> 
 * 
 * @author	Cristian Abrante Dorta
 * @date 	3 mar. 2018
 * @version 	1.0.0
 */
public enum CellState {
	ALIVE,	// Cell is alive.
	DEAD;	// Cell is dead.
	
	/**
	 * Method that converts the enum value into a String.
	 * 
	 * @return 	the representation of the enum value 
	 * 			as a string.
	 */
	public String toString() {
		return (this == ALIVE)? "O" : "*";
	}
	
	/**
	 * Method that converts the enum value into a boolean.
	 * 
	 * @return 	the representation of the enum value 
	 * 			as a boolean. If cell is alive true is returned,
	 * 			and if cell is dead, false is returned.
	 */
	public boolean toBool() {
		return (this == ALIVE)? true : false;
	}
	
	/**
	 * static methods that returns the cell state given a representation string.
	 * 
	 * @param 	cellStateString could be "O" if cell is alive and "*" otherwise.
	 * @return					ALIVE if string was "O", DEAD if it was "*" and
	 * 							null otherwise.
	 */
	public static CellState of(String cellStateString) {
		for (CellState state : CellState.values()) {
			if (cellStateString.equals(state.toString())) {
				return state;
			}
		}
		return null;
	}
}
