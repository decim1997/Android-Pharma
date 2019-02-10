package com.example.thony.pharma;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
public class AllPharmacyFragment extends Fragment {

    private List<Pharmacy> allpharmacy;
    private String url;
    private RecyclerView pharma;
    private PharmaAdapter pharmaAdapter;
    private Activity myactivity;
    private List<Pharmacy> filterpharmacy;

    public AllPharmacyFragment() {
        // Required empty public constructor
    }

    public void fetchPharmacy()
    {

        allpharmacy = new ArrayList<>();
        //192.168.1.2
        //10.0.2.2
        url = "http://192.168.43.69:3000/pharmacy/pharmacylist";

         JsonArrayRequest jsprequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {


              System.out.println("resultreq"+response);

              if(response!= null)
              {
                  try
                  {
                      for(int i=0; i<response.length();i++)
                      {

                          allpharmacy.add(new Pharmacy(response.getJSONObject(i).getInt("id_pharmacie"),response.getJSONObject(i).getInt("id_pharmacien"),
                                  response.getJSONObject(i).getString("nom_pharmacie"),response.getJSONObject(i).getString("desciprion"),response.getJSONObject(i).getString("numeros_pharmacie"),
                                  response.getJSONObject(i).getString("ville"),response.getJSONObject(i).getString("pay"),response.getJSONObject(i).getString("adresse"),
                                  response.getJSONObject(i).getString("pharma_picture"),response.getJSONObject(i).getInt("garde"),response.getJSONObject(i).getDouble("longitude"),
                                  response.getJSONObject(i).getDouble("latitude")));
                      }

                      pharmaAdapter = new PharmaAdapter(getContext(),allpharmacy);
                      pharma.setLayoutManager(new LinearLayoutManager(getContext()));
                      pharma.setAdapter(pharmaAdapter);

                  }
                  catch (JSONException e)
                  {
                      System.out.println("pherrror: "+e.getMessage());
                  }

              }

          }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        NetworkServer.getMySingleton(getContext()).addToRequestQue(jsprequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_all_pharmacy, container, false);

        pharma = view.findViewById(R.id.recyclerviewpharma);
        allpharmacy = new ArrayList<>();
fetchPharmacy();

myactivity =  (PharmacyActivity) getActivity();

((PharmacyActivity) myactivity).getSearchView().setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
    @Override
    public void onSearchViewShown() {
    }

    @Override
    public void onSearchViewClosed()
    {
        pharmaAdapter = new PharmaAdapter(getContext(),allpharmacy);
        pharma.setLayoutManager(new LinearLayoutManager(getContext()));
        pharma.setAdapter(pharmaAdapter);
        pharmaAdapter.notifyDataSetChanged();
    }
});

((PharmacyActivity) myactivity).getSearchView().setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        filterpharmacy = new ArrayList<>();
        String pharmaname = "";
        for(int i = 0 ; i<allpharmacy.size();i++)
        {
            pharmaname = allpharmacy.get(i).getNom_pharmacie();

             if(pharmaname.toLowerCase().contains(newText.toLowerCase()))
             {
                 filterpharmacy.add(allpharmacy.get(i));
             }

             else
             {
                 //System.out.println("otherchecking");
             }

        }
        pharmaAdapter = new PharmaAdapter(getContext(),filterpharmacy);
        pharma.setLayoutManager(new LinearLayoutManager(getContext()));
        pharma.setAdapter(pharmaAdapter);
        pharmaAdapter.notifyDataSetChanged();

        return true;
    }
});

        return view;
    }

}
