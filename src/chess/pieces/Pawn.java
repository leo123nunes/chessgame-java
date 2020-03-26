package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
		if (getColor() == Color.WHITE) {

			p.setValues(position.getRow() - 2, position.getColumn());

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && getMoveCount() == 0
					&& getBoard().thereIsAPiece(p) == false) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() - 1, position.getColumn());

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && getBoard().thereIsAPiece(p) == false) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() - 1, position.getColumn() - 1);

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() - 1, position.getColumn() + 1);

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// el passant move white piece

			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExistss(left) == true && isThereOpponentPiece(left) == true
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExistss(right) == true && isThereOpponentPiece(right) == true
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}

		} else {
			p.setValues(position.getRow() + 2, position.getColumn());

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && getMoveCount() == 0
					&& getBoard().thereIsAPiece(p) == false) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() + 1, position.getColumn());

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && getBoard().thereIsAPiece(p) == false) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() + 1, position.getColumn() - 1);

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow(), position.getColumn());

			p.setValues(position.getRow() + 1, position.getColumn() + 1);

			if (getBoard().positionExists(p.getRow(), p.getColumn()) == true && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// el passant move black piece

			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExistss(left) == true && isThereOpponentPiece(left) == true
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExistss(right) == true && isThereOpponentPiece(right) == true
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}

	@Override

	public String toString() {
		return "P";
	}

}
