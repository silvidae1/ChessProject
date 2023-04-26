package com.example.gui.modelo;

public class Knight extends Piece{

    public Knight(EnumColour cor, int pos_X, int pos_Y) {
        super(cor, pos_X, pos_Y);
    }

    @Override
    public boolean validMove(Position position, Board board) {

        int xDif = this.getPosition().getX() - position.getX();
        int yDif = this.getPosition().getY() - position.getY();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if (x == position.getX() && y == position.getY())
            return false;

        if(board.getPiece(position) != null && this.colour == board.getPiece(position).colour)
            return false;

        if(xDif == 0 || yDif == 0)
            return false;

        if(Math.abs(xDif) == 1 && Math.abs(yDif) ==2 ) {
            return true;
        }
        if(Math.abs(xDif) == 2 && Math.abs(yDif) ==1){
            return true;
        }

        return false;
    }

    @Override
    public void display() {
        System.out.print('N');
    }
}
