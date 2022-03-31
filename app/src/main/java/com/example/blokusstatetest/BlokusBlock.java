package com.example.blokusstatetest;

/**
 * Class for each of the Blokus block pieces.
 *
 * @author Max Clark, Skyelar Cann, Gavin Raguindin
 * @version February 24th 2022
 */
public class BlokusBlock {
    private int type;
    private int blockScore;
    private int[][] pieceArr;

    /** No param ctor */
    public BlokusBlock()
    {
        this.type = 5;
        this.blockScore = 4;
        this.pieceArr = new int[5][5];
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<5; j++)
            {
                this.pieceArr[i][j] = 0;
            }
        }
        this.pieceArr[0][0] = 2;
        this.pieceArr[0][1] = 1;
        this.pieceArr[1][0] = 1;
        this.pieceArr[1][1] = 1;
    }

    public BlokusBlock(BlokusBlock toCopy)
    {
        this.type = toCopy.type;
        this.blockScore = toCopy.blockScore;
        this.pieceArr = new int[5][5];
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<5; j++)
            {
                this.pieceArr[i][j] = toCopy.pieceArr[i][j];
            }
        }
    }
    /** Getters of BlokusBlock variables */
    public int getType()
    {
        return this.type;
    }

    public int getBlockScore()
    {
        return this.blockScore;
    }

    public int[][] getPieceArr()
    {
        return this.pieceArr;
    }

    /** Setters of BlokusBlock variables */
    public void setType(int toSet)
    {
        this.type = toSet;
    }

    public void setBlockScore(int toSet)
    {
        this.blockScore = toSet;
    }

    public void setPieceArr(int[][] toSet)
    {
        if(toSet == null)
        {
            return;
        }
        else
        {
            for(int i = 0; i<5; i++)
            {
                for(int j = 0; j<5; j++)
                {
                    this.pieceArr[i][j] = toSet[i][j];
                }
            }
        }
    }

    /**
     *  Returns a string version of the BlokusBlock
     */
    @Override
    public String toString()
    {
        String toReturn = "Type: " + this.type + " Score: " + this.blockScore + "\n";
        for(int i = 0; i<5; i++)
        {
            for(int j = 0; j<5; j++)
            {
                toReturn += this.pieceArr[i][j] + " ";
            }
            toReturn += "\n";
        }
            return toReturn;
    }
}
