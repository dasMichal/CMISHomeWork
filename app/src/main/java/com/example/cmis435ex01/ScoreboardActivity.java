package com.example.cmis435ex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ScoreboardActivity extends AppCompatActivity
{

	String usrName;
	int[]  scoreArray = new int[2];
	int totalScore;


	TextView PosScore;
	TextView NegScore;
	TextView totalScoreText;
	TextView Playername;
	ExtendedFloatingActionButton efab;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoreboard_activity);

		Intent in = getIntent();
		usrName = in.getStringExtra("usrName");
		scoreArray = in.getIntArrayExtra("scoreArray");
		totalScore = in.getIntExtra("totalScore",0);

		init();
		logic();
	}

	private void init()         //Assign all the Objects in one Place
	{

		PosScore = findViewById(R.id.PosScore);
		NegScore = findViewById(R.id.NegScore);
		totalScoreText = findViewById(R.id.totalScoreValue);
		Playername = findViewById(R.id.ScorePlayerName);
		efab = findViewById(R.id.eFab);


	}


	private void logic()
	{


		PosScore.setText(getResources().getQuantityString(R.plurals.pointsPlural,scoreArray[0] ,scoreArray[0] ));

		NegScore.setText(getResources().getQuantityString(R.plurals.pointsPlural,scoreArray[1] ,scoreArray[1] ));

		totalScoreText.setText(getResources().getQuantityString(R.plurals.totalScorePlural,Math.abs(totalScore),totalScore ));

		Playername.setText(getResources().getString(R.string.ScorePlayerName,usrName));

		efab.setOnClickListener(v ->
		{

			new MaterialAlertDialogBuilder(this)
					.setTitle(getResources().getString(R.string.newgamedialog))
					.setMessage("")
					.setCancelable(false)
					.setPositiveButton(getResources().getString(R.string.yes),(dialog, which) ->  {
						{

							newGame();
						}
					}).show();





		}); //Attaches a onClick to each CardView


	}

	private void newGame()
	{







		Intent intent = new Intent(ScoreboardActivity.this, SettingsActivityActivity.class);

		startActivity(intent);      //Start New activity

	}
}