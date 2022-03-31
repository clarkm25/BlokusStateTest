package com.example.blokusstatetest;

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
    private int playerTurn;
    private int[] playerScore;
    private BlokusBlock [][] blockArray; //Represents each players collection of pieces
    private tileState[][] board;
    private boolean gameOn;

    /** Default ctor */
    public BlokusGameState() {

        /* int containing the current player's turn*/
        this.playerTurn = 1;

        /* Array for holding player scores */
        this.playerScore = new int[] {0,0,0,0};

        /* Array containing the block objects within each player box and each player's box will be populated with the appropriate blocks */
        blockArray = new BlokusBlock[4][21];
        for(int i = 0; i<4; i++) {
            for (int j = 0; j<21; j++) {
                        this.blockArray[i][j] = new BlokusBlock();
                        this.blockArray[i][j].setType(j);
                }
            }

        /* Array representing the board and the legal moves within it and all values are initially set to EMPTY */
        this.board = new tileState[20][20];
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                this.board[i][j] = tileState.EMPTY;
            }
        }
        this.gameOn = true;
    }

    /**
     *  Deep copy ctor
     *
     * @param toCopy given BlokusGameState to copy
     */
    public BlokusGameState(BlokusGameState toCopy) {

        /* Copy process for the player turn setting the playerTurn to the other state's playerTurn */
        this.playerTurn = toCopy.playerTurn;

        /* Copy process for the player scores Starts by initializing a new array then copies contents over */
        this.playerScore = new int[4];
        for (int i = 0; i<4; i++) {
            this.playerScore[i] = toCopy.playerScore[i];
        }

        /* Copy process for the array of blocks Starts by initializing a new array then copies contents over */
        this.blockArray = new BlokusBlock[4][21];
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<21; j++) {
                this.blockArray[i][j] = new BlokusBlock(toCopy.blockArray[i][j]);
            }
        }

        /* Copies array over from orig state by going cell by cell to set the values */
        this.board = new tileState[20][20];
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                this.board[i][j] = toCopy.board[i][j];
            }
        }
        this.gameOn = toCopy.gameOn;
    }

    /**
     *  This will change the gameOn boolean to take steps to quit
     *  the game
     *
     * @param initGameOn given boolean to quit game
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
     * @param playerTurn given player turn
     * @param xPos given x position
     * @param yPos given y position
     * @param piece given BlokusBlock
     *
     * @return boolean
     */
    public boolean placePiece(int playerTurn, int xPos, int yPos, BlokusBlock piece)
    {
        /* Variables to represent the relative x and y coords as well as the current playerState*/
        int relX = 0;
        int relY = 0;
        tileState playerState = getTileStateForId(playerTurn);

        if(piece == null) //If passed piece is null (already placed) returns false
        {
            return false;
        }

        /* Iterates through the board to find the relative x and y coordinates */
        if (this.board[xPos][yPos] == tileState.LEGAL)
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    if (piece.getPieceArr()[i][j] == 2)
                    {
                        relX = j;
                        relY = i;
                    }
                    else
                    {
                        continue;
                    }
                }
            }
            try
            {
                /* Then, iterates through the board again to place the piece on the board according to the playerNum*/
                for (int i = 0; i < 5; i++)
                {
                    for (int j = 0; j < 5; j++)
                    {
                        if (piece.getPieceArr()[i][j] == 0)
                        {
                            continue;
                        }
                        else
                        {
                            board[yPos + i - relY][xPos + j - relX] = playerState;
                        }
                    }
                }
                this.playerScore[playerTurn-1] += this.blockArray[playerTurn-1][piece.getType()].getBlockScore();
                this.blockArray[playerTurn-1][piece.getType()] = null;
                for(int i = 0; i<20; i++)
                {
                    for(int j = 0; j<20; j++)
                    {
                        if(board[i][j] == tileState.LEGAL)
                        {
                            board[i][j] = tileState.EMPTY;
                        }
                    }
                }
                return true;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                return false;
            }
        }
        return false;
    }

    /**
     * This will rotate a player's given piece 90 degrees clockwise given that it is the
     * player's turn.
     *
     * @param piece given BlokusBlock
     *
     * @return boolean
     */
    public boolean rotatePiece(BlokusBlock piece)
    {
        /* First, will perform a swap along a diagonal line like y=-x where 2,2 is the "origin" */
        for(int i = 0; i<5; i++)
        {
            for (int j = 1; j<5; j++)
            {
              int temp = piece.getPieceArr()[i][j];
              piece.getPieceArr()[i][j] = piece.getPieceArr()[j][i];
              piece.getPieceArr()[j][i] = temp;
            }
        }

        /* Then, will swap the array elements across a vertical line going down the middle of the array */
        int n = 4;
        for (int i = 0; i<5; i++)
        {
            for(int j = 0; j<5; j++)
            {
                 int temp = piece.getPieceArr()[i][j];
                 piece.getPieceArr()[i][j] = piece.getPieceArr()[i][n];
                 piece.getPieceArr()[i][n] = temp;
                 n--;
                 if(n==2)
                 {
                     n=4;
                     break;
                 }
            }
        }
        return true;
    }

    /**
     * This will iterate through the given board to calculate legal moves based off of
     * a given piece's orientation using helper methods to cut down on length
     *
     * @param board given board
     * @param playerTurn given player turn
     *
     * @return boolean
     */
    public boolean calcLegalMoves(tileState[][] board, int playerTurn)
    {
        int numChanged = 0; //int representing the number of legal moves successfully calculated and changed
        tileState playerState = getTileStateForId(playerTurn);
        if(beginningGameCheck(playerTurn))
        {
            numChanged++;
        }
        for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                if (i < 19) //This check will run as long as i is to the left of the right most column
                {
                    if(checkNeighbor(board,playerTurn,i,j,1,1)) //If successful increment numChanged
                    {
                        numChanged++;
                    }
                }
                if (i > 0) //This check will run as long as i is to the right of the left most column
                {
                    if(checkNeighbor(board,playerTurn,i,j,1,-1))
                    {
                        numChanged++;
                    }
                }
                if(j< 19) //This check will run as long as j is to the left of the right most column
                {
                    if(checkNeighbor(board,playerTurn,i,j,-1,1))
                    {
                        numChanged++;
                    }
                }
                if(j>0) //This check will run as long as j is to the right of the left most column
                {
                    if(checkNeighbor(board,playerTurn,i,j,-1,-1))
                    {
                        numChanged++;
                    }
                }
            }
        }

        if(numChanged > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This will be the main helper method for calculating legal moves by going through a set of
     * checks that will check appropriate neighbors based on passed in ints
     *
     * @param board given board
     * @param playerTurn given player turn
     * @param yPos given y position
     * @param xPos given x position
     * @param yDelta given y shift
     * @param xDelta given x shift
     *
     * @return boolean
     */
    public boolean checkNeighbor(tileState[][] board, int playerTurn, int yPos, int xPos, int yDelta, int xDelta) {
        tileState playerState = getTileStateForId(playerTurn);

        if ((board[yPos][xPos] == playerState //Checks to see if specified tile matches playerState
                && board[yPos + yDelta][xPos]!=playerState && board[yPos][xPos + xDelta]!=playerState)//Checks the tile below and tile to the right to see if they are not equal to the playerState
                && board[yPos + yDelta][xPos + xDelta] == tileState.EMPTY) //Finally, tile to the bottom right must be empty
        {
            board[yPos+yDelta][xPos+xDelta] = tileState.LEGAL;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This will be ran everytime that calcLegalMoves is called to catch the beginning game
     * conditions where the corners of the board are EMPTY and will set the appropriate
     * legal positions based on whose turn it is
     *
     * @param playerTurn given player turn
     *
     * @return boolean
     */
    public boolean beginningGameCheck(int playerTurn)
    {
        tileState playerState = getTileStateForId(playerTurn);
        switch(playerState) //Checks the appropriate playerState to set the needed corner to a legal tile
        {
            case RED:
                if(this.board[0][0] == tileState.EMPTY)
                {
                    this.board[0][0] = tileState.LEGAL;
                    return true;
                }
                break;

            case BLUE:
                if(this.board[0][19] == tileState.EMPTY)
                {
                    this.board[0][19] = tileState.LEGAL;
                    return true;
                }
                break;

            case GREEN:
                if(this.board[19][0] == tileState.EMPTY)
                {
                    this.board[19][0] = tileState.LEGAL;
                    return true;
                }
                break;

            case YELLOW:
                if(this.board[19][19] == tileState.EMPTY)
                {
                    this.board[19][19] = tileState.LEGAL;
                    return true;
                }
                break;

            default:
                return false;
        }
        return false;
    }

    /**
     * This will be another helper method that checkNeighbor utilizes to get a specific player's
     * block color by their ID that is passed in
     *
     * @param playerTurn given player turn
     *
     * @return tileState
     */
    public tileState getTileStateForId(int playerTurn) {
        //Based on playerTurn, returns specific tileStates where red is associated with 1 and so on
        switch(playerTurn) {
            case 1:
                return tileState.RED;
            case 2:
                return tileState.BLUE;
            case 3:
                return tileState.GREEN;
            case 4:
                return tileState.YELLOW;
            default: //Something invalid passed in, returning null
                return null;
        }
    }

    /**
     * Getter methods for the game state
     */
    public BlokusBlock[][] getBlockArray() { return this.blockArray; }
    public tileState[][] getBoard() { return this.board; }


    /**
     * This will return a string version of the entire BlokusGameState
     */
    @Override
    public String toString() {
        String gameStateInfo;

        gameStateInfo = "Game status is: " + gameOn + "\n";

        /* Concatenates gameStateInfo with the scores of all the players */
        for (int i = 0; i < playerScore.length; i++) {
            gameStateInfo += "Player " + (i+1) + " score: " + playerScore[i] + "\n";
        }

        /* Concatenates gameStateInfo with the turn strings of all the players */
            gameStateInfo += "Player " + playerTurn + "'s turn. \n";

        /* Concatenates gameStateInfo with the string versions of each block */
        for(int i = 0; i<4; i++) {
            for(int j = 0; j<21; j++) {
                if(this.blockArray[i][j] != null)
                {
                    gameStateInfo += this.blockArray[i][j].toString() + "\n";
                }
                else
                {
                    gameStateInfo += "This block is null!\n\n";
                }
            }
        }

        /* Concatenates gameStateInfo with array of legal moves on the board */
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