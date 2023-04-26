package com.example.gui.modelo;

public class Board {
    private Cell[][] board;
    public boolean checkB;
    public boolean checkW;
    public boolean mateW;
    public boolean mateB;

    public Board(){
        this.board = new Cell[8][8];
        this.setupBoard();
        this.checkB = false;
        this.checkW = false;
        this.mateB = false;
        this.mateW = false;
    }
    public void setupBoard(){
        for (int i = 0; i < 8 ;i++){
            for(int j=0;j<8;j++){
                board[i][j] = new Cell();
            }
        }

        //setup black rooks in position
        Rook rookB1 = new Rook(EnumColour.Black,0,0);
        Rook rookB2 = new Rook(EnumColour.Black,0,7);
        this.setPiece(rookB1,rookB1.getPosition());
        this.setPiece(rookB2,rookB2.getPosition());

        //setup White rooks in position
        Rook rookW1 = new Rook(EnumColour.White,7,0);
        Rook rookW2 = new Rook(EnumColour.White,7,7);
        this.setPiece(rookW1,rookW1.getPosition());
        this.setPiece(rookW2,rookW2.getPosition());

        //setup black knights in position
        Knight knightB1 = new Knight(EnumColour.Black,0,1);
        Knight knightB2 = new Knight(EnumColour.Black,0,6);
        this.setPiece(knightB1,knightB1.getPosition());
        this.setPiece(knightB2,knightB2.getPosition());

        //setup White knights in position
        Knight knightW1 = new Knight(EnumColour.White,7,1);
        Knight knightW2 = new Knight(EnumColour.White,7,6);
        this.setPiece(knightW1,knightW1.getPosition());
        this.setPiece(knightW2,knightW2.getPosition());

        //setup black bishops in position
        Bishop bishopB1 = new Bishop(EnumColour.Black,0,2);
        Bishop bishopB2 = new Bishop(EnumColour.Black,0,5);
        this.setPiece(bishopB1,bishopB1.getPosition());
        this.setPiece(bishopB2,bishopB2.getPosition());

        //setup White bishops in position
        Bishop bishopW1 = new Bishop(EnumColour.White,7,2);
        Bishop bishopW2 = new Bishop(EnumColour.White,7,5);
        this.setPiece(bishopW1,bishopW1.getPosition());
        this.setPiece(bishopW2,bishopW2.getPosition());

        //setup black queen in position
        Queen queenB = new Queen(EnumColour.Black,0,3);
        this.setPiece(queenB,queenB.getPosition());

        //setup White queen in position
        Queen queenW = new Queen(EnumColour.White,7,3);
        this.setPiece(queenW,queenW.getPosition());

        //setup black king in position
        King kingB = new King(EnumColour.Black,0,4);
        this.setPiece(kingB,kingB.getPosition());

        //setup white king in position
        King kingW = new King(EnumColour.White,7,4);
        this.setPiece(kingW,kingW.getPosition());

        //setup black pawns in position
        for(int i= 0 ;i<8; i++){
            Pawn pawn = new Pawn(EnumColour.Black,1,i);
            this.setPiece(pawn,pawn.getPosition());
        }
        //setup white pawns in position
        for(int i= 0 ;i<8; i++){
            Pawn pawn = new Pawn(EnumColour.White,6,i);
            this.setPiece(pawn,pawn.getPosition());
        }
    }
    public void setPiece(Piece piece, Position position){

        Cell cell = board[position.getX()][position.getY()];


        if (cell.isEmpty())
            cell.setPiece(piece);
    }
    public boolean movePiece(Position position, Position newPosition){
        Cell cell = board[position.getX()][position.getY()];
        Cell finalCell = board[newPosition.getX()][newPosition.getY()];
        if(cell.getPiece() == null)
            return false;

        Piece piece = cell.getPiece();
        Piece auxPiece = finalCell.getPiece();

        if(auxPiece instanceof King){
            return false;
        }

        this.verifyCheck(piece.colour,this);

        if(!piece.validMove(newPosition,this)) {
            return false;
        }

        if(this.verifyCheck2(piece.colour,position,newPosition)){
            return false;
        }

        return true;
    }
    public void movePieceWChecks(Position position, Position newPosition){
        Cell cell = board[position.getX()][position.getY()];
        Cell finalCell = board[newPosition.getX()][newPosition.getY()];
        if(cell.getPiece() == null)
            return;

        Piece piece = cell.getPiece();
        Piece auxPiece = finalCell.getPiece();
        cell.setPiece(null);
        if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
            finalCell.setPiece(null);
        }

        board[newPosition.getX()][newPosition.getY()].removePiece();
        board[newPosition.getX()][newPosition.getY()].setPiece(piece);
        board[position.getX()][position.getY()].removePiece();
        piece.updatePosition(newPosition);
    }
    public boolean verifyCheck2(EnumColour colour, Position position, Position newPosition){
        Cell cell = board[position.getX()][position.getY()];
        Cell finalCell = board[newPosition.getX()][newPosition.getY()];
        Piece king = this.getKing(colour);
        Piece piece = cell.getPiece();
        Piece auxPiece = finalCell.getPiece();
        cell.setPiece(null);
        if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
            finalCell.setPiece(null);
        }
        board[newPosition.getX()][newPosition.getY()].removePiece();
        board[newPosition.getX()][newPosition.getY()].setPiece(piece);
        board[position.getX()][position.getY()].removePiece();
        piece.updatePosition(newPosition);
        for(int a = 0; a < 8; a++){
            for(int b=0; b < 8; b++){
                Piece piece2 = this.getBoard()[a][b].getPiece();
                if(piece2 != null && piece2.getColour() != colour && piece2.validMove(king.getPosition(),this)){
                    if (colour == EnumColour.Black){
                        checkB= true;
                    }else if(colour == EnumColour.White){
                        checkW = true;
                    }
                    board[position.getX()][position.getY()].removePiece();
                    board[newPosition.getX()][newPosition.getY()].removePiece();
                    board[position.getX()][position.getY()].setPiece(piece);

                    if(auxPiece != null){
                        board[newPosition.getX()][newPosition.getY()].setPiece(auxPiece);
                    }
                    piece.updatePosition(position);
                    if(auxPiece != null){
                        auxPiece.updatePosition(newPosition);
                    }
                    return true;
                }
            }
        }
        checkB= false;
        checkW = false;
        return false;
    }
    public void verifyCheck(EnumColour colour, Board board1){
        Piece king = board1.getKing(colour);
        for(int a = 0; a < 8; a++){
            for(int b=0; b < 8; b++){
                Piece piece = board1.getBoard()[a][b].getPiece();
                if(piece != null && piece.getColour() != colour && piece.validMove(king.getPosition(),board1)){
                    if (colour == EnumColour.Black){
                        checkB= true;
                    }else if(colour == EnumColour.White){
                        checkW = true;
                    }
                    return;
                }
            }
        }
        checkB= false;
        checkW = false;
    }
    public Piece getKing(EnumColour colour){
        for(int a = 0; a < 8; a++){
            for(int b=0; b < 8; b++){
                if(board[a][b].getPiece() != null && board[a][b].getPiece() instanceof King && board[a][b].getPiece().getColour() == colour){
                    return board[a][b].getPiece();
                }
            }
        }
        return null;
    }
    public boolean castle(Position position,Position newPosition){
        Cell cellKing = board[position.getX()][position.getY()];
        Cell finalCellKing = board[newPosition.getX()][newPosition.getY()];
        Cell cellRook;
        Cell finalCellRook;
        Cell aux1;
        Cell aux2;
        Cell aux3;
        Position finalPositionRook;

        if(cellKing.getPiece() == null) {
            return false;
        }
        Piece pieceKing = cellKing.getPiece();
        Piece pieceRook ;

        if(!(pieceKing instanceof King)){
            return false;
        }

        if(!pieceKing.firstMove){
            return false;
        }
        if(pieceKing.colour == EnumColour.White){
            if(newPosition.getY() == 2){
                cellRook = board[7][0];
                finalCellRook = board[7][3];
                finalPositionRook = new Position(7,3);
                pieceRook = cellRook.getPiece();
                if(!(pieceRook instanceof Rook) || !pieceRook.firstMove){
                    return false;
                }
                aux1 = board[7][1];
                aux2 = board[7][2];
                aux3 = board[7][3];
                if(aux1.getPiece() != null || aux2.getPiece() != null || aux3.getPiece() != null){
                    return false;
                }
                cellKing.setPiece(null);
                cellRook.setPiece(null);
                board[newPosition.getX()][newPosition.getY()].setPiece(pieceKing);
                board[7][3].setPiece(pieceRook);
                pieceKing.updatePosition(newPosition);
                position.updatePosition(newPosition);
                pieceRook.updatePosition(finalPositionRook);
                position.updatePosition(finalPositionRook);
                return true;
            }
            if(newPosition.getY() == 6){
                cellRook = board[7][7];
                finalCellRook = board[7][5];
                finalPositionRook = new Position(7,5);
                pieceRook = cellRook.getPiece();
                if(!(pieceRook instanceof Rook) || !pieceRook.firstMove){
                    return false;
                }
                aux1 = board[7][5];
                aux2 = board[7][6];
                if(aux1.getPiece() != null || aux2.getPiece() != null){
                    return false;
                }
                cellKing.setPiece(null);
                cellRook.setPiece(null);
                board[newPosition.getX()][newPosition.getY()].setPiece(pieceKing);
                board[7][5].setPiece(pieceRook);
                pieceKing.updatePosition(newPosition);
                position.updatePosition(newPosition);
                pieceRook.updatePosition(finalPositionRook);
                position.updatePosition(finalPositionRook);
                return true;
            }
        }
        if(pieceKing.colour == EnumColour.Black){
            if(newPosition.getY() == 2){
                cellRook = board[0][0];
                finalCellRook = board[0][3];
                finalPositionRook = new Position(0,3);
                pieceRook = cellRook.getPiece();
                if(!(pieceRook instanceof Rook) || !pieceRook.firstMove){
                    return false;
                }
                aux1 = board[0][1];
                aux2 = board[0][2];
                aux3 = board[0][3];
                if(aux1.getPiece() != null || aux2.getPiece() != null || aux3.getPiece() != null){
                    return false;
                }
                cellKing.setPiece(null);
                cellRook.setPiece(null);
                board[newPosition.getX()][newPosition.getY()].setPiece(pieceKing);
                board[0][3].setPiece(pieceRook);
                pieceKing.updatePosition(newPosition);
                position.updatePosition(newPosition);
                pieceRook.updatePosition(finalPositionRook);
                position.updatePosition(finalPositionRook);
                return true;
            }
            if(newPosition.getY() == 6){
                cellRook = board[0][7];
                finalCellRook = board[0][5];
                finalPositionRook = new Position(0,5);
                pieceRook = cellRook.getPiece();
                if(!(pieceRook instanceof Rook) || !pieceRook.firstMove){
                    return false;
                }
                aux1 = board[0][5];
                aux2 = board[0][6];
                if(aux1.getPiece() != null || aux2.getPiece() != null){
                    return false;
                }
                cellKing.setPiece(null);
                cellRook.setPiece(null);
                board[newPosition.getX()][newPosition.getY()].setPiece(pieceKing);
                board[0][5].setPiece(pieceRook);
                pieceKing.updatePosition(newPosition);
                position.updatePosition(newPosition);
                pieceRook.updatePosition(finalPositionRook);
                position.updatePosition(finalPositionRook);
                return true;
            }
        }

        return false;
    }

    /*
     *Posiçao da peça a mover
     * Posicao da peça final
     * Nr correspondente a peça a ser promovido(1-Rainha,2-Torre,3-Bispo,4-Cavalo)
     */
    public void pawnPromotion(Position position, Position newPosition, int numeroCorrespondenteAPeca){
        Cell cell = board[position.getX()][position.getY()];
        Cell finalCell = board[newPosition.getX()][newPosition.getY()];
        if(cell.getPiece() == null) {
            return;
        }

        Piece piece = cell.getPiece();
        Piece auxPiece = finalCell.getPiece();

        if(auxPiece instanceof King){
            return;
        }

        if(!(piece instanceof Pawn)){
            return;
        }

        if(!piece.validMove(newPosition,this)) {
            return;
        }

        if(piece.colour == EnumColour.White && newPosition.getX() != 0){
            return;
        }

        if(piece.colour == EnumColour.Black && newPosition.getX() != 7){
            return;
        }
        if(numeroCorrespondenteAPeca == 1){
            Queen queen = new Queen(piece.colour,newPosition.getX(), newPosition.getY());
            cell.setPiece(null);
            if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
                finalCell.setPiece(null);
            }
            board[newPosition.getX()][newPosition.getY()].setPiece(queen);
            piece.updatePosition(newPosition);
            position.updatePosition(newPosition);
        }
        if(numeroCorrespondenteAPeca == 2){
            Rook rook = new Rook(piece.colour,newPosition.getX(), newPosition.getY());
            cell.setPiece(null);
            if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
                finalCell.setPiece(null);
            }
            board[newPosition.getX()][newPosition.getY()].setPiece(rook);
            piece.updatePosition(newPosition);
            position.updatePosition(newPosition);
        }
        if(numeroCorrespondenteAPeca == 3){
            Bishop bishop = new Bishop(piece.colour,newPosition.getX(), newPosition.getY());
            cell.setPiece(null);
            if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
                finalCell.setPiece(null);
            }
            board[newPosition.getX()][newPosition.getY()].setPiece(bishop);
            piece.updatePosition(newPosition);
            position.updatePosition(newPosition);
        }
        if(numeroCorrespondenteAPeca == 4){
            Knight knight = new Knight(piece.colour,newPosition.getX(), newPosition.getY());
            cell.setPiece(null);
            if(finalCell.getPiece() != null && getPiece(newPosition).colour != piece.colour){
                finalCell.setPiece(null);
            }
            board[newPosition.getX()][newPosition.getY()].setPiece(knight);
            piece.updatePosition(newPosition);
            position.updatePosition(newPosition);
        }

    }
    public void checkMate(Board board){
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                Piece piece = board.getBoard()[a][b].getPiece();
                if (piece != null && piece.getColour() == EnumColour.Black) {
                    Position position = piece.getPosition();
                    for (int c = 0; c < 8; c++) {
                        for (int d = 0; d < 8; d++) {
                            Position newPosition = new Position(c,d);
                            Cell finalCell = this.board[newPosition.getX()][newPosition.getY()];
                            Piece auxPiece = finalCell.getPiece();
                            movePiece(position,newPosition);
                            if(!checkB){
                                movePieceWChecks(newPosition,position);
                                if (auxPiece != null){
                                    this.board[newPosition.getX()][newPosition.getY()].setPiece(auxPiece);
                                }
                                verifyCheck(EnumColour.Black,this);
                                return;
                            }
                        }
                    }
                }
            }
        }
        mateB = true;
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                Piece piece = board.getBoard()[a][b].getPiece();
                if (piece != null && piece.getColour() == EnumColour.White) {
                    Position position = piece.getPosition();
                    for (int c = 0; c < 8; c++) {
                        for (int d = 0; d < 8; d++) {
                            Position newPosition = new Position(c,d);
                            Cell finalCell = this.board[newPosition.getX()][newPosition.getY()];
                            Piece auxPiece = finalCell.getPiece();
                            movePiece(position,newPosition);
                            if(!checkW){
                                movePieceWChecks(newPosition,position);
                                if (auxPiece != null){
                                    this.board[newPosition.getX()][newPosition.getY()].setPiece(auxPiece);
                                }
                                verifyCheck(EnumColour.White,this);
                                return;
                            }
                        }
                    }
                }
            }
        }
        mateW = true;
    }
    public Cell[][] getBoard(){
        return this.board;
    }
    public Piece getPiece(Position position){
        return this.board[position.getX()][position.getY()].getPiece();
    }
    public void removePiece(Position position){
        board[position.getX()][position.getY()].removePiece();
    }
    public void clearBoard(){

        for (int i = 0; i < 8 ;i++){
            for(int j=0;j<8;j++) {
                board[i][j].setPiece(null);
            }
        }
    }
    public void display(){
        for (int i = 0; i < 8 ;i++){
            for(int j=0;j<8;j++){
                board[i][j].display();
            }
            System.out.println();
        }
    }
}
