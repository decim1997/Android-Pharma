package com.example.thony.pharma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_pharmacien_interface.*
import kotlinx.android.synthetic.main.app_bar_pharmacien_interface.*

class PharmacienInterfaceActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    fun destroySession() {
        val db = PharmaDataBase(applicationContext)
        db.emptyPersonneSession()
        db.EmptyPanier()
        val preferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        preferences.edit().remove("pseudo").commit()
        preferences.edit().remove("password").commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacien_interface)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pharmacien_interface, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.pharmasignout -> {
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
            R.id.pharmaexit -> {

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
                return true }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_qrcode ->
            {
                supportFragmentManager.beginTransaction().replace(R.id.pharmacienfragment,PharmacienQRCodeFragment(),null).commit()

            }
            R.id.nav_gallery ->
            {
                supportFragmentManager.beginTransaction().replace(R.id.pharmacienfragment,AjoutPharmacyFragment(),null).commit()

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val myfrag = supportFragmentManager.findFragmentById(R.id.pharmacienfragment) as PharmacienQRCodeFragment

          if(myfrag != null)
          {
         myfrag.onActivityResult(requestCode,resultCode,data)
          }
    }
}
