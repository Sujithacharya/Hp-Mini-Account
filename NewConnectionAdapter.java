package com.example.hp_mini_account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewConnectionAdapter extends RecyclerView.Adapter<NewConnectionAdapter.MyViewHolder>
{
    Context context;
    List<NewConnectionResponseModel> data;
    LayoutInflater inflater;

    public NewConnectionAdapter(Context context, List<NewConnectionResponseModel> data)
    {
        this.context = context;
        this.data = data;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.newconnectioncard, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
         holder.no.setText(data.get(position).getId());
         holder.newConneDate.setText(data.get(position).getDate_new());
         holder.consumerNumber.setText(data.get(position).getConsumerNo());
         holder.place.setText(data.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView no,newConneDate,consumerNumber,place;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            no=(TextView)itemView.findViewById(R.id.newconnectioncard_no);
            newConneDate=(TextView)itemView.findViewById(R.id.newconnectioncard_date);
            consumerNumber=(TextView)itemView.findViewById(R.id.newconnectioncard_consumerno);
            place=(TextView)itemView.findViewById(R.id.newconnectioncard_place);

        }
    }
}
