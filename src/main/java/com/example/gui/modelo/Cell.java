package com.example.gui.modelo;

public class Cell {
    private EnumColour colour;
    private Piece piece = null;

    public Cell(){

    }
    public Piece getPiece(){
        return this.piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void removePiece(){
        this.piece = null;
    }
    boolean isEmpty(){
        return piece == null;
    }
    public void display(){
        if(this.isEmpty()){
            System.out.print('_');
        }else{
            this.piece.display();
        }
    }
}
