package com.example.blokusstatetest;

import android.graphics.Matrix;
import android.graphics.Path;

/**
 * Game State for the Blokus game. Provides information about the game as well as a constructor
 * and deep copy constructor for all of the information being supplied and initialized.
 *
 * @author Max Clark, Skyelar Cann, Gavin Raguindin
 * @version March 15th 2022
 */
public class BlokusGameState {

    /** Declaration of an enum type representing the states a certain tile can have*/
    public enum tileState{
        EMPTY, LEGAL, RED, BLUE, YELLOW, GREEN
    }

    /** Declaration of instance variables*/
    private int[] playerTurn;
    private int[] playerScore;
    private BlokusBlock [][] blockArray; //Represents each players collection of pieces
    private tileState[][] board;
    private boolean gameOn;

    /** Default ctor */
    public BlokusGameState() {

        /** Array containing the expected ids for checking player turn */
        this.playerTurn = new int[] {1,2,3,4};

        /** Array for holding player scores */
        this.playerScore = new int[] {0,0,0,0};

        /**
         *  Array containing the block objects within each player box
         *  and each player's box will be populated with the appropriate blocks
         */
        blockArray = new BlokusBlock[4][21];
        for(int i = 0; i<4; i++) {
            for (int j = 0; j<21; j++) {
                        this.blockArray[i][j] = new BlokusBlock();
                        this.blockArray[i][j].setType(j);
                }
            }

        /**
         *  Array representing the board and the legal moves within it
         *  and all values are initially set to EMPTY
         */
        this.board = new tileState[20][20];
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                this.board[i][j] = tileState.EMPTY;
            }
        }
    }

    /**
     *  Deep copy ctor
     *
     * @param toCopy
     */
    public BlokusGameState(BlokusGameState toCopy) {

        /**
         *  Copy process for the player turn array
         *  Starts by initializing a new array then copies contents over
         */
        this.playerTurn = new int[4];
        for(int i = 0; i<4; i++) {
            this.playerTurn[i] = toCopy.playerTurn[i];
        }

        /**
         *  Copy process for the player scores
         *  Starts by initializing a new array then copies contents over
         */
        this.playerScore = new int[4];
        for (int i = 0; i<4; i++) {
            this.playerScore[i] = toCopy.playerScore[i];
        }

        /**
         *  Copy process for the array of blocks
         *  Starts by initializing a new array then copies contents over
         */
        this.blockArray = new BlokusBlock[4][21];
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<21; j++) {
                this.blockArray[i][j] = toCopy.blockArray[i][j];
            }
        }

        /**
         *  Copies array over from orig state by going cell by cell to set the values;
         */
        this.board = new tileState[20][20];
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                this.board[i][j] = toCopy.board[i][j];
            }
        }
    }

    /**
     *  This will change the gameOn boolean to take steps to quit
     *  the game
     *
     * @param initGameOn
     *
     * @return boolean
     */
    public boolean quitGame(boolean initGameOn) {
        this.gameOn = initGameOn;
        if (gameOn = false) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *  This method will be responsible for handling the display of the help menu once the
     *  game has access to the game framework and will return false for now
     *
     * @return boolean
     */
    public boolean helpMenu() {
        return false;
    }
    /**
     *  This will check to see if a legal move is at the current touch position
     *  and then will increment the appropriate players score based on the
     *  piece placed
     *
     * @param playerNum
     * @param xPos
     * @param yPos
     * @param pieceNum
     *
     * @return boolean
     */
    public boolean placePiece(int playerNum, int xPos, int yPos, int pieceNum) {
        if (this.board[xPos][yPos]==tileState.EMPTY) {
            this.playerScore[playerNum] += this.blockArray[playerNum][pieceNum].getBlockScore();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This will rotate a player's given piece 90 degrees counterclockwise given that it is the
     * player's turn.
     *
     * @param player
     * @param piece
     * @param midX
     * @param midY
     *
     * @return boolean
     */
    public boolean rotatePiece(int player, Path piece, int midX, int midY) {
        //Switches to true if it is given players turn
        boolean legal = false;
        //Matrix that will rotate pieces 90 degrees
        Matrix midPoint = new Matrix();
        midPoint.setRotate(90.0f, midX, midY);

        for (int i = 0; i < playerTurn.length; i++) {
            if (playerTurn[i] == player) {
                piece.transform(midPoint);
                legal = true;
            }
        }
        return legal;
    }

    public tileState[][] getBoard() { return this.board; }

    /**
     * This will return a string version of the entire BlokusGameState
     */
    @Override
    public String toString() {
        String gameStateInfo;

        gameStateInfo = "Game status is: " + gameOn + "\n";

        /** Concatenates gameStateInfo with the scores of all the players */
        for (int i = 0; i < playerScore.length; i++) {
            gameStateInfo += "Player " + (i+1) + " score: " + playerScore[i] + "\n";
        }

        /** Concatenates gameStateInfo with the turn strings of all the players */
        for (int i = 0; i < playerTurn.length; i++) {
            gameStateInfo += "Player " + playerTurn[i] + "'s turn. \n";
        }

        /** Concatenates gameStateInfo with the string versions of each block */
        for(int i = 0; i<4; i++) {
            for(int j = 0; j<21; j++) {
                gameStateInfo += this.blockArray[i][j].toString() + "\n";
            }
        }

        /** Concatenates gameStateInfo with array of legal moves on the board */
        gameStateInfo += "Board of tile states: \n";
        for(int i = 0; i<20; i++) {
            for(int j = 0; j<20; j++) {
                    gameStateInfo += this.board[i][j].toString() + " ";
                }
                gameStateInfo += "\n";
            }
        return gameStateInfo;
    }
}