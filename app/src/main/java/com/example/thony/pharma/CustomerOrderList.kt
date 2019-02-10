package com.example.thony.pharma


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_customer_order_list.*
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CustomerOrderList : Fragment()
{
    var idcommande:Int? = null
    var lignecommandes:MutableList<LigneCommande> = ArrayList<LigneCommande>()


    fun fetCommandeClient(url:String)
    {
        val jspreq:JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener
        {
             response ->

            if(response != null)
            {
                val objlg:JSONObject = response.getJSONObject(0)
                lbtotalamount.text = "TotalAmount: " + objlg.getDouble("prix_total") + " DT"

                for(i in 0..response.length() -1)
                {
lignecommandes.add(LigneCommande(response.getJSONObject(i).getInt("id_lignecommande"),response.getJSONObject(i).getInt("id_commande"),
        response.getJSONObject(i).getInt("id_medicament"),response.getJSONObject(i).getDouble("prix_unitaire"),response.getJSONObject(i).getInt("quantite"),
        response.getJSONObject(i).getDouble("prix_totalligne"),response.getJSONObject(i).getString("nom_medicament"),response.getJSONObject(i).getString("image_medicament"),
        response.getJSONObject(i).getInt("id_pharmacie"),response.getJSONObject(i).getDouble("prix_total"),response.getJSONObject(i).getString("date_commande"),response.getJSONObject(i).getInt("id_client"),
        response.getJSONObject(i).getString("code")))
                }

                val myadapter:LigneAdapter = LigneAdapter(context,lignecommandes)
                lignerecycle.layoutManager = LinearLayoutManager(context)
                lignerecycle.adapter = myadapter
            }
            else
            {
Toast.makeText(context,"LIgne commande vide",Toast.LENGTH_LONG).show()
            }

        },Response.ErrorListener
        {
           error ->

            if(error != null)
            {
                println("DisplayErrorVolley: "+error.message)
            }
        })

        NetworkServer.getMySingleton(context).addToRequestQue(jspreq)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {

        val view:View = inflater.inflate(R.layout.fragment_customer_order_list, container, false)


        val url:String = "http://192.168.43.69:3000/pharmacy/getlignecommande/"+idcommande


        fetCommandeClient(url)

        return view
    }


}
