package com.healingjeonnam.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.healingjeonnam.R;
import com.healingjeonnam.base.ForestApp;

import com.healingjeonnam.models.Mountain;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MountainMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    List<Mountain> mountains = ForestApp.mountain;
    Double[] latitude = new Double[mountains.size()];
    Double[] longitude = new Double[mountains.size()];
    String[] addrs = new String[mountains.size()];
    String[] name = new String[mountains.size()];
    com.google.android.gms.maps.MapFragment googleMap;
    static int i;
    Mountain mountain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_map);

        googleMap = ((com.google.android.gms.maps.MapFragment) getFragmentManager().
                findFragmentById(R.id.map));
        googleMap.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng place = new LatLng(34.8224857,126.8829388);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 8));

        for (i = 0; i < mountains.size(); i++) {

            latitude[i] = mountains.get(i).getGpsLatitude();
            longitude[i] = mountains.get(i).getGpsLongitude();
            addrs[i] = mountains.get(i).getAddr();
            name[i] = mountains.get(i).getName();
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
                Intent intent = new Intent(MountainMapActivity.this, MountainDetailActivity.class);
                for(int j = 0 ; j < mountains.size(); j++){
                    String name = mountains.get(j).getName();
                    if(name.equals(marker.getTitle())){
                        mountain = mountains.get(j);
                    }
                }
                intent.putExtra("forest", mountain);
                startActivity(intent);

            }
        });

    }

}
