package com.example.gui.modelo;

public class Game {
    Board board;
    Player player1;
    Player player2;

    public Game(Board board, Player player1,Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setPlayerColour1(){
        // da set da 1 combinaçao possivel
        player1.setColour(EnumColour.White);
        player2.setColour(EnumColour.Black);
    }
    public void setPlayerColour2(){
        //da set da 2 combinaçao possivel
        player1.setColour(EnumColour.Black);
        player2.setColour(EnumColour.White);
    }
    public void run(){
        long a = Math.round(Math.random());
        if (a == 0)
            setPlayerColour1();
        if (a == 1)
            setPlayerColour2();

        if(player1.playerTurn == true);
    }
}
