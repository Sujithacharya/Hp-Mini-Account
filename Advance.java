package com.example.hp_mini_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Advance extends AppCompatActivity
{
TextView amount,reason,advance_sujith,advance_akshay;
Spinner name;
Button submit,refresh;
String res;
boolean flag=true;


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
        name=(Spinner) findViewById(R.id.advance_name);
        amount=(TextView) findViewById(R.id.advance_amount);
        reason=(TextView) findViewById(R.id.advance_reason);
        submit=(Button) findViewById(R.id.advance_submit);
//        refresh=(Button) findViewById(R.id.advance_refresh);
        advance_akshay=(TextView) findViewById(R.id.et_advance_akshay);
        advance_sujith=(TextView) findViewById(R.id.et_advance_sujih);
        recyclerView=(RecyclerView)findViewById(R.id.et_recyclerview_advance);

//        recyclerView=findViewById(R.id.et_recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(flag)
                {
                 updateAdvance();
                }

            }
        });
    }


    private void getData()
    {
        Call<List<AdvanceResponseModel>> call=MyClient.getInstance().getMyapi().getAdvance();
        call.enqueue(new Callback<List<AdvanceResponseModel>>()
        {
            @Override
            public void onResponse(Call<List<AdvanceResponseModel>> call, Response<List<AdvanceResponseModel>> response)
            {
                List<AdvanceResponseModel> data=response.body();
                AdvanceAdapter advanceAdapter=new AdvanceAdapter(getApplicationContext(),data);
                recyclerView.setAdapter(advanceAdapter);
                getSumAdvance();
            }
            @Override
            public void onFailure(Call<List<AdvanceResponseModel>> call, Throwable t) {
                  Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getSumAdvance()
    {
        Call<List<Pojoadv>> call=MyClient.getInstance().getMyapi().getadv();
        call.enqueue(new Callback<List<Pojoadv>>() {
            @Override
            public void onResponse(Call<List<Pojoadv>> call, Response<List<Pojoadv>> response) {
                List<Pojoadv> l=response.body();
                String sujith="0",akshay="0",combine="0";
                if(l.get(0).getAdvS()!=null)
                {
                    sujith=l.get(0).advS;
//
                }
                if(l.get(1).getAdvA()!=null)
                {
                    akshay=l.get(1).advA;
//
                }
                if(l.get(2).getAdvC()!=null)
                {
                    combine=l.get(2).advC;
                }
                advance_sujith.setText(String.valueOf(Integer.parseInt(sujith)+Integer.parseInt(combine)*0.5));
                advance_akshay.setText(String.valueOf(Integer.parseInt(akshay)+Integer.parseInt(combine)*0.5));
            }
            @Override
            public void onFailure(Call<List<Pojoadv>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void updateAdvance()
    {
        flag=false;
        final ProgressDialog pd=new ProgressDialog(Advance.this);
        pd.setMessage("Loading...");
        boolean value=true;
        if(name.getSelectedItem().toString().equals("Select"))
        {
           name.setSelection(1);
        }
        if(amount.getText().toString().equals(""))
        {
            amount.setError("Enter amount!");
        }
        if(reason.getText().toString().equals(""))
        {
            reason.setError("Reason Should not be empty !");
        }
        if((!(name.getSelectedItem().toString().equals("Select")))&&(!(amount.getText().toString().equals("")))&&(!(reason.getText().toString().equals(""))))
//        if(value)
        {
            pd.show();
            Call<ResponseBody> call = MyClient.getInstance().getMyapi().addData_advance(name.getSelectedItem().toString(), amount.getText().toString(), reason.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    pd.dismiss();
                    try {
                        res = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (res.equals("yes"))
                    {
                        name.setSelection(0);
                        amount.setText("");
                        reason.setText("");
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                        flag = true;
                        getData();
                    } else {
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
                }
            });
        }

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Advance.this,IndexA.class));
        finish();
    }
}