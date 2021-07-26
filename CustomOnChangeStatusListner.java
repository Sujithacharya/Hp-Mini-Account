package com.example.hp_mini_account;

import android.view.View;
import android.widget.Toast;

public class CustomOnChangeStatusListner implements View.OnClickListener
{
    @Override
    public void onClick(View view)
    {
        Toast.makeText(view.getContext(),"Inspected :",Toast.LENGTH_SHORT).show();
    }
}
