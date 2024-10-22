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
	final float[] randomNumberRange = new float[2];


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
		numbrSlider.setValue(4); //Set The Slider to its default Values


		numbrSliderText = findViewById(R.id.numbrSliderText);
		numbrSliderText.setText(getResources().getString(R.string.numOfNumbersValue,(int) numbrSlider.getValue()));


		randomNumberBoundSlider = findViewById(R.id.randomNumberBoundSlider);
		randomNumberBoundSlider.setStepSize((float) 1.0);    //Set The Range Slider step Size
		randomNumberBoundSlider.setValues((float) 1.0, (float) 10.0); //Set The Range Slider to its default Values


		randomNumberBoundText = findViewById(R.id.randomNumberBoundText);
		randomNumberBoundText.setText(getResources().getString(R.string.rangeOfRandomValue,randomNumberBoundSlider.getValues().get(0).intValue(),randomNumberBoundSlider.getValues().get(1).intValue()));


		settingText = findViewById(R.id.settingText);
		userNameInput = findViewById(R.id.userNameInput);
		userNameInput.setAutofillHints(View.AUTOFILL_HINT_NAME); //Set Autofill to fill in the Name of Phone User if available

		startMainGame = findViewById(R.id.startMainGame);




	}




	private void logic()
	{


		//On Change Listener which executes each time the Slider has been moved
		numbrSlider.addOnChangeListener((slider, value, fromUser) ->
		{

			System.out.println("NumberSlider= "+value);

			numbrSliderText.setText(getResources().getString(R.string.numOfNumbersValue,(int) value)); //Print the new value of the Slider


			checkBound();



		});

		//On Change Listener which executes each time the Slider has been moved
		randomNumberBoundSlider.addOnChangeListener((slider, value, fromUser) ->
		{
			rangeArray = slider.getValues();

			randomNumberRange[0]= rangeArray.get(0);
			randomNumberRange[1]= rangeArray.get(1);

			checkBound();


			randomNumberBoundText.setText(getResources().getString(R.string.rangeOfRandomValue,(int) randomNumberRange[0],(int) randomNumberRange[1]));

		});







		startMainGame.setOnClickListener(v ->
		{

			//Check if Username Field is Empty
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

			//System.out.println(randomNumberRange[0]);
			//System.out.println(randomNumberRange[1]);

			String temp= userNameInput.getText().toString();
			Intent intent = new Intent(SettingsActivityActivity.this, MainActivity3.class);

			intent.putExtra("usrName",temp);            //Send Username to next Activity
			intent.putExtra("numberFields",(int) numbrSlider.getValue()); //Send the Amount of Numbers selected to next Activity
			intent.putExtra("RandomBound",randomNumberRange); //Send the Random numbers as float Array to next Activity


			startActivity(intent);      //Start New activity


		});


	}



	public void checkBound()
	{

		//Checking if Random Number Bound is than amount of numbers

		randomNumberRange[0]= randomNumberBoundSlider.getValues().get(0);
		randomNumberRange[1]= randomNumberBoundSlider.getValues().get(1);

		System.out.println((randomNumberRange[1]-randomNumberRange[0])+1);


		int range = (int) ((randomNumberRange[1]-randomNumberRange[0])+1);
		int space = (int) (numbrSlider.getValue() -range); // how much distance has to be added


		if (range < (int) numbrSlider.getValue())
		{


			if(randomNumberBoundSlider.getValues().get(0) > randomNumberBoundSlider.getValueFrom() )
			{
				int tmp = (int) (randomNumberBoundSlider.getValues().get(0) - randomNumberBoundSlider.getValueFrom());
				System.out.println("How much left can i go: "+tmp);
				if (tmp >= space )
				{

					randomNumberBoundSlider.setValues(randomNumberBoundSlider.getValues().get(0)-space, randomNumberBoundSlider.getValues().get(1));

				}

				//slider.setValues((float) 1.0, (float) 10.0);;
			}


			if(randomNumberBoundSlider.getValues().get(1) < randomNumberBoundSlider.getValueTo())
			{
				int tmp = (int) (randomNumberBoundSlider.getValueTo() - randomNumberBoundSlider.getValues().get(1));
				System.out.println("How much right can i go: "+tmp);
				if (tmp >= space )
				{

					randomNumberBoundSlider.setValues(randomNumberBoundSlider.getValues().get(0), randomNumberBoundSlider.getValues().get(1)+space);

				}

			}



		}



	}



}