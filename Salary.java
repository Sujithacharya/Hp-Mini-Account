package com.example.hp_mini_account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Salary extends AppCompatActivity {
   TextView totalSalary,advance_sujith,advance_akshay,eachsalary_sujith,eachsalary_akshay,salaryasWorkAkshay,salaryasWorkSujith;

   List<GetSalaryItems> values;
   PojoPriceandProfit profitandprice;
   List<Pojoadv> pojoadv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        totalSalary=(TextView)findViewById(R.id.salary_total_salary);
        advance_sujith=(TextView)findViewById(R.id.salary_advance_sujith);
        advance_akshay=(TextView)findViewById(R.id.salary_advance_akshay);
        eachsalary_sujith=(TextView)findViewById(R.id.salary_eachsalary_sujith);
        eachsalary_akshay=(TextView)findViewById(R.id.salary_eachsalary_akshay);
        salaryasWorkAkshay=(TextView)findViewById(R.id.salary_aswork_akshay);
        salaryasWorkSujith=(TextView)findViewById(R.id.salary_aswork_sujith);

        getValues();
    }

    private void getPrice()
    {
        Call<PojoPriceandProfit> call=MyClient.getInstance().getMyapi().getPriceProfit();
        call.enqueue(new Callback<PojoPriceandProfit>() {
            @Override
            public void onResponse(Call<PojoPriceandProfit> call, Response<PojoPriceandProfit> response)
            {
//                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
                PojoPriceandProfit pojoPriceandProfit=response.body();
//                Toast.makeText(getApplicationContext(),pojoPriceandProfit.getProfitregulator(),Toast.LENGTH_LONG).show();
                profitandprice=pojoPriceandProfit;
                getadvance();

            }
            @Override
            public void onFailure(Call<PojoPriceandProfit> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getadvance()
    {
        Call<List<Pojoadv>>call=MyClient.getInstance().getMyapi().getadv();
        call.enqueue(new Callback<List<Pojoadv>>() {
            @Override
            public void onResponse(Call<List<Pojoadv>> call, Response<List<Pojoadv>> response)
            {
                List<Pojoadv> pojoad=response.body();
                pojoadv=pojoad;
//                Toast.makeText(getApplicationContext(),pojoadv.get(1).getAdvA(),Toast.LENGTH_LONG).show();
                countEachSalary(values,profitandprice,pojoadv);

            }
            @Override
            public void onFailure(Call<List<Pojoadv>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getValues()
    {
       Call<List<GetSalaryItems>> call=MyClient.getInstance().getMyapi().getSalaryItems();
       call.enqueue(new Callback<List<GetSalaryItems>>() {
           @Override
           public void onResponse(Call<List<GetSalaryItems>> call, Response<List<GetSalaryItems>> response)
           {
               List<GetSalaryItems> data=response.body();
               values=data;
               getPrice();
           }
           @Override
           public void onFailure(Call<List<GetSalaryItems>> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
                 Log.d("ErrorMSG",t.toString());
           }
       });


    }

    private void countEachSalary(List<GetSalaryItems> data,PojoPriceandProfit pojoPriceandProfit,List<Pojoadv> adv)
    {
        String totalSalarySujith=null,totalSalaryAkshay=null,totalSalaryCombine=null,totalSalarys=null;


        totalSalarys=String.valueOf(
                Integer.parseInt(data.get(0).getInsSujith())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                        Integer.parseInt(data.get(1).getOneHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                        Integer.parseInt(data.get(2).getOneHalftwoSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                        Integer.parseInt(data.get(3).getOneHalfthreeSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                        Integer.parseInt(data.get(4).getTwoHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                        Integer.parseInt(data.get(5).getThreeHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                        Integer.parseInt(data.get(6).getFourHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                        Integer.parseInt(data.get(7).getOneHalfplustwoHalfSujith())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                        Integer.parseInt(data.get(8).getIntoOneSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                        Integer.parseInt(data.get(9).getIntoTwoSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                        Integer.parseInt(data.get(10).getIntoThreeSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                        Integer.parseInt(data.get(11).getIntoFourSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                        Integer.parseInt(data.get(12).getRegulatorSujith())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(13).getRegulatorRSujith())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(14).getInsAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                        Integer.parseInt(data.get(15).getOneHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                        Integer.parseInt(data.get(16).getOneHalftwoAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                        Integer.parseInt(data.get(17).getOneHalfthreeAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                        Integer.parseInt(data.get(18).getTwoHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                        Integer.parseInt(data.get(19).getThreeHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                        Integer.parseInt(data.get(20).getFourHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                        Integer.parseInt(data.get(21).getOneHalfplustwoHalfAkshay())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                        Integer.parseInt(data.get(22).getIntoOneAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                        Integer.parseInt(data.get(23).getIntoTwoAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                        Integer.parseInt(data.get(24).getIntoThreeAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                        Integer.parseInt(data.get(25).getIntoFourAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                        Integer.parseInt(data.get(26).getRegulatorAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(27).getRegulatorRAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(28).getInsCombine())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                        Integer.parseInt(data.get(29).getOneHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                        Integer.parseInt(data.get(30).getOneHalftwoCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                        Integer.parseInt(data.get(31).getOneHalfthreeCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                        Integer.parseInt(data.get(32).getTwoHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                        Integer.parseInt(data.get(33).getThreeHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                        Integer.parseInt(data.get(34).getFourHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                        Integer.parseInt(data.get(35).getOneHalfplustwoHalfCombine())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                        Integer.parseInt(data.get(36).getIntoOneCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                        Integer.parseInt(data.get(37).getIntoTwoCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                        Integer.parseInt(data.get(38).getIntoThreeCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                        Integer.parseInt(data.get(39).getIntoFourCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                        Integer.parseInt(data.get(40).getRegulatorCombine())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(41).getRegulatorRCombine())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(42).getNewconnectCount())*Integer.parseInt(pojoPriceandProfit.getProfitNewConnection())

        );
        totalSalary.setText("Rs. "+totalSalarys);
        totalSalaryCombine=String.valueOf(

                                Integer.parseInt(data.get(28).getInsCombine())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                                Integer.parseInt(data.get(29).getOneHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                                Integer.parseInt(data.get(30).getOneHalftwoCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                                Integer.parseInt(data.get(31).getOneHalfthreeCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                                Integer.parseInt(data.get(32).getTwoHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                                Integer.parseInt(data.get(33).getThreeHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                                Integer.parseInt(data.get(34).getFourHalfCombine())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                                Integer.parseInt(data.get(35).getOneHalfplustwoHalfCombine())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                                Integer.parseInt(data.get(36).getIntoOneCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                                Integer.parseInt(data.get(37).getIntoTwoCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                                Integer.parseInt(data.get(38).getIntoThreeCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                                Integer.parseInt(data.get(39).getIntoFourCombine())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                                Integer.parseInt(data.get(40).getRegulatorCombine())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                                Integer.parseInt(data.get(41).getRegulatorRCombine())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())

        );

        totalSalarySujith=String.valueOf(
                Integer.parseInt(totalSalaryCombine)*0.5+
                Integer.parseInt(data.get(0).getInsSujith())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                Integer.parseInt(data.get(1).getOneHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                Integer.parseInt(data.get(2).getOneHalftwoSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                Integer.parseInt(data.get(3).getOneHalfthreeSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                Integer.parseInt(data.get(4).getTwoHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                Integer.parseInt(data.get(5).getThreeHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                Integer.parseInt(data.get(6).getFourHalfSujith())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                Integer.parseInt(data.get(7).getOneHalfplustwoHalfSujith())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                Integer.parseInt(data.get(8).getIntoOneSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                Integer.parseInt(data.get(9).getIntoTwoSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                Integer.parseInt(data.get(10).getIntoThreeSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                Integer.parseInt(data.get(11).getIntoFourSujith())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                Integer.parseInt(data.get(12).getRegulatorSujith())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                Integer.parseInt(data.get(13).getRegulatorRSujith())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                Integer.parseInt(data.get(42).getNewconnectCount())*Integer.parseInt(pojoPriceandProfit.getProfitNewConnection())*0.5
        );
        salaryasWorkSujith.setText("Rs. "+totalSalarySujith);
        totalSalaryAkshay=String.valueOf(
                        Integer.parseInt(totalSalaryCombine)*0.5+
                        Integer.parseInt(data.get(14).getInsAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitinspection())+
                        Integer.parseInt(data.get(15).getOneHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())+
                        Integer.parseInt(data.get(16).getOneHalftwoAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*2+
                        Integer.parseInt(data.get(17).getOneHalfthreeAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube15())*3+
                        Integer.parseInt(data.get(18).getTwoHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube25())+
                        Integer.parseInt(data.get(19).getThreeHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube35())+
                        Integer.parseInt(data.get(20).getFourHalfAkshay())*Integer.parseInt(pojoPriceandProfit.getProfittube45())+
                        Integer.parseInt(data.get(21).getOneHalfplustwoHalfAkshay())*(Integer.parseInt(pojoPriceandProfit.getProfittube15())+Integer.parseInt(pojoPriceandProfit.getProfittube25()))+
                        Integer.parseInt(data.get(22).getIntoOneAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())+
                        Integer.parseInt(data.get(23).getIntoTwoAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*2+
                        Integer.parseInt(data.get(24).getIntoThreeAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*3+
                        Integer.parseInt(data.get(25).getIntoFourAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitclamp())*4+
                        Integer.parseInt(data.get(26).getRegulatorAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(27).getRegulatorRAkshay())*Integer.parseInt(pojoPriceandProfit.getProfitregulator())+
                        Integer.parseInt(data.get(42).getNewconnectCount())*Integer.parseInt(pojoPriceandProfit.getProfitNewConnection())*0.5
        );
        salaryasWorkAkshay.setText("Rs. "+totalSalaryAkshay);

//        double combineAdv=0,sujithAdv=0,akshyAdv=0;
        String sujith="0",akshay="0",combine="0";
        if(adv.get(0).getAdvS()!=null)
        {
            sujith=adv.get(0).advS;
//
        }
        if(adv.get(1).getAdvA()!=null)
        {
            akshay=adv.get(1).advA;
//
        }
        if(adv.get(2).getAdvC()!=null)
        {
            combine=adv.get(2).advC;
        }
        advance_sujith.setText(String.valueOf(Double.parseDouble(sujith)+Double.parseDouble(combine)*0.5));
        advance_akshay.setText(String.valueOf(Double.parseDouble(akshay)+Double.parseDouble(combine)*0.5));

//       double eachSalaryS=Double.parseDouble(totalSalarySujith)-(Double.parseDouble(String.valueOf(sujith)+Double.parseDouble(combine)*0.5));
//       double eachSalaryA=Double.parseDouble(totalSalaryAkshay)-Double.parseDouble(String.valueOf(akshay));
       eachsalary_sujith.setText("Rs. "+String.valueOf(Double.parseDouble(totalSalarySujith)-(Double.parseDouble(sujith)+Double.parseDouble(combine)*0.5)));
       eachsalary_akshay.setText("Rs. "+String.valueOf(Double.parseDouble(totalSalaryAkshay)-(Double.parseDouble(akshay)+Double.parseDouble(combine)*0.5)));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Salary.this,IndexA.class));
        finish();
    }
}