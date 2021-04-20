package com.example.cmis435ex01.model;

import java.util.List;
import java.util.Random;

public class MemoryModel
{

	private final com.example.cmis435ex01.model.RandEmoji EmojiList;

	public MemoryModel()
	{

		EmojiList = new com.example.cmis435ex01.model.RandEmoji();

	}



	public List<String> getEmojiList()
	{
		return  EmojiList.getEmojiList();

	}





	private void roll()
	{
		Random r = new Random();
		rNumL.setRandNum(r.nextInt(9));
		rNumR.setRandNum(r.nextInt(9));

		while (rNumL.getRandNum() == rNumR.getRandNum())
			rNumR.setRandNum(r.nextInt(9));
	}














}
