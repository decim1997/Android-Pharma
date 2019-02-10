package com.example.thony.pharma;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class LigneAdapter extends RecyclerView.Adapter<LigneAdapter.LigneVieHolder>
{

    private List<LigneCommande> ligneCommandes;
    private Context context;
    private LayoutInflater inflater;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public LigneAdapter(Context context,List<LigneCommande> ligneCommandes)
    {
        this.context = context;
        this.ligneCommandes = ligneCommandes;
    }

    @Override
    public LigneVieHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;

        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.ligneadpaterdesign,parent,false);
        return new LigneVieHolder(view);
    }


    @Override
    public void onBindViewHolder(LigneVieHolder holder, int position)
    {

        Picasso.get().load(ligneCommandes.get(position).getImage_medicament())
                .placeholder(R.drawable.himalayanemtab)
                .into(holder.imgmed);

        holder.txtmedname.setText(ligneCommandes.get(position).getNom_medicament());
        holder.txtligneamount.setText(df2.format(ligneCommandes.get(position).getPrix_totalligne()).toString());
        holder.medtxt.setText(String.valueOf(ligneCommandes.get(position).getQuantite()));
    }


    @Override
    public int getItemCount() {
        return ligneCommandes.size();
    }


    public static  class LigneVieHolder extends RecyclerView.ViewHolder
    {
        TextView txtmedname;
        ImageView imgmed;
        TextView txtligneamount;
        TextView medtxt;

        public LigneVieHolder(View item) {
            super(item);

            txtmedname = item.findViewById(R.id.lignmedname);
            imgmed = item.findViewById(R.id.lignimg);
            txtligneamount = item.findViewById(R.id.lignetotalamount);
            medtxt = item.findViewById(R.id.ligneqte);
        }
    }
}
