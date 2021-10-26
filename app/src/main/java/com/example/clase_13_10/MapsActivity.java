package com.example.clase_13_10;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.clase_13_10.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(41.8898814,12.4916866);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Coliseo"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                double Latitud=latLng.latitude;
                double Longitud= latLng.longitude;
                //Toast.makeText(getApplicationContext(),"UD selecciono"+Latitud+","+Longitud,Toast.LENGTH_LONG).show();
                //Log.v("MIO","ud selecciono:"+Latitud+","+Longitud);
                mMap.addMarker(new MarkerOptions().position(latLng).title("MARCADOR TMP"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Double Latitud=marker.getPosition().latitude;
                Double Longitud=marker.getPosition().longitude;
                String Titulo= marker.getTitle();
                Toast.makeText(getApplicationContext(), "ud selecciono"+Titulo, Toast.LENGTH_SHORT).show();
                Log.v("MIO","UD SELECCIONO"+Latitud+","+Longitud);

                return false;
            }
        });

    }
}