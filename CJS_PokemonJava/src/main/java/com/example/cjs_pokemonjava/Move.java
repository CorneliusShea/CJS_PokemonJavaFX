package com.example.cjs_pokemonjava;

public class Move
{
    private int moveId;

    private String moveName;

    private int moveDamage;

    public Move(int moveId, String moveName, int moveDamage)
    {
        this.moveId = moveId;
        this.moveName = moveName;
        this.moveDamage = moveDamage;
    }

    public int getMoveId() {
        return moveId;
    }

    public String getMoveName() {
        return moveName;
    }

    public int getMoveDamage() {
        return moveDamage;
    }
}
