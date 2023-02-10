package edu.iastate.cs228.hw1;

public class Empty extends TownCell {

	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		
		//We know that Empty will always return to a new Casual town cell.
		TownCell newTownCell = null;
		int[] newNC = new int[NUM_CELL_TYPE];
		this.census(newNC);
		if ((newNC[1] + newNC[3]) <= 1) {
			newTownCell = new Reseller(tNew, row, col);
		} 	else {
		newTownCell = new Casual(tNew,row,col);
	}
		return newTownCell;
	}
}
