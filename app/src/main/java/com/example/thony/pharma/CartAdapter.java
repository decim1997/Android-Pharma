package com.example.thony.pharma;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CartAdapter extends ExpandableRecyclerViewAdapter<CartAdapter.cartGroupViewHolder,CartAdapter.cartViewHolder>
{

    private Context context;
    private LayoutInflater inflater;
    private List<? extends ExpandableGroup> groups;
    private PharmaDataBase dataBase;
    private int numlinge = -1;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private  double totalmaount = 0;

    public CartAdapter(List<? extends ExpandableGroup> groups,Context context)
    {
        super(groups);
        this.groups = groups;
        this.context = context;
    }

    @Override
    public cartGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View myview;
        inflater = LayoutInflater.from(context);
        myview = inflater.inflate(R.layout.gestcartlayout,parent,false);
        return new cartGroupViewHolder(myview);
    }

    @Override
    public cartViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        View cartview;
        inflater = LayoutInflater.from(context);
        cartview = inflater.inflate(R.layout.designodcart,parent,false);
        return new cartViewHolder(cartview);
    }

    @Override
    public void onBindChildViewHolder(cartViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex)
    {
final Cart cart = (Cart) group.getItems().get(childIndex);
dataBase = new PharmaDataBase(context);

holder.imgplus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
           List<Cart> carts = new ArrayList<>();
        carts.addAll(group.getItems());
        int qte = carts.get(childIndex).getQuantite_medicament();
        qte ++;
        carts.get(childIndex).setQuantite_medicament(qte);
        totalmaount = 0;
        dataBase.updateQuantiteMedicamentPanier(carts.get(childIndex).getId_panier(),carts.get(childIndex).getQuantite_medicament());


        PharmcyOwnInterface myactitviy = (PharmcyOwnInterface) context;
         TextView mytxt = myactitviy.findViewById(R.id.txttotalamount);
         notifyDataSetChanged();
        Cursor nbrel = dataBase.getMedicamentPanier();
        while (nbrel.moveToNext())
        {
            totalmaount += nbrel.getDouble(3)*nbrel.getInt(4);
        }
        mytxt.setText("TotalAmount: "+df2.format(totalmaount).toString()+" DT");


    }
});

holder.imgmoin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        List<Cart> carts = new ArrayList<>();
        carts.addAll(group.getItems());
        int qte = carts.get(childIndex).getQuantite_medicament();
        qte --;
totalmaount = 0;

        if(qte >= 1)
        {
       carts.get(childIndex).setQuantite_medicament(qte);
       dataBase.updateQuantiteMedicamentPanier(carts.get(childIndex).getId_panier(),carts.get(childIndex).getQuantite_medicament());
            PharmcyOwnInterface myactitviy = (PharmcyOwnInterface) context;
            TextView mytxt = myactitviy.findViewById(R.id.txttotalamount);
            notifyDataSetChanged();
            Cursor nbrel = dataBase.getMedicamentPanier();
            while (nbrel.moveToNext())
            {
                totalmaount += nbrel.getDouble(3)*nbrel.getInt(4);
            }
            mytxt.setText("TotalAmount: "+df2.format(totalmaount).toString()+" DT");

        }
    }
});

holder.imgsup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you delete it from your cart")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Cart> carts = new ArrayList<>();
                        carts.addAll(group.getItems());
                        dataBase.deleteMedicamentPanier(String.valueOf(carts.get(childIndex).getId_panier()));
                        carts.remove(childIndex);
                        group.getItems().remove(childIndex);
                        PharmcyOwnInterface myactitviy = (PharmcyOwnInterface) context;
                        TextView mytxt = myactitviy.findViewById(R.id.txttotalamount);
                        notifyDataSetChanged();
                        Cursor nbrel = dataBase.getMedicamentPanier();
                        while (nbrel.moveToNext())
                        {
                            totalmaount += nbrel.getDouble(3)*nbrel.getInt(4);
                        }
                        mytxt.setText("TotalAmount: "+df2.format(totalmaount).toString()+" DT");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("Remove");
        alert.show();
    }
});
holder.bind(cart);
    }

    @Override
    public void onBindGroupViewHolder(cartGroupViewHolder holder, int flatPosition, ExpandableGroup group)
    {
final CartGroup cartGroup = (CartGroup) group;
        numlinge = flatPosition;

        holder.btordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to proceed to Order")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                List<Cart> carts = new ArrayList<>();
                                carts.addAll(group.getItems());

                                int idpharmacie =carts.get(0).getId_pharmacie();
                                double prixtotal = 0.0;
                                int idclient = carts.get(0).getId_client();
                                char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
                                char[] c = new char[15];
                                String mycode ="";
                                SecureRandom random = new SecureRandom();

                                for (int i = 0; i < 15; i++) {
                                    c[i] = ch[random.nextInt(ch.length)];
                                }
                               mycode = new String(c);

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String datecommande = dateFormat.format(date);

                                final String code = mycode;

                                for(int i=0; i<carts.size();i++)
                                {
                                    prixtotal += carts.get(i).getPrix_medicament()*carts.get(i).getQuantite_medicament();
                                }

                                //
                                //10.0.2.2
                                String url = "http://192.168.43.69:3000/pharmacy/customer/addcommande";

                                JSONObject jsonObject = new JSONObject();

                                try
                                {
                            jsonObject.put("idpharmacie",idpharmacie);
                            jsonObject.put("prixtotal",df2.format(prixtotal));
                            jsonObject.put("datecommande",datecommande);
                            jsonObject.put("idclient",idclient);
                            jsonObject.put("codecommande",code);
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                                final String requestBody = jsonObject.toString();

                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response)
                                    {
                                      try
                                      {
                                          if(response.get("resultat").equals("Success"))
                                          {
                                              System.out.println("SuccesCommande");

                                              String urlcommande = "http://192.168.43.69:3000/pharmacy/getidcommande/";
                                              urlcommande += code;
//10.0.2.2

                                              JsonArrayRequest req  = new JsonArrayRequest(Request.Method.GET, urlcommande, null, new Response.Listener<JSONArray>() {
                                                  @Override
                                                  public void onResponse(JSONArray response)
                                                  {
                                                    if(response!=null)
                                                    {
                                                        System.out.println("codecommande"+code);
                                                        try
                                                    {
                                                        JSONObject jsb = response.getJSONObject(0);
                                                        int idcommande = jsb.getInt("id_commande");
                                                        String urllncommande = "http://192.168.43.69:3000/pharmacy/customer/addlignecommande";
                                                        JSONObject jsonObjectlignecommande = new JSONObject();

                                                        for(int i=0;i<carts.size();i++)
                                                        {
                                                     jsonObjectlignecommande.put("idcommande",idcommande);
                                                     jsonObjectlignecommande.put("idmedicament",carts.get(i).getId_medicamen());
                                                     jsonObjectlignecommande.put("prixunitaire",carts.get(i).getPrix_medicament());
                                                     jsonObjectlignecommande.put("quantite",carts.get(i).getQuantite_medicament());
                                                     jsonObjectlignecommande.put("prixtotalligne",carts.get(i).getPrix_medicament()*carts.get(i).getQuantite_medicament());
                                                     jsonObjectlignecommande.put("nommedicament",carts.get(i).getNom_medicament());
                                                     jsonObjectlignecommande.put("imagemedicament",carts.get(i).getImage_medicament());

                                                     JsonObjectRequest jsprelc = new JsonObjectRequest(Request.Method.POST, urllncommande, jsonObjectlignecommande, new Response.Listener<JSONObject>() {
                                                         @Override
                                                         public void onResponse(JSONObject response)
                                                         {
                                                            try
                                                            {
                                                                if(response.get("resultat").equals("Success"))
                                                                {


                                                                    dataBase.EmptyPanier();
                                                                    for(int i = 0;i<carts.size();i++)
                                                                    {
                                                                        carts.remove(i);
                                                                        group.getItems().remove(i);
                                                                    }
                                                                    notifyDataSetChanged();



                                                                    holder.btordered.setVisibility(View.INVISIBLE);
                                                                    PharmcyOwnInterface myactitviy = (PharmcyOwnInterface) context;
                                                                    myactitviy.getImgofcart().setVisibility(View.INVISIBLE);
                                                                    myactitviy.getLbquantite().setVisibility(View.INVISIBLE);
                                                                    Fragment qrf = new QRCodeReaderFragment();
                                                                    ((QRCodeReaderFragment) qrf).setCode(code);
                                                                    myactitviy.getSupportFragmentManager().beginTransaction().replace(R.id.pharma_frament,qrf,null).commit();
                                                                }
                                                            }
                                                            catch (JSONException e)
                                                            {
                                                                System.out.println(e.getMessage());
                                                            }


                                                         }
                                                     }, new Response.ErrorListener() {
                                                         @Override
                                                         public void onErrorResponse(VolleyError error)
                                                         {

                                                         }
                                                     });
                                                            NetworkServer.getMySingleton(context).addToRequestQue(jsprelc);
                                                        }

                                                    }
                                                    catch (JSONException e)
                                                    {
                                                        e.getMessage();
                                                    }

                                                    }
                                                  }
                                              }, new Response.ErrorListener() {
                                                  @Override
                                                  public void onErrorResponse(VolleyError error) {
                                                      System.out.println("ErrorofVolleyOLL"+error.getMessage());
                                                  }
                                              });

                                              NetworkServer.getMySingleton(context).addToRequestQue(req);


                                          }
                                          else
                                          {
                                              System.out.println("Erroropfkdckdccdslkcm");
                                          }
                                      }
                                      catch (JSONException e)
                                      {
                                          e.printStackTrace();
                                          Toast.makeText(context,"Vous devez saisire votre login et votre mot de passe",Toast.LENGTH_SHORT).show();
                                      }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });

                                NetworkServer.getMySingleton(context).addToRequestQue(request);
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
        });
holder.bind(cartGroup);
    }

    public static class cartGroupViewHolder extends GroupViewHolder
    {
        private TextView titletext;
        private Button btordered;
        public cartGroupViewHolder(final  View item)
        {
            super(item);
            titletext = item.findViewById(R.id.txtpharmaname);
            btordered = item.findViewById(R.id.btordered);

        }

        public void bind(CartGroup cartGroup)
        {
            titletext.setText(cartGroup.getTitle());
        }
    }

    public static  class cartViewHolder extends ChildViewHolder
    {
        private ImageView medpic;
        private TextView  txtmedname;
        private TextView txtlignamount;
        private TextView txtqte;
        private ImageView imgsup;
        private ImageView imgmoin;
        private ImageView imgplus;

        public cartViewHolder(final  View item)
          {
              super(item);
              medpic = item.findViewById(R.id.imgmedcart);
              txtmedname = item.findViewById(R.id.txtmedname);
              txtlignamount = item.findViewById(R.id.txtamount);
              txtqte = item.findViewById(R.id.txtcartqte);
              imgsup = item.findViewById(R.id.ipmgdel);
              imgmoin = item.findViewById(R.id.imgmoin);
              imgplus = item.findViewById(R.id.imgplus);
          }

        public void bind(Cart cart)
        {
            Picasso.get().load(cart.getImage_medicament())
                    .error(R.drawable.himalayanemtab)
                    .into(medpic);
            txtmedname.setText(cart.getNom_medicament());

            txtlignamount.setText("Amount: "+df2.format(cart.getPrix_medicament()*cart.getQuantite_medicament()).toString());
            txtqte.setText(String.valueOf(cart.getQuantite_medicament()));
        }
    }

    @Override
    public List<? extends ExpandableGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<? extends ExpandableGroup> groups) {
        this.groups = groups;
    }

}
