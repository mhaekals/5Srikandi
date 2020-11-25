package com.example.lenovog480.a5srikandi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovog480.a5srikandi.Model.Klinik;
import com.example.lenovog480.a5srikandi.R;

import java.util.List;

public class KlinikAdapter extends RecyclerView.Adapter<KlinikAdapter.MyViewHolder> {

    private Context mContext;
    private List<Klinik> klinikList;

    public KlinikAdapter(Context mContext, List<Klinik> klinikList) {
        this.mContext = mContext;
        this.klinikList = klinikList;
    }

    @Override
    public KlinikAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_klinik, parent, false);
        KlinikAdapter.MyViewHolder holder = new KlinikAdapter.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final KlinikAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.nama.setText(klinikList.get(i).getKlinik());
        viewHolder.alamat.setText(klinikList.get(i).getAlamat());
        viewHolder.kontak.setText(klinikList.get(i).getKontak());
        //Picasso.with(mContext).load(klinikList.get(i).getImage()).into(viewHolder.images);
        Glide.with(mContext).load(klinikList.get(i).getImage()).into(viewHolder.images);
    }

    @Override
    public int getItemCount() {
        return klinikList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, nama, alamat, kontak;
        public ImageView images;

        public MyViewHolder(View view) {
            super(view);
           // id = (TextView)view.findViewById(R.id.txtId);
            nama = (TextView) view.findViewById(R.id.txtNama);
            alamat = (TextView) view.findViewById(R.id.txtAlamat);
            kontak = (TextView)view.findViewById(R.id.txtKontak);
            images = (ImageView)view.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Klinik clickedDataItem = klinikList.get(pos);
//                        Intent intent = new Intent(mContext, DetailDokumen.class);
//                        intent.putExtra("id_dokumen", dokumenList.get(pos).getID());
//                        intent.putExtra("nama_dokumen", dokumenList.get(pos).getNama_KLP());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "you clicked " + clickedDataItem.getKlinik(), Toast.LENGTH_LONG).show();

                    }
                }
            });

        }
    }
}

