package com.example.hp_mini_account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{
TextView name,password;
Button button;
private  Myapi myapi;
private String res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(TextView) findViewById(R.id.et_name);
        password=(TextView) findViewById(R.id.et_password);
        button=(Button) findViewById(R.id.et_login);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
              checkValidUser();
            }
        });
    }
    private void checkValidUser()
    {
        Intent intent = new Intent(getApplicationContext(),IndexA.class);
        startActivity(intent);
        finish();
        final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please wait...");

        if(name.getText().toString().equals(""))
        {
            name.setError("Enter Name !");
            name.requestFocus();
            return;
        }
        if(password.getText().toString().equals(""))
        {
            password.setError("Enter password !");
            password.requestFocus();
            return;
        }
            pd.show();

   Call<ResponseBody> call=MyClient.getInstance().getMyapi().addData_login(name.getText().toString().trim(),password.getText().toString().trim());
   call.enqueue(new Callback<ResponseBody>()
   {
       @Override
       public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
       {
           name.setText("");
           password.setText("");
           pd.dismiss();

           try {
                res=response.body().string();
           } catch (IOException e) {
               e.printStackTrace();
           }
           if(res.equals("yes"))
           {
                  saveUser();
                  Intent intent = new Intent(getApplicationContext(),IndexA.class);
                   startActivity(intent);
                   finish();
           }
           else if(res.equals("no"))
           {
               Toast.makeText(getApplicationContext(),"Invalid User ",Toast.LENGTH_LONG).show();
               name.setError("Invalid User");
           }
           
       }

       @Override
       public void onFailure(Call<ResponseBody> call, Throwable t)
       {
            Toast.makeText(getApplicationContext(),t.toString().toString(),Toast.LENGTH_LONG).show();
       }
   });



}

    private void saveUser()
    {
        String name1=name.getText().toString().trim();
        String password1=password.getText().toString().trim();


    }
}