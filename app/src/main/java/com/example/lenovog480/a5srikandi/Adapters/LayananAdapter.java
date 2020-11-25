package com.example.lenovog480.a5srikandi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.CheckoutActivity;
import com.example.lenovog480.a5srikandi.HomeTreatmentActivity;
import com.example.lenovog480.a5srikandi.Model.Layanan;
import com.example.lenovog480.a5srikandi.R;

import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.MyViewHolder> {

    private Context mContext;
    private List<Layanan> laynanList;
    public String emailSession;

    public LayananAdapter(Context mContext, List<Layanan> layananList) {
        this.mContext = mContext;
        this.laynanList = layananList;
    }

    @Override
    public LayananAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layanan, parent, false);
        LayananAdapter.MyViewHolder holder = new LayananAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final LayananAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.id.setText(laynanList.get(i).getId_layanan());
        viewHolder.nama.setText(laynanList.get(i).getNama_layanan());
        viewHolder.tipe.setText(laynanList.get(i).getTipe_layanan());
        viewHolder.klinik.setText(laynanList.get(i).getId_klinik());
        viewHolder.harga.setText(laynanList.get(i).getHarga());

    }

    @Override
    public int getItemCount() {
        return laynanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, nama, tipe, klinik, harga;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView)view.findViewById(R.id.txtIdLayanan);
            nama = (TextView)view.findViewById(R.id.txtLayanan);
            tipe = (TextView)view.findViewById(R.id.txtKategori);
            klinik = (TextView)view.findViewById(R.id.txtIdKlinik);
            harga = (TextView)view.findViewById(R.id.txtHarga);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent ems = ((HomeTreatmentActivity)mContext).getIntent();
                    emailSession = ems.getStringExtra("emailSession");

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Layanan clickedDataItem = laynanList.get(pos);
                        Intent intent = new Intent(mContext, CheckoutActivity.class);
                        intent.putExtra("id", laynanList.get(pos).getId_layanan());
                        intent.putExtra("nama", laynanList.get(pos).getNama_layanan());
                        intent.putExtra("tipe", laynanList.get(pos).getTipe_layanan());
                        intent.putExtra("klinik", laynanList.get(pos).getId_klinik());
                        intent.putExtra("harga", laynanList.get(pos).getHarga());
                        intent.putExtra("emailSession",emailSession);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "you clicked " + clickedDataItem.getNama_layanan(), Toast.LENGTH_LONG).show();

                    }
                }
            });

        }
    }
}
