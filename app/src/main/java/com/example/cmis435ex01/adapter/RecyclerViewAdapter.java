package com.example.cmis435ex01.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmis435ex01.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private final List<String> data;
    private final RecyclerViewAdapter.OnItemClickListener listener;
    private final int selectedPos = RecyclerView.NO_POSITION;



    public RecyclerViewAdapter(List<String> generateData, RecyclerViewAdapter.OnItemClickListener listener)
    {
        this.data = generateData;
        this.listener = listener;
    }

    public interface OnItemClickListener
    {

        void RecycleClick(CardView memCard, TextView cardText, int itemCount, int layoutPosition, RecyclerViewAdapter.ViewHolder viewHolder);



    }



    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorycard, parent, false);
        return new ViewHolder(rowItem);
    }



    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position)
    {
        holder.cardText.setText(" "+this.data.get(position));
        holder.cardText.setVisibility(View.INVISIBLE);
        holder.memCard.setId(this.data.size());
        holder.memCard.setTag(" "+this.data.get(position));

        holder.bind(data.get(position), listener);
        //holder.itemView.setSelected(selectedPos == position);
        holder.memCard.setSelected(selectedPos == position);

    }

    @Override
    public int getItemCount()
    {
        return this.data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView cardText;
        private final CardView memCard;



        public ViewHolder(@NonNull View view)
        {
            super(view);

            this.cardText = view.findViewById(R.id.cardText);
            this.memCard = view.findViewById(R.id.memCard);

            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view)
        {

            Log.d("RecycleViewer","cardText.isSelected() = " + cardText.isSelected());

            if (View.VISIBLE != cardText.getVisibility())
            {
                //System.out.println("Empty Field");
                //selectedPos = getLayoutPosition();
                //notifyItemChanged(selectedPos);
                Log.d("RecycleView","Empty Field");

                cardText.setVisibility(View.VISIBLE);
                listener.RecycleClick(this.memCard,this.cardText,getItemCount(),getLayoutPosition(), this);


            } else
            {

                //Log.d("RecycleView","Full Field");
                //notifyItemChanged(selectedPos);
                //selectedPos = RecyclerView.NO_POSITION;
                //cardText.setVisibility(View.INVISIBLE);
                //listener.test(this.memCard,this.cardText,getItemCount(),getLayoutPosition(),this);

            }


            //System.out.println("cardText.getVisibility() = " + cardText.getVisibility());
            //notifyItemChanged(selectedPos);

            //Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.cardText.getText(), Toast.LENGTH_SHORT).show();




        }


        public void bind(String integer, RecyclerViewAdapter.OnItemClickListener listener)
        {


        }
    }


}
