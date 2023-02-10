package edu.iastate.cs228.hw1;

public class Streamer extends TownCell {
	
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
		
	}

	@Override
	public State who() {
		return State.STREAMER;
	}

	@Override
	public TownCell next(Town tNew) {
		//Creating our return town cell
		TownCell newTownCell = null;
		// Making a new array to call census on so we can determine neighborhood.
		int[] newNC = new int[NUM_CELL_TYPE];
		this.census(newNC);
		//if that new array (newNC) contains any of our States we need to change we set the new townCell to according state, determined by rules.
		if ((newNC[1] + newNC[3]) <= 1) {
			newTownCell = new Reseller(tNew,this.row,this.col);
		} else if (newNC[0] >= 1) {
			newTownCell = new Outage(tNew,this.row,this.col);
		}else if(newNC[3] >= 1) {
			newTownCell = new Empty(tNew,this.row,this.col);
		} else if (newNC[2] >= 5) {
			newTownCell = new Streamer(tNew, this.row,this.col);
		} else {
			newTownCell = new Streamer(tNew,this.row,this.col);
		}
		return newTownCell;
	}

}
