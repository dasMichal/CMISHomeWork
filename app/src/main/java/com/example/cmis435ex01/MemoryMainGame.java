package com.example.cmis435ex01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemoryMainGame extends AppCompatActivity
{



	RecyclerView recyclerView;
	RecycleAdapter adapter;
	List<String> memCardContent = new ArrayList<>();




	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_main_game);

		recyclerView = findViewById(R.id.MemoryCardHolder);

		adapter = new RecycleAdapter(memCardContent, item ->
		{
			//System.out.println("Hi");
			//System.out.println(item);
			//recyclerView.removeViewAt(item);
			//adapter.notifyItemRemoved(item);
		});

		recyclerView.setAdapter(adapter);
		//recyclerView.setLayoutManager(new LinearLayoutManager(this));

		recyclerView.setLayoutManager(new GridLayoutManager(this,3));




		generateData();

	}


	private void generateData()
	{


		for (int i = 0; i < 12; i++)
		{
			memCardContent.add(String.valueOf(i));
		}

		adapter.notifyDataSetChanged();

	}







}