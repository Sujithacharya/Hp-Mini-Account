package com.example.hp_mini_account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.MyViewHolder>
{
    List<HomeResponseModel> data;
    Context context;
    LayoutInflater inflater;

    public HomeAdapter(List<HomeResponseModel> data,Context context)
    {
        this.data = data;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.items_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
         holder.inspector.setText(data.get(position).getName_inspector());
         holder.inspection.setText(data.get(position).getInspection());
         holder.cosumerNumber.setText(data.get(position).getConsumer_num());
         holder.hose.setText(data.get(position).getHose());
         holder.clamp.setText(data.get(position).getClamp());
         holder.regulator.setText(data.get(position).getRegulator());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
      TextView inspector,inspection,cosumerNumber,hose,clamp,regulator;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            inspector=itemView.findViewById(R.id.et_inspector);
            inspection=itemView.findViewById(R.id.et_inspected);
            cosumerNumber=itemView.findViewById(R.id.et_consumer_Number);
            hose=itemView.findViewById(R.id.et_hoseused);
            clamp=itemView.findViewById(R.id.et_clampused);
            regulator=itemView.findViewById(R.id.et_regulatorused);
        }
    }
}
