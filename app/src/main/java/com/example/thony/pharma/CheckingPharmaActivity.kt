package com.example.thony.pharma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_checking_pharma.*
import org.json.JSONArray

class CheckingPharmaActivity : AppCompatActivity() {


    var pharmcies:MutableList<Pharmacy> =  ArrayList<Pharmacy>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checking_pharma)

        val medname:Bundle?  = intent.extras

        //url = "http://192.168.1.7:3000/pharmacylist/"
        //url += medname!!.getString("medicament_name")


      var url = "http://192.168.43.69:3000/pharmacylist/"


        url += medname!!.getString("medicament_name")
        
        //print("contenturl"+url)
        val jsonArrayRequest:JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener
        {

            response->

             if(response != null)
             {
                 for(i in 0 ..response.length() -1 )
                 {
                     print("contentjson"+response.getJSONObject(i));


                     pharmcies.add(Pharmacy(response.getJSONObject(i).getInt("id_pharmacie"),response.getJSONObject(i).getInt("id_pharmacien"),
                            response.getJSONObject(i).getString("nom_pharmacie"),response.getJSONObject(i).getString("desciprion"),response.getJSONObject(i).getString("numeros_pharmacie"),
                             response.getJSONObject(i).getString("ville"),response.getJSONObject(i).getString("pay"),response.getJSONObject(i).getString("adresse"),
                             response.getJSONObject(i).getString("pharma_picture"),response.getJSONObject(i).getInt("garde"),
                             response.getJSONObject(i).getDouble("longitude"),response.getJSONObject(i).getDouble("latitude")))
                 }

                 var adapterpharma:PharmaAdapter = PharmaAdapter(applicationContext,pharmcies)
                 checkingpharmarecyclerview.layoutManager = LinearLayoutManager(applicationContext)
                 checkingpharmarecyclerview.adapter = adapterpharma
             }

              //   Toast.makeText(applicationContext,"Coolsucccess"+response,Toast.LENGTH_LONG).show()
        }, Response.ErrorListener
        {
            erreur->
            Toast.makeText(applicationContext,"Errur:"+erreur.message,Toast.LENGTH_LONG).show()
        })


        NetworkServer.getMySingleton(applicationContext).addToRequestQue(jsonArrayRequest)
         // println(medname!!.getString("medicament_name"))
    }
}
