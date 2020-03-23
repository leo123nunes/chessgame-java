package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.BoardGameException;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> capturedPieces = new ArrayList<>();
		
		while(chessMatch.getCheckMate()==false) {
			try {
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedPieces);
		System.out.println();
		System.out.print("Source: ");
		ChessPosition source = UI.readChessPosition(sc);
		
		boolean[][] possibleMoves = chessMatch.possibleMoves(source);
		UI.clearScreen();
		UI.printBoard(chessMatch.getPieces(),possibleMoves);
		System.out.println();
		
		System.out.println();
		System.out.print("Target: ");
		ChessPosition target = UI.readChessPosition(sc);
		
		ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		
		if(capturedPiece!=null) {
			capturedPieces.add(capturedPiece);
		}
		
		
			}catch(InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}catch(ChessException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}catch(BoardGameException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, capturedPieces);
	}
}
