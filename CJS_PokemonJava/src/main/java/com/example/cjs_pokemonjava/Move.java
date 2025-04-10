package com.example.cjs_pokemonjava;

public class Move
{
    private int moveId;

    private String moveName;

    public Move(int moveId, String moveName)
    {
        this.moveId = moveId;
        this.moveName = moveName;
    }

    public int getMoveId() {
        return moveId;
    }

    public String getMoveName() {
        return moveName;
    }
}
