package com.example.lenovog480.a5srikandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Adapters.LayananAdapter;
import com.example.lenovog480.a5srikandi.Model.APIService;
import com.example.lenovog480.a5srikandi.Model.Layanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeTreatmentActivity extends AppCompatActivity {

    public final String URL = "your_api";
    private List<Layanan> layananList = new ArrayList<>();
    RecyclerView.LayoutManager mlayoutManager;
    private LayananAdapter viewAdapter;
    RecyclerView recyclerView;
    TextView idSession, idSessionL;
    public String emailSession, session, sessionLayanan;
    //public Integer idK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_treatment);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        idSession = (TextView)findViewById(R.id.idSession);
        idSessionL = (TextView)findViewById(R.id.idSessionL);

        Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession");
        session = getMail.getStringExtra("idSession");
        sessionLayanan = getMail.getStringExtra("idSessionLayanan");

        idSession.setText(session);
        idSessionL.setText(sessionLayanan);


      //  idK = Integer.parseInt(idSession.getText().toString());


        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewLayanan); // untuk deklarasiin recycler view
        viewAdapter = new LayananAdapter(HomeTreatmentActivity.this, layananList);
        mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //untuk membuat garis di recyclerview
        recyclerView.setLayoutManager(mlayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build(); //penjelasan sama dengan komen di LoginActivuty
        APIService API = retrofit.create(APIService.class);
        Call<Layanan> call = API.viewLayanan(idSession.getText().toString(), idSessionL.getText().toString());
        call.enqueue(new Callback<Layanan>() {
            @Override
            public void onResponse(Call<Layanan> call, Response<Layanan> response) {
                layananList = response.body().getResult(); //untuk ngeget result sesuai di webservice
                viewAdapter = new LayananAdapter(HomeTreatmentActivity.this, layananList);
                recyclerView.setAdapter(viewAdapter);
                viewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Layanan> call, Throwable t) {
                Toast.makeText(HomeTreatmentActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();

    }
}
