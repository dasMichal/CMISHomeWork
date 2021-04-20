package com.example.cmis435ex01.viewmodel;

import androidx.databinding.ObservableField;

import com.example.cmis435ex01.model.NumGuessGame;

public class NumGuessViewModel
{
    private final NumGuessGame game;
    public ObservableField<String> numL, numR, sc;

    public NumGuessViewModel()
    {
        game = new NumGuessGame();
        numL = new ObservableField<>();
        numR = new ObservableField<>();
        sc = new ObservableField<>();

        numL.set("" + game.getrNumL());
        numR.set("" + game.getrNumR());
        sc.set("Score: " + game.getScore());
    }

    public void onBtnClicked(int btn)
    {
        game.check(btn);

        numL.set("" + game.getrNumL());
        numR.set("" + game.getrNumR());
        sc.set("Score: " + game.getScore());
    }
}
