package com.example.thony.pharma


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PharmacienQRCodeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {


        val view:View = inflater.inflate(R.layout.fragment_pharmacien_qrcode, container, false)
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        integrator.setPrompt("Scan")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()


        return view
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        println("hbdjvbhjadjschvdsjcvsghdvhcvdsvchg")
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null)
        {
if(result.contents ==  null)
{
    Toast.makeText(context,"You cancelled the scanning",Toast.LENGTH_LONG).show()
}
    else
{

    Toast.makeText(context,"Code: "+result.contents,Toast.LENGTH_LONG).show()
    var myframent = CustomerOrderList()
    myframent.idcommande = 69
    fragmentManager!!.beginTransaction().replace(R.id.pharmacienfragment,myframent,null).commit()


    /*var url:String = "192.168.1.2:3000/pharmacy/getidcommande/" + result.contents

    val jsonreq:JsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null, Response.Listener {

        response ->

        if(response != null)
        {

            val commandobj:JSONObject = response.getJSONObject(0)
            val idcommande:Int = commandobj.getInt("id_commande")

            var myframent = CustomerOrderList()
            myframent.idcommande = idcommande
            fragmentManager!!.beginTransaction().replace(R.id.pharmacienfragment,myframent,null).commit()


        }
        else
        {
            Toast.makeText(context,"This Commande does not exit",Toast.LENGTH_LONG).show()
        }

    },Response.ErrorListener {
        error ->

        if(error != null)
        {
            println("Erroreqvolley: "+error.message)
        }
    })*/


}
        }
        else
        {
            Toast.makeText(context,"Impossible to read the qrcode",Toast.LENGTH_LONG).show()
        }

    }





}
