package com.example.hp_mini_account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdvanceAdapter extends  RecyclerView.Adapter<AdvanceAdapter.MyViewHolder>
{
     Context context;
     LayoutInflater inflater;
     List<AdvanceResponseModel> data;
    public AdvanceAdapter(Context context,List<AdvanceResponseModel> data)
    {
        this.context = context;
        this.data = data;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.advancecard, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
         holder.id.setText(data.get(position).getId());
         holder.date.setText(data.get(position).getPresent_adv_date());
         holder.name.setText(data.get(position).getNameadv());
         holder.amount.setText(data.get(position).getAmmount());
         holder.reason.setText(data.get(position).getReason());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
      TextView id,date,name,amount,reason;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.advancecard_number);
            date=(TextView)itemView.findViewById(R.id.advancecard_Date);
            name=(TextView)itemView.findViewById(R.id.advancecard_name);
            amount=(TextView)itemView.findViewById(R.id.advancecard_amount);
            reason=(TextView)itemView.findViewById(R.id.advancecard_reason);
        }
    }
}
