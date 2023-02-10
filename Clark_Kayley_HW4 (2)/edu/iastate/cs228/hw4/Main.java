package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//@author Kayley Clark

public class Main {

	public static void main(String[] args) throws IOException {
		// Start of program
		System.out.println("Please enter  to decode:");
		// Creating variables to read the file.
		Scanner scnr = new Scanner(System.in);
		String Input = scnr.next().trim();
		File inFile = new File(Input);
		Scanner fileScanner = new Scanner(inFile);
		// top part contains the ^ and characters
		String top = "";
		// Originally tried to do a path, and then readAllBytes, found this way to read
		// the file and it worked.
		// Using intellij for the first time on this project, in order to run the tests
		// I had to copy and paste the complete file path.
		Scanner getTop = new Scanner(inFile);
		while (getTop.nextLine().contains("^")) {
			top += fileScanner.nextLine() + "\n";
		}
		// bottom part contains the decoding path
		String bottom = fileScanner.nextLine();
		// making the tree
		MsgTree root = new MsgTree(top);
		// printing all of the codes
		System.out.println("character code\n-------------------------");
		MsgTree.printCodes(root, "");
		// printing the message, unsure if it needed to be a \n here for formatting
		// purposes.
		System.out.println("MESSAGE:");
		root.decode(root, bottom);
		// Closing scanners
		scnr.close();
		fileScanner.close();
		getTop.close();
	}
}
