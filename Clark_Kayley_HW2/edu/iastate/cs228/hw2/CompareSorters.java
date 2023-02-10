package edu.iastate.cs228.hw2;

/**
 *  
 * @author Kayley Clark
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		// Creating Varibales
		Scanner scnr = new Scanner(System.in);
		Point[] points = null;
		PointScanner[] scanners = new PointScanner[4];
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		System.out.println(
				"Preformances of Four Sorting Algorithms in Point Scanning\n\nkeys: 1 (random integers) 2 (file input) 3 (exit)");
		for (int trialNum = 1; trialNum < 3; trialNum++) {
			System.out.print("Trial " + trialNum + ": ");
			int answer = scnr.nextInt();
			if (answer == 1) {
				System.out.print("Enter number of random points: ");
				int randInput = scnr.nextInt();
				Random rand = new Random();
				points = generateRandomPoints(randInput, rand);
				scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(points, Algorithm.MergeSort);
				scanners[3] = new PointScanner(points, Algorithm.QuickSort);
				System.out.println("algorithm   size  time (ns)");
				System.out.println("----------------------------------");
				for (int i = 0; i < 4; i++) {
					scanners[i].scan();
					scanners[i].writeMCPToFile();
					System.out.println(scanners[i].stats());
				}
				System.out.println("----------------------------------");
			} else if (answer == 2) {
				System.out.print("Points from a file\nFile name: ");
				String fileName = scnr.next();
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				System.out.println("algorithm   size  time (ns)");
				System.out.println("----------------------------------");

				for (int i = 0; i < 4; i++) {
					scanners[i].scan();
					scanners[i].writeMCPToFile();
					System.out.println(scanners[i].stats());
				}
				System.out.println("----------------------------------");
			} else {
				return;
			}
		}
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] × [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		Point[] pointsRand = new Point[numPts];
		for (int i = 0; i <= numPts - 1; i++) {
			pointsRand[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		return pointsRand;
	}

}
