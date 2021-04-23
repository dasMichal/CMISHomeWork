package com.example.cmis435ex01.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmis435ex01.R;
import com.example.cmis435ex01.adapter.RecyclerViewAdapter;
import com.example.cmis435ex01.model.MemoryModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel
{

    MutableLiveData<List<String>> EmojiLiveData;
    MutableLiveData<String> totalFlipsLiveData, totalWrongFlipsLiveData;
    MemoryModel game;



    public MainViewModel(Application app)
    {
        super(app);

        game = new MemoryModel();


        EmojiLiveData = new MutableLiveData<>();
        EmojiLiveData.setValue(game.getEmojiList());

        totalFlipsLiveData = new MutableLiveData<>();
        totalWrongFlipsLiveData = new MutableLiveData<>();

        totalFlipsLiveData.setValue(getTotalFlipsString());
        totalWrongFlipsLiveData.setValue(getTotalWrongFlipsString());

    }



    public MutableLiveData<List<String>> getEmojiMutableLiveData()
    {
        return EmojiLiveData;
    }


    public void RecycleViewClick(CardView v, TextView text, int itemCount, int layoutPosition, RecyclerViewAdapter.ViewHolder viewHolder, RecyclerView recyclerView, Context context)
    {
        game.logic(v,text,itemCount, layoutPosition, viewHolder,recyclerView,context);      //hehe gameLogic
    }




    public MutableLiveData<String> getTotalFlipsLiveData()
    {

        this.totalFlipsLiveData.postValue(getTotalFlipsString());
        return totalFlipsLiveData;

    }


    public MutableLiveData<String> getTotalWrongFlipsLiveData()
    {

        this.totalWrongFlipsLiveData.postValue(getTotalWrongFlipsString());

        return totalWrongFlipsLiveData;
    }



    public String getTotalFlipsString()
    {
        //return String.valueOf(points.getTotalFlips());

        return getApplication().getResources().getQuantityString(R.plurals.flipsPlural,game.getTotalFlipsMODEL() , game.getTotalFlipsMODEL()  );

    }

    public String getTotalWrongFlipsString()
    {
        return getApplication().getResources().getQuantityString(R.plurals.wrongflipsPlural,game.getTotalWrongFlipsMODEL() ,game.getTotalWrongFlipsMODEL() );
        // return String.valueOf(points.getTotalWrongFlips());
    }





}
