package edu.iastate.cs228.hw1;

public class Outage extends TownCell {

	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		//We know that any outage cell will always turn into a new empty town cell in next cycle
		return new Empty(tNew, row, col);
	}

}
