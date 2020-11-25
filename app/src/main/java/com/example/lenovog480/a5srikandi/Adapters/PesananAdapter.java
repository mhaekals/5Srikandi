package com.example.lenovog480.a5srikandi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog480.a5srikandi.Model.Order;
import com.example.lenovog480.a5srikandi.PesananActivity;
import com.example.lenovog480.a5srikandi.R;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.MyViewHolder>{

    private Context mContext;
    private List<Order> orderList;
    public String emailSession, namaS, alamatS, kontakS;

    public PesananAdapter(Context mContext, List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @Override
    public PesananAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pesanan, parent, false);
        PesananAdapter.MyViewHolder holder = new PesananAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final PesananAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.email.setText(orderList.get(i).getEmail());
        viewHolder.nama.setText(orderList.get(i).getNama_layanan());
        viewHolder.tipe.setText(orderList.get(i).getTipe_layanan());
        viewHolder.harga.setText(orderList.get(i).getHarga());

        viewHolder.id_pesanan.setText(orderList.get(i).getId_pesanan());
        viewHolder.id_klinik.setText(orderList.get(i).getId_klinik());
        viewHolder.id_layanan.setText(orderList.get(i).getId_layanan());
        viewHolder.nama_k.setText(orderList.get(i).getNama_klinik());
        viewHolder.alamat_k.setText(orderList.get(i).getAlamat());
        viewHolder.kontak.setText(orderList.get(i).getKonntak());

        Intent ems = ((PesananActivity)mContext).getIntent();
        namaS = ems.getStringExtra("namaS");
        alamatS = ems.getStringExtra("alamatS");
        kontakS = ems.getStringExtra("kontakS");

        viewHolder.namaU.setText(namaS);
        viewHolder.alamatU.setText(alamatS);
        viewHolder.kontakU.setText(kontakS);

    }

    @Override
    public int getItemCount() {
        try{
            return orderList.size();
        }catch(NullPointerException e){
            Toast.makeText(mContext,"Belum Membuat Pesanan", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id_pesanan, id_klinik, id_layanan, nama_k, alamat_k, kontak, email, nama, tipe, harga;
        public TextView namaU, alamatU, kontakU;

        public MyViewHolder(View view) {
            super(view);
            email = (TextView)view.findViewById(R.id.txtEmail);
            nama = (TextView)view.findViewById(R.id.txtNamaLayanan);
            tipe = (TextView)view.findViewById(R.id.txtKategori);
            harga = (TextView)view.findViewById(R.id.txtHarga);

            id_pesanan = (TextView)view.findViewById(R.id.idPesanan);
            id_klinik = (TextView)view.findViewById(R.id.idKlinik);
            id_layanan = (TextView)view.findViewById(R.id.idLayanan);
            nama_k = (TextView)view.findViewById(R.id.NamaK);
            alamat_k = (TextView)view.findViewById(R.id.AlamatK);
            kontak = (TextView)view.findViewById(R.id.KontakK);

            namaU = (TextView)view.findViewById(R.id.txtNamaUser);
            alamatU = (TextView)view.findViewById(R.id.txtAlamatUser);
            kontakU = (TextView)view.findViewById(R.id.txtKontakUser);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent ems = ((PesananActivity)mContext).getIntent();
                    emailSession = ems.getStringExtra("emailSession");

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Order clickedDataItem = orderList.get(pos);
//                        Intent intent = new Intent(mContext, CheckoutActivity.class);
//                        intent.putExtra("id", laynanList.get(pos).getId_layanan());
//                        intent.putExtra("nama", laynanList.get(pos).getNama_layanan());
//                        intent.putExtra("tipe", laynanList.get(pos).getTipe_layanan());
//                        intent.putExtra("klinik", laynanList.get(pos).getId_klinik());
//                        intent.putExtra("harga", laynanList.get(pos).getHarga());
//                        intent.putExtra("emailSession",emailSession);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "you clicked " + clickedDataItem.getNama_layanan(), Toast.LENGTH_LONG).show();

                    }
                }
            });

        }
    }
}
