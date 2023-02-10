package edu.iastate.cs228.hw1;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author <<Kayley Clark>>
 *
 */
public class Town {

	private int length, width; // Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the
	 * given seed. This constructor does not populate each cell of the grid (but
	 * should assign a 2D array to it).
	 * 
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		// TODO: Write your code here.
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of
	 * catching it. Ensure that you close any resources (like file or scanner) which
	 * is opened in this function.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		// Takes our file and turns that into a scanner so we can read through the file.
		File inputFile = new File(inputFileName);
		Scanner scnr = new Scanner(inputFile);
		// Setting up the length, width, and grid.
		this.length = scnr.nextInt();
		this.width = scnr.nextInt();
		grid = new TownCell[length][width];
		// Try catch for a file not found exception,
		try {
			// Loop to go through every character in file given.
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {
					// Making the file into a string.
					String type = scnr.next();
					// Using if else to add town cells into the grid.
					if (type.equals("C")) {
						grid[i][j] = (new Casual(this, i, j));
					} else if (type.equals("S")) {
						grid[i][j] = (new Streamer(this, i, j));
					} else if (type.equals("R")) {
						grid[i][j] = (new Reseller(this, i, j));
					} else if (type.equals("O")) {
						grid[i][j] = (new Outage(this, i, j));
					} else {
						grid[i][j] = (new Empty(this, i, j));
					}

				}
			}
		} catch (Exception FileNotFoundException) {
			System.out.print("File not found. :(");

		} finally {
			// Close scanner
			scnr.close();
		}
	}

	/**
	 * Returns width of the grid.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following
	 * class object: Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		// When choosing to generate a grid randomly, go through the entire array from
		// given length and width. Assign a number and append new town cells based off
		// list of numbers given.
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				int num = rand.nextInt(5);
				if (num == 0) {
					this.grid[i][j] = new Reseller(this, i, j);
				} else if (num == 1) {
					this.grid[i][j] = new Empty(this, i, j);
				} else if (num == 2) {
					this.grid[i][j] = new Casual(this, i, j);
				} else if (num == 3) {
					this.grid[i][j] = new Outage(this, i, j);
				} else if (num == 4) {
					this.grid[i][j] = new Streamer(this, i, j);
				}
			}
		}

	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell
	 * type. Each letter should be separated either by a single space or a tab. And
	 * each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		// Runs two four loops determining what each state the length and width is, then
		// appends letter to string.
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j].who() == State.CASUAL) {
					s += "C ";
				} else if (grid[i][j].who() == State.STREAMER) {
					s += "S ";
				} else if (grid[i][j].who() == State.RESELLER) {
					s += "R ";
				} else if (grid[i][j].who() == State.OUTAGE) {
					s += "O ";
				} else {
					s += "E ";
				}
			}
			s += "\n";
		}
		return s;
	}

}
