package com.example.tictactoe;

import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int [][] gameBoard;
    private int player =1;



    private String[] names = {"Player1", "Player2"};

    private Button playAgain;
    private Button home;
    private TextView playerDisplay;

    GameLogic(){
        gameBoard = new int[3][3];

        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                gameBoard[r][c] = 0; //these spots are available within the gameboard
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] ==0) {
            gameBoard[row-1][col-1] = player;

            if(player ==1){
                playerDisplay.setText((names[1] + "'s turn!"));
            }
            else{
                playerDisplay.setText((names[0] + "'s turn!"));
            }

            return true;
        }
        else{
            return false;
        }
    }

    public boolean winnerCheck(){
        boolean isWinner=false; //by default the a winner is false until proven

        //this is if there's a win horizontally
        for(int r=0; r<3; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] ==gameBoard[r][2] &&
            gameBoard[r][0] !=0) {
                isWinner=true;
            }
        }

        //this is to figure if there's a win vertically
        for(int c=0; c<3; c++){
            if(gameBoard[0][c]==gameBoard[1][c] && gameBoard[2][0] ==gameBoard[0][c]
            && gameBoard[0][c] !=0){
                isWinner=true;
            }
        }

        //forward diagonal
       if(gameBoard[0][0]==gameBoard[1][1] &&gameBoard[0][0]==gameBoard[2][2] &&
       gameBoard[0][0]!=0){
           isWinner = true;
       }
        //forward backward diagonal
        if(gameBoard[2][2] == gameBoard[1][1] && gameBoard[2][2]==gameBoard[0][0] &&
        gameBoard[2][2]!=0){
            isWinner=true;
        }

        int boardFilled =0;
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                if(gameBoard[r][c] !=0){
                    boardFilled++;
                }
            }
        }

        if(isWinner){
            playAgain.setEnabled(true);
            home.setEnabled(true);
            playerDisplay.setText((names[player-1] + " Won!!!!"));
            return true;
        }
        else if(boardFilled==9){
            playAgain.setEnabled(true);
            home.setEnabled(true);
            playerDisplay.setText("Tie Game!");
            return true;
        }

        else{
            return false;
        }
    }

    public void resetGameBoard(){
        gameBoard = new int[3][3];
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                gameBoard[r][c]=0;
            }
        }

        //reset variables over again for
        player=1;
        playAgain.setEnabled(false);
        home.setEnabled(false);
        playerDisplay.setText((names[0] + "'s turn"));
    }

    //SETTERS
    public void setPlayAgain(Button playAgain) {
        this.playAgain = playAgain;
    }

    public void setHome(Button home) {
        this.home = home;
    }

    public void setPlayerDisplay(TextView playerDisplay) {
        this.playerDisplay = playerDisplay;
    }

    public int[][] getGameBoard(){
        return gameBoard;
    }
    public void setNames(String[] names) {
        this.names = names;
    }
        //SET PLAYER TO VALUE SO IT CAN BE USED WITHIN THE
    public void setPlayer(int player){
        this.player=player;
    }

    public int getPlayer(){
        return player;
    }

    }
