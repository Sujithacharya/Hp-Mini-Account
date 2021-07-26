package com.example.hp_mini_account;

import androidx.activity.OnBackPressedCallback;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallet extends AppCompatActivity {
    TextView wallet_Collection,wallet_submited,wallet_balance;
    Button submit;
    EditText wallet_amount_submited;
    String res;
    RecyclerView recyclerView;
    boolean flag=true;
    List<GetSalaryItems> data;
    PojoPriceandProfit price;
    PojoSubmit p;
    String collection,sub="0",wallet_b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        wallet_Collection=(TextView)findViewById(R.id.wallet_collection);
        wallet_submited=(TextView)findViewById(R.id.wallet_submited);
        wallet_balance=(TextView)findViewById(R.id.wallet_balance);
        wallet_amount_submited=(EditText)findViewById(R.id.wallet_amount_submited);

        submit=(Button)findViewById(R.id.wallet_submit);

        recyclerView=(RecyclerView)findViewById(R.id.et_recyclerview_wallet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getWallet();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             if(flag)
             {
                 try {
                     insetData();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
            }
        });
        setSubmittedList();

    }

    private void getWallet()
    {
        Call<List<GetSalaryItems>> call = MyClient.getInstance().getMyapi().getSalaryItems();
        call.enqueue(new Callback<List<GetSalaryItems>>() {
            @Override
            public void onResponse(Call<List<GetSalaryItems>> call, Response<List<GetSalaryItems>> response) {
                data = response.body();
                getPrice();

            }

            @Override
            public void onFailure(Call<List<GetSalaryItems>> call, Throwable t) {

            }
        });
    }

    private void getPrice()
    {
        Call<PojoPriceandProfit> call1=MyClient.getInstance().getMyapi().getPriceProfit();
        call1.enqueue(new Callback<PojoPriceandProfit>() {
            @Override
            public void onResponse(Call<PojoPriceandProfit> call, Response<PojoPriceandProfit> response)
            {
                price=response.body();
                collectionCalculate(data,price);
            }

            @Override
            public void onFailure(Call<PojoPriceandProfit> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getSubmitttedAmout(String collect)
    {

          Call<PojoSubmit> call=MyClient.getInstance().getMyapi().getSubAMT();
          call.enqueue(new Callback<PojoSubmit>() {
              @Override
              public void onResponse(Call<PojoSubmit> call, Response<PojoSubmit> response)
              {
                 p=response.body();

                 if(p.getSubmitAmt()!=null)
                 {
                      sub=p.getSubmitAmt();
                      wallet_b=String.valueOf(Double.parseDouble(collect)-Double.parseDouble(sub));
                     wallet_submited.setText(sub);
                     wallet_balance.setText(wallet_b);
                 }

              }
              @Override
              public void onFailure(Call<PojoSubmit> call, Throwable t) {
                  Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
              }
          });
        setSubmittedList();
    }

//       collectionCalculate(data,price);


    @SuppressLint("SetTextI18n")
    private void collectionCalculate(List<GetSalaryItems> data, PojoPriceandProfit pojoPriceandProfit)
    {
//        collection=null;
        collection=String.valueOf
                (
                        Integer.parseInt(data.get(0).getInsSujith())*Integer.parseInt(pojoPriceandProfit.getCostinspection())+
                        Integer.parseInt(data.get(1).getOneHalfSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube15())+
                        Integer.parseInt(data.get(2).getOneHalftwoSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*2+
                        Integer.parseInt(data.get(3).getOneHalfthreeSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*3+
                        Integer.parseInt(data.get(4).getTwoHalfSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube25())+
                        Integer.parseInt(data.get(5).getThreeHalfSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube35())+
                        Integer.parseInt(data.get(6).getFourHalfSujith())*Integer.parseInt(pojoPriceandProfit.getCosttube45())+
                        Integer.parseInt(data.get(7).getOneHalfplustwoHalfSujith())*(Integer.parseInt(pojoPriceandProfit.getCosttube15())+Integer.parseInt(pojoPriceandProfit.getCosttube25()))+
                        Integer.parseInt(data.get(8).getIntoOneSujith())*Integer.parseInt(pojoPriceandProfit.getCostclamp())+
                        Integer.parseInt(data.get(9).getIntoTwoSujith())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*2+
                        Integer.parseInt(data.get(10).getIntoThreeSujith())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*3+
                        Integer.parseInt(data.get(11).getIntoFourSujith())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*4+
                        Integer.parseInt(data.get(12).getRegulatorSujith())*Integer.parseInt(pojoPriceandProfit.getCostregulator())+
                        Integer.parseInt(data.get(13).getRegulatorRSujith())*Integer.parseInt(pojoPriceandProfit.getCostregulator())*0.5+
                        Integer.parseInt(data.get(14).getInsAkshay())*Integer.parseInt(pojoPriceandProfit.getCostinspection())+
                        Integer.parseInt(data.get(15).getOneHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube15())+
                        Integer.parseInt(data.get(16).getOneHalftwoAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*2+
                        Integer.parseInt(data.get(17).getOneHalfthreeAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*3+
                        Integer.parseInt(data.get(18).getTwoHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube25())+
                        Integer.parseInt(data.get(19).getThreeHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube35())+
                        Integer.parseInt(data.get(20).getFourHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getCosttube45())+
                        Integer.parseInt(data.get(21).getOneHalfplustwoHalfAkshay())*(Integer.parseInt(pojoPriceandProfit.getCosttube15())+Integer.parseInt(pojoPriceandProfit.getCosttube25()))+
                        Integer.parseInt(data.get(22).getIntoOneAkshay())*Integer.parseInt(pojoPriceandProfit.getCostclamp())+
                        Integer.parseInt(data.get(23).getIntoTwoAkshay())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*2+
                        Integer.parseInt(data.get(24).getIntoThreeAkshay())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*3+
                        Integer.parseInt(data.get(25).getIntoFourAkshay())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*4+
                        Integer.parseInt(data.get(26).getRegulatorAkshay())*Integer.parseInt(pojoPriceandProfit.getCostregulator())+
                        Integer.parseInt(data.get(27).getRegulatorRAkshay())*Integer.parseInt(pojoPriceandProfit.getCostregulator())*0.5+
                        Integer.parseInt(data.get(28).getInsCombine())*Integer.parseInt(pojoPriceandProfit.getCostinspection())+
                        Integer.parseInt(data.get(29).getOneHalfCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube15())+
                        Integer.parseInt(data.get(30).getOneHalftwoCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*2+
                        Integer.parseInt(data.get(31).getOneHalfthreeCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube15())*3+
                        Integer.parseInt(data.get(32).getTwoHalfCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube25())+
                        Integer.parseInt(data.get(33).getThreeHalfCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube35())+
                        Integer.parseInt(data.get(34).getFourHalfCombine())*Integer.parseInt(pojoPriceandProfit.getCosttube45())+
                        Integer.parseInt(data.get(35).getOneHalfplustwoHalfCombine())*(Integer.parseInt(pojoPriceandProfit.getCosttube15())+Integer.parseInt(pojoPriceandProfit.getCosttube25()))+
                        Integer.parseInt(data.get(36).getIntoOneCombine())*Integer.parseInt(pojoPriceandProfit.getCostclamp())+
                        Integer.parseInt(data.get(37).getIntoTwoCombine())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*2+
                        Integer.parseInt(data.get(38).getIntoThreeCombine())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*3+
                        Integer.parseInt(data.get(39).getIntoFourCombine())*Integer.parseInt(pojoPriceandProfit.getCostclamp())*4+
                        Integer.parseInt(data.get(40).getRegulatorCombine())*Integer.parseInt(pojoPriceandProfit.getCostregulator())+
                        Integer.parseInt(data.get(41).getRegulatorRCombine())*Integer.parseInt(pojoPriceandProfit.getCostregulator())*0.5+
                        Integer.parseInt(data.get(42).getNewconnectCount())*Integer.parseInt(pojoPriceandProfit.getCostNewConnection())

        );
        wallet_Collection.setText("Rs. "+collection);
        getSubmitttedAmout(collection);
    }

    private void setSubmittedList()
    {
        Call<List<WalletResponseModel>> call=MyClient.getInstance().getMyapi().getWalletList();
        call.enqueue(new Callback<List<WalletResponseModel>>() {
            @Override
            public void onResponse(Call<List<WalletResponseModel>> call, Response<List<WalletResponseModel>> response)
            {
                List<WalletResponseModel> data=response.body();
                WalletAdapter walletAdapter=new WalletAdapter(getApplicationContext(),data);
                recyclerView.setAdapter(walletAdapter);
//                getSubmitttedAmout();
            }

            @Override
            public void onFailure(Call<List<WalletResponseModel>> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insetData() throws Exception
    {
        flag=false;
        if(wallet_amount_submited.getText().toString().equals(""))
        {
          wallet_amount_submited.setError("Enter the submitted amount");
        }
        else
        {
           Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_wallet(wallet_amount_submited.getText().toString());
           call.enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
               {
//                   Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                   try
                   {
                       res=response.body().string();
                   }
                   catch (IOException e)
                   {
                       e.printStackTrace();
                   }
//                   Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                   if(res.equals("yes"))
                   {
                       wallet_amount_submited.setText("");
                       Toast.makeText(getApplicationContext(),"Success!!",Toast.LENGTH_LONG).show();
                       getWallet();
//                       setSubmittedList();
                       flag = true;

                   }
                   else
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
        startActivity(new Intent(Wallet.this,IndexA.class));
        finish();
    }
}