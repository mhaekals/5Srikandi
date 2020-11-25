package com.example.lenovog480.a5srikandi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class KategoriActivity extends AppCompatActivity {

    public String emailSession, session;
    public String satu = "1";
    public String dua = "2";
    public String tiga = "3";
    TextView w, b, wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        w = (TextView)findViewById(R.id.txtWajah);
        b = (TextView)findViewById(R.id.txtBadan);
        wb = (TextView)findViewById(R.id.txtWB);

        Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession");
        session = getMail.getStringExtra("idSession");

        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KategoriActivity.this, "Wajah", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(KategoriActivity.this, HomeTreatmentActivity.class);
                intent.putExtra("emailSession",emailSession);
                intent.putExtra("idSession", session);
                intent.putExtra("idSessionLayanan", satu);
                startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KategoriActivity.this, "Badan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(KategoriActivity.this, HomeTreatmentActivity.class);
                intent.putExtra("emailSession",emailSession);
                intent.putExtra("idSession", session);
                intent.putExtra("idSessionLayanan", dua);
                startActivity(intent);
            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(KategoriActivity.this, "Wajah & Badan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(KategoriActivity.this, HomeTreatmentActivity.class);
                intent.putExtra("emailSession",emailSession);
                intent.putExtra("idSession", session);
                intent.putExtra("idSessionLayanan", tiga);
                startActivity(intent);
            }
        });


    }
}
