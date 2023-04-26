package com.example.gui.modelo;

import java.text.ParsePosition;

public abstract class Piece {
    protected EnumColour colour;
    public boolean firstMove = true;
    private boolean dead;
    private boolean selected ;
    private Board board;
    private Position position;
    public Piece(EnumColour colour, int x, int y) {
        this.colour = colour;
        this.position = new Position(x,y);
        this.dead = false;
        this.selected = false;
    }

    public abstract boolean validMove(Position position,Board board);
    public abstract void display();
    public Position getPosition() {
        return position;
    }
    public void updatePosition(Position position){
        this.position = position;
    }

    public EnumColour getColour(){
        return this.colour;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isSelected() {
        return this.selected;
    }

}
