package com.example.cmis435ex01;

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
import androidx.cardview.widget.CardView;

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
    int[]  scoreArray = new int[2];


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





        for (int i = 0; i < numberFields; i++) //Check if no more that two cards in each Row
        {
            if (i % 2 == 0)         //Else create new Row
            {
                tr = new TableRow(this);
                tr.setLayoutParams(TableLayout);
                TableGameField.addView(tr);
            }


            CardView playCard = new CardView(this); //Create new CardView
            TextView playCardText = new TextView(this); //Create corresponding new TextView


            playCardText.setText(" "+ranNumbers.get(i));
            playCardText.setGravity(Gravity.CENTER);
            playCardText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);


            playCard.setMinimumWidth((int) dptopx(100));
            playCard.setMinimumHeight((int) dptopx(80));
            playCard.setContentPadding((int) dptopx(10),(int) dptopx(10),(int) dptopx(10),(int) dptopx(10));


            playCard.setId(i);
            playCard.setTag(ranNumbers.get(i)); //Setting the Value the Card has as its Tag to easily extract it later
            playCard.setElevation((int) dptopx(2));
            playCard.addView(playCardText);     //Add TextView to the CardView so the Text stays with the Card
            playCard.setLayoutParams(RowLayout);    //Applies the RowLayout parameters to the cardView

            playCard.setOnClickListener(v -> compare2(ranNumbers, average, (Integer) playCard.getTag())); //Attaches a onClick to each CardView
            tr.addView(playCard);   //Adds the CardView with its TextView to the Table Row

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
            scoreArray[0]= scoreArray[0] +1;
            Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show();
            hinttxt.setText(" ");

        } else {
            score--;
            scoreArray[1]= scoreArray[1] +1;
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
                //secLeft.setText(getString(R.string.time, (millisUntilFinished / 1000)));


                secLeft.setText(getResources().getQuantityString(R.plurals.timePlural, (int) (millisUntilFinished / 1000), (int) (millisUntilFinished / 1000)));
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
                scoreArray[1]= scoreArray[1] +1;
                //hinttxt.setText(getResources().getString(R.string.strgfailed));

                String text1 = getString(R.string.Score, score);
                scoretxt.setText(text1);
                secLeft.setText(" ");
                hinttxt.setText(" ");

                roll2();
            }

        };

    }

    /**
     *
     * @param usrName   Player Name
     * @param scoreArray    Array with the Points
     * @param totalScore    Totale Score of Game
     */
    private void endGame(String usrName, int[] scoreArray, int totalScore)
    {



        Intent intent = new Intent(MainActivity3.this, ScoreboardActivity.class);


        //intent.putExtra("RandomBound",rangeArray);

        //Sending the username and score to endActivity
        intent.putExtra("usrName",usrName);
        intent.putExtra("scoreArray",scoreArray);
        intent.putExtra("totalScore",totalScore);


        startActivity(intent);


    }






    /**
     *
     * @param dp Pixel Value to convert it to dp
     * @return  Returns the Value
     */
    public float dptopx(float dp)
    {
        return dp * getResources().getDisplayMetrics().density;
    }



}