package com.example.lenovog480.a5srikandi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Model.APIService;
import com.example.lenovog480.a5srikandi.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText email, password, confirm;
    public ProgressDialog pd;
    public final String URL = "your_api";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        email = (EditText)findViewById(R.id.edtEmailRegist);
        password = (EditText)findViewById(R.id.edtPassRegist);
        //confirm = (EditText)findViewById(R.id.edtConfrimPass);
        register = (Button)findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("loading...");
                pd.show();

                Retrofit register = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build(); //penjelasan sama seperti di komen LoginActivity
                APIService API = register.create(APIService.class);
                Call<User> call = API.register(email.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                       pd.dismiss();

//                        confirm.getText().toString();
                       // String passs = password.getText().toString();
                        String value = response.body().getStatus();

                        if (value.contains("success")){
                            //List<User> nilai = response.body().getResult();
                             //&& confirm.equals(password)
                            Toast.makeText(RegisterActivity.this,"Register Success", Toast.LENGTH_SHORT).show();
                            Intent pindah = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(pindah);

                        }else{
                            //value.equals("failed");
                            Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                            email.getText().clear();
                            password.getText().clear();
                            confirm.getText().clear();

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
