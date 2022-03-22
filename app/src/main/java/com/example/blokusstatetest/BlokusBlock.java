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

    /** No param ctor */
    public BlokusBlock()
    {
        this.type = -1;
        this.blockScore = 5;
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

    /** Setters of BlokusBlock variables */
    public void setType(int toSet)
    {
        this.type = toSet;
    }

    public void setBlockScore(int toSet)
    {
        this.blockScore = toSet;
    }

    /**
     *  Returns a string version of the BlokusBlock
     */
    @Override
    public String toString()
    {
            return "Type: " + this.type + " Score: " + this.blockScore + "\n";
    }
}
