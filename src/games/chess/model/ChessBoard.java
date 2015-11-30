package games.chess.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Satya on 14/06/14.
 */
public final class ChessBoard implements Serializable {
    public static final int ROWS = 8;
    public static final int COLS = 8;

    private Piece pieces[][] = new Piece[ROWS][COLS];
    private Set<Piece> whitePieces = new TreeSet<>();
    private Set<Piece> blackPieces = new TreeSet<>();

    private Pose2d whiteKingPosition = new Pose2d(0, 4);
    private Pose2d blackKingPosition = new Pose2d(7, 4);

    public ChessBoard() {
        for (int i = 0; i < 8; ++i) {
            pieces[1][i] = new Pawn(new Pose2d(1, i), Player.WHITE, this);
            pieces[6][i] = new Pawn(new Pose2d(6, i), Player.BLACK, this);
        }
        pieces[0][0] = new Rook(new Pose2d(0, 0), Player.WHITE, this);
        pieces[0][7] = new Rook(new Pose2d(0, 7), Player.WHITE, this);
        pieces[0][1] = new Knight(new Pose2d(0, 1), Player.WHITE, this);
        pieces[0][6] = new Knight(new Pose2d(0, 6), Player.WHITE, this);
        pieces[0][2] = new Bishop(new Pose2d(0, 2), Player.WHITE, this);
        pieces[0][5] = new Bishop(new Pose2d(0, 5), Player.WHITE, this);
        pieces[0][3] = new Queen(new Pose2d(0, 3), Player.WHITE, this);
        pieces[0][4] = new King(new Pose2d(0, 4), Player.WHITE, this);

        pieces[7][0] = new Rook(new Pose2d(7, 0), Player.BLACK, this);
        pieces[7][7] = new Rook(new Pose2d(7, 7), Player.BLACK, this);
        pieces[7][1] = new Knight(new Pose2d(7, 1), Player.BLACK, this);
        pieces[7][6] = new Knight(new Pose2d(7, 6), Player.BLACK, this);
        pieces[7][2] = new Bishop(new Pose2d(7, 2), Player.BLACK, this);
        pieces[7][5] = new Bishop(new Pose2d(7, 5), Player.BLACK, this);
        pieces[7][3] = new Queen(new Pose2d(7, 3), Player.BLACK, this);
        pieces[7][4] = new King(new Pose2d(7, 4), Player.BLACK, this);

    }
    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPiece(Pose2d position, Piece piece) {
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    public boolean isFree(Pose2d pos) {
        return !this.isOutOfBounds(pos) && pieces[pos.getRow()][pos.getColumn()] == null;
    }

    public Set<Piece> getAllPiecesList() {
        Set<Piece> all = whitePieces;
        all.addAll(blackPieces);
        return all;
    }

    public Set<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Set<Piece> getBlackPieces() {
        return blackPieces;
    }

    public Pose2d getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public Pose2d getBlackKingPosition() {
        return blackKingPosition;
    }

    public Set<Piece> getPieceListOfPlayer(Player player) {
        if (player == Player.WHITE) return whitePieces; else return blackPieces;
    }

    public Piece getPiece(Pose2d pos) {
        return pieces[pos.getRow()][pos.getColumn()];
    }

    public boolean containsEnemy(Pose2d startPos, Pose2d endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        if (isFree(endPos)) {
            return false;
        }
        Player movingPieceColor = pieces[startPos.getRow()][startPos.getColumn()].getPlayer();

        Player receivingPieceColor = pieces[endPos.getRow()][endPos.getColumn()].getPlayer();

        return (movingPieceColor != receivingPieceColor);

    }
    public boolean isOutOfBounds(Pose2d endPos) {
        return endPos.getRow() >= ChessBoard.ROWS || endPos.getRow() < 0
                || endPos.getColumn() >= ChessBoard.COLS || endPos.getColumn() < 0;
    }

    public boolean isUnmovedRook(Pose2d pos){
        Piece piece = pieces[pos.getRow()][pos.getColumn()];
        if(!(piece instanceof Rook)){
            return false;
        }
        Rook tower = (Rook) piece;

        return tower.isMoved();
    }

}
