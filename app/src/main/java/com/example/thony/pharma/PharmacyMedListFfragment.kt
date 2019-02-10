package com.example.thony.pharma


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.content_pharmcy_own_interface.*
import kotlinx.android.synthetic.main.fragment_pharacy_med_list_ffragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PharacyMedListFfragment : Fragment()
{

    private var medicaments:MutableList<Medicament> = ArrayList<Medicament>()
    private var url = ""

    private  var filtermedicament:MutableList<Medicament> = ArrayList<Medicament>()

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

    fun FetchPharmacyMedication(mydb: PharmaDataBase)
    {

        val ph:Pharmacy = FetchPharmaFromDataBase(mydb);
        var medadapter:MedicamentAdapter

        println("Testerequst")
        //192.168.1.2
        //10.0.2.2
        url  = "http://192.168.43.69:3000/allmedicamentinthispharmacy/"
        url += ph.id_pharmacie

        println("URLRequest:"+url)
        val jspreq:JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null, Response.Listener
        {

            response ->

            if(response != null)
            {
                for(i in 0..response.length() -1)
                {
medicaments.add(Medicament(response.getJSONObject(i).getInt("id_medicament"),response.getJSONObject(i).getString("nom_medicament"),
        response.getJSONObject(i).getString("image_medicament"),response.getJSONObject(i).getDouble("prix"),
        response.getJSONObject(i).getInt("quantite"),response.getJSONObject(i).getInt("ordonnance"),response.getJSONObject(i).getInt("id_type"),
        response.getJSONObject(i).getInt("id_categorie"),response.getJSONObject(i).getInt("id_forme"),
        response.getJSONObject(i).getInt("id_pharmacie"),response.getJSONObject(i).getInt("id_pharmacien"),
        response.getJSONObject(i).getString("nom_pharmacie"),response.getJSONObject(i).getString("nom_categorie"),
        response.getJSONObject(i).getString("type"),response.getJSONObject(i).getString("descriptiom"),
        response.getJSONObject(i).getString("nom_forme")))
                }
               medadapter = MedicamentAdapter(context,medicaments)
                medadapter.putbtstate(true)
                pharmamedrecyclerview.layoutManager = GridLayoutManager(context,2)
                pharmamedrecyclerview.adapter = medadapter
            }

        },Response.ErrorListener {

            error ->

            println("ErrorREDKFLKF:"+error.message)
        })

        NetworkServer.getMySingleton(context).addToRequestQue(jspreq)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {

        val view:View = inflater.inflate(R.layout.fragment_pharacy_med_list_ffragment, container, false)
        val dataBase:PharmaDataBase = PharmaDataBase(context)
        FetchPharmacyMedication(dataBase)

        val myactivity = activity as PharmcyOwnInterface

        myactivity.csearchview!!.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown()
            {

            }

            override fun onSearchViewClosed()
            {
                var medadapter:MedicamentAdapter
                medadapter = MedicamentAdapter(context,medicaments)
                medadapter.putbtstate(true)
                pharmamedrecyclerview.layoutManager = GridLayoutManager(context,2)
                pharmamedrecyclerview.adapter = medadapter
                medadapter.notifyDataSetChanged()
            }
        })

        myactivity.csearchview!!.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean
            {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && !newText.isEmpty())
                {
                    filtermedicament = ArrayList<Medicament>()

                    var nommed:String =  ""

                    for(i in 0 ..medicaments.size - 1)
                    {
                        nommed = medicaments.get(i).nom_medicament

                        if(nommed.toLowerCase().contains(newText.toLowerCase()))
                        {
                            filtermedicament.add(medicaments.get(i))
                        }
                    }

                    var medadapter:MedicamentAdapter
                    medadapter = MedicamentAdapter(context,filtermedicament)
                    medadapter.putbtstate(true)
                    pharmamedrecyclerview.layoutManager = GridLayoutManager(context,2)
                    pharmamedrecyclerview.adapter = medadapter
                    medadapter.notifyDataSetChanged()
                }
                return true
            }
        })

        if(dataBase.numberofMedicamentinPanier > 0)
        {
            myactivity.cartimg.visibility = View.VISIBLE
            myactivity.txtqte.text = dataBase.numberofMedicamentinPanier.toString()
            myactivity.txtqte.visibility = View.VISIBLE
        }
        else
        {
       myactivity.cartimg.visibility = View.INVISIBLE
            myactivity.txtqte.visibility = View.INVISIBLE
        }





        return  view
    }


}
