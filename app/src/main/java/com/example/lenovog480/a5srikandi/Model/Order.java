package com.example.lenovog480.a5srikandi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("id_pesanan") String id_pesanan;
    @SerializedName("id_klinik") String id_klinik;
    @SerializedName("id_layanan") String id_layanan;
    @SerializedName("email") String email;
    @SerializedName("nama_layanan") String nama_layanan;
    @SerializedName("tipe_layanan") String tipe_layanan;
    @SerializedName("nama_klinik") String nama_klinik;
    @SerializedName("alamat") String alamat;
    @SerializedName("kontak") String konntak;
    @SerializedName("harga") String harga;
    @SerializedName("result") List<Order> result;
    @SerializedName("status") String status;
    @SerializedName("value") String value;

    public String getId_pesanan() {
        return id_pesanan;
    }

    public String getId_klinik() {
        return id_klinik;
    }

    public String getId_layanan() {
        return id_layanan;
    }

    public String getEmail() {
        return email;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public String getTipe_layanan() {
        return tipe_layanan;
    }

    public String getNama_klinik() {
        return nama_klinik;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKonntak() {
        return konntak;
    }

    public String getHarga() {
        return harga;
    }

    public List<Order> getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }
}
