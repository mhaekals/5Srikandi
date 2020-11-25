package com.example.lenovog480.a5srikandi.Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("Login")
    Call<User> login(@Field("email")String email,
                     @Field("password")String password);

    @FormUrlEncoded
    @POST("User")
    Call<User> register(@Field("email")String email,
                        @Field("password")String password);

    @FormUrlEncoded
    @POST("update_user")
    Call<User> reset(@Field("email")String email,
                        @Field("password")String password);


    @GET("Klinik")
    Call<Klinik> viewKlinik();

    @GET("Layanan")
    Call<Layanan> viewLayanan(@Query("id_klinik")String idKlinik, @Query("&tipe_layanan")String tipeLayanan);

    @FormUrlEncoded
    @POST("Order")
    Call<Order> order(@Field("email")String email,
                     @Field("id_layanan")String password);

    @GET("Order")
    Call<Order> viewOrder(@Query("email")String Email);
}
