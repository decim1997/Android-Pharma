package com.example.thony.pharma;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrugFragment extends Fragment {

    private List<Medicament> medicaments;
    private List<Medicament> filtermedicament;
    private RecyclerView medrcv;
    private MedicamentAdapter medicamentAdapter;
    private Toolbar toolbar;
    private MaterialSearchView materialSearchView;
    private Activity mactivity;
    private  String url = "";
    private  boolean search = false;


    public DrugFragment() {
        // Required empty public constructor
    }

public void fetchPharmacy()
{
     medicaments = new ArrayList<>();
    url = "http://192.168.43.69:3000/pharma/listmed";

    JsonArrayRequest jsreq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

            if(response!= null)
            {
                System.out.println("RespJSP"+response.length());

                try {
                    for (int i = 0; i < response.length(); i++) {
                        medicaments.add(new Medicament(response.getJSONObject(i).getInt("id_medicament"), response.getJSONObject(i).getString("nom_medicament"),
                                response.getJSONObject(i).getString("image_medicament"), response.getJSONObject(i).getDouble("prix"), response.getJSONObject(i).getInt("quantite"),
                                response.getJSONObject(i).getInt("ordonnance"), response.getJSONObject(i).getInt("id_type"), response.getJSONObject(i).getInt("id_categorie"),
                                response.getJSONObject(i).getInt("id_forme"), response.getJSONObject(i).getString("nom_categorie"), response.getJSONObject(i).getString("type"),
                                response.getJSONObject(i).getString("descriptiom"), response.getJSONObject(i).getString("nom_forme")));

                    }
                    medicamentAdapter = new MedicamentAdapter(getContext(),medicaments);
                    medrcv.setLayoutManager(new GridLayoutManager(getContext(),2));
                    medrcv.setAdapter(medicamentAdapter);
                    System.out.println("gotit:"+medicaments);
                }
                catch (JSONException e)
                {
                    System.out.println("msg: "+e.getMessage());
                }

            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("ErreurVolley:"+error);
        }
    });

    NetworkServer.getMySingleton(getContext()).addToRequestQue(jsreq);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.fragment_drug, container, false);
        medrcv = view.findViewById(R.id.medrecycleview);
        mactivity = (PharmacyActivity) getActivity();

        ((PharmacyActivity) mactivity).getSearchView().setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                System.out.println("Lookingggggggg");
            }

            @Override
            public void onSearchViewClosed() {
                medicamentAdapter = new MedicamentAdapter(getContext(),medicaments);
                medrcv.setLayoutManager(new GridLayoutManager(getContext(),2));
                medrcv.setAdapter(medicamentAdapter);
                medicamentAdapter.notifyDataSetChanged();
            }
        });

        ((PharmacyActivity) mactivity).getSearchView().setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {

                System.out.println("Closedfornochange");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if(newText != null && !newText.isEmpty())
                {
                    filtermedicament = new ArrayList<>();
                    String nommed = "";
                    for(int i = 0 ; i<medicaments.size();i++)
                    {
                        nommed = medicaments.get(i).getNom_medicament();

                        if(nommed.toLowerCase().contains(newText.toLowerCase()))
                        {
                            // System.out.println("nomed"+nommed);
                            //   System.out.println("cONTAINTNEWTEXT:"+newText);
                            filtermedicament.add(medicaments.get(i));
                        }
                    }

                    medicamentAdapter = new MedicamentAdapter(getContext(),filtermedicament);
                    medrcv.setLayoutManager(new GridLayoutManager(getContext(),2));
                    medrcv.setAdapter(medicamentAdapter);
                    medicamentAdapter.notifyDataSetChanged();

                    // System.out.println("ContentMed"+filtermedicament);

                }
                return true;
            }
        });
        fetchPharmacy();
        return  view;
    }


    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

        getActivity().getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.item_search_bar);
        materialSearchView.setMenuItem(item);
       super.onCreateOptionsMenu(menu, inflater);
    }*/


}
