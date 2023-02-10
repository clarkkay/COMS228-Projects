package edu.iastate.cs228.hw1;

public class Reseller extends TownCell {

	public Reseller(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub

	}

	@Override
	public State who() {
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		//Creating return town cell
		TownCell newTownCell = null;
		//Making a new array census list to determine neighborhood
		int[] newNC = new int[NUM_CELL_TYPE];
		this.census(newNC);
		//if that new array (newNC) contains any of our States we need to change we set the new townCell to according state, determined by rules.
		if (newNC[2] <= 3 || newNC[1] >= 3) {
			newTownCell = new Empty(tNew, row, col);
		} else if(newNC[2] >=5 ) {
			newTownCell = new Streamer(tNew,row,col);
		} else {
			newTownCell = new Reseller(tNew,row,col);
		}
		return newTownCell;
	}

}
