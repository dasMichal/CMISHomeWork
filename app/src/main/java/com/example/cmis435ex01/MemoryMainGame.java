package com.example.cmis435ex01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryMainGame extends AppCompatActivity
{



	RecyclerView recyclerView;
	RecycleAdapter adapter;
	List<String> memCardContent = new ArrayList<>();
	List<String> list = Stream.of("🍕", "🌭","🏓","🙈","🛠","⚽","🌈","🥫","🍝", "🍜" ,"🍲", "🍛", "🍣" ,"🍱", "🥟", "🦪", "🍤", "🍙", "🍚", "🍘", "🍥", "🥠", "🥮", "🍢", "🍡", "🍧", "🍨", "🍦", "🥧", "🧁").collect(Collectors.toList());
	List<String> list2 = Stream.of("🍕","🍕","🌭","🌭","🍥","🍥","⚽","⚽").collect(Collectors.toList());
	private int countFlipped = 0;
	private int firstPos;
	private int secondPos;
	private String firstTag;
	private String secondTag;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_main_game);

		recyclerView = findViewById(R.id.MemoryCardHolder);


		adapter = new RecycleAdapter(memCardContent, new RecycleAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(Integer item)
			{
				System.out.println("Hi");
				System.out.println(item);
				Collections.shuffle(memCardContent);
				adapter.notifyDataSetChanged();

			}


			public void test(CardView v, int itemCount, int layoutPosition, RecycleAdapter.ViewHolder viewHolder)
			{
				//System.out.println(v);
				System.out.println("itemCount = " + itemCount);
				System.out.println("layoutPosition = " + layoutPosition);
				//recyclerView.getChildAt(layoutPosition).setEnabled(false);

				logic(v,itemCount, layoutPosition, viewHolder);

			}

		});
		recyclerView.setAdapter(adapter);
		recyclerView.setClickable(true);
		//recyclerView.setLayoutManager(new LinearLayoutManager(this));

		recyclerView.setLayoutManager(new GridLayoutManager(this,2));


		generateData();
		Collections.shuffle(memCardContent);


	}

	private void logic(CardView v, int itemCount, int layoutPosition, RecycleAdapter.ViewHolder viewHolder)
	{



		if (countFlipped == 0)
		{
			System.out.println("First flip");
			firstPos = layoutPosition;
			firstTag = (String) v.getTag();
			countFlipped++;


		}else if (countFlipped == 1)
		{
			System.out.println("Second flip");
			secondPos = layoutPosition;
			secondTag = (String) v.getTag();

			countFlipped++;

		}



		if(countFlipped == 2)
		{

			System.out.println("compare(firstTag,secondTag) = " + compare(firstTag, secondTag));

			if (compare(firstTag,secondTag))
			{

				recyclerView.getChildAt(firstPos).setEnabled(false);
				recyclerView.getChildAt(secondPos).setEnabled(false);
			}



			countFlipped = 0;
		}




	}


	private boolean compare(String tag1,String tag2)
	{

		return tag1.equalsIgnoreCase(tag2);


	}






	private void generateData()
	{


		for (int i = 0; i < 8; i++)
		{
			memCardContent.add(list2.get(i));
			//memCardContent.add(String.valueOf(i));
		}
		Collections.shuffle(memCardContent);
		adapter.notifyDataSetChanged();

	}







}