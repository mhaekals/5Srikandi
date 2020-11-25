package com.example.lenovog480.a5srikandi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public String emailSession;
    public String satu = "1";
    public String dua = "2";
    public String tiga = "3";
    private GoogleMap mMap;
    Marker klinik1;
    Marker klinik2;
    Marker klinik3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent getMail = getIntent();
        emailSession = getMail.getStringExtra("emailSession");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-6.914744, 107.609810); //angka yg di situ merupkan lokasi
        klinik1 = mMap.addMarker(new MarkerOptions().position(sydney).title("Klinik1"));
        klinik2 = mMap.addMarker(new MarkerOptions().position(new LatLng(-6.121435, 106.774124)).title("Klinik2"));
        klinik3 = mMap.addMarker(new MarkerOptions().position(new LatLng( -6.595038, 106.816635)).title("Klinik3"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(klinik1)){
                    Toast.makeText(MapsActivity.this, "Klinik 1", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapsActivity.this, KategoriActivity.class);
                    intent.putExtra("emailSession",emailSession);
                    intent.putExtra("idSession", satu);
                    startActivity(intent);
                }
                if (marker.equals(klinik2)){
                    Toast.makeText(MapsActivity.this, "Klinik 2", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapsActivity.this, KategoriActivity.class);
                    intent.putExtra("emailSession",emailSession);
                    intent.putExtra("idSession", dua);
                    startActivity(intent);
                }
                if (marker.equals(klinik3)){
                    Toast.makeText(MapsActivity.this, "Klinik 3", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapsActivity.this, KategoriActivity.class);
                    intent.putExtra("emailSession",emailSession);
                    intent.putExtra("idSession", tiga);
                    startActivity(intent);
                }
                return false;
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
