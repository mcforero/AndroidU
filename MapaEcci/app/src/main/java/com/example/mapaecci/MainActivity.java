package com.example.mapaecci;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

   private FusedLocationProviderClient mFusedLocationProviderclient;
   private Button btnMap;
   DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnMap=findViewById(R.id.btnMapa);

        checkUbicacion();
    }

    private void checkUbicacion() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

        mFusedLocationProviderclient.getLastLocation()
            .addOnSuccessListener(this, new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location){
                    if (location != null) {
                        Log.e("Latitud: ",+location.getLatitude()+" Longitud: "+location.getLongitude());
                        Map<String,Object> latlang = new HashMap<>();
                        latlang.put("latitud",location.getLatitude());
                        latlang.put("longitud",location.getLongitude());
                        mDatabase.child("usuarios").push().setValue(latlang);
                    }
                }
            });
    }

    public void abrirMapa(View view){
        switch (view.getId()){
            case R.id.btnMapa : Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                                startActivity(intent);
                                break;
        }
    }
}
