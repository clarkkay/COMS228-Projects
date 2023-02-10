package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Kayley Clark 
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if needed

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		// TODO
		super(pts);
		algorithm = "mergesort";

	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts) {
		int i = 0;
		int j = 0;
		int n = pts.length;
		if (n > 1) {
			int middle = n / 2;
			// creating two halves of the array (later halving them recursively)
			Point[] left = new Point[middle];
			Point[] right = new Point[n - middle];
			for (i = 0; i < n; i++) {
				if (i < middle) {
					left[i] = pts[i];
				} else {
					right[j] = pts[i];
					j++;
				}
			}
			// recursive
			mergeSortRec(left);
			mergeSortRec(right);
			// new merge method.
			merge(pts, left, right);
		}
	}

	private void merge(Point[] pts, Point[] left, Point[] right) {
		// merging them together
		// array lengths.
		int p = left.length;
		int q = right.length;
		// counts in the array
		int i = 0;
		int j = 0;
		// new merge index
		int m = 0;
		// while i is reaching up to left length and j is reaching up to right length
		while (i < p && j < q) {
			// comparing to check if left point on array is greater than right point
			if (pointComparator.compare(left[i], right[j]) < 0) {
				pts[m] = left[i];
				i++;
			} else {
				// if the right point is bigger then we add that to the final points.
				pts[m] = right[j];
				j++;
			}
			// next final array index
			m++;
		}
		// copying the array left[i] into pts[m] for the rest of left length. Same for
		// right half as well.
		System.arraycopy(left, i, pts, m, left.length - i);
		System.arraycopy(right, j, pts, m, right.length - j);
	}
}
