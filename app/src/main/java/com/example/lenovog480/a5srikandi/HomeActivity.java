package com.example.lenovog480.a5srikandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    ImageView img1, img2;
    public String emailSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);



        Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession"); // ngambil putextra string dari halaman sebelumnya, kebetulan yang diambil adalah email
                                                                        //inget key harus sama ato nggak gak bakal kebaca key yg di dlm ""
        //Toast.makeText(HomeActivity.this, emailSession, Toast.LENGTH_LONG).show();



        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah1 = new Intent(HomeActivity.this, DaftarKlinikActivity.class);
                startActivity(pindah1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(HomeActivity.this, MapsActivity.class);
                pindah.putExtra("emailSession",emailSession);
                startActivity(pindah);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            Intent pindah = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(pindah);
            finish();
            return true;
        }

        if (id == R.id.pesanan) {
            Intent pindah = new Intent(HomeActivity.this, PesananActivity.class);
            pindah.putExtra("emailSession",emailSession);
            startActivity(pindah);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
