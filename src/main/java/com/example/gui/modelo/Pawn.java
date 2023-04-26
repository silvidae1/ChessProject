package com.example.gui.modelo;

public class Pawn extends Piece{

    private boolean firstMove = true;
    private boolean promotion = false;

    public Pawn(EnumColour cor, int pos_X, int pos_Y) {
        super(cor, pos_X, pos_Y);
    }

    @Override
    public boolean validMove(Position position,Board board) {

        //pawn can move more than one piece if first move
        int xDif = this.getPosition().getX() - position.getX();
        int yDif = this.getPosition().getY() - position.getY();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if (x == position.getX() && y == position.getY())
            return false;

        //if he's a black piece - only go down
        if  (this.colour == EnumColour.Black){
            if(xDif > 0)
                return false;
        }

        //if he is white - only go up
        if (this.colour == EnumColour.White){
            if(xDif < 0)
                return false;
        }

        //if move more than two
        if(!firstMove) {
            if (Math.abs(xDif) >= 2)
                return false;
            if (Math.abs(yDif) >= 2)
                return false;
        }

        //if he's on his first move
        if(firstMove) {
            if (Math.abs(xDif) > 2)
                return false;
            if  (Math.abs(yDif) > 2)
                return false;
        }
        // nunca pode andar na horizontal
        if(xDif == 0 && yDif != 0)
            return false;

        //se movimento diagonal
        if(xDif != 0 && yDif != 0){
            if (Math.abs(xDif) > 1 || Math.abs(yDif) > 1)
                return false;
            //se não tem uma peça é inválido :)
            if (board.getPiece(position) == null) {
                return false;
            }
            //se pertencer a mesma equipa :)
            if (this.colour == board.getPiece(position).getColour()) {
                return false;
            }
        }
        //se andar em frente e tiver uma peça
        if(board.getPiece(position)!= null &&(xDif == 0 || yDif == 0))
            return false;


        firstMove = false;

        return true;
    }

    @Override
    public void display() {
        System.out.print('P');
    }
}
