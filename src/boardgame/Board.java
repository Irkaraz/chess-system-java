package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board() {
    }

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
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

    public Piece getPiece(int row, int column) {
    if (!positionExist(row, column)){
        throw new BoardException("Position not on the board");
    }
        return pieces[row][column];
    }

    public Piece getPiece(Position position) {
        if (!positionExist(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece (Piece piece, Position position){
        if (thereIsAPiece(position)){
            throw new BoardException("There is already exist a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece (Position position){
        if(!positionExist(position)){
            throw new BoardException("Position not on the board");
        }
        if (getPiece(position) == null){
            return null;
        }
        Piece aux = getPiece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExist (int row, int column){
        return row >= 0 && row < rows && column >=0 && column < columns;
    }

    public boolean positionExist (Position position){
        return positionExist(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece (Position position){
        if (!positionExist(position)){
            throw new BoardException("Position not on the board");
        }
        return getPiece(position) != null;
    }
}
