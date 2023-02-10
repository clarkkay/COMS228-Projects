package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class OutageTest {
 //Goes through the .who method and next method using file given. - Kayley Clark
	@Test
	void test() {
		Town testinTown = new Town (4,4);
		TownCell outageTest = new Outage(testinTown, 4, 4);
		assertEquals(State.OUTAGE, outageTest.who());
	}
	@Test
	void testNext() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(State.EMPTY,testinTown.grid[2][2].next(testinTown).who());

	}

}
