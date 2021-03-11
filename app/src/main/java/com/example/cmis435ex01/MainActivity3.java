package com.example.cmis435ex01;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainActivity3 extends AppCompatActivity {

    // All Variables in Global use
    double average;
    private int score = 0;
    static final int duration = 20000;
    int numberFields;
    String usrName;
    List<Integer> ranNumbers = new ArrayList<>();
    float[] randomNumberRange = new float[2];


    // Declare all the Objects
    ProgressBar progressBar;
    TextView secLeft;
    TextView hinttxt;
    TextView scoretxt;
    Button endGame;
    public CountDownTimer counter;
    TableRow tr;
    Random r = new Random();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent in = getIntent();
        usrName = in.getStringExtra("usrName");
        numberFields = in.getIntExtra("numberFields",4);
        randomNumberRange = in.getFloatArrayExtra("RandomBound");

        //System.out.println(usrName);
        //System.out.println(numberFields);



        init();
        roll2();
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
        endGame = findViewById(R.id.endGame);
        endGame.setOnClickListener(v -> endGame(usrName,scoreArray,score));

        String text1 = getString(R.string.Score,0);
        scoretxt.setText(" "+text1);
        hinttxt.setText(" ");

    }


    public int randomNumber()
    {

        System.out.println(randomNumberRange[0]);
        System.out.println(randomNumberRange[1]);
        int low = (int) randomNumberRange[0];
        int high = (int) randomNumberRange[1];
        high = high+1;
        //int result = r.nextInt(high-low) + low;

        //return r.nextInt(9);
        return r.nextInt(high-low) + low;
    }

    private void roll2() {


        // Create random numbers and add them to List

        boolean notInList;
        ranNumbers.clear();

        for (int i = 0; i < numberFields; i++)
        {


            do
            {

                int tmp = randomNumber();

                long count = ranNumbers.stream().filter(ranNumbers -> Objects.equals(tmp, ranNumbers)).count(); //Using Java Stream to get how often tmp occurs in ranNumbers.
                System.out.println("Loopcount "+i+"| "+tmp+" occures "+count);

                notInList = count == 0;         //notInList  is true if count equals 0

                ranNumbers.add(tmp);





            }while(!notInList);






            //System.out.print(ranNumbers.get(i)+",");

        }

        Map<Integer, Long> counts = ranNumbers.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(" ");
        System.out.print("Map ");
        System.out.println(counts);

        System.out.println("Keys ");
        counts.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " + v));




        System.out.println(" ");


        average = ranNumbers.stream().mapToInt(Integer::intValue).sum();  //Using Java Stream to add all numbers from ranNumbers
        System.out.println("Sum "+average);
        average = average/numberFields;
        System.out.println("Average "+average);
        //System.out.println(ranNumbers.size());



        createGameField(ranNumbers,numberFields,average);
        hinttxt.setText(" ");
        progressBar.setMax(duration / 1000); // Setting Progressbar length according to the Time duration
        progressBar.setProgress(0); // Resetting Progressbar
        progress3(); // Creating a Countdown timer
        counter.cancel(); // For Safety cancel a possible running countdown Timer
        counter.start(); // Start a new countdown Timer

    }




    /**
     * @param ranNumbers    Array with the random numbers
     * @param average  Average of the numbers from the Array
     * @param inputVal Value the User had selected
     */
    private void compare2(List<Integer> ranNumbers, double average, int inputVal) {


        int closest = 0;
        double diff;

        for (int value : ranNumbers) {
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

    private void createGameField(List<Integer> ranNumbers, int numberFields, double average)
    {

        TableLayout TableGameField = findViewById(R.id.TableGameField);

        TableGameField.removeAllViews();
        TableGameField.setPadding(0,10,0,10);

        TableLayout.LayoutParams TableLayout = new TableLayout.LayoutParams(android.widget.TableLayout.LayoutParams.MATCH_PARENT, android.widget.TableLayout.LayoutParams.MATCH_PARENT);
        TableLayout.weight = 1;

        TableRow.LayoutParams RowLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        RowLayout.weight = 1;
        RowLayout.gravity= Gravity.CENTER;
        RowLayout.setMargins(10,20 ,10,20);





        for (int i = 0; i < numberFields; i++)
        {

            //System.out.println(i%2);
            if (i % 2 == 0)
            {
                //System.out.println("modulo if");
                tr = new TableRow(this);
                tr.setLayoutParams(TableLayout);
                TableGameField.addView(tr);

            }

            MaterialCardView playCard = new MaterialCardView(this);
            TextView playCardText = new TextView(this);
            playCardText.setText(" "+ranNumbers.get(i));
            playCardText.setGravity(Gravity.CENTER);



            playCard.setId(i);
            playCard.setTag(ranNumbers.get(i));
            playCard.setElevation(10);
            playCard.addView(playCardText);
            playCard.setLayoutParams(RowLayout);
            playCard.setOnClickListener(v -> compare2(ranNumbers, average, (Integer) playCard.getTag()));
            tr.addView(playCard);


        }

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

        roll2();
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

                roll2();
            }

        };

    }


}