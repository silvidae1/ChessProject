package com.example.gui.modelo;

public class Rook extends Piece{
    //private boolean firstMove = true;

    public Rook(EnumColour cor, int pos_X, int pos_Y) {
        super(cor, pos_X, pos_Y);
    }

    @Override
    public boolean validMove(Position position, Board board) {


        int xDif = this.getPosition().getX() - position.getX();
        int yDif = this.getPosition().getY() - position.getY();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        int offset = 0;
        Position intposition;

        if (x == position.getX() && y == position.getY())
            return false;

        if(xDif != 0 && yDif != 0)
            return false;

        if(board.getPiece(position) != null && this.colour == board.getPiece(position).colour)
            return false;

        if(xDif != 0){
            if(xDif < 0) {
                offset = 1;
            }else{
                offset = -1;
            }

            for(x += offset; x != position.getX(); x += offset){
                intposition = new Position(x, y);
                if(board.getPiece(intposition) != null)
                    return false;
            }
        }

        //verifica as linhas
        if(yDif != 0){
            if(yDif < 0) {
                offset = 1;
            }else{
                offset = -1;
            }

            for(y += offset; y != position.getY(); y += offset){
                intposition = new Position(x, y);
                if(board.getPiece(intposition) != null)
                    return false;
            }
        }
        firstMove = false;
        return true;

    }

    @Override
    public void display() {
        System.out.print('R');
    }
}

