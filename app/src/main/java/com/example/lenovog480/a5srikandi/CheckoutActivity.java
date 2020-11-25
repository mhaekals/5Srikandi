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
import com.example.lenovog480.a5srikandi.Model.Order;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutActivity extends AppCompatActivity {

    Button finish;
    TextView layanan, kategori, harga, email, id_layanan;
    EditText namaUser, alamatUser, kontakUser;
    public ProgressDialog pd;
    public final String URL = "your_api";
    public String emailSession, idLayanan, layananSession, kategoriSession, hargaSession;
//    public String namaS, alamatS, kontakS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        layanan = (TextView)findViewById(R.id.txtLayanan);
        kategori = (TextView)findViewById(R.id.txtKategori);
        harga = (TextView)findViewById(R.id.txtHarga);
        finish = (Button)findViewById(R.id.btnSelesai);
        email = (TextView)findViewById(R.id.txtEmail);
        id_layanan = (TextView)findViewById(R.id.txtIdLayanan);

//        namaUser = (EditText)findViewById(R.id.edtNama);
//        alamatUser = (EditText)findViewById(R.id.edtAlamat);
//        kontakUser = (EditText)findViewById(R.id.edtKontak);

//        namaUser.getText().toString();
//        alamatUser.getText().toString();
//        kontakUser.getText().toString();


        final Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession");
        idLayanan = getMail.getStringExtra("id");
        layananSession = getMail.getStringExtra("nama");
        kategoriSession = getMail.getStringExtra("tipe");
        hargaSession = getMail.getStringExtra("harga");

        layanan.setText(layananSession);
        kategori.setText(kategoriSession);
        harga.setText(hargaSession);
        email.setText(emailSession);
        id_layanan.setText(idLayanan);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(CheckoutActivity.this);
                pd.setMessage("loading...");
                pd.show();

                Retrofit order = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
                APIService API = order.create(APIService.class);
                Call<Order> call = API.order(email.getText().toString(), id_layanan.getText().toString());
                call.enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        pd.dismiss();
                        String status = response.body().getStatus();
                        String value = response.body().getValue();

                        if (status.equals("success") && value.equals("1")){
                            Toast.makeText(CheckoutActivity.this,"Order Success", Toast.LENGTH_SHORT).show();
                            Intent pindah = new Intent(CheckoutActivity.this, PesananActivity.class);
                            pindah.putExtra("emailSession", emailSession);
//                            pindah.putExtra("namaS", namaUser.getText().toString());
//                            pindah.putExtra("alamatS", alamatUser.getText().toString());
//                            pindah.putExtra("kontakS", kontakUser.getText().toString());
                            startActivity(pindah);
                            finish();
                        }else{
                            Toast.makeText(CheckoutActivity.this, "Order Fail", Toast.LENGTH_SHORT).show();



                        }
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(CheckoutActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
