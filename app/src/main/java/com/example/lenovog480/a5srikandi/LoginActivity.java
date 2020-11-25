package com.example.lenovog480.a5srikandi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Model.APIService;
import com.example.lenovog480.a5srikandi.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button login, register, google;
    EditText email, password;
    TextView lupa;
    public ProgressDialog pd;
    public final String URL = "your_api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPass);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegist);
        lupa = (TextView)findViewById(R.id.txtLupa);
        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(LoginActivity.this, ResetPassActivity.class);
                startActivity(pindah);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("loading...");
                pd.show();

                Retrofit Login = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build(); //deklerasi retrofit ngambil url sesuai webservice
                APIService API = Login.create(APIService.class);
                Call<User> login = API.login(email.getText().toString(),password.getText().toString()); //sesuaikan dengan interface class (APIService) & sesuikan model class mana yg diambil contoh disitu USER
                login.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        pd.dismiss();

                        String value = response.body().getStatus(); // ngambil dari model class
                        if(value.equals("success")){ // sesuikan aturan api, kebetulan di api aturannya harus ngambil success baru dapat di jalankan
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();;
                            Intent pindah = new Intent(LoginActivity.this, HomeActivity.class);
                            pindah.putExtra("emailSession", email.getText().toString());
                            startActivity(pindah);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Email/Password Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(LoginActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(pindah);
            }
        });
    }
}
