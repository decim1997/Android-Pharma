package com.example.thony.pharma;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CartGroupViewHolder extends GroupViewHolder
{
    private TextView titletext;
    public CartGroupViewHolder(View itemView)
    {
        super(itemView);
        titletext = itemView.findViewById(R.id.txtpharmaname);
    }

    public void bind(CartGroup cartGroup)
    {
titletext.setText(cartGroup.getTitle());
    }

}
