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

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.MyViewHolder>
{
    private Context context;
    private LayoutInflater inflater;
    private List<WalletResponseModel> data;

    public WalletAdapter(Context context, List<WalletResponseModel> data) {
        this.context = context;
        this.data = data;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.walletcard, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.id.setText(data.get(position).getId());
          holder.date_submit.setText(data.get(position).getDate_submit());
          holder.amount.setText(data.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView id,date_submit,amount;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.wallet_no);
            date_submit=(TextView)itemView.findViewById(R.id.wallet_dates);
            amount=(TextView)itemView.findViewById(R.id.walletcard_amount_submited);

        }
    }
}
