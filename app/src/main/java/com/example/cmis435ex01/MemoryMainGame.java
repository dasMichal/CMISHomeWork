package com.example.cmis435ex01;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
	TextView firstText;
	TextView secondText;
	CardView firstCard;
	CardView secondCard;
	FloatingActionButton fabReset;

	int flipCounter= 0;
	int wrongflipCounter= 0;
	TextView flipCounterText;
	TextView wrongflipCounterText;


	static final int duration = 800;
	private final int score = 0;
	public CountDownTimer counter;
	int backFlippedColour ;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory_main_game);
		init();

		fabReset.show();

		flipCounterText.setText(getResources().getQuantityString(R.plurals.flipsPlural, (int) flipCounter, (int) flipCounter ));
		wrongflipCounterText.setText(getResources().getQuantityString(R.plurals.wrongflipsPlural, (int) wrongflipCounter, (int) wrongflipCounter ));

		adapter = new RecycleAdapter(memCardContent, new RecycleAdapter.OnItemClickListener()
		{
			@Override
			public void onItemClick(Integer item)
			{

			}

			public void RecycleClick(CardView v, TextView text, int itemCount, int layoutPosition, RecycleAdapter.ViewHolder viewHolder)
			{

				Log.d("MainGame","itemCount = " + itemCount);
				logic(v,text,itemCount, layoutPosition, viewHolder);

			}

		});
		recyclerView.setAdapter(adapter);
		recyclerView.setClickable(true);

		recyclerView.setLayoutManager(new GridLayoutManager(this,2));


		generateData();
		Collections.shuffle(memCardContent);

	}

	private void init()
	{
		recyclerView = findViewById(R.id.MemoryCardHolder);
		flipCounterText = findViewById(R.id.totalFlips);
		fabReset = findViewById(R.id.fabRestart);
		wrongflipCounterText = findViewById(R.id.wrongtotalFlips);
		backFlippedColour= getColor(R.color.md_amber_800);




		fabReset.setOnClickListener(v ->
		{



			new MaterialAlertDialogBuilder(this)
					.setTitle(getResources().getString(R.string.newgamedialog))
					.setMessage(getResources().getString(R.string.progressLost))
					.setCancelable(false)
					.setPositiveButton(getResources().getString(R.string.yes),(dialog, which) ->  {
						{
							//Recreating the Activity
							recreate();


						}
					}).show();




			//resetGame();




		});

	}

	private void logic(CardView v,TextView text, int itemCount, int layoutPosition, RecycleAdapter.ViewHolder viewHolder)
	{

		//To use the Default CardView Colour from Theme als colour to symbolize its flipped state
		TypedArray a = getTheme().obtainStyledAttributes(R.style.Theme_CMIS435Ex01, new int[] { R.attr.cardBackgroundColor });
		// Get color hex code (eg, #fff)
		int intColor = a.getColor(0 /* index */, 0 /* defaultVal */);
		a.recycle();


		if (countFlipped == 0)  //If first Card was flipped
		{
			Log.d("GameLogic","First Card Flip");
			//Saving the Elements from the Recyclerview
			firstPos = layoutPosition;
			firstTag = (String) v.getTag();
			firstText = text;
			firstCard = v;

			//Increasing the Counter
			countFlipped++;
			flipCounter++;

			firstCard.setCardBackgroundColor(intColor);
			flipCounterText.setText(getResources().getQuantityString(R.plurals.flipsPlural, (int) flipCounter, (int) flipCounter ));

		}else if (countFlipped == 1) //If second Card was flipped
		{
			Log.d("GameLogic","Second Card Flip");
			//Saving the Elements from the Recyclerview
			secondPos = layoutPosition;
			secondTag = (String) v.getTag();
			secondText = text;
			secondCard = v;

			//Increasing the Counter
			countFlipped++;
			flipCounter++;

			secondCard.setCardBackgroundColor(intColor);
			flipCounterText.setText(getResources().getQuantityString(R.plurals.flipsPlural, (int) flipCounter, (int) flipCounter ));
		}


		//If two Cards are flipped
		if(countFlipped == 2)
		{

			//To use the Default CardView Colour from Theme als colour to symbolize its flipped state

			//a = getTheme().obtainStyledAttributes(R.style.Theme_CMIS435Ex01, new int[] { R.attr.cardBackgroundColor });
			// Get color hex code (eg, #fff)
			//intColor = a.getColor(0 /* index */, 0 /* defaultVal */);
			//a.recycle();




			//TODO ADD END GAME ACTIVITY

			Log.d("GameLogic","compare(firstTag,secondTag) = " + compare(firstTag, secondTag));
			if (!compare(firstTag, secondTag))      //Cards do not Match
			{
				wrongflipCounter++;
				wrongflipCounterText.setText(getResources().getQuantityString(R.plurals.wrongflipsPlural, (int) wrongflipCounter, (int) wrongflipCounter ));


				//Set the Card background to Red
				firstCard.setCardBackgroundColor(getColor(R.color.md_red_800));
				secondCard.setCardBackgroundColor(getColor(R.color.md_red_800));

				//Create CountdownTimer to "Flip" the wrong Cards back to its initial state
				progress3(firstText,secondText,firstPos,secondPos, intColor); // Creating a Countdown timer
				counter.cancel(); // For Safety cancel a possible running countdown Timer
				counter.start(); // Start a new countdown Timer


			} else     //Cards Match
			{

				//Disables CardView when they match
				recyclerView.getChildAt(firstPos).setEnabled(false);
				recyclerView.getChildAt(secondPos).setEnabled(false);


				//Setting CardView Background to Green to symbolize that the Cards match
				// and a successful move was done by the user
				firstCard.setCardBackgroundColor(getColor(R.color.md_green_400));
				secondCard.setCardBackgroundColor(getColor(R.color.md_green_400));


			}

			//reset countFlipped for the next Card Pair
			countFlipped = 0;
		}

	}


	/**
	 *
	 * @param firstText TextView from the first Card that was flipped
	 * @param secondText TextView from the second Card that was flipped
	 * @param firstPos  The Position of the first Card in the Recyclerview
	 * @param secondPos The Position of the second Card in the Recyclerview
	 * @param defaultColour Default Colour of the CardView from the Theme
	 */
	private void progress3(TextView firstText, TextView secondText, int firstPos, int secondPos, int defaultColour )
	{

		counter = new CountDownTimer(duration, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{


			}

			@Override
			public void onFinish()
			{
				//After Timer set the Card Background back to Default (Amber)
				firstText.setVisibility(View.INVISIBLE);
				secondText.setVisibility(View.INVISIBLE);
				firstCard.setCardBackgroundColor(backFlippedColour);
				secondCard.setCardBackgroundColor(backFlippedColour);

			}
		};
	}

	/**
	 *
	 * @param tag1 Tag Value from the first Card that was flipped
	 * @param tag2 Tag Value from the second Card that was flipped
	 * @return  returns true if Tag equals
	 */
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