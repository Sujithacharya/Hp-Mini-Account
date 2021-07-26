package com.example.hp_mini_account;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private Context context;
    private LayoutInflater inflater;
    private  ArrayList<ModelClass> data;

    public MyAdapter(Context context,ArrayList<ModelClass> data)
    {
      this.context=context;
      this.data=data;
      inflater=LayoutInflater.from(context);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
      TextView consumerNo,inspection,hose,clamp,regulator;
      @SuppressLint("WrongViewCast")
      public MyViewHolder(View itemview)
      {
         super(itemview);
          consumerNo=(TextView) itemview.findViewById(R.id.et_consumer_Number);
          inspection=(TextView) itemview.findViewById(R.id.et_inspected);
          hose=(TextView) itemview.findViewById(R.id.et_hoseused);
          clamp=(TextView) itemview.findViewById(R.id.et_clampused);
          regulator=(TextView) itemview.findViewById(R.id.et_regulatorused);
      }

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = inflater.inflate(R.layout.items_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int position)
    {
        myViewHolder.consumerNo.setText(data.get(position).consumerNo);
        myViewHolder.inspection.setText(data.get(position).inspection);
        myViewHolder.hose.setText(data.get(position).hose);
        myViewHolder.clamp.setText(data.get(position).clamp);
        myViewHolder.regulator.setText(data.get(position).regulator);
    }
    @Override
    public int getItemCount() {
        return 0;
    }
}
