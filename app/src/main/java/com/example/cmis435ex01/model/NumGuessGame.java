package com.example.cmis435ex01.model;

import java.util.Random;

public class NumGuessGame
{

    private final com.example.cmis435ex01.model.RandNum rNumL;
    private final com.example.cmis435ex01.model.RandNum rNumR;
    private final com.example.cmis435ex01.model.Score sc;

    public NumGuessGame()
    {
        rNumL = new com.example.cmis435ex01.model.RandNum();
        rNumR = new com.example.cmis435ex01.model.RandNum();
        sc = new com.example.cmis435ex01.model.Score();

        roll();
    }

    public int getrNumL()
    {
        return rNumL.getRandNum();
    }
    public int getrNumR() {
        return rNumR.getRandNum();
    }
    public int getScore() {
        return sc.getScore();
    }

    public void check(int btn)
    {
        if (btn == 0)
        {
            if (rNumL.getRandNum() > rNumR.getRandNum())
                sc.setScore(sc.getScore() + 1);
            else
                sc.setScore(sc.getScore() - 1);
        } else {
            if (rNumL.getRandNum() < rNumR.getRandNum())
                sc.setScore(sc.getScore() + 1);
            else
                sc.setScore(sc.getScore() - 1);
        }

        roll();
    }

    private void roll()
    {
        Random r = new Random();
        rNumL.setRandNum(r.nextInt(9));
        rNumR.setRandNum(r.nextInt(9));

        while (rNumL.getRandNum() == rNumR.getRandNum())
            rNumR.setRandNum(r.nextInt(9));
    }

}
