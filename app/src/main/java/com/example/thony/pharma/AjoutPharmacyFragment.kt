package com.example.thony.pharma


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.mapbox.mapboxsdk.Mapbox.getApplicationContext
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException
import org.json.JSONObject
import retrofit2.http.POST
import com.android.volley.toolbox.JsonObjectRequest
import com.mapbox.mapboxsdk.Mapbox.getApplicationContext
import kotlinx.android.synthetic.main.fragment_order.*
import com.example.thony.pharma.R.id.adr_et
import com.example.thony.pharma.R.id.ville_et
import com.example.thony.pharma.R.id.pays_et
import com.example.thony.pharma.R.id.num_et
import com.example.thony.pharma.R.id.desc_et
import com.example.thony.pharma.R.id.nom_et
import com.mapbox.mapboxsdk.Mapbox.getApplicationContext






// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AjoutPharmacyFragment : Fragment()
{




    private var id_pharmacien = 1
    private var url = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.fragment_ajout_pharmacy, container, false)


        var nom_et: EditText = view.findViewById(R.id.nom_et)
        var desc_et:EditText = view.findViewById(R.id.desc_et)
        var num_et: EditText = view.findViewById(R.id.num_et)
        var pays_et:EditText = view.findViewById(R.id.pays_et)
        var ville_et: EditText = view.findViewById(R.id.ville_et)
        var adr_et: EditText = view.findViewById(R.id.adr_et)
        var send_btn: Button = view.findViewById(R.id.send_btn)

        send_btn.setOnClickListener {

            if(nom_et.getText().toString().length >0 && desc_et.getText().toString().length >0 && num_et.getText().toString().length >0 && pays_et.getText().toString().length >0 && ville_et.getText().toString().equals(adr_et.getText().toString()))
            {
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("nom_pharmacie", nom_et.text.toString())
                    jsonObject.put("desciprion", desc_et.text.toString())
                    jsonObject.put("numeros_pharmacie", num_et.text.toString())
                    jsonObject.put("pay", pays_et.text.toString())
                    jsonObject.put("ville", ville_et.text.toString())
                    jsonObject.put("adresse", adr_et.text.toString())
                    jsonObject.put("id_pharmacien", id_pharmacien)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }


                val requestBody:String = jsonObject.toString()


                url = "http://192.168.43.69:3000/pharmacy/add";

                val jsp = JsonObjectRequest(Request.Method.POST, url, jsonObject, Response.Listener { response ->
                    try {
                        println("Resultat: " + response.getString("resultat"))
                        if (response.get("resultat") == "Success") {
                            Toast.makeText(context, "pharmacie creer avec success", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Erreur: Creation de pharmacie", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(getApplicationContext(), "une erreur s'est produite lors de la creation de la pharmacie", Toast.LENGTH_SHORT).show()
                    }
                }, Response.ErrorListener { error -> println("volleyError: " + error.message) })


                NetworkServer.getMySingleton(context).addToRequestQue(jsp)
            }
            else
            {
                Toast.makeText(context,"Erreur: Donnees invalides",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }


}
