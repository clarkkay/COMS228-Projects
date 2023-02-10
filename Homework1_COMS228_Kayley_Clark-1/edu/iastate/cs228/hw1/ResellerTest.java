package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class ResellerTest {

	@Test
	void test() {
		Town testinTown = new Town (4,4);
		TownCell resellerTest = new Reseller(testinTown, 4, 4);
		assertEquals(State.RESELLER, resellerTest.who());
	}
	@Test
	void testNext() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(State.EMPTY,testinTown.grid[0][3].next(testinTown).who());

	}
}
