package com.example.hp_mini_account;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class CustomOnItemSelectedListner implements AdapterView.OnItemSelectedListener
{
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Toast.makeText(adapterView.getContext(),"Used :" + adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
