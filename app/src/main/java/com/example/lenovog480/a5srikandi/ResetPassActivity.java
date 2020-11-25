package com.example.lenovog480.a5srikandi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ResetPassActivity extends AppCompatActivity {

    EditText email, pass;
    Button reset;
    public ProgressDialog pd;
    public final String URL = "your_api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        getSupportActionBar().hide();

        email = (EditText)findViewById(R.id.edtEmail);
        pass = (EditText)findViewById(R.id.edtPass);
        reset = (Button)findViewById(R.id.btnReset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd = new ProgressDialog(ResetPassActivity.this);
                pd.setMessage("loading...");
                pd.show();

                Retrofit edit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
                APIService API = edit.create(APIService.class);
                Call<User> edits = API.reset(email.getText().toString(),pass.getText().toString());
                edits.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        pd.dismiss();

                        String value = response.body().getStatus();
                        if(value.equals("success")){
                            Toast.makeText(ResetPassActivity.this, "Reset Password Success", Toast.LENGTH_SHORT).show();;
                            Intent pindah = new Intent(ResetPassActivity.this, LoginActivity.class);
                            pindah.putExtra("emailSession", email.getText().toString());
                            startActivity(pindah);
                            finish();
                        }else {
                            Toast.makeText(ResetPassActivity.this, "Email/Password Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(ResetPassActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
