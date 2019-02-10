package com.example.thony.pharma;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MedicamentAdapter extends RecyclerView.Adapter<MedicamentAdapter.MyViewHolder>
{

    private Context context;
    private List<Medicament> medlist;
    private LayoutInflater inflater;
    private  PharmaDataBase db;
    private FragmentManager fragmentManager;
    private ArrayList<String> permisos;
    private RequestOptions option;
    private String url = "";
    private boolean state = false;

    public MedicamentAdapter(Context context,List<Medicament> medlist)
    {
        this.context = context;
        this.medlist = medlist;
        option = new RequestOptions().centerCrop()
                .placeholder(R.drawable.himalayanemtab)
                .error(R.drawable.himalayanemtab);
    }


    private Pharmacy FetchPharmaFromDataBase(PharmaDataBase mydatabase)
    {
        Pharmacy ph = new Pharmacy();

        Cursor res = mydatabase.AllPharamacy();

        if(res.getCount() >0)
        {
            while (res.moveToNext())
            {
                ph.setId_pharmacie(res.getInt(1));
                ph.setId_pharmacien(res.getInt(2));
                ph.setNom_pharmacie(res.getString(3));
                ph.setDescription(res.getString(4));
                ph.setNumeros_pharmacie(res.getString(5));
                ph.setVille( res.getString(6));
                ph.setPay(res.getString(7));
                ph.setAddresse( res.getString(8));
                ph.setPharmapicture(res.getString(9));
                ph.setGarde(res.getInt(10));
                ph.setLongitude( res.getDouble(11));
                ph.setLatitude( res.getDouble(12));
            }
        }

        return  ph;
    }

    public Perssone Session(PharmaDataBase dataBase)
    {
        Perssone p = new Perssone();
        Cursor res = dataBase.getPersonneSession();

        System.out.println("curres"+res.getCount());
        if(res.getCount() > 0)
        {
            while(res.moveToNext())
            {
                p.setId_session(res.getInt(0));
                p.setId_personne(res.getInt(1));
                p.setEmail(res.getString(2));
                p.setPseudo(res.getString(3));
                p.setPassword(res.getString(4));
                p.setPhoto(res.getString(5));
                p.setNumber(res.getString(6));
                p.setRole(res.getInt(7));
                p.setActivate(res.getInt(8));
            }
        }

        return p;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_medicament_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        db = new PharmaDataBase(context);
         Perssone p =Session(db);
         Pharmacy ph = FetchPharmaFromDataBase(db);

        Picasso.get().load(medlist.get(position).getImage_medicament())
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(holder.img.getDrawable())
                .error(holder.img.getDrawable())
               .into(holder.img, new Callback() {
                   @Override
                   public void onSuccess() {
                       System.out.println("Reussite"+medlist.get(position).getImage_medicament());
                   }

                   @Override
                   public void onError(Exception e) {
                       System.out.println("Myerrormsg"+e.getMessage());
                   }
               });
        holder.img.setImageResource(R.drawable.himalayanemtab);
        holder.txtmedname.setText(medlist.get(position).getNom_medicament());
        holder.txtmedprice.setText("Price: "+String.valueOf(medlist.get(position).getPrix_medicament())+" DT");

        holder.btaddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //db.EmptyPanier();

                if(db.verififMedicamentExist(medlist.get(position).getNom_medicament(),medlist.get(position).getId_pharmacie()))
                {
                    Toast.makeText(context,"This medication is already in your cart",Toast.LENGTH_LONG).show();
                }
                else
                {
                    PharmcyOwnInterface myactitviy = (PharmcyOwnInterface) context;
                    myactitviy.getImgofcart().setVisibility(View.VISIBLE);
                    myactitviy.getLbquantite().setVisibility(View.VISIBLE);

                    if(db.insererProduitPanier(medlist.get(position).getId_medicament(),medlist.get(position).getNom_medicament(),medlist.get(position).getImage_medicament()
                            ,medlist.get(position).getPrix_medicament(),1,p.getId_personne(),medlist.get(position).getId_pharmacie(),medlist.get(position)
                    .getNom_pharmacy()))
                    {
                        myactitviy.getLbquantite().setText(String.valueOf(db.getNumberofMedicamentinPanier()));
                        //notifyDataSetChanged();
                        Toast.makeText(context,"Add with Success",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        if(state == false)
        {
            holder.btaddcard.setVisibility(View.INVISIBLE);
            holder.medcardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    //10.0.2.2

                    url = "http://192.168.43.69:3000/pharmacylist/";
                    url += medlist.get(position).getNom_medicament();
                    JsonArrayRequest jspreq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response)
                        {

                            if(response.length() >0)
                            {
                                Intent intent = new Intent(context,CheckingPharmaActivity.class);
                                intent.putExtra("medicament_name",medlist.get(position).getNom_medicament());
                                context.startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(context,"This medication is not avalaible ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("VolleyEror:"+error.getMessage());
                        }
                    });

                    NetworkServer.getMySingleton(context).addToRequestQue(jspreq);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return medlist.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView img;
        TextView txtmedname;
        TextView txtmedprice;
        Button btaddcard;
        Dialog detaidialog;
        CardView medcardview;

        public void ShowDrugDetaiDialog()
        {
      detaidialog.setContentView(R.layout.fragment_card);



           /*card.getImgclose().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               detaidialog.dismiss();
                }
            });*/
           detaidialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
           detaidialog.show();
        }

        public MyViewHolder(final View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.photomed);
            txtmedname = itemView.findViewById(R.id.productname);
            txtmedprice = itemView.findViewById(R.id.productprice);
            btaddcard = itemView.findViewById(R.id.btadd);
            medcardview =itemView.findViewById(R.id.medcdisplaylayout);
            detaidialog = new Dialog(itemView.getContext());

           img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   System.out.println("clickimage");
                 //  ShowDrugDetaiDialog();
               }
           });

    }
    }


    public List<Medicament> getMedlist() {
        return medlist;
    }

    public void setMedlist(List<Medicament> medlist) {
        this.medlist = medlist;
    }


    public void putbtstate(boolean state)
    {
        this.state = state;
    }

}
