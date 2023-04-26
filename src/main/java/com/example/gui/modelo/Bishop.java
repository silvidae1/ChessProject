package com.example.gui.modelo;

public class Bishop extends Piece{

    public Bishop(EnumColour cor, int pos_X, int pos_Y) {
        super(cor, pos_X, pos_Y);
    }

    @Override
    public boolean validMove(Position position, Board board) {
        // casas que vai andar
        int xDif = this.getPosition().getX() - position.getX();
        int yDif = this.getPosition().getY() - position.getY();
        int rowoffset = 0;
        int coloffset = 0;
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        Position intposition;

        if (x == position.getX() && y == position.getY())
            return false;

        // so mexe na diagonal
        if(xDif == 0 || yDif == 0 || Math.abs(xDif) != Math.abs(yDif))
            return false;

        if(board.getPiece(position) != null && this.colour == board.getPiece(position).colour)
            return false;

        if(Math.abs(xDif) == Math.abs(yDif)){
            //verify if it needs to go up or down
            if(xDif < 0) {
                rowoffset = 1;
            }else{
                rowoffset = -1;
            }
            //verify if it needs to go left or right
            if(yDif < 0) {
                coloffset = 1;
            }else{
                coloffset = -1;
            }

            y += coloffset;
            for(x += rowoffset; x != position.getX(); x += rowoffset){
                intposition = new Position( x, y);
                if(board.getPiece(intposition) != null)
                    return false;
                y += coloffset;
            }
        }
        firstMove = false;
        return true;
    }

    @Override
    public void display() {
        System.out.print('B');
    }

}
