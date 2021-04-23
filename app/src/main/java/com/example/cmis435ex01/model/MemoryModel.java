package com.example.cmis435ex01.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmis435ex01.R;
import com.example.cmis435ex01.adapter.RecyclerViewAdapter;

import java.util.List;

public class MemoryModel
{

	static final int duration = 800;
	private final int score = 0;
	private final RandEmoji EmojiList;
	public CountDownTimer counter;
	TextView firstText;
	TextView secondText;
	CardView firstCard;
	CardView secondCard;
	int flipCounter = 0;
	int wrongflipCounter = 0;
	TextView flipCounterText;
	TextView wrongflipCounterText;
	int backFlippedColour;
	Points points;
	private int countFlipped = 0;
	private int firstPos;
	private int secondPos;
	private String firstTag;
	private String secondTag;

	public MemoryModel()
	{
		EmojiList = new RandEmoji();
		points = new Points();
	}


	public int getTotalFlipsMODEL()
	{
		return points.getTotalFlips();
	}

	public int getTotalWrongFlipsMODEL()
	{
		return points.getTotalWrongFlips();
	}

	private void incrementTotalFlips()
	{
		points.setTotalFlips(points.getTotalFlips()+1);
	}

	private void incrementTotalWrongFlips()
	{
		points.setTotalWrongFlips(points.getTotalWrongFlips()+1);
	}



	public List<String> getEmojiList()
	{
		return EmojiList.RandEmojiList();
	}



	public void logic(CardView v, TextView text, int itemCount, int layoutPosition, RecyclerViewAdapter.ViewHolder viewHolder, RecyclerView recyclerView, Context context)
	{

		//To use the Default CardView Colour from Theme als colour to symbolize its flipped state
		TypedArray a = context.getTheme().obtainStyledAttributes(R.style.Theme_CMIS435Ex01, new int[]{R.attr.cardBackgroundColor});
		// Get color hex code (eg, #ffff)
		int intColor = a.getColor(0 /* index */, 0 /* defaultVal */);
		a.recycle();

		backFlippedColour= context.getColor(R.color.md_amber_800);



		if (countFlipped == 0)  //If first Card was flipped
		{
			Log.d("GameLogic", "First Card Flip");
			//Saving the Elements from the Recyclerview
			firstPos = layoutPosition;
			firstTag = (String) v.getTag();
			firstText = text;
			firstCard = v;

			//Increasing the Counter
			countFlipped++;
			flipCounter++;

			firstCard.setCardBackgroundColor(intColor);

			incrementTotalFlips();
			//flipCounterText.setText(context.getResources().getQuantityString(R.plurals.flipsPlural, flipCounter, flipCounter));

		} else if (countFlipped == 1) //If second Card was flipped
		{
			Log.d("GameLogic", "Second Card Flip");
			//Saving the Elements from the Recyclerview
			secondPos = layoutPosition;
			secondTag = (String) v.getTag();
			secondText = text;
			secondCard = v;

			//Increasing the Counter
			countFlipped++;
			flipCounter++;

			secondCard.setCardBackgroundColor(intColor);

			incrementTotalFlips();



			//flipCounterText.setText(context.getResources().getQuantityString(R.plurals.flipsPlural, (int) flipCounter, (int) flipCounter ));
		}


		//If two Cards are flipped
		if (countFlipped == 2)
		{

			//To use the Default CardView Colour from Theme als colour to symbolize its flipped state

			//TODO ADD END GAME ACTIVITY

			Log.d("GameLogic", "compare(firstTag,secondTag) = " + compare(firstTag, secondTag));
			if (!compare(firstTag, secondTag))      //Cards do not Match
			{
				wrongflipCounter++;

				incrementTotalWrongFlips();

				//wrongflipCounterText.setText(context.getResources().getQuantityString(R.plurals.wrongflipsPlural, wrongflipCounter, wrongflipCounter));


				//Set the Card background to Red
				firstCard.setCardBackgroundColor(context.getColor(R.color.md_red_800));
				secondCard.setCardBackgroundColor(context.getColor(R.color.md_red_800));

				//Create CountdownTimer to "Flip" the wrong Cards back to its initial state
				progress3(firstText, secondText, firstPos, secondPos, intColor); // Creating a Countdown timer
				counter.cancel(); // For Safety cancel a possible running countdown Timer
				counter.start(); // Start a new countdown Timer


			} else     //Cards Match
			{

				//Disables CardView when they match
				recyclerView.getChildAt(firstPos).setEnabled(false);
				recyclerView.getChildAt(secondPos).setEnabled(false);


				//Setting CardView Background to Green to symbolize that the Cards match
				// and a successful move was done by the user
				firstCard.setCardBackgroundColor(context.getColor(R.color.md_green_400));
				secondCard.setCardBackgroundColor(context.getColor(R.color.md_green_400));


			}

			//reset countFlipped for the next Card Pair
			countFlipped = 0;
		}

	}


	/**
	 * @param firstText     TextView from the first Card that was flipped
	 * @param secondText    TextView from the second Card that was flipped
	 * @param firstPos      The Position of the first Card in the Recyclerview
	 * @param secondPos     The Position of the second Card in the Recyclerview
	 * @param defaultColour Default Colour of the CardView from the Theme
	 */
	private void progress3(TextView firstText, TextView secondText, int firstPos, int secondPos, int defaultColour)
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
	 * @param tag1 Tag Value from the first Card that was flipped
	 * @param tag2 Tag Value from the second Card that was flipped
	 * @return returns true if Tag equals
	 */
	private boolean compare(String tag1, String tag2)
	{
		return tag1.equalsIgnoreCase(tag2);
	}


}
