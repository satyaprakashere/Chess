package games.chess.model;

/**
 * Created by Satya on 14/06/14.
 */
public final class ChessBoard {
    public static final int ROWS = 8;
    public static final int COLS = 8;

    private final Piece pieces[][] = new Piece[ROWS][COLS];
//    private Set<Piece> whitePieces = new TreeSet<>();
//    private Set<Piece> blackPieces = new TreeSet<>();

//    private Pose2d whiteKingPosition = Pose2d.withPosition(0, 4);
//    private Pose2d blackKingPosition = Pose2d.withPosition(7, 4);

    public ChessBoard() {
        for (int i = 0; i < 8; ++i) {
            pieces[1][i] = new Pawn(Pose2d.create(1, i), Player.WHITE, this);
            pieces[6][i] = new Pawn(Pose2d.create(6, i), Player.BLACK, this);
        }
        pieces[0][0] = new Rook(Pose2d.create(0, 0), Player.WHITE, this);
        pieces[0][7] = new Rook(Pose2d.create(0, 7), Player.WHITE, this);
        pieces[0][1] = new Knight(Pose2d.create(0, 1), Player.WHITE, this);
        pieces[0][6] = new Knight(Pose2d.create(0, 6), Player.WHITE, this);
        pieces[0][2] = new Bishop(Pose2d.create(0, 2), Player.WHITE, this);
        pieces[0][5] = new Bishop(Pose2d.create(0, 5), Player.WHITE, this);
        pieces[0][3] = new Queen(Pose2d.create(0, 3), Player.WHITE, this);
        pieces[0][4] = new King(Pose2d.create(0, 4), Player.WHITE, this);

        pieces[7][0] = new Rook(Pose2d.create(7, 0), Player.BLACK, this);
        pieces[7][7] = new Rook(Pose2d.create(7, 7), Player.BLACK, this);
        pieces[7][1] = new Knight(Pose2d.create(7, 1), Player.BLACK, this);
        pieces[7][6] = new Knight(Pose2d.create(7, 6), Player.BLACK, this);
        pieces[7][2] = new Bishop(Pose2d.create(7, 2), Player.BLACK, this);
        pieces[7][5] = new Bishop(Pose2d.create(7, 5), Player.BLACK, this);
        pieces[7][3] = new Queen(Pose2d.create(7, 3), Player.BLACK, this);
        pieces[7][4] = new King(Pose2d.create(7, 4), Player.BLACK, this);

    }
//    public Piece[][] getPieces() {
//        return pieces;
//    }

    public boolean setPiece(Pose2d position, Piece piece) {
        if (this.isOutOfBounds(position)) {
            return false;
        }
        pieces[position.row()][position.col()] = piece;
        return true;
    }

    public boolean isFree(Pose2d pos) {
        return !this.isOutOfBounds(pos) && pieces[pos.row()][pos.col()] == null;
    }

//    public Set<Piece> getAllPiecesList() {
//        Set<Piece> all = whitePieces;
//        all.addAll(blackPieces);
//        return all;
//    }
//
//    public Set<Piece> getWhitePieces() {
//        return whitePieces;
//    }
//
//    public Set<Piece> getBlackPieces() {
//        return blackPieces;
//    }
//
//    public Pose2d getWhiteKingPosition() {
//        return whiteKingPosition;
//    }
//
//    public Pose2d getBlackKingPosition() {
//        return blackKingPosition;
//    }
//
//    public Set<Piece> getPieceListOfPlayer(player player) {
//        if (player == player.WHITE) return whitePieces; else return blackPieces;
//    }
    public boolean movePiece(Piece piece, Pose2d pos) {
        Move move = piece.isMovableTo(pos);
        if (move != null) {
            this.setPiece(piece.position(), null);
            this.setPiece(pos, piece.withPosition(pos));
            return true;
        }
        return false;
    }
    public Piece pieceAtPosition(Pose2d pos) {
        if (this.isOutOfBounds(pos)) {
            return null;
        }
        return pieces[pos.row()][pos.col()];
    }

    public boolean containsEnemy(Pose2d startPos, Pose2d endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        if (isFree(endPos)) {
            return false;
        }
        Player movingPieceColor = pieces[startPos.row()][startPos.col()].player();

        Player receivingPieceColor = pieces[endPos.row()][endPos.col()].player();

        return (movingPieceColor != receivingPieceColor);

    }
    public boolean isOutOfBounds(Pose2d endPos) {
        return endPos.row() >= ChessBoard.ROWS || endPos.row() < 0
                || endPos.col() >= ChessBoard.COLS || endPos.col() < 0;
    }

    public boolean isUnmovedRook(Pose2d pos){
        Piece piece = pieces[pos.row()][pos.col()];
        if(!(piece instanceof Rook)){
            return false;
        }
        Rook tower = (Rook) piece;

        return tower.isMoved();
    }

}
