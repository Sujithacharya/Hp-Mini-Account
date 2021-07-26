package com.example.hp_mini_account;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.IllegalFormatConversionException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdFragment extends Fragment {
    TextView stDate,edDate,paidDate;
    public static int styear,stmonth,stdate,edyear,edmonth,eddate;
     Button bt;
    String res;
    public ThirdFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
        @Override
        public  void onViewCreated(View view,Bundle saveInstanceState)
        {
            stDate=(TextView)view.findViewById(R.id.ft_stateDate);
            edDate=(TextView)view.findViewById(R.id.ft_endDate);
            bt=(Button)view.findViewById(R.id.ft_paid);
            paidDate=(TextView)view.findViewById(R.id.ft_paidDate);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    settlementDate();
                }
            });
            stDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                   startDate();
                }
            });
            edDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    endDate();
                }
            });
            getDate();
        }

    private void getDate()
    {
        Call<List<GetDate>> call=MyClient.getInstance().getMyapi().getDate();
        call.enqueue(new Callback<List<GetDate>>() {
            @Override
            public void onResponse(Call<List<GetDate>> call, Response<List<GetDate>> response)
            {
                Toast.makeText(getContext(),"Loading...",Toast.LENGTH_LONG).show();
                List<GetDate> data=response.body();
//                MainActivity m=new MainActivity(data.get(1).getSettle());
                stDate.setText(data.get(0).getStartDate());
                edDate.setText(data.get(0).getEndDate());
                paidDate.setText(data.get(1).getSettle());
            }
            @Override
            public void onFailure(Call<List<GetDate>> call, Throwable t) {
                Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void settlementDate()
    {
        Calendar calendar = Calendar.getInstance();
        int curyear = calendar.get(calendar.YEAR);
        int curmonth =calendar.get(calendar.MONTH)+1;
        int curdate = calendar.get(Calendar.DAY_OF_MONTH);

        String dateString=String.format("%d-%02d-%02d",curyear,curmonth,curdate);
        updateSettleDate(dateString);
        paidDate.setText(dateString);
    }

    private void updateSettleDate(String dateString)
    {
        Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_settleDate(dateString);
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
                Toast.makeText(getContext(),res,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startDate()
    {
        Calendar calendar = Calendar.getInstance();
        styear = calendar.get(Calendar.YEAR);
        stmonth = calendar.get(Calendar.MONTH);
        stdate = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date)
            {
                String curyear = String.valueOf(year);
                String curmonth = String.valueOf(month+1);
                String curdate = String.valueOf(date);
                @SuppressLint("DefaultLocale")
                String  dateString = String.format("%d-%02d-%02d",Integer.parseInt(curyear),Integer.parseInt(curmonth),Integer.parseInt(curdate));

                updateStartDate(dateString);
                stDate.setText(dateString);
            }
        }, styear, stmonth, stdate);
        datePickerDialog.show();
    }

    private void updateStartDate(String dateString)
    {
        Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_startdate(dateString);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                String res="no";
                try {
                    res=response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(res.equals("Yes"))
                {
                    Toast.makeText(getContext(),"updated",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void endDate()
    {
        Calendar calendar = Calendar.getInstance();
        edyear = calendar.get(Calendar.YEAR);
        edmonth = calendar.get(Calendar.MONTH);
        eddate = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                String curyear = String.valueOf(year);
                String curmonth = String.valueOf(month+1);
                String curdate = String.valueOf(day);
                String dateString=String.format("%d-%02d-%02d",Integer.parseInt(curyear),Integer.parseInt(curmonth),Integer.parseInt(curdate));
                updateEndDate(dateString);
                edDate.setText(dateString);
            }
        }, edyear, edmonth, eddate);
        datePickerDialog.show();
    }

    private void updateEndDate(String dateString)
    {
        Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_enddate(dateString);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                String res="no";
                try {
                    res=response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(res.equals("Yes"))
                {
                    Toast.makeText(getContext(),"updated",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });

    }
}