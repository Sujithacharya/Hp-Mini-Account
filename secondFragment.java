package com.example.hp_mini_account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class secondFragment extends Fragment
{
EditText costInspection,costtube15,costtube25,costtube35,costtube45,costclamp,costregulator,costNewConnection,
    profitInspection,profittube15,profittube25,profittube35,profittube45,profitclamp,profitregulator,profitNewConnection;
Button costSubmit;

String res,res1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
      // TODO: Rename and change types of parameters


    public secondFragment() {
        // Required empty public constructor
    }
    @Override
    public  void onViewCreated(View view,Bundle saveInstanceState)
    {
        costInspection=(EditText)view.findViewById(R.id.fs_cost_inspection);
        costtube15=(EditText)view.findViewById(R.id.fs_cost_tube_15);
        costtube25=(EditText)view.findViewById(R.id.fs_cost_Tube_25);
        costtube35=(EditText)view.findViewById(R.id.fs_cost_tube_35);
        costtube45=(EditText)view.findViewById(R.id.fs_cost_tube_45);
        costclamp=(EditText)view.findViewById(R.id.fs_cost_clamp);
        costregulator=(EditText)view.findViewById(R.id.fs_cost_regulator);
        costNewConnection=(EditText)view.findViewById(R.id.fs_cost_newConnection);

        profitInspection=(EditText)view.findViewById(R.id.fs_profit_inspection);
        profittube15=(EditText)view.findViewById(R.id.fs_profit_tube_15);
        profittube25=(EditText)view.findViewById(R.id.fs_profit_tube_25);
        profittube35=(EditText)view.findViewById(R.id.fs_profit_tube_35);
        profittube45=(EditText)view.findViewById(R.id.fs_profit_tube_45);
        profitclamp=(EditText)view.findViewById(R.id.fs_profit_clamp);
        profitregulator=(EditText)view.findViewById(R.id.fs_profit_regulator);
        profitNewConnection=(EditText)view.findViewById(R.id.fs_profit_newConnection);

        costSubmit=(Button)view.findViewById(R.id.cost_submit);
//        profitSubmit=(Button)view.findViewById(R.id.profit_submit);

        getCost();
        getProfit();

        costSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCost();
                updateProfit();
            }
        });
    }

    private void updateProfit()
    {
        if(profitInspection.getText().toString().equals(""))
        {
            profitInspection.setError("Enter Profit");
        }
        else if(profittube15.getText().toString().equals(""))
        {
            profittube15.setError("Enter Profit");
        }
        else if(profittube25.getText().toString().equals(""))
        {
            profittube25.setError("Enter Profit");
        }
        else if(profittube35.getText().toString().equals(""))
        {
            profittube35.setError("Enter Profit");
        }
        else if(profittube45.getText().toString().equals(""))
        {
            profittube45.setError("Enter Profit");
        }
        else if(profitclamp.getText().toString().equals(""))
        {
            profitclamp.setError("Enter Profit");
        }
        else if(profitregulator.getText().toString().equals(""))
        {
            profitregulator.setError("Enter Profit");
        }
        else if(profitNewConnection.getText().toString().equals(""))
        {
            profitNewConnection.setError("Enter Profit");
        }
        else
        {
            Call<ResponseBody> call=MyClient.getInstance().getMyapi().addProfit(profitInspection.getText().toString(),profittube15.getText().toString(),profittube25.getText().toString(),profittube35.getText().toString(),profittube45.getText().toString(),profitclamp.getText().toString(),profitregulator.getText().toString(),profitNewConnection.getText().toString());
            call.enqueue(new Callback<ResponseBody>()
            {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                {
                    try {
                        res1=response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(res1.equals("yes"))
                    {
                        getProfit();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void getProfit()
    {
        Call<GetProfit> call=MyClient.getInstance().getMyapi().getProfit();
        call.enqueue(new Callback<GetProfit>() {
            @Override
            public void onResponse(Call<GetProfit> call, Response<GetProfit> response)
            {
                GetProfit getProfit=response.body();
                profitInspection.setText(getProfit.getProfitInspection());
                profittube15.setText(getProfit.getProfittube15());
                profittube25.setText(getProfit.getProfittube25());
                profittube35.setText(getProfit.getProfittube35());
                profittube45.setText(getProfit.getProfittube45());
                profitclamp.setText(getProfit.getProfitclamp());
                profitregulator.setText(getProfit.getProfitregulator());
                profitNewConnection.setText(getProfit.getProfitNewConnection());

            }
            @Override
            public void onFailure(Call<GetProfit> call, Throwable t) {
            Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCost()
    {
        Call<GetCost> call=MyClient.getInstance().getMyapi().getCost();
        call.enqueue(new Callback<GetCost>() {
            @Override
            public void onResponse(Call<GetCost> call, Response<GetCost> response)
            {
                GetCost getCost=response.body();
               costInspection.setText(getCost.getCostInspection());
               costtube15.setText(getCost.getCosttube15());
               costtube25.setText(getCost.getCosttube25());
               costtube35.setText(getCost.getCosttube35());
               costtube45.setText(getCost.getCosttube45());
               costclamp.setText(getCost.getCostclamp());
               costregulator.setText(getCost.getCostregulator());
               costNewConnection.setText(getCost.getCostNewConnection());
            }
            @Override
            public void onFailure(Call<GetCost> call, Throwable t)
            {
                Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateCost()
    {
        if(costInspection.getText().toString().equals(""))
        {
            costInspection.setError("Enter price");
        }
        else if(costtube15.getText().toString().equals(""))
        {
            costtube15.setError("Enter price");
        }
        else if(costtube25.getText().toString().equals(""))
        {
            costtube25.setError("Enter price");
        }
        else if(costtube35.getText().toString().equals(""))
        {
            costtube35.setError("Enter price");
        }
        else if(costtube45.getText().toString().equals(""))
        {
            costtube45.setError("Enter price");
        }
        else if(costclamp.getText().toString().equals(""))
        {
            costclamp.setError("Enter price");
        }
        else if(costregulator.getText().toString().equals(""))
        {
            costregulator.setError("Enter price");
        }
        else if(costNewConnection.getText().toString().equals(""))
        {
            costNewConnection.setError("Enter price");
        }
        else
        {
            Call<ResponseBody> call=MyClient.getInstance().getMyapi().addCost(costInspection.getText().toString(),costtube15.getText().toString(),costtube25.getText().toString(),costtube35.getText().toString(),costtube45.getText().toString(),costclamp.getText().toString(),costregulator.getText().toString(),costNewConnection.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
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
                       getCost();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
                }
            });
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}