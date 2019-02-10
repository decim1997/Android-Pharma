package com.example.thony.pharma


import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.content_pharmcy_own_interface.*
import kotlinx.android.synthetic.main.fragment_gestionair_cart.*
import java.lang.reflect.AccessibleObject.setAccessible
import java.text.DecimalFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GestionairCartFragment : Fragment()
{

     // val myinflater:LayoutInflater = LayoutInflater.from(context)
      //val myview:View = myinflater.inflate(R.layout.fragment_gestionair_cart, null, false);
     var lbmountant:TextView? = view?.findViewById(R.id.txttotalamount)
     var btempty:Button? =  view?.findViewById(R.id.btallemptycart)
    private val df2 = DecimalFormat(".##")

    fun initlbtotalamount(myview:View)
    {
        val txt:TextView = myview.findViewById(R.id.txttotalamount)
        txt.setText("TotalAmount")
    }

    fun initAll(db:PharmaDataBase,allempty:Button?,txtmount:TextView?)
    {
        var totalmaount:Double = 0.0

        if(db.numberofMedicamentinPanier >0)
        {
            val nbrel = db.medicamentPanier
            while (nbrel.moveToNext())
        {
            totalmaount += nbrel.getDouble(3)*nbrel.getInt(4)
        }

             txtmount!!.text = "TotalAmount: "+df2.format(totalmaount).toString()+" DT"
            allempty!!.visibility = View.VISIBLE
            txtmount!!.visibility = View.VISIBLE
        }
        else
        {
            allempty!!.visibility = View.INVISIBLE
            txtmount!!.visibility = View.INVISIBLE
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.fragment_gestionair_cart, container, false)

        lbmountant = view.findViewById(R.id.txttotalamount)
        btempty = view.findViewById(R.id.btallemptycart)
        val dataBase:PharmaDataBase = PharmaDataBase(context)
///        initQantitemontant(dataBase,lbmountant)

        val res = dataBase.GroupCartData()
        var gestCart:MutableList<GestCart> = ArrayList<GestCart>()
        var groupcart:MutableList<CartGroup> = ArrayList<CartGroup>()
        var carts:MutableList<Cart> = ArrayList<Cart>()
        var carts2:MutableList<Cart> = ArrayList<Cart>()
        var carts3:MutableList<Cart> = ArrayList<Cart>()
        val allempty = view.findViewById<Button>(R.id.btallemptycart)
        val txtmount = view.findViewById<TextView>(R.id.txttotalamount)

        while (res.moveToNext())
        {
            gestCart.add(GestCart(res.getInt(7),res.getString(8)))
        }

initAll(dataBase,allempty,txtmount)

        for(i in 0..gestCart.size - 1)
        {
            var cartresult = dataBase.getCustomerPharmaCart(gestCart.get(i).pharma_id,1)
            while (cartresult.moveToNext()) {
                println("nameofproduc: " + cartresult.getString(8))
                    carts.add(Cart(cartresult.getInt(0), cartresult.getInt(1), cartresult.getString(6), cartresult.getString(2),
                            cartresult.getDouble(3), cartresult.getInt(4), cartresult.getInt(5), cartresult.getInt(7)))
                }
            groupcart.add(CartGroup(gestCart.get(i).pharmaname,carts))
        }


      /*  for(i in 0..gestCart.size - 1)
        {

            for(j in 0..carts.size -1)
            {
                if(gestCart.get(i).pharma_id == carts.get(j).id_pharmacie)
                {
                    println("okkkndsjvbjhbvj")
                    carts2.add(carts.get(j))
                    carts3.addAll(carts2)
                }
            }
            carts2.removeAt(0)
        }*/

        val cartAdapter:CartAdapter = CartAdapter(groupcart,context)
        var reco:RecyclerView = view.findViewById(R.id.pharmarec)


        
        allempty.setOnClickListener {

            val builder = AlertDialog.Builder(context!!)
            builder.setMessage("Do you want to empty your cart")
                    .setCancelable(false)
                    .setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                        dataBase.EmptyPanier()
                        val myactivity = activity as PharmcyOwnInterface
                        myactivity.cartimg.visibility = View.INVISIBLE
                        myactivity.txtqte.visibility = View.INVISIBLE
                        myactivity.txtqte.text = "1"
                        txtmount.visibility = View.INVISIBLE
                        println("sizeoftable"+groupcart.size)
                        val taille = groupcart.size - 1
                        for(i in 0 ..taille)
                        {
                            groupcart.removeAt(i)
                        }
                        cartAdapter.notifyDataSetChanged()
                        allempty.visibility = View.INVISIBLE

                        fragmentManager!!.beginTransaction().replace(R.id.pharma_frament,PharacyMedListFfragment(),null).commit()

                    })
                    .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    });
            val alert = builder.create()
            alert.setTitle("Empty your cart")
            alert.show()
        }
        println("DataofCart"+carts)



        reco.layoutManager = LinearLayoutManager(context)
        reco.adapter = cartAdapter


        return view
    }

    override fun onDetach() {
        super.onDetach()

        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)

        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }

    }


}
