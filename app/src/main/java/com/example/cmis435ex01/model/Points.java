package com.example.cmis435ex01.model;

import androidx.databinding.BaseObservable;

public class Points extends BaseObservable
{

	private int totalFlips;
	private int totalWrongFlips;

	//Constructor for Points
	public Points()
	{
		this.totalFlips = 0;
		this.totalWrongFlips = 0;
	}


	public int getTotalWrongFlips()
	{
		return totalWrongFlips;
	}

	public void setTotalWrongFlips(int totalWrongFlips)
	{
		this.totalWrongFlips = totalWrongFlips;


	}


	public int getTotalFlips()
	{
		return this.totalFlips;
	}

	public void setTotalFlips(int totalFlips)
	{
		this.totalFlips = totalFlips;
	}


}
