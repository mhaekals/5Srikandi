package com.example.lenovog480.a5srikandi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Klinik {

    @SerializedName("id") String id; // yg di dalam "" harus sama dengan di webservice, kalo tidak menggunakan @SerializedName nama String nya harus sama dengan di webservice
    @SerializedName("nama_klinik") String klinik;
    @SerializedName("alamat") String alamat;
    @SerializedName("kontak") String kontak;
    @SerializedName("image") String image;
    @SerializedName("result") List<Klinik> result;  //untuk nampung result yg di webservice

    public String getId() {
        return id;
    }

    public String getKlinik() {
        return klinik;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKontak() {
        return kontak;
    }

    public String getImage() {
        return image;
    }

    public List<Klinik> getResult() {
        return result;
    }
}
