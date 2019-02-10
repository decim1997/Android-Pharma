package com.example.thony.pharma

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pharmcy_own_interface.*
import kotlinx.android.synthetic.main.app_bar_pharmcy_own_interface.*
import kotlinx.android.synthetic.main.content_pharmcy_own_interface.*

class PharmcyOwnInterface : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{


var imgofcart: ImageView? = null
var lbquantite:TextView? = null
    var csearchview:MaterialSearchView? = null

var backuplist:MutableList<Medicament> = ArrayList()

    fun destroySession() {
        val db = PharmaDataBase(applicationContext)
        db.emptyPersonneSession()
        db.EmptyPanier()
        val preferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        preferences.edit().remove("pseudo").commit()
        preferences.edit().remove("password").commit()
    }



    fun FetchPharmaFromDataBase(mydatabase: PharmaDataBase):Pharmacy
    {
        val ph:Pharmacy = Pharmacy()
       val res = mydatabase.AllPharamacy()

        if(res.count >0)
        {
            while (res.moveToNext())
            {
ph.id_pharmacie = res.getInt(1)
ph.id_pharmacien = res.getInt(2)
ph.nom_pharmacie = res.getString(3)
ph.description = res.getString(4)
ph.numeros_pharmacie = res.getString(5)
ph.ville = res.getString(6)
ph.pay = res.getString(7)
ph.addresse = res.getString(8)
ph.pharmapicture = res.getString(9)
ph.garde = res.getInt(10)
ph.longitude = res.getDouble(11)
ph.latitude = res.getDouble(12)
            }
        }

        return  ph
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmcy_own_interface)
        setSupportActionBar(toolbar)
        var dataBase = PharmaDataBase(applicationContext)
        val mypharma = FetchPharmaFromDataBase(dataBase)
        csearchview = findViewById(R.id.customersearchview)
        imgofcart = findViewById(R.id.cartimg)
        lbquantite = findViewById(R.id.txtqte);

        csearchview!!.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                println("OK MDNSMNC DMNC ")
            }

            override fun onSearchViewClosed() {
                println("KJDSCNKNNDKNCD")
            }
        })

        txtqte.text = "1"
        cartimg.visibility = View.INVISIBLE
        txtqte.visibility = View.INVISIBLE
        if(dataBase.getNumberofMedicamentinPanier() > 0)
        {
            cartimg.visibility = View.VISIBLE
            txtqte.text = dataBase.getNumberofMedicamentinPanier().toString()
            txtqte.visibility = View.VISIBLE
        }



        cartimg.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.pharma_frament,GestionairCartFragment(),null).commit()
        }

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView

        val  pharmanav =  findViewById<NavigationView>(R.id.nav_view)
        val headerView = pharmanav.getHeaderView(0)

        var pharmapic =  headerView.findViewById<ImageView>(R.id.pharmapic)
        var pharmaname = headerView.findViewById<TextView>(R.id.pharmaname)
        var pharmaadresse = headerView.findViewById<TextView>(R.id.adressepharmacy)


        Picasso.get().load(mypharma.pharmapicture)
                .error(R.drawable.images)
                .into(pharmapic)

        pharmaname.text = mypharma.nom_pharmacie
        pharmaadresse.text = mypharma.addresse


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().replace(R.id.pharma_frament,PharacyMedListFfragment(),null).commit()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.pharmcy_own_interface, menu)
        val item = menu.findItem(R.id.customsearchbar)
        csearchview!!.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {

            R.id.pharma_exit ->
            {
                val builder = AlertDialog.Builder(this)

                builder.setMessage("Do you want to exit???")
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, which ->
                            destroySession()
                            moveTaskToBack(true)
                            android.os.Process.killProcess(android.os.Process.myPid())
                            System.exit(1)
                        }
                        .setNegativeButton("No") { dialog, which -> dialog.cancel() }
                val alert = builder.create()
                alert.setTitle("Exit")
                alert.show()
                return true
            }

          R.id.sedeconnecter ->
          {
              val builder = AlertDialog.Builder(this)

              builder.setMessage("Do you want to logout??")
                      .setCancelable(false)
                      .setPositiveButton("Yes") { dialog, which ->
                          destroySession()
                          val intent = Intent(applicationContext, LoginActivity::class.java)
                          startActivity(intent)
                      }
                      .setNegativeButton("No") { dialog, which -> dialog.cancel() }

              val alert = builder.create()
              alert.setTitle("Exit")
              alert.show()
              return true
          }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_homepharma ->
            {
         val intent:Intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_pharmamedicament ->
            {
          supportFragmentManager.beginTransaction().replace(R.id.pharma_frament,PharacyMedListFfragment(),null).commit()
            }
            R.id.nav_pharmamap ->
            {
println("Pharmamap")
            }
            R.id.nav_pharmacall ->
            {

              /*  val builder = AlertDialog.Builder(applicationContext)

                builder.setMessage("Do yout want to call this Pharmacy")
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, which -> println("Calling pharmacy") }
                        .setNegativeButton("No") { dialog, which -> dialog.cancel() }

                val alert = builder.create()
                alert.setTitle("Call Pharmacy")
                alert.show()*/
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }



}
