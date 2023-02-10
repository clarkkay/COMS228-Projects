package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {
//Runs through the updatePlain and getProfit method in ISPBusiness - Kayley Clark
	@Test
	void testUpdatePlain() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		String newTown = "E E E E \nC C O E \nC O E O \nC E E E \n";
		assertEquals(newTown, ISPBusiness.updatePlain(testinTown).toString());
	}
	@Test
	void testGetProfit() throws FileNotFoundException {
		Town testinTown = new Town("ISP4x4.txt");
		assertEquals(6, ISPBusiness.getProfit(testinTown) );
	}
}
