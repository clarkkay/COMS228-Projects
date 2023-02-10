package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TownCellTest {
// Runs through the census method - Kayley Clark
	@Test
	void testCensus() throws FileNotFoundException  {
		Town testinTown = new Town("ISP4x4.txt");
		int [] newNC = new int[5];
		testinTown.grid[2][2].census(newNC);
		assertEquals(newNC[2],0);
	}
}
