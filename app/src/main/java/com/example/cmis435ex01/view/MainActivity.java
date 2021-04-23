package com.example.cmis435ex01.view;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmis435ex01.R;
import com.example.cmis435ex01.adapter.RecyclerViewAdapter;
import com.example.cmis435ex01.databinding.ActivityMemoryMainGameBinding;
import com.example.cmis435ex01.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    MainActivity context;
    MainViewModel viewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Application app;
    TextView totalFlipsTEXT;
    TextView totalWrongFlipsTEXT;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_main_game);

        //Setting Data Binding
        ActivityMemoryMainGameBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_memory_main_game);
        binding.setLifecycleOwner(this);    //Sets the LifeCycle Owner whose DataBindings should be observed

        context = this;
        app = getApplication();

        init();

        //Setting the LiveData Observer for the RecycleViewer
        viewModel.getEmojiMutableLiveData().observe(context, EmojiListUpdateObserver);

        //Setting the LiveData Observer for the TextView
        viewModel.getTotalFlipsLiveData().observe(context, s ->
        {
            //Executes onChange of the LiveData
            Log.d("LiveData ", "--onChanged-- " + s);

            totalFlipsTEXT.setText(s);

        });

        //Setting the LiveData Observer for the TextView
        viewModel.getTotalWrongFlipsLiveData().observe(this, s ->
        {
            //Executes onChange of the LiveData
            Log.d("LiveData ", "--onChanged-- " + s);
            totalWrongFlipsTEXT.setText(s);

        });

    }

    private void init()
    {

        totalFlipsTEXT = this.findViewById(R.id.totalFlips);
        totalWrongFlipsTEXT = this.findViewById(R.id.wrongtotalFlips);
        recyclerView = findViewById(R.id.MemoryCardHolder);
        viewModel = new ViewModelProvider(context).get(MainViewModel.class);
    }



    Observer<List<String>> EmojiListUpdateObserver = new Observer<List<String>>() //OnChange Obserer for the Emoji LiveData List
    {
        @Override
        public void onChanged(List<String> userArrayList)
        {

            recyclerViewAdapter = new RecyclerViewAdapter(userArrayList, new RecyclerViewAdapter.OnItemClickListener()
            {

                //Custom OnClick Interface from the RecycleViewer Adapter
                public void RecycleClick(CardView v, TextView text, int itemCount, int layoutPosition, RecyclerViewAdapter.ViewHolder viewHolder)
                {

                    Log.d("MainGame","itemCount = " + itemCount);


                    //Calling ViewModel to pass Data to the Model
                    viewModel.RecycleViewClick(v,  text,  itemCount,  layoutPosition, viewHolder,recyclerView,context);

                    //Refreshing the TextView
                    viewModel.getTotalFlipsLiveData();
                    viewModel.getTotalWrongFlipsLiveData();

                }

            });


            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setClickable(true);
            recyclerView.setLayoutManager(new GridLayoutManager(context,2));

            recyclerViewAdapter.notifyDataSetChanged();

        }
    };


}
