package com.example.gui.modelo;

public class Player {
    String name;
    String password;

    EnumColour colour;

    boolean playerTurn;

    int score;
    int gamesPlayed;
    int victories;
    int defeats;
    int draws;

    public Player(String name){
        this.name = name;
    }

    public EnumColour getColour() {
        return colour;
    }

    public void setColour(EnumColour colour) {
        this.colour = colour;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
