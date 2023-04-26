package com.example.gui.modelo;

public class King extends Piece{
    public boolean check = false;
    //public boolean firstMove = true;
    public King(EnumColour cor, int pos_X, int pos_Y) {
        super(cor, pos_X, pos_Y);
    }

    @Override
    public boolean validMove(Position position, Board board) {

        //casas que anda
        int xDif = this.getPosition().getX() - position.getX();
        int yDif = this.getPosition().getY() - position.getY();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        if (x == position.getX() && y == position.getY())
            return false;

        //se for mais que 1 casa na horizontal e na vertical
        if(Math.abs(xDif) > 1 || Math.abs(yDif) > 1)
            return false;

        //se for mais que uma casa na diagonal
        if (Math.abs(xDif)+Math.abs(yDif) > 2)
            return false;

        //se tiver uma peça na nova posiçao
        if (board.getPiece(position) != null && this.colour == board.getPiece(position).getColour())
            return false;


        firstMove = false;
        return true;

    }
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public void display() {
        System.out.print('K');
    }
}