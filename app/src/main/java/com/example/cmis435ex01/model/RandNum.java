package com.example.cmis435ex01.model;

import java.util.Random;

public class RandNum
{
    private final int RMAX = 9;
    private int rNum;
    private final Random r = new Random();

    public RandNum()
    {
        this.rNum = r.nextInt(RMAX);
    }

    public int getRandNum()
    {
        return rNum;
    }

    public void setRandNum(int rNum)
    {
        this.rNum = rNum;
    }
}
