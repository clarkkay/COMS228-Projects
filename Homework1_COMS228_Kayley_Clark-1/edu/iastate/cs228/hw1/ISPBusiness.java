package edu.iastate.cs228.hw1;

import java.io.File;
import java.util.Scanner;

/**
 * @author <<Kayley Clark>>
 *
 *         The ISPBusiness class performs simulation over a grid plain with
 *         cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object.
	 * @return: New town object. Go through each cell and call next on it.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		// Creates a new grid based off of previous grid, and inputs the new grid for
		// the next functions.
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);	
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		// Adds a profit for every casual in the town passed into it. Returns the
		// potential profit which is used in main after 12 cycles.
		int profit = 0;
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					profit += 1;
				}
			}
		}
		return 100 * profit / (town.getLength() * town.getWidth());

	}

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements
	 * of grid via an input file (option: 1) or wants to generate it randomly
	 * (option: 2).
	 * 
	 * Depending on the user choice, create the Town object using respective
	 * constructor and if user choice is to populate it randomly, then populate the
	 * grid here.
	 * 
	 * Finally: For 12 billing cycle calculate the profit and update town object
	 * (for each cycle). Print the final profit in terms of %. You should print the
	 * profit percentage with two digits after the decimal point: Example if profit
	 * is 35.5600004, your output should be:
	 *
	 * 35.56%
	 * 
	 * Note that this method does not throw any exception, so you need to handle all
	 * the exceptions in it.
	 * 
	 * @param args
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// Variables
		Scanner scnr = new Scanner(System.in);
		Town inputTown = null;
		double profitMade = 0;
		// Asks user
		System.out.println("How to populate grid (type 1 or 2) : 1: from a file. 2: randomly with seed");
		// Determines option
		int input = scnr.nextInt();
		// If option is entering a file path
		if (input == 1) {
			System.out.println("Please enter file path:");
			// creates a string to pass into town.
			String fileName = scnr.next();
			// Try's to pass the file name created into a new town to be generated.
			try {
				inputTown = new Town(fileName);
			} catch (Exception e) {
				System.out.println("File cannot be found");
			}
			// If user chooses to create grid using random.
		} else if (input == 2) {
			Scanner newInput = new Scanner(System.in);
			System.out.println("Provide rows, cols and seed integer separated by spaces: ");
			String inputRand = newInput.nextLine();
			Scanner randInput = new Scanner(inputRand);
			// Creates an array to append each integer from response, then can be used as
			// row (0), col (1), seed(2).
			int[] arrayList = new int[3];
			int count = 0;
			// while there is an integer in the response, can only go to the first three
			// integers using count.
			while (randInput.hasNextInt()) {
				arrayList[count] = randInput.nextInt();
				count += 1;
			}
			// Uses the row and column given in the array list to generate a new town, then
			// creates the town cells using the seed given (in array list)
			inputTown = new Town(arrayList[0], arrayList[1]);
			inputTown.randomInit(arrayList[2]);
		}

		// Iterates through each 12 month cycle and updates the plain, then appends all
		// the profit potential.
		Town endTown = inputTown;
		 profitMade += getProfit(inputTown);

		for (int profitInt = 0; profitInt <= 11; profitInt++) {
			endTown = updatePlain(endTown);
			profitMade += getProfit(endTown);
			
		}
		// Uses the profit potential and first divdes by 12 for each month average.
		profitMade = profitMade / 12;
		// Turns average into a decimal
		profitMade = Math.floor(profitMade * 100) / 100d;
		System.out.println(profitMade + "%");
	}

}
