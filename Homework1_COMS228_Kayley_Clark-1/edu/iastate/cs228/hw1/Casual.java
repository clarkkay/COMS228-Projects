package edu.iastate.cs228.hw1;

public class Casual extends TownCell {

	

	public Casual(Town p, int r, int c) {
		super(p, r, c);
		// TODO Auto-generated constructor stub
		

	}

	@Override
	public State who() {
		// TODO Auto-generated method stub
		return State.CASUAL;
	}

	@Override
	public TownCell next(Town tNew) {
		//Creating a new towncell to use in our return.
		TownCell newTownCell = null;

		// Making a new census to call in our townCell class using the census method.
		
		int[] newNC = new int[NUM_CELL_TYPE];
		this.census(newNC);
		//if that new array (newNC) contains any of our States we need to change we set the new townCell to according state, determined by rules.
		if ((newNC[1] + newNC[3]) <= 1) {
			newTownCell = new Reseller(tNew,row,col);
		} else if (newNC[0] >= 1) {
			newTownCell = new Outage(tNew,row,col);
		} else if(newNC[4] >=1) {
			newTownCell = new Streamer(tNew,row,col);
		} else if (newNC[2] >= 5) {
			newTownCell = new Streamer(tNew, row,col);
		} else {
			 newTownCell = new Casual(tNew,row,col);
		}
		return newTownCell;
	}

}
