package com.example.hp_mini_account;

import android.app.usage.StorageStatsManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Index extends AppCompatActivity {
    TextView clmapCount,inspectionCount,regulatorCount,oneHalf,twoHalf,threeHalf,fourHalf;
    List<ItemSold> arrayList;
//    int res[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        oneHalf=(TextView)findViewById(R.id.et_hoseCount_1_5);
        twoHalf=(TextView)findViewById(R.id.et_hoseCount_2_5);
        threeHalf=(TextView)findViewById(R.id.et_hoseCount_3_5);
        fourHalf=(TextView)findViewById(R.id.et_hoseCount_4_5);
        clmapCount=(TextView)findViewById(R.id.et_clampCount);
        inspectionCount=(TextView)findViewById(R.id.et_inspectionCount);
        regulatorCount=findViewById(R.id.et_regulatorCount);
        processData();
    }

    private void processData()
    {
        Call<List<ItemSold>> call=MyClient.getInstance().getMyapi().getItemCount();
        call.enqueue(new Callback<List<ItemSold>>() {
            @Override
            public void onResponse(Call<List<ItemSold>> call, Response<List<ItemSold>> response)
            {
                List<ItemSold> data=response.body();
                soldItemExactCount(data);
            }
            @Override
            public void onFailure(Call<List<ItemSold>> call,Throwable t)
            {
                Toast.makeText(getApplicationContext(),"No Internet.",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void soldItemExactCount(List<ItemSold> list)
    {
        String inspection ="0";
        String hose1="0";
        String hose2="0";
        String hose3="0";
        String hose4="0";
        String clamp="0";
        String regulator=null;
        inspection=list.get(0).getTotalInspection();
        hose1=String.valueOf(Integer.parseInt(list.get(1).getTotalHoseOneHalf())+ Integer.parseInt(list.get(7).getTotalHoseTwohalfPlusOneHalf())+Integer.parseInt(list.get(2).getTotalHoseHalfTwo())*2+Integer.parseInt(list.get(3).getTotalHoseOneHalfThree())*3);
        hose2=String.valueOf(Integer.parseInt(list.get(4).getTotalHoseTwoHalf())+ Integer.parseInt(list.get(7).getTotalHoseTwohalfPlusOneHalf()));
        hose3=String.valueOf(Integer.parseInt(list.get(5).getTotalHoseThreeHalf()));hose4=String.valueOf(Integer.parseInt(list.get(6).getTotalHoseFourHalf()));
        clamp=String.valueOf(Integer.parseInt(list.get(8).getTotalClampOne())+Integer.parseInt(list.get(9).getTotalClampTwo())*2+Integer.parseInt(list.get(10).getTotalClampThree())*3+Integer.parseInt(list.get(11).getTotalClampFour())*4);
        regulator=String.valueOf(Integer.parseInt(list.get(12).getTotalRegulator())+Integer.parseInt(list.get(13).getTotalRegulatorReturned()));

        inspectionCount.setText(String.valueOf(inspection));
        oneHalf.setText(String.valueOf(hose1));
        twoHalf.setText(String.valueOf(hose2));
        threeHalf.setText(String.valueOf(hose3));
        fourHalf.setText(String.valueOf(hose4));
        clmapCount.setText(String.valueOf(clamp));
        regulatorCount.setText(String.valueOf(regulator));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Index.this,IndexA.class));
        finish();
    }
}