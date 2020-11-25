package com.example.lenovog480.a5srikandi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Layanan {

    @SerializedName("id") String id_layanan;
    @SerializedName("nama_layanan") String nama_layanan;
    @SerializedName("tipe_layanan") String tipe_layanan;
    @SerializedName("id_klinik") String id_klinik;
    @SerializedName("harga") String harga;
    @SerializedName("result") List<Layanan> result;

    public String getId_layanan() {
        return id_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public String getTipe_layanan() {
        return tipe_layanan;
    }

    public String getId_klinik() {
        return id_klinik;
    }

    public String getHarga() {
        return harga;
    }

    public List<Layanan> getResult() {
        return result;
    }
}
