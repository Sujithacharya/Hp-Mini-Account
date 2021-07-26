package com.example.hp_mini_account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexA extends AppCompatActivity {

TabLayout tabLayout;
ViewPager2 viewPager;
FragmentAdapter fragmentAdapter;
    private String  res;
    int ftdate,ftmonth,ftyear;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        Toast.makeText(getApplicationContext(),"Oncreate",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_index2);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

       FragmentManager fm=getSupportFragmentManager();

       fragmentAdapter=new FragmentAdapter(fm,getLifecycle());

       viewPager.setAdapter(fragmentAdapter);

       tabLayout.addTab(tabLayout.newTab().setText("Activity"));
       tabLayout.addTab(tabLayout.newTab().setText("Update"));
       tabLayout.addTab(tabLayout.newTab().setText("Settings"));

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab)
           {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

       viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               tabLayout.selectTab(tabLayout.getTabAt(position));
           }
       });
    }
    private void updateFilterNewDate()
    {
        Calendar calendar = Calendar.getInstance();
        ftyear = calendar.get(Calendar.YEAR);
        ftmonth = calendar.get(Calendar.MONTH)+1;
        ftdate = calendar.get(Calendar.DATE);



        String dateString=String.format("%d-%02d-%02d",ftyear,ftmonth,ftdate);
        Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_FilterDate(dateString);
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
//                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        updateFilterNewDate();
//        try {
//            Thread.sleep(5000);
//           } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Toast.makeText(getApplicationContext(),"App Closed",Toast.LENGTH_SHORT).show();
        Log.d("onDestroy","OnDestroy");

    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
//        Toast.makeText(getApplicationContext(),"onPostResume",Toast.LENGTH_SHORT).show();
        Log.d("onResume","OnResume");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
//        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_SHORT).show();
        Log.d("onStart","OnStart");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
//        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_SHORT).show();
        Log.d("onStop","Onstop");

    }

    @Override
    protected void onPause()
    {
        super.onPause();
//        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
        Log.d("onPause","OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_SHORT).show();
        Log.d("onResume","OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(getApplicationContext(),"onRestart",Toast.LENGTH_SHORT).show();
        Log.d("onRestart","OnRestart");
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        return;

    }
}