package com.example.hp_mini_account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastDivide extends AppCompatActivity
{
    private LinearLayout soldItem,todaysUpdate,predictSalary,advance,newConnection,myWallet;
    public static int styear,stmonth,stdate,edyear,edmonth,eddate;
    String stDate,edDate;

    public CastDivide() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_divide);
        soldItem=(LinearLayout)findViewById(R.id.cast_SoldItem);
        todaysUpdate=(LinearLayout)findViewById(R.id.cast_TodaysUpdate);
        predictSalary=(LinearLayout)findViewById(R.id.cast_PredictSalary);
        advance=(LinearLayout)findViewById(R.id.cast_Advance);
        newConnection=(LinearLayout)findViewById(R.id.cast_NewConnection);
        myWallet=(LinearLayout)findViewById(R.id.cast_Mywallet);
        switchTO();

//        updateDate();
    }

//    private void updateDate()
//    {
//        if(!(stDate.length()==0 && edDate.length()==0))
//        {
//            Call <CastDivide> call=MyClient.getInstance().getMyapi().addData_startdate(stDate,edDate);
//            call.enqueue(new Callback<CastDivide>() {
//                @Override
//                public void onResponse(Call<CastDivide> call, Response<CastDivide> response) {
//                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<CastDivide> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item1:
            Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
            return  true;
            case R.id.item2:
                Toast.makeText(this,"UpdatePrice",Toast.LENGTH_LONG).show();
                return  true;
            case R.id.item3:
                Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
                return  true;
            case R.id.startDate:
//                Toast.makeText(this,"startDate",Toast.LENGTH_LONG).show();
                startDate();
                return  true;
            case R.id.endDate:
//                Toast.makeText(this,"endDate",Toast.LENGTH_LONG).show();
                endDate();
                return  true;
            case R.id.item4:
                Toast.makeText(this,"Settlement",Toast.LENGTH_LONG).show();
                return  true;
            case R.id.submitDate:
//                Toast.makeText(this,"SubmitDate",Toast.LENGTH_LONG).show();
//                updateDate();

                return  true;

        }
        return super.onOptionsItemSelected(item);

    }

    private void startDate()
    {
        Calendar calendar = Calendar.getInstance();
        styear = calendar.get(Calendar.YEAR);
        stmonth = calendar.get(Calendar.MARCH);
        stdate = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CastDivide.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date)
            {
                stDate = String.valueOf(year + "-" + month + "-" + date);
                Toast.makeText(getApplicationContext(),stDate, Toast.LENGTH_LONG).show();

            }
        }, styear, stmonth, stdate);
        datePickerDialog.show();
    }
    private void endDate()
    {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(CastDivide.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                edDate = String.valueOf(year + "-" + month + "-" + day);
                Toast.makeText(getApplicationContext(),edDate, Toast.LENGTH_LONG).show();
            }
        }, edyear, edmonth, eddate);
        datePickerDialog.show();

    }
    private void switchTO()
    {
        soldItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CastDivide.this,Index.class);
                startActivity(intent);
                finish();
            }
        });
        todaysUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastDivide.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        predictSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastDivide.this,Salary.class);
                startActivity(intent);
                finish();
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastDivide.this,Advance.class);
                startActivity(intent);
                finish();
            }
        });
        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastDivide.this,Wallet.class);
                startActivity(intent);
                finish();
            }
        });
        newConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CastDivide.this,New_Connection.class);
                startActivity(intent);
                finish();
            }
        });
    }
}