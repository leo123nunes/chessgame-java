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
	
	public void thereIsAPiece(Position position) {
		if(position.getRow()>=this.getRows()||position.getColumn()>=this.getColumns()) {
			throw new BoardGameException("This position doesn't exist.");
		}
		if(pieces[position.getRow()][position.getColumn()]!=null) {
			throw new BoardGameException("This position is already used.");
		}
	}
	
	public void thereIsAPieceSource(Position position) {
		if(pieces[position.getRow()][position.getColumn()]==null) {
			throw new BoardGameException("There is no piece in source.");
		}
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position.getRow(),position.getColumn())) {
			throw new BoardGameException("Posiion not on the board.");
		}
		if(piece(position)==null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column <columns;
	}
	
	
}
