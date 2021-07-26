package com.example.hp_mini_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_Connection extends AppCompatActivity {
    TextView consumerNum,place,newconnCount;
    Button submit,refresh;
    String res;

    RecyclerView recyclerView;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__connection);
        consumerNum=(TextView)findViewById(R.id.et_newConnection_consumerNum);
        place=(TextView) findViewById(R.id.et_newConnection_place);
        newconnCount=(TextView)findViewById(R.id.et_countNewConnect);
        submit=(Button) findViewById(R.id.et_newConnection_sub);
        recyclerView=(RecyclerView)findViewById(R.id.et_recyclerview_newConnection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(flag)
                {
                    upDateNewConnection();
                }
            }
        });
        getNewConnetion();
    }

    private void getNewConnetion()
    {
        Call<List<NewConnectionResponseModel>> call=MyClient.getInstance().getMyapi().getNewConnect();
        call.enqueue(new Callback<List<NewConnectionResponseModel>>() {
            @Override
            public void onResponse(Call<List<NewConnectionResponseModel>> call, Response<List<NewConnectionResponseModel>> response)
            {
               List<NewConnectionResponseModel> data=response.body();
               NewConnectionAdapter newConnectionAdapter=new NewConnectionAdapter(getApplicationContext(),data);
               recyclerView.setAdapter(newConnectionAdapter);
               countNewConnection();
            }
            @Override
            public void onFailure(Call<List<NewConnectionResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void countNewConnection()
    {
        Call<NewConnectCount> call=MyClient.getInstance().getMyapi().getnewConnectCount();
        call.enqueue(new Callback<NewConnectCount>() {
            @Override
            public void onResponse(Call<NewConnectCount> call, Response<NewConnectCount> response) {
                NewConnectCount c=response.body();
                newconnCount.setText("Count : "+c.getNewConnectCount());
            }

            @Override
            public void onFailure(Call<NewConnectCount> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void upDateNewConnection()
    {
        flag=false;
        final ProgressDialog pd=new ProgressDialog(New_Connection.this);
        pd.setMessage("Please wait...");
        if(consumerNum.getText().toString().equals(""))
        {
            consumerNum.setError("Enter Number !");
        }
        else if(consumerNum.getText().toString().length()!=6)
        {
           consumerNum.setError("Enter Valid Consumer Number");
        }
       else if(place.getText().toString().equals(""))
        {
           place.setError("Enter place");
        }
        else
        {
             pd.show();
            Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_newConnection(consumerNum.getText().toString(),place.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                {
                    consumerNum.setText("");
                    place.setText("");
                    pd.dismiss();

                    try {
                        res=response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(res.equals("yes"))
                    {
                        consumerNum.setText("");
                        place.setText("");
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        flag=true;
                        getNewConnetion();
                    }
                    else if(res.equals("no"))
                    {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t)
                {
                    Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(New_Connection.this,IndexA.class));
        finish();
    }
}