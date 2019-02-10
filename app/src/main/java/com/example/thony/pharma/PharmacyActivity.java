package com.example.thony.pharma;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.sql.SQLOutput;

public class PharmacyActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment selectedfragment;
    private Toolbar toolbar;
    private MaterialSearchView searchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.drug_fragmanent_container,new AllPharmacyFragment(),null).commit();
                    System.out.println("chek");
                       return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.drug_fragmanent_container, new DrugFragment(),null).commit();
                    return true;

                    case R.id.location:
                    Intent intent = new Intent(getApplicationContext(),LocationActivity.class);
                    startActivity(intent);
           return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phramacy);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar = findViewById(R.id.search_toolbar);
        searchView=findViewById(R.id.searchview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Bar");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        navigation.setSelectedItemId(R.id.navigation_home);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                System.out.println("OK MDNSMNC DMNC ");
            }

            @Override
            public void onSearchViewClosed() {
                System.out.println("KJDSCNKNNDKNCD");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

   getMenuInflater().inflate(R.menu.menu_search,menu);
   MenuItem item = menu.findItem(R.id.item_search_bar);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
        //return  true;
    }


    public MaterialSearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(MaterialSearchView searchView) {
        this.searchView = searchView;
    }

}
