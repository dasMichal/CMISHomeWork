package com.example.cmis435ex01;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStore;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class MainActivity3 extends AppCompatActivity {

    // All Variables in Global use
    double average;
    final int[] array = new int[4];
    private int score = 0;
    static final int duration = 20000;

    AlertDialog dialog;

    // Declare all the Objects
    ProgressBar progressBar;
    TextView secLeft;
    TextView hinttxt;
    TextView scoretxt;
    TextView card1text;
    TextView card2text;
    TextView card3text;
    TextView card4text;
    MaterialCardView card1;
    MaterialCardView card2;
    MaterialCardView card3;
    MaterialCardView card4;
    public CountDownTimer counter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent in = getIntent();
        String usrName = in.getStringExtra("usrName");
        int numberFields = in.getIntExtra("numberFields",4);
        ArrayList<Parcelable> RandomBound = in.getParcelableArrayListExtra("RandomBound");

        System.out.println(usrName);
        System.out.println(numberFields);

        init();

        roll();
    }





    /**
     * Init function to assign all fields
     */
    private void init()         //Assign all the Objects in one Place
    {


        progressBar = findViewById(R.id.progressBar2);
        secLeft = findViewById(R.id.secleft);
        hinttxt = findViewById(R.id.hinttxt);
        scoretxt = findViewById(R.id.score);


        //Creating the Material Cards
        card1 = findViewById(R.id.card1);
        card1text = findViewById(R.id.card1text);

        card2 = findViewById(R.id.card2);
        card2text = findViewById(R.id.card2text);

        card3 = findViewById(R.id.card3);
        card3text = findViewById(R.id.card3text);

        card4 = findViewById(R.id.card4);
        card4text = findViewById(R.id.card4text);

        //Tried doing an alert. Didn't get it to work
        /*
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder((card1text.getContext()));
        materialAlertDialogBuilder.setTitle(getResources().getString(R.string.app_name));
        materialAlertDialogBuilder.setMessage(getResources().getString(R.string.app_name));

        materialAlertDialogBuilder.setNeutralButton(resources.getString(R.string.cancel))
        {
            System.out.println("TEST");
             // Respond to neutral button press
        }
        dialog = materialAlertDialogBuilder.show();

        */
        String text1 = getString(R.string.Score,0);
        scoretxt.setText(" "+text1);
        hinttxt.setText(" ");

    }

    private void roll() {


        Random r = new Random();

        // Create random numbers and Sae them to array
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(9);
            System.out.println(i);
            System.out.println(array[i]);
        }

        // calculate average of the numbers
        average = (array[0] + array[1] + array[2] + array[3]);
        average = average / 4;

        card1.setTag(array[0]); // Setting the Object tag with its value to be able to retrieve it later
        card1text.setText(" " + array[0]);
        card1.setOnClickListener(v -> compare(array, average, (Integer) card1.getTag()));

        card2.setTag(array[1]);
        card2text.setText(" " + array[1]);
        card2.setOnClickListener(v -> compare(array, average, (Integer) card2.getTag()));

        card3.setTag(array[2]);
        card3text.setText(" " + array[2]);
        card3.setOnClickListener(v -> compare(array, average, (Integer) card3.getTag()));

        card4.setTag(array[3]);
        card4text.setText(" " + array[3]);
        card4.setOnClickListener(v -> compare(array, average, (Integer) card4.getTag()));

        hinttxt.setText(" ");
        progressBar.setMax(duration / 1000); // Setting Progressbar length according to the Time duration
        progressBar.setProgress(0); // Resetting Progressbar
        progress3(); // Creating a Countdown timer
        counter.cancel(); // For Safety cancel a possible running countdown Timer
        counter.start(); // Start a new countdown Timer

    }



    /**
     * @param array    Array with the random numbers
     * @param average  Average of the numbers from the Array
     * @param inputVal Value the User had selected
     */
    private void compare(int[] array, double average, int inputVal) {

        System.out.println(progressBar.getProgress());
        int closest = 0;
        double diff;
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        for (int value : array) {
            // System.out.print(array[i]+", ");
            diff = Math.abs(average - value);

            if (diff < Math.abs(average - closest)) {
                closest = value;
                System.out.print("Ergebnis!: ");
                System.out.println(closest + " ");
            }
        }

        System.out.println();
        System.out.println("Average= " + average);
        System.out.println("Closest= " + closest);

        check2(inputVal, closest);

    }

    /**
     * @param selectedVal Value the User had selected
     * @param closest     Closest number to average from number set in Array passed
     *                    from previous function
     */
    private void check2(int selectedVal, int closest) {

        if (selectedVal == closest) {
            score++;
            Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show();
            hinttxt.setText(" ");

        } else {
            score--;
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
        }
        counter.cancel();

        String text1 = getString(R.string.Score, score);
        scoretxt.setText(text1);

        roll();
    }


    public void progress3() {

        counter = new CountDownTimer(duration, 1000) {
            int progressStatus = 0;

            final int half = ((duration / 2) / 1000);

            @Override
            public void onTick(long millisUntilFinished) {
                progressStatus++;
                // secLeft.setText("seconds remaining: " + millisUntilFinished / 1000);
                secLeft.setText(getString(R.string.time, (millisUntilFinished / 1000)));
                if (progressStatus == half) {

                    String text1 = getString(R.string.hint, average);
                    // String text1 = res.getString(R.string.Score,score);
                    hinttxt.setText(text1);

                }
                progressBar.setProgress(progressStatus, true);

            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                progressStatus = 0;
                score--;
                //hinttxt.setText(getResources().getString(R.string.strgfailed));

                String text1 = getString(R.string.Score, score);
                scoretxt.setText(text1);
                secLeft.setText(" ");
                hinttxt.setText(" ");

                roll();
            }

        };

    }


}