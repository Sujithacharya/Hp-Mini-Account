package com.example.hp_mini_account;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainFragment extends Fragment
{
    private LinearLayout soldItem,todaysUpdate,predictSalary,advance,newConnection,myWallet;
    public MainFragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public  void onViewCreated(View view,Bundle saveInstanceState)
    {
        soldItem=(LinearLayout)view.findViewById(R.id.cast_SoldItem);
        todaysUpdate=(LinearLayout)view.findViewById(R.id.cast_TodaysUpdate);
        predictSalary=(LinearLayout)view.findViewById(R.id.cast_PredictSalary);
        advance=(LinearLayout)view.findViewById(R.id.cast_Advance);
        newConnection=(LinearLayout)view.findViewById(R.id.cast_NewConnection);
        myWallet=(LinearLayout)view.findViewById(R.id.cast_Mywallet);
        switchTO();
    }
    private void switchTO()
    {
        soldItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(),Index.class);
                startActivity(intent);
            }
        });
        todaysUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),HomeActivity.class);
                startActivity(intent);
//                finish();
            }
        });
        predictSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Salary.class);
                startActivity(intent);
//                finish();
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Advance.class);
                startActivity(intent);
//                finish();
            }
        });
        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Wallet.class);
                startActivity(intent);
//                finish();
            }
        });
        newConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),New_Connection.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}