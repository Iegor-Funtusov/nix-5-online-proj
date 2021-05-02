package com.nixsolutions.pieces;

import com.nixsolutions.pieces.AbstractPiece;

public class Rook extends AbstractPiece {
	public Rook(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("\u2656");
		}
		else{
			System.out.print("\u265C");
		}		
	}
	
	private static Boolean straightPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// returns true if the path is straight
		// arguments are initial and final coordinates of move in chessboard
		// array
		// good for checking if a move is valid
		return !((srcRow != destRow) && (srcCol != destCol));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return straightPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		return 5;
	}

}
