package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class StreamerTest {
 //Goes through the .who method and the next method using the file given. - Kayley Clark
	@Test
	void test() {
		Town testinTown = new Town (4,4);
		TownCell streamerTest = new Streamer(testinTown, 4, 4);
		assertEquals(State.STREAMER, streamerTest.who());
	}
	@Test
	void testNext() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(State.OUTAGE,testinTown.grid[2][1].next(testinTown).who());

	}
}
