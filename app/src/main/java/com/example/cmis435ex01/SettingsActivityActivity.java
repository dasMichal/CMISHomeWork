package com.example.cmis435ex01;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivityActivity extends AppCompatActivity
{

	// All Variables in Global use
	double average;
	List<Float> rangeArray = new ArrayList<>();
	float[] randomNumberRange = new float[2];


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
	Button testBtt;
	MaterialCardView card1;
	public CountDownTimer counter;
	TableRow tr;






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
		testBtt = findViewById(R.id.testBtt);
		System.out.println("Hi");
		System.out.println(startMainGame.getLayoutParams());





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


	private void test(int inputVal)
	{

		System.out.println("Hello, its Me");
		System.out.println("Nr: "+inputVal);
	}

	private void testLayout()
	{

		TableLayout mytable = findViewById(R.id.Testtable);

		mytable.removeAllViews();
		mytable.setPadding(0,10,0,10);


		TableLayout.LayoutParams TableLayout = new TableLayout.LayoutParams(android.widget.TableLayout.LayoutParams.MATCH_PARENT, android.widget.TableLayout.LayoutParams.MATCH_PARENT);
		TableLayout.weight = 1;
		//TableLayout.gravity= Gravity.CENTER;
		//TableLayout.setMargins(10,10 ,10,10);


		TableRow.LayoutParams RowLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
		RowLayout.weight = 1;
		RowLayout.gravity= Gravity.CENTER;
		RowLayout.setMargins(10,20 ,10,20);



		int sliderVal = (int) numbrSlider.getValue();

		for (int i = 0; i < sliderVal; i++)
		{

			//System.out.println(i%2);
			if (i % 2 == 0)
			{
				//System.out.println("modulo if");
				tr = new TableRow(this);
				//tr.setLayoutParams(LinearLayout);
				tr.setLayoutParams(TableLayout);

				mytable.addView(tr);

			}

			MaterialCardView testcrd = new MaterialCardView(this);
			TextView cardtxt = new TextView(this);
			cardtxt.setText(" "+(i+1));
			cardtxt.setGravity(Gravity.CENTER);



			testcrd.setId(i);
			testcrd.setTag(i);
			testcrd.setElevation(10);
			testcrd.addView(cardtxt);
			testcrd.setLayoutParams(RowLayout);
			//testcrd.setOnClickListener(v -> test((Integer) testcrd.getTag()));
			tr.addView(testcrd);

			/*
			//set the properties for button
			Button btnTag = new Button(this);
			btnTag.setText(" "+i);
			btnTag.setId(i);
			btnTag.setLayoutParams(RowLayout);

			//add button to the layout
			tr.addView(btnTag);

			 */


		}



	}




	private void logic()
	{

		numbrSlider.addOnChangeListener((slider, value, fromUser) ->
		{

			System.out.println("NumberSlider= "+value);
			String text1 = getString(R.string.numbrSliderText, (int) value);
			numbrSliderText.setText(text1);
			//testLayout();
			//System.out.println(userNameInput.getText());



		});


		randomNumberBoundSlider.addOnChangeListener((slider, value, fromUser) ->
		{
			rangeArray = slider.getValues();
			randomNumberRange[0]= rangeArray.get(0);
			randomNumberRange[1]= rangeArray.get(1);



			//String text1 = getString(R.string.numbrSliderText, value);
			//numbrSliderText.setText(text1);


		});



		testBtt.setOnClickListener(v ->
		{
			testLayout();
		});





		startMainGame.setOnClickListener(v ->
		{

			if(TextUtils.isEmpty(userNameInput.getText()))
			{
				String text1 = getString(R.string.nameEmptyError);
				userNameInput.setError(text1);
				return;
			}

			String temp= userNameInput.getText().toString();
			Intent intent = new Intent(SettingsActivityActivity.this, MainActivity3.class);
			intent.putExtra("usrName",temp);
			intent.putExtra("numberFields",(int) numbrSlider.getValue());
			//intent.putExtra("RandomBound",rangeArray);
			intent.putExtra("RandomBound",randomNumberRange);


			startActivity(intent);


		});


	}






}