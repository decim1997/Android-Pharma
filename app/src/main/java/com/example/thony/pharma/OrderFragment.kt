package com.example.thony.pharma


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.fragment_order.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OrderFragment : Fragment() {


    private var commandes:MutableList<Order> = ArrayList<Order>()

    fun Session(dataBase: PharmaDataBase): Perssone {
        val p = Perssone()
        val res = dataBase.personneSession

        println("curres" + res.count)
        if (res.count > 0) {
            while (res.moveToNext()) {
                p.setId_session(res.getInt(0))
                p.setId_personne(res.getInt(1))
                p.setEmail(res.getString(2))
                p.setPseudo(res.getString(3))
                p.setPassword(res.getString(4))
                p.setPhoto(res.getString(5))
                p.setNumber(res.getString(6))
                p.setRole(res.getInt(7))
                p.setActivate(res.getInt(8))
            }
        }
        return p
    }

    fun FetchCommande(dataBase: PharmaDataBase,perssone: Perssone)
    {
        var url = "http://192.168.43.69:3000/pharmacy/getallUserCommande/"
        url += perssone.getId_personne()

        val jsp:JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener
        {
            response ->

            if(response != null)
            {
for(i in 0..response.length() -1)
{
    commandes.add(Order(response.getJSONObject(i).getInt("id_commande"),response.getJSONObject(i).getInt("id_pharmacie"),
            response.getJSONObject(i).getDouble("prix_total"),response.getJSONObject(i).getString("date_commande"),
            response.getJSONObject(i).getString("code"),response.getJSONObject(i).getInt("id_client"),
            response.getJSONObject(i).getString("nom_pharmacie"),response.getJSONObject(i).getString("adresse"),
            response.getJSONObject(i).getDouble("longitude"),response.getJSONObject(i).getDouble("latitude")))
}
                val orderAdapter:OrderAdapter = OrderAdapter(context,commandes)
                orderrecycler.layoutManager = LinearLayoutManager(context)
                orderrecycler.adapter = orderAdapter
            }
        },Response.ErrorListener {
            error ->
            if(error != null)
            {
                print("VolleyError"+error.message)
            }
        })
        NetworkServer.getMySingleton(context).addToRequestQue(jsp)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {

        val view:View = inflater.inflate(R.layout.fragment_order, container, false)

        val db = PharmaDataBase(context)
        val people = Session(db)


        FetchCommande(db,people)
        return view
    }


}
