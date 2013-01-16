package com.robinkaja.sdm;

import java.util.ArrayList;

public class MoveFinder {
	MoveFinder() {
	
	}
	
	public Move findBestMove(int[][] fieldData, boolean[] diamonds) throws NoMoveFoundException {
		ArrayList<Move> moveList = findAllMoves(fieldData);
		if (moveList.isEmpty()) throw(new NoMoveFoundException("No moves"));
		
		if (moveList.size() > 1) {
			int wantedGem = findWantedGem(diamonds);
			ArrayList<Move> wantedList = new ArrayList<Move>();
			for (Move move : moveList) {
				if (move.gemType == wantedGem) wantedList.add(move);
			}
			if (!wantedList.isEmpty())
				moveList = wantedList;
		}		
		Move bestMove = moveList.get(0);
		
		return bestMove;
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
		
		for (int y=0; y<7;y++) {
			for (int x=0; x<6; x++) {
				gem1 = fieldData[y][x];
				gem2 = fieldData[y][x+1];
				gem3 = fieldData[y][x+2];
				gem4 = fieldData[y+1][x];
				gem5 = fieldData[y+1][x+1];
				gem6 = fieldData[y+1][x+2];
				// XOO
				// OXX
				// OR
				// OXX
				// XOO
				if (checkMatch(gem1, gem5, gem6))
					moveList.add(new Move(y, x, y+1, x, gem1));
				if (checkMatch(gem2, gem3, gem4))
					moveList.add(new Move(y, x, y+1, x, gem2));
				// OXO
				// XOX
				// OR
				// XOX
				// OXO
				if (checkMatch(gem2, gem4, gem6))
					moveList.add(new Move(y, x+1, y+1, x+1, gem2));
				if (checkMatch(gem1, gem3, gem5))
					moveList.add(new Move(y, x+1, y+1, x+1, gem1));				
				// OOX
				// XXO
				// OR
				// XXO
				// OOX
				if (checkMatch(gem3, gem4, gem5))
					moveList.add(new Move(y, x+2, y+1, x+2, gem3));
				if (checkMatch(gem1, gem2, gem6))
					moveList.add(new Move(y, x+2, y+1, x+2, gem1));
			}
		}
		
		for (int y=0; y<6;y++) {
			for (int x=0; x<7; x++) {
				gem1 = fieldData[y][x];
				gem2 = fieldData[y+1][x];
				gem3 = fieldData[y+2][x];
				gem4 = fieldData[y][x+1];
				gem5 = fieldData[y+1][x+1];
				gem6 = fieldData[y+2][x+1];
				// XO
				// OX
				// OX
				// OR
				// OX
				// XO
				// XO
				if (checkMatch(gem1, gem5, gem6))
					moveList.add(new Move(y, x, y, x+1, gem1));
				if (checkMatch(gem2, gem3, gem4))
					moveList.add(new Move(y, x, y, x+1, gem2));
				// OX
				// XO
				// OX
				// OR
				// XO
				// OX
				// XO
				if (checkMatch(gem2, gem4, gem6))
					moveList.add(new Move(y+1, x, y+1, x+1, gem2));
				if (checkMatch(gem1, gem3, gem5))
					moveList.add(new Move(y+1, x, y+1, x+1, gem1));				
				// OX
				// OX
				// XO
				// OR
				// XO
				// XO
				// OX
				if (checkMatch(gem3, gem4, gem5))
					moveList.add(new Move(y+2, x, y+2, x+1, gem3));
				if (checkMatch(gem1, gem2, gem6))
					moveList.add(new Move(y+2, x, y+2, x+1, gem1));
			}
		}
		
		return moveList;
	}
	
	public Boolean checkMatch(int gem1, int gem2, int gem3) {
		if (gem1 == Gem.NONE || gem2 == Gem.NONE || gem3 == Gem.NONE) return false;
		return (gem1 == gem2 && gem2 == gem3);
	}
	
	public int findWantedGem(boolean[] diamonds) {
		if (!diamonds[5]) return Gem.BLUE;
		if (!diamonds[4]) return Gem.GREEN;
		if (!diamonds[3]) return Gem.RED;
		if (!diamonds[2]) return Gem.YELLOW;
		if (!diamonds[1]) return Gem.PURPLE;
		if (!diamonds[0]) return Gem.CYAN;
		return Gem.WHITE;
	}
}
