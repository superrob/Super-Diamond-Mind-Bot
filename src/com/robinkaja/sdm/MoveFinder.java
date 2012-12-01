package com.robinkaja.sdm;

import java.util.ArrayList;

public class MoveFinder {
	MoveFinder() {
	
	}
	
	public Move findBestMove(int[][] fieldData) throws NoMoveFoundException {
		ArrayList<Move> moveList = findAllMoves(fieldData);
		if (moveList.isEmpty()) throw(new NoMoveFoundException("No moves"));
		
		return moveList.get(0);
	}
	
	public ArrayList<Move> findAllMoves(int[][] fieldData) {
		ArrayList<Move> moveList = new ArrayList<Move>();
		
		int gem1;
		int gem2;
		int gem3;
		int gem4;
		int gem5;
		int gem6;
		
		for (int y=0; y<8;y++) {
			for (int x=0; x<5; x++) {
				gem1 = fieldData[y][x];
				gem2 = fieldData[y][x+1];
				gem3 = fieldData[y][x+2];
				gem4 = fieldData[y][x+3];
				// XXOX
				if (checkMatch(gem1, gem2, gem4))
					moveList.add(new Move(y, x+2, y, x+3, gem1));
				// XOXX
				if (checkMatch(gem1, gem3, gem4))
					moveList.add(new Move(y, x, y, x+1, gem1));
			}
		}
		
		for (int y=0; y<5;y++) {
			for (int x=0; x<8; x++) {
				gem1 = fieldData[y][x];
				gem2 = fieldData[y+1][x];
				gem3 = fieldData[y+2][x];
				gem4 = fieldData[y+3][x];
				// X
				// X
				// O
				// X
				if (checkMatch(gem1, gem2, gem4))
					moveList.add(new Move(y+2, x, y+3, x, gem1));
				// X
				// O
				// X
				// X
				if (checkMatch(gem1, gem3, gem4))
					moveList.add(new Move(y, x, y+1, x, gem1));
			}
		}
		
		return moveList;
	}
	
	public Boolean checkMatch(int gem1, int gem2, int gem3) {
		return (gem1 == gem2 && gem2 == gem3);
	}
}
