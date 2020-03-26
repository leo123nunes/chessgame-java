package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		if (p == null || p.getColor() != getColor()) {
			return true;
		}

		return false;
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		if(p!=null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount()==0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above

		p.setValues(position.getRow() - 1, position.getColumn());

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// down

		p.setValues(position.getRow() + 1, position.getColumn());

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left

		p.setValues(position.getRow(), position.getColumn() - 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right

		p.setValues(position.getRow(), position.getColumn() + 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw

		p.setValues(position.getRow() - 1, position.getColumn() - 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne

		p.setValues(position.getRow() - 1, position.getColumn() + 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se

		p.setValues(position.getRow() + 1, position.getColumn() + 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw

		p.setValues(position.getRow() + 1, position.getColumn() - 1);

		if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && canMove(p) == true) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		if(getMoveCount()==0 && chessMatch.getCheck() == false ) {
			// Castling move King side
			Position p1 = new Position(position.getRow(), position.getColumn()+3);
			if(testRookCastling(p1)==true) {
				Position p2 = new Position(position.getRow(), position.getColumn()+1);
				Position p3 = new Position(position.getRow(), position.getColumn()+2);
				
				if(getBoard().piece(p2)==null && getBoard().piece(p3)==null) {
					mat[position.getRow()][position.getColumn()+2] = true;
				}
			}
			
			// Castling move Queen side
			Position p4 = new Position(position.getRow(), position.getColumn()-4);
			if(testRookCastling(p4)==true) {
				Position p5 = new Position(position.getRow(), position.getColumn()-1);
				Position p6 = new Position(position.getRow(), position.getColumn()-2);
				Position p7 = new Position(position.getRow(), position.getColumn()-3);
				
				if(getBoard().piece(p5)==null && getBoard().piece(p6)==null && getBoard().piece(p7)==null) {
					mat[position.getRow()][position.getColumn()-2] = true;
				}
			}
		}
		
		return mat;
	}
}
