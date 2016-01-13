package com.healingjeonnam.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.healingjeonnam.R;
import com.healingjeonnam.base.ForestApp;
import com.healingjeonnam.models.Forest;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    List<Forest> forest1 = ForestApp.forest;
    Double[] latitude = new Double[forest1.size()];
    Double[] longitude = new Double[forest1.size()];
    String[] addrs = new String[forest1.size()];
    String[] name = new String[forest1.size()];
    MapFragment googleMap;
    static int i;
    Forest forest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.healingjeonnam.R.layout.activity_map);

        googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager().
                findFragmentById(R.id.map));
        googleMap.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng place = new LatLng(34.8224857,126.8829388);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 8));

        for (i = 0; i < forest1.size(); i++) {

            latitude[i] = forest1.get(i).getGpsLatitude();
            longitude[i] = forest1.get(i).getGpsLongitude();
            addrs[i] = forest1.get(i).getAddr();
            name[i] = forest1.get(i).getName();
            map.addMarker(new MarkerOptions().position(
                            new LatLng(longitude[i], latitude[i]))
                            .title(name[i])
                            .snippet(addrs[i])
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mountains))

            );
        }
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapActivity.this, ForestDetailActivity.class);
                for(int j = 0 ; j < forest1.size(); j++){
                    String name = forest1.get(j).getName();
                    if(name.equals(marker.getTitle())){
                        forest = forest1.get(j);
                    }
                }
                intent.putExtra("forest", forest);
                startActivity(intent);

            }
        });

    }

}
