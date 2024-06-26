package chess_pieces;

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

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        //above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //bellow
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //nw
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //ne
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //sw
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //se
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //castling move
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            //king side rook
            Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posRook1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            //king side queen
            Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posRook1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null && getBoard().getPiece(p3) == null) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }
        return mat;
    }
}
