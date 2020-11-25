package com.example.lenovog480.a5srikandi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Adapters.KlinikAdapter;
import com.example.lenovog480.a5srikandi.Model.APIService;
import com.example.lenovog480.a5srikandi.Model.Klinik;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarKlinikActivity extends AppCompatActivity {

    public final String URL = "your_api";
    private List<Klinik> klinikList = new ArrayList<>();
    RecyclerView.LayoutManager mlayoutManager;
    private KlinikAdapter viewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_klinik);

       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Daftar Klinik");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewKlinik);
        viewAdapter = new KlinikAdapter(DaftarKlinikActivity.this, klinikList);
        mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService API = retrofit.create(APIService.class);
        Call<Klinik> call = API.viewKlinik();
        call.enqueue(new Callback<Klinik>() {
            @Override
            public void onResponse(Call<Klinik> call, Response<Klinik> response) {
                    klinikList = response.body().getResult();
                    viewAdapter = new KlinikAdapter(DaftarKlinikActivity.this, klinikList);
                    recyclerView.setAdapter(viewAdapter);
                    viewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Klinik> call, Throwable t) {
                Toast.makeText(DaftarKlinikActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

        @Override
        protected void onResume(){
            super.onResume();

        }
}
