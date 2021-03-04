package com.example.cmis435ex01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.List;

public class SettingsActivityActivity extends AppCompatActivity
{

	// All Variables in Global use
	double average;
	List<Float> rangeArray;
	static final int duration = 20000;

	AlertDialog dialog;

	// Declare all the Objects
	ProgressBar progressBar;
	TextView settingText;
	TextView numbrSliderText;
	TextView randomNumberBoundText;
	EditText userNameInput;
	Slider numbrSlider;
	RangeSlider randomNumberBoundSlider;
	Button startMainGame;
	MaterialCardView card1;
	public CountDownTimer counter;




	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		init();
		logic();

	}



	private void init()         //Assign all the Objects in one Place
	{

		numbrSlider = findViewById(R.id.numbrSlider);
		numbrSlider.setValue(4);





		randomNumberBoundSlider = findViewById(R.id.randomNumberBoundSlider);
		numbrSliderText = findViewById(R.id.numbrSliderText);


		settingText = findViewById(R.id.settingText);
		userNameInput = findViewById(R.id.userNameInput);

		startMainGame = findViewById(R.id.startMainGame);







	}

/*
	Slider.OnChangeListener test = new Slider.OnChangeListener() {

		@Override
		public void onValueChange(@NonNull Slider slider, float value, boolean fromUser)
		{
			System.out.println(value);
			String text1 = getString(R.string.numbrSliderText, value);
			numbrSliderText.setText(text1);
		}


	};

*/



	private void logic()
	{

		numbrSlider.addOnChangeListener((slider, value, fromUser) ->
		{

			System.out.println("NumberSlider= "+value);
			String text1 = getString(R.string.numbrSliderText, (int) value);
			numbrSliderText.setText(text1);


		});


		randomNumberBoundSlider.addOnChangeListener((slider, value, fromUser) ->
		{
			rangeArray = slider.getValues();
			//System.out.println("BoundSliderValue1= "+value);
			//System.out.println("BoundSliderValue2= "+value);
			System.out.println("BoundSliderValue1= "+ rangeArray.get(0));
			System.out.println("BoundSliderValue2= "+ rangeArray.get(1));


			//String text1 = getString(R.string.numbrSliderText, value);
			//numbrSliderText.setText(text1);


		});









		startMainGame.setOnClickListener(v ->
		{

			if(TextUtils.isEmpty(userNameInput.getText()))
			{
				String text1 = getString(R.string.nameEmptyError);
				userNameInput.setError(text1);
				return;
			}

			Intent intent = new Intent(SettingsActivityActivity.this, MainActivity3.class);
			intent.putExtra("usrName",userNameInput.getText());
			intent.putExtra("numberFields",numbrSlider.getValue());
			intent.putExtra("RandomBound", (Parcelable) rangeArray);
			startActivity(intent);


		});


	}






}