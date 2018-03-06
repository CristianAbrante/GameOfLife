/**
 * @author cristian
 * @date 6 mar. 2018
 */
package game_of_life;

import java.io.IOException;

import board.Board;

/**
 * 
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	6 mar. 2018
 * @version 	1.0.0
 */
public class GameOfLife {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean debug = false;
		int numberOfSteps = 0;
		String outputFile = "";
		String inputFile = "";
		try {
			switch(args.length) {
			case 4:
				if (args[3].equals("debug")) {
					debug = true;
				} else {
					System.err.println("unknown parameter " + args[3]);
				}
			case 3:
				numberOfSteps = Integer.parseInt(args[0]);
				inputFile = args[1];
				outputFile = args[2];
				break;
			default:
				throw new IllegalArgumentException("incorrect number of arguments");
			}
			
			Board gameOfLifeBoard = new Board(inputFile);
			
			gameOfLifeBoard.printIteration(outputFile);
			
			for (int step = 0; step < numberOfSteps; ++step) {
				gameOfLifeBoard.updateBoard();
				if (debug) {
					gameOfLifeBoard.printIteration(outputFile);
				}
			}
			if (!debug) {
				gameOfLifeBoard.printIteration(outputFile);
			}
		}
		catch(IOException io) {
			System.err.println("could not open file " + io.getMessage());
		}
		catch(NumberFormatException nfe) {
			System.err.println("parameter " + args[0] + " is not a valid number of steps.");
		}
		catch(IllegalArgumentException iae) {
			System.err.println(iae.getMessage());
		}
	}
}
