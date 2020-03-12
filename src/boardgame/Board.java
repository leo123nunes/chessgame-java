package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board() {
	}

	public Board(int rows, int columns) {
		if(rows<1||columns<1) {
			throw new BoardGameException("Rows and Columns must be more than 0.");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		thereIsAPiece(position);
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private void thereIsAPiece(Position position) {
		if(position.getRow()>=this.getRows()||position.getColumn()>=this.getColumns()) {
			throw new BoardGameException("This position doesn't exist.");
		}
		if(pieces[position.getRow()][position.getColumn()]!=null) {
			throw new BoardGameException("This position is already used.");
		}
	}
}
