package com.example.thony.pharma;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment selectedfragment;
    private FragmentManager fragmentManager;
    private  FragmentTransaction fragmentTransaction;
    private  PharmaDataBase db;
    private SharedPreferences preferences;


    public Perssone Session(PharmaDataBase dataBase)
    {
        Perssone p = new Perssone();
        Cursor res = dataBase.getPersonneSession();

        System.out.println("curres"+res.getCount());
        if(res.getCount() > 0)
        {
            while(res.moveToNext())
            {
           p.setId_session(res.getInt(0));
          p.setId_personne(res.getInt(1));
          p.setEmail(res.getString(2));
          p.setPseudo(res.getString(3));
          p.setPassword(res.getString(4));
          p.setPhoto(res.getString(5));
          p.setNumber(res.getString(6));
          p.setRole(res.getInt(7));
          p.setActivate(res.getInt(8));
            }
        }

        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new PharmaDataBase(getApplicationContext());

        Perssone p = Session(db);


        fragmentManager=getSupportFragmentManager();
        selectedfragment=new MenuFragment();
        fragmentTransaction=fragmentManager.beginTransaction().replace(R.id.fragment_container,selectedfragment,null);
        fragmentTransaction.commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //        getActionBar().setTitle("");



        View headerView = navigationView.getHeaderView(0);
        ImageView profilepicture = (ImageView) headerView.findViewById(R.id.profilepicture);
        TextView txtpseudo = (TextView) headerView.findViewById(R.id.pseudotxt);
        TextView txtemail = (TextView) headerView.findViewById(R.id.emailtxt);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        Picasso.get().load(p.getPhoto()).placeholder(R.drawable.images)
                .error(R.drawable.images)
                .into(profilepicture, new Callback() {
                            @Override
                            public void onSuccess() {
                                System.out.println("Ruessite");
                            }

                            @Override
                            public void onError(Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                );

       txtpseudo.setText(p.getPseudo());
       txtemail.setText(p.getEmail());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void destroySession()
    {
        db = new PharmaDataBase(getApplicationContext());
        db.emptyPersonneSession();
        db.EmptyPanier();
        preferences = getSharedPreferences("login",MODE_PRIVATE);
        preferences.edit().remove("pseudo").commit();
        preferences.edit().remove("password").commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Do you want to exit???")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            destroySession();
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.setTitle("Exit");
            alert.show();
            return true;
        }

        if(id == R.id.action_logout)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Do you want to logout??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            destroySession();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.setTitle("Exit");
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch(item.getItemId())
        {
            case R.id.nav_account:
                selectedfragment= new AccountFragment();
                break;

            case R.id.nav_faq:
                selectedfragment= new FAQFragment();
                break;

            case R.id.nav_historical:
                selectedfragment=  new HistoricalFragment();
                break;

            case R.id.nav_helpe:
                selectedfragment = new HelpFragment();
                break;

            case R.id.nav_about:
                selectedfragment = new AboutFragment();
                break;

            case R.id.main_home:
                selectedfragment = new MenuFragment();
                break;
            case R.id.nav_seting:
                selectedfragment = new SetingFragment();
                break;

        }


        fragmentTransaction=fragmentManager.beginTransaction().replace(R.id.fragment_container,selectedfragment,null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
