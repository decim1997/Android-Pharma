package com.example.thony.pharma;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>
{
    private Context context;
    private List<Order> orders;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.commandesignlayout,parent,false);
        return new OrderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position)
    {
        holder.txtdatecommande.setText(orders.get(position).getDatecommande());
        holder.txtamount.setText("TotalAmount: "+orders.get(position).getPrixtotal().toString()+" DT");
        holder.txtadresse.setText(orders.get(position).getAddresse());
        holder.txtname.setText(orders.get(position).getNompharmacie());
        holder.imgorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = orders.get(position).getCode();
                MainActivity myactitviy = (MainActivity) context;
                Fragment qrcodefrag = new QRCodeReaderFragment();
                ((QRCodeReaderFragment) qrcodefrag).setCode(code);
                myactitviy.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,qrcodefrag,null).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }


    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtdatecommande;
        TextView txtamount;
        ImageView imglocation;
        ImageView imgorder;
        TextView txtadresse;
        TextView txtname;

        public OrderViewHolder(View itemView) {
            super(itemView);

            txtdatecommande = itemView.findViewById(R.id.commandedate);
            txtamount = itemView.findViewById(R.id.commandemontant);
            imglocation = itemView.findViewById(R.id.commandelocation);
            imgorder = itemView.findViewById(R.id.commandepayementimg);
            txtadresse = itemView.findViewById(R.id.commandeadresse);
            txtname = itemView.findViewById(R.id.commandepharmaciename);
        }
    }

}
