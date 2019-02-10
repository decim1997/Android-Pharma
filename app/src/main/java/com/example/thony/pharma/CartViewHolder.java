package com.example.thony.pharma;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CartViewHolder extends ChildViewHolder
{

    private ImageView medpic;
    private TextView  txtmedname;
    private TextView txtlignamount;
    private TextView txtqte;
    private ImageView imgsup;
    private ImageView imgmoin;
    private ImageView imgplus;


    public CartViewHolder(View itemView) {
        super(itemView);
        medpic = itemView.findViewById(R.id.imgmedcart);
        txtmedname = itemView.findViewById(R.id.txtmedname);
        txtlignamount = itemView.findViewById(R.id.txtamount);
        txtqte = itemView.findViewById(R.id.txtcartqte);
        imgsup = itemView.findViewById(R.id.ipmgdel);
        imgmoin = itemView.findViewById(R.id.imgmoin);
        imgplus = itemView.findViewById(R.id.imgplus);
    }

    public void bind(Cart cart)
    {
        Picasso.get().load(cart.getImage_medicament())
                .error(R.drawable.himalayanemtab)
                .into(medpic);
        txtmedname.setText(cart.getNom_medicament());
        txtlignamount.setText("Amount: "+String.valueOf(cart.getPrix_medicament()));
        txtqte.setText(String.valueOf(cart.getQuantite_medicament()));
    }

}
