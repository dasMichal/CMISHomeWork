 package com.example.cmis435ex01;

 import android.content.Intent;
 import android.os.Bundle;
 import android.text.TextUtils;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;

 import androidx.appcompat.app.AppCompatActivity;

 import com.google.android.material.dialog.MaterialAlertDialogBuilder;
 import com.google.android.material.slider.RangeSlider;
 import com.google.android.material.slider.Slider;

 import java.util.ArrayList;
 import java.util.List;

public class SettingsActivityActivity extends AppCompatActivity
{

	// All Variables in Global use
	List<Float> rangeArray = new ArrayList<>();
	float[] randomNumberRange = new float[2];


	// Declare all the Objects
	TextView settingText;
	TextView numbrSliderText;
	TextView randomNumberBoundText;
	EditText userNameInput;
	Slider numbrSlider;
	RangeSlider randomNumberBoundSlider;
	Button startMainGame;







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







		startMainGame.setOnClickListener(v ->
		{

			if(TextUtils.isEmpty(userNameInput.getText()))
			{
				String text1 = getString(R.string.nameEmptyError);
				userNameInput.setError(text1);
				return;
			}


			rangeArray = randomNumberBoundSlider.getValues();

			randomNumberRange[0]= rangeArray.get(0).intValue();
			randomNumberRange[1]= rangeArray.get(1).intValue();

			if (randomNumberRange[0] == randomNumberRange[1])
			{
				new MaterialAlertDialogBuilder(this)
						.setTitle(getResources().getString(R.string.dialogerror))
						.setMessage(getResources().getString(R.string.dialogranError))
						.setCancelable(true)
						.setPositiveButton("ok",(dialog, which) ->  {
							{

								randomNumberBoundSlider.setValues((float) 1.0, (float) 10.0);
							}
						}).show();

				return;
			}

			System.out.println(randomNumberRange[0]);
			System.out.println(randomNumberRange[1]);

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