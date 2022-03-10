package com.example.blokusstatetest;

/**
 * Class for each of the Blokus block pieces.
 *
 * @author Max Clark, Skyelar Cann, Gavin Raguindin
 * @version February 24th 2022
 */
public class BlokusBlock {
    private int color;
    private int blockScore;

    /** No param ctor */
    public BlokusBlock()
    {
        this.color = -1;
        this.blockScore = 5;
    }

    /** Getters of BlokusBlock variables */
    public int getColor()
    {
        return this.color;
    }

    public int getBlockScore()
    {
        return this.blockScore;
    }

    /** Setters of BlokusBlock variables */
    public void setColor(int toSet)
    {
        this.color = toSet;
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
        return "Color: " + this.color + " Score: " + this.blockScore;
    }
}
