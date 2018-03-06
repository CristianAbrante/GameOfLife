/**
 * @author cristian
 * @date 3 mar. 2018
 */
package board;

import java.io.*;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
		boardString += '\n';
		return boardString;
	}
	
	/**
	 * Method that returns the number of rows of the board.
	 * 
	 * @return	number of rows of the board.
	 */
	public int numberOfRows() {
		return matrixOfCells.size();
	}
	
	/**
	 * Method that returns the number of columns of the board.
	 * 
	 * @return	number of rows of the board.
	 */
	public int numberOfColumns() {
		return matrixOfCells.get(0).size();
	}
	
	/**
	 * Method that returns a certain cell specified by a position.
	 * 
	 * @param numberOfRow	row position from 0 to rows size - 1.
	 * @param numberOfColumn	column position form 0 to column size -1.
	 * @return 				Specified cell and null if position is not valid.
	 */
	public Cell getCell(int numberOfRow, int numberOfColumn) {
		if (numberOfRow < 0 || numberOfRow >= this.numberOfRows()) {
			return null;
		}
		if (numberOfColumn < 0 || numberOfColumn >= this.numberOfColumns()) {
			return null;
		}
		return matrixOfCells.get(numberOfRow).get(numberOfColumn);
	}
	
	/**
	 * Method that save current board into the specified file.
	 * It used the file configuration of the board.
	 * 
	 * @param outFilePath	file path where the board is going to be stored.
	 * 
	 * @throws FileNotFoundException If can't open the file.
	 */
	public void saveAt(String outFilePath) throws FileNotFoundException {
		PrintWriter outWriter = new PrintWriter(outFilePath);
		outWriter.println(this.numberOfRows());
		outWriter.println(this.numberOfColumns());
		outWriter.print(this.toString());
		outWriter.close();
	}
	
	/**
	 * Method that updates all cell states by apliying the rules of surveillance.
	 * Each rule is specified in the cell configuration.
	 */
	public void updateBoard() {
		for (int row = 0; row < this.numberOfRows(); row++)
			for (int col = 0; col < this.numberOfColumns(); col++)
				this.getCell(row, col).updateState(countNumberOfALiveNeighbours(row, col));
	}
	
	/**
	 * Private methods that counts the number of alive neighbours of a certain cell.
	 * 
	 * @param rowPos	 	row position of the cell we want to calculate the neighbours.
	 * @param colPos		column position of the cell we want to calculate the neighbours.
	 * @return			number of alive neighbours of the given cell in this turn.
	 */
	private int countNumberOfALiveNeighbours(int rowPos, int colPos) {
		final int PROXIMITY = 1;
		int numberOfALiveNeighbours = 0;
		
		for (int row = rowPos - PROXIMITY; row <= rowPos + PROXIMITY; row++) {
			for (int col = colPos - PROXIMITY; col <= colPos + PROXIMITY; col++) {
				Cell cellAtPos = this.getCell(row, col);
				if (cellAtPos != null && !(row == rowPos && col == colPos)) {
					if (row == rowPos - PROXIMITY || (row == rowPos && col == colPos - PROXIMITY)) {
						if (cellAtPos.getPreviousState() == CellState.ALIVE)
							numberOfALiveNeighbours++;
					}
					if (row == rowPos + PROXIMITY || (row == rowPos && col == colPos + PROXIMITY)) {
						if (cellAtPos.getCurrentState() == CellState.ALIVE)
							numberOfALiveNeighbours++;
					}
				}
			}
		}
		return numberOfALiveNeighbours;
	}
	
	/**
	 * Method that prints an iteration into a file, appending it at the end.
	 * 
	 * @param outFilePath	
	 * @throws IOException
	 */
	public void printIteration(String outFilePath) throws IOException {
		Files.write(Paths.get(outFilePath), this.toString().getBytes(), StandardOpenOption.APPEND);
	}
}
