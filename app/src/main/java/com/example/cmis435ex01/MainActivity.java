package com.example.cmis435ex01;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    double average;
    final int[] array = new int[4];
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll();

    }

    private void roll()
    {



        Random r = new Random();
        int num1 = r.nextInt(9);
        int num2 = r.nextInt(9);
        int num3 = r.nextInt(9);
        int num4 = r.nextInt(9);

        //2, 2, 3, 7,

        array[0] = num1;
        array[1] = num2;
        array[2] = num3;
        array[3] = num4;

        average = (num1 + num2 + num3 + num4);
        average = average/4;


        /*
        array[0] = 0;
        array[1] = 5;
        array[2] = -1;
        array[3] = 6;

        num1 = 0;
        num2 = 5;
        num3 = -1;
        num4 = 6;

        */
        //while (num1 == num2 )
            //num2 = r.nextInt(9);

        Button btt1 = findViewById(R.id.btt1);
        btt1.setTag(num1);
        btt1.setText(" "+ num1);

        btt1.setOnClickListener(v ->
        {

            compare(array,average,(Integer) btt1.getTag());


        });


        Button btt2 = findViewById(R.id.btt2);
        btt2.setTag(num2);
        btt2.setText(" "+ num2);
        btt2.setOnClickListener(v ->
        {

            compare(array,average,(Integer) btt2.getTag());


        });

        Button btt3 = findViewById(R.id.btt3);
        btt3.setTag(num3);
        btt3.setText(" "+ num3);
        btt3.setOnClickListener(v ->
        {

            compare(array,average,(Integer) btt3.getTag());


        });

        Button btt4 = findViewById(R.id.btt4);
        btt4.setTag(num4);
        btt4.setText(" "+ num4);
        btt4.setOnClickListener(v ->
        {

            compare(array,average,(Integer) btt4.getTag());


        });


        Button change = findViewById(R.id.change_activ);

        change.setOnClickListener(v ->
        {

            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);


        });





        //test(num1,num2,num3,num4,array,average);

    }



    private void compare(int[] array,double average,int inputVal)
    {

        System.out.println(inputVal);

        int closest = 0;
        double diff;
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        for (int value : array)
        {
            //System.out.print(array[i]+", ");
            diff = Math.abs(average - value);

            if (diff < Math.abs(average - closest))
            {
                closest = value;
                System.out.print("Ergebniss!: ");
                System.out.println(closest + " ");


            }


        }

        System.out.println();
        System.out.println("Average= "+average);
        System.out.println("Closest= "+closest);

        check2(inputVal,closest);

    }


    private void test(int n1, int n2,int n3,int n4,int[] array,double average)
    {

        int closest = 0;
        double diff;
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        for (int value : array)
        {
            //System.out.print(array[i]+", ");
            diff = Math.abs(average - value);

            if (diff < Math.abs(average - closest))
            {
                closest = value;
                System.out.print("Ergebniss!: ");
                System.out.println(closest + " ");


            }


        }

        System.out.println();
        System.out.println("Average= "+average);
        System.out.println("Closest= "+closest);


    }

    private void check2(int selectedVal, int closest)
    {


        if (selectedVal == closest) {
            score++;
            Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show();
        } else {
            score--;
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
        }

        TextView txtScore = findViewById(R.id.txtScore);
        txtScore.setText("Score: " + score);

        roll();
    }


    private void check(int n1, int n2)
    {


        if (n1 > n2) {
            score++;
            Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show();
        } else {
            score--;
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
        }

        TextView txtScore = findViewById(R.id.txtScore);
        Resources res = getResources();
        String text = res.getString(R.string.Score,score);
        txtScore.setText(text);

        roll();
    }

    /*
    public void clickBtt1(View view)
    {
        //check(num1, num2);
    }

    public void clickBtt2(View view)
    {
        //check(num2, num1);
    }
    */


}