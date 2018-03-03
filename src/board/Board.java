/**
 * @author cristian
 * @date 3 mar. 2018
 */
package board;

import java.io.*;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import cell.*;

/**
 * Board Class represents the board in the Game of Life.
 * <p>
 * Game of Life was developed by American mathematician
 * John Conway. It is a cellular automaton that evolves 
 * turn by turn. Cells has special conditions to evolve
 * adn to change their current state.
 * <p>
 * Game of life has the same computational power as the 
 * Turing machine, so all algorithms could be computed 
 * in the Game of Life.
 * 
 * @author	Cristian Abrante Dorta
 * @company	University of La Laguna
 * @date 	3 mar. 2018
 * @version 	1.0.0
 */
public class Board {
	// Attribute used to store the cells.
	private ArrayList<ArrayList<Cell>> matrixOfCells;
	
	/**
	 * Default constructor of the class. It creates
	 * and empty matrix of cells.
	 */
	public Board() {
		matrixOfCells = new ArrayList<ArrayList<Cell>>();
	}
	
	/**
	 * Constructor of the class. It reads and parse the 
	 * specified file to create the cell board.
	 * <p>
	 * The configuration file is as it follows:
	 * - int that specifies the number of rows.
	 * - int that specifies the number of columns.
	 * 
	 * matrix with characters, * is used to put a dead cell,
	 * and O is used to create an alive cell.
	 * <p>
	 * Example:
	 * 3
	 * 5
	 * **O**
	 * *OOO*
	 * **O**
	 * 
	 * @param boardFilePath	path to the configuration file.
	 * 	
	 * @throws IOException				If something happens opening the file.
	 * @throws IllegalArgumentException	If there is any problem with matrix 
	 * 									characters or length.
	 * @throws NumberFormatException		If number of columns or row is not valid.
	 */
	public Board(String boardFilePath) 
	throws IOException, IllegalArgumentException, NumberFormatException {
		this();
		BufferedReader boardFile = new BufferedReader(new FileReader(boardFilePath));
		
		// read number of lines.
		int numberOfLines = Integer.parseInt(boardFile.readLine());
		// read number of columns.
		int numberOfColumns = Integer.parseInt(boardFile.readLine());
		
		int currentLine = 0;
		String lineOfCells;
		while((lineOfCells = boardFile.readLine()) != null) {
			lineOfCells = lineOfCells.replaceAll("\\s", "");
			if (lineOfCells.length() != numberOfColumns) {
				throw new IllegalArgumentException("number of symbols should be equal as specified column size.");
			}
			
			matrixOfCells.add(new ArrayList<Cell>());
			for (int i = 0; i < lineOfCells.length(); ++i) {
				String cellStateSymbol = Character.toString(lineOfCells.charAt(i));
				CellState state = CellState.of(cellStateSymbol);
				
				if (state != null) {
					matrixOfCells.get(currentLine).add(new Cell(state));
				} else {
					throw new IllegalArgumentException(cellStateSymbol + " is not a valid cell state symbol.");
				}
			}
			currentLine++;
		}
		if (currentLine != numberOfLines) {
			throw new IllegalArgumentException("number of lines should be equal as specified lines.");
		}
	}
	
	/**
	 * Method that converts the current state of the board into String.
	 * 
	 * @return String that represents the board.
	 */
	public String toString() {
		String boardString = "";
		for (ArrayList<Cell> cellRow : matrixOfCells) {
			for (Cell cell : cellRow) {
				boardString += cell.toString();
			}
			boardString += '\n';
		}
		return boardString;
	}
}
