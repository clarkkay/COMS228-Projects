package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class EmptyTest {
//Goes through the .who method and the next method using the file given and what it should update too. - Kayley Clark
	@Test
	void test() {
		Town testinTown = new Town (4,4);
		TownCell emptyTest = new Empty(testinTown, 4, 4);
		assertEquals(State.EMPTY, emptyTest.who());
	}
	@Test 
	void testNext() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(State.CASUAL,testinTown.grid[1][1].next(testinTown).who());

	}
}
