package com.example.hp_mini_account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    TextView consumerNumber;
    RadioButton inspection;
    Spinner spinner1, spinner2, spinner3,spinner4;
    Button submit,refresh;
    String res;
    RecyclerView recyclerView;
    Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        consumerNumber = (TextView) findViewById(R.id.et_consumerNum);
        inspection = (RadioButton) findViewById(R.id.et_inspection);
//        inspection.setOnClickListener(new CustomOnChangeStatusListner());
        spinner1 = (Spinner) findViewById(R.id.et_spinner1);
        spinner2 = (Spinner) findViewById(R.id.et_spinner2);
        spinner3 = (Spinner) findViewById(R.id.et_spinner3);
        spinner4 = (Spinner) findViewById(R.id.et_spinner4);

//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListner());
//        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListner());
//        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListner());
//        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListner());
        submit=(Button)findViewById(R.id.et_button_Add);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(flag)
                {
                    onclickAddNewRow();
                }

            }
        });

        recyclerView=findViewById(R.id.et_recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        processData();
//        setVisitedHouse();
    }

    private void processData()
    {
        Call<List<HomeResponseModel>>call=MyClient.getInstance().getMyapi().getData();
        call.enqueue(new Callback<List<HomeResponseModel>>()
        {
            @Override
            public void onResponse(Call<List<HomeResponseModel>> call, Response<List<HomeResponseModel>> response)
            {
                List<HomeResponseModel> data=response.body();
                HomeAdapter adapter=new HomeAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<HomeResponseModel>> call, Throwable t)
            {
//                Toast.makeText(getApplicationContext(),"OFF",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
                Log.d("Error Response",t.toString());
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onclickAddNewRow()
    {
        flag=false;
        int conNum=0;
        String inspec="0",spin1="0",spin2="0",spin3="0",spin4="0";
        if(consumerNumber.getText().toString().equals(""))
        {
            consumerNumber.setError("Enter Consumer number !!");
        }
        else if(consumerNumber.getText().toString().length()!=6)
        {
            consumerNumber.setError("Enter Valid number !!");
        }
        else if(spinner4.getSelectedItem().toString().equals("Select"))
        {
            spinner4.setBackground(getDrawable(R.color.red));
        }
        else if(inspection.isChecked() || !(spinner1.getSelectedItem().toString().equals("Select")) ||!(spinner2.getSelectedItem().toString().equals("Select")) || !(spinner3.getSelectedItem().toString().equals("Select")))
        {
            conNum=Integer.parseInt(consumerNumber.getText().toString());
            if(inspection.isChecked())
            {
                inspec="1";
            }
            spin1=spinner1.getSelectedItem().toString();
            spin2=spinner2.getSelectedItem().toString();
            spin3=spinner3.getSelectedItem().toString();
            spin4=spinner4.getSelectedItem().toString();

            Call<ResponseBody>call=MyClient.getInstance().getMyapi().addData(conNum,inspec,spin1,spin2,spin3,spin4);
            call.enqueue(new Callback<ResponseBody>()
            {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                {

                    try {
                        res=response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(res.equals("yes"))
                    {
                        Toast.makeText(getApplicationContext(),"Update Successfull .",Toast.LENGTH_LONG).show();
                        consumerNumber.setText("");
                        inspection.setChecked(false);
                        spinner1.setSelection(0);
                        spinner2.setSelection(0);
                        spinner3.setSelection(0);
                        spinner4.setSelection(0);
                        flag=true;
                        processData();
                    }
                    else if(res.equals("no"))
                    {
                        Toast.makeText(getApplicationContext(),"Not updated",Toast.LENGTH_LONG).show();
                        consumerNumber.setError("Update failed .");
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
        startActivity(new Intent(HomeActivity.this,IndexA.class));
        finish();
    }

}
