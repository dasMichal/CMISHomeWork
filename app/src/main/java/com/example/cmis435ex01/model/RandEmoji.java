package com.example.cmis435ex01.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandEmoji
{

	private final Random r = new Random();

	List<String> memCardContent = new ArrayList<>();
	List<String> list = Stream.of("🍕", "🌭","🏓","🙈","🛠","⚽","🌈","🥫","🍝", "🍜" ,"🍲", "🍛", "🍣" ,"🍱", "🥟", "🦪", "🍤", "🍙", "🍚", "🍘", "🍥", "🥠", "🥮", "🍢", "🍡", "🍧", "🍨", "🍦", "🥧", "🧁").collect(Collectors.toList());
	//List<String> list2 = Stream.of("🍕","🍕","🌭","🌭","🍥","🍥","⚽","⚽").collect(Collectors.toList());
	List<String> list3 ;


	public void RandEmojiList()
	{

		// Create random numbers and add them to List
		list3.clear();


		for (int i = 0; i < 4; i++)
		{


			boolean notInList;
			do
			{
				int randomIndex = r.nextInt(list.size());

				String tmp = list.get(randomIndex);




				long count = list3.stream().filter(ranNumbers -> Objects.equals(tmp, list3)).count(); //Using Java Stream to get how often tmp occurs in ranNumbers.
				//System.out.println("Loopcount "+i+"| "+tmp+" occures "+count);

				notInList = count == 0;         //notInList  is true if count equals 0


				list3.add(tmp);

			}while(!notInList);



		}


	}

	public List<String> getEmojiList()
	{
		return list3;
	}



	public void setRandNum(int rNum)
	{
		this.rNum = rNum;
	}











}
