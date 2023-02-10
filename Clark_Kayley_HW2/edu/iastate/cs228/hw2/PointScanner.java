package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Kayley Clark
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		try {
			if (pts != null && pts.length != 0) {
				sortingAlgorithm = algo;
				points = pts;
			}
		} catch (IllegalArgumentException e) {
			return;
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		// TODO

		try {
			// creating file to read through
			File file = new File(inputFileName);
			// reading through the file
			Scanner scnr = new Scanner(file);
			// getting the count of integers in the array to divide by 2, this way we can
			// create a new point with an array size. Otherwise null.
			int i = 0;
			int count = 0;
			while (scnr.hasNextInt()) {
				scnr.nextInt();
				count++;
			}

			scnr.close();
			// creating a new scanner to read the file again this time appending a new point
			// (using the next two ints) to the end of our array.
			Scanner scnr2 = new Scanner(file);
			points = new Point[count / 2];
			while (scnr2.hasNextInt()) {
				points[i] = new Point(scnr2.nextInt(), scnr2.nextInt());
				i++;
			}
			scnr.close();
			sortingAlgorithm = algo;
		} catch (Exception e) {

		}
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter = null;
		long scanTimeA;
		long scanTimeB;
		// Creating new abstract sorters to make new sorter methods.
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		} else {
			aSorter = new QuickSorter(points);
		}
		// x coordinates
		aSorter.setComparator(0);
		scanTimeA = System.nanoTime();
		aSorter.sort();
		scanTimeA = System.nanoTime() - scanTimeA;
		int xCord = aSorter.getMedian().getX();
		// y coordinates
		aSorter.setComparator(1);
		scanTimeB = System.nanoTime();
		aSorter.sort();
		scanTimeB = System.nanoTime() - scanTimeB;
		int yCord = aSorter.getMedian().getY();
		scanTime = scanTimeA + scanTimeB;
		medianCoordinatePoint = new Point(xCord, yCord);
	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		int size = points.length;
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			return "SelectionSort     " + size + "  " + scanTime;
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			return "InsertionSort     " + size + "  " + scanTime;
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			return "MergeSort         " + size + "  " + scanTime;
		} else {
			return "QuickSort         " + size + "  " + scanTime;
		}
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {

		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
		// TODO

	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		// TODO
		try {
			FileWriter outputFile = new FileWriter("outputFileName.txt");
			outputFile.write(toString());
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
