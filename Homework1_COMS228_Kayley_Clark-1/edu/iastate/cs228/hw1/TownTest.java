package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TownTest {
//Runs through testing width, length, randomInit, and string methods from town. - Kayley Clark 
	@Test
	void testWidth() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(4,testinTown.getWidth());
	}
	@Test 
	void testLength() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(4,testinTown.getLength());
	}
	@Test
	void testRandomInit() throws FileNotFoundException {
		Town testinTown = new Town(2,2);
		Town checkTown = new Town(2,2);
		checkTown.randomInit(2);
		testinTown.randomInit(2);
		assertEquals(checkTown.toString(),testinTown.toString());
		
	}
	@Test
	void testToString() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		String testingString = "O R O R \nE E C O \nE S O S \nE O R R \n";
		assertEquals(testingString,testinTown.toString());
	}
}
