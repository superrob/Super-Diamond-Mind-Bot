package com.robinkaja.sdm;

public class Move {
	public int y1;
	public int x1;
	public int y2;
	public int x2;
	public int gemType;
	
	Move (int y1, int x1, int y2, int x2, int gemType) {
		this.y1 = y1;
		this.x1 = x1;
		this.y2 = y2;
		this.x2 = x2;
		this.gemType = gemType;
	}
	
	public int[][] getMoveCoordinates() {
		int[][] theMove = new int[2][2];
		theMove[0][0] = (y1 * Game.cellPadding) + Game.cellTestPoint;
		theMove[0][1] = (x1 * Game.cellPadding) + Game.cellTestPoint;
		theMove[1][0] = (y2 * Game.cellPadding) + Game.cellTestPoint;
		theMove[1][1] = (x2 * Game.cellPadding) + Game.cellTestPoint;
		return theMove;
	}
}
