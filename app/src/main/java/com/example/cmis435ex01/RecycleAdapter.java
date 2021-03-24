package com.example.cmis435ex01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



interface OnAdapterItemClickListener
{
	void onAdapterItemClickListener(int position);
}


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>
{
	private final List<String> data;
	private final OnItemClickListener listener;

	public RecycleAdapter(List<String> generateData, OnItemClickListener listener)
	{
		this.data = generateData;
		this.listener = listener;
	}

	public interface OnItemClickListener
	{
		void onItemClick(Integer item);

	}



	@Override
	public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorycard, parent, false);
		return new ViewHolder(rowItem);
	}



	@Override
	public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position)
	{
		holder.cardText.setText(" "+this.data.get(position));

		holder.memCard.setId(this.data.size());

		holder.bind(data.get(position), listener);

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



		public ViewHolder(@NonNull View view) {
			super(view);

			this.cardText = view.findViewById(R.id.cardText);
			this.memCard = view.findViewById(R.id.memCard);



			view.setOnClickListener(this);


		}

		@Override
		public void onClick(View view)
		{


			Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.cardText.getText(), Toast.LENGTH_SHORT).show();

			/*
			Context context = view.getContext();

			Intent intent = new Intent(context, ToDoEdit_Create.class);
			intent.putExtra("toDoTitle",ToDoText.getText());
			intent.putExtra("toDoID",todo1.getId());
			context.startActivity(intent);
			 */


		}


		public void bind(String integer, OnItemClickListener listener)
		{



		}
	}


}

