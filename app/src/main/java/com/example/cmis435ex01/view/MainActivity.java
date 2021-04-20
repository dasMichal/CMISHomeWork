package com.example.cmis435ex01.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cmis435ex01.R;
import com.example.cmis435ex01.databinding.ActivityMainBinding;
import com.example.cmis435ex01.viewmodel.NumGuessViewModel;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new NumGuessViewModel());
        activityMainBinding.executePendingBindings();
    }
}