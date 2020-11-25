package com.example.lenovog480.a5srikandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Adapters.PesananAdapter;
import com.example.lenovog480.a5srikandi.Model.APIService;
import com.example.lenovog480.a5srikandi.Model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PesananActivity extends AppCompatActivity {

    public final String URL = "your_api";
    private List<Order> orderList = new ArrayList<>();
    RecyclerView.LayoutManager mlayoutManager;
    private PesananAdapter viewAdapter;
    RecyclerView recyclerView;
    public String emailSession;
    TextView emailS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.toolbar_icon);

        Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession");

        emailS = (TextView)findViewById(R.id.txtemailS);
        emailS.setText(emailSession);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewPesanan);
        viewAdapter = new PesananAdapter(PesananActivity.this, orderList);
        mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

//        if(orderList.isEmpty()){
//            Toast.makeText(PesananActivity.this,"No data", Toast.LENGTH_SHORT).show();
//        }else {
//            viewAdapter = new PesananAdapter(PesananActivity.this, orderList);
//            recyclerView.setAdapter(viewAdapter);
//        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService API = retrofit.create(APIService.class);
        Call<Order> call = API.viewOrder(emailS.getText().toString());
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

//                if(orderList.isEmpty()){
//                    Toast.makeText(PesananActivity.this,"Belum Membuat Pesanan", Toast.LENGTH_SHORT).show();
//
//                }else {
                    orderList = response.body().getResult();
                    viewAdapter = new PesananAdapter(PesananActivity.this, orderList);
                    recyclerView.setAdapter(viewAdapter);
                    viewAdapter.notifyDataSetChanged();

                //}

//                orderList = response.body().getResult();
//                viewAdapter = new PesananAdapter(PesananActivity.this, orderList);
//                recyclerView.setAdapter(viewAdapter);
//                viewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(PesananActivity.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();

    }
}
