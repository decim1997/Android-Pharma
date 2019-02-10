package com.example.thony.pharma;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {


   // private MapView mapView;

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
     //   Mapbox.getInstance(getContext(), "pk.eyJ1IjoiZGVjaW0iLCJhIjoiY2pwbXQzZ3BqMDByODQyb2U2dDllcnFkMSJ9.oyHqnguPqm6INt8nFA3ugg");
        View  view= inflater.inflate(R.layout.fragment_location, container, false);
       // mapView = view.findViewById(R.id.mapView);
        //mapView.onCreate(savedInstanceState);
        return  view;
    }

  /*  @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }*/

}
