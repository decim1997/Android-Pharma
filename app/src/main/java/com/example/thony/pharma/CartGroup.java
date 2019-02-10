package com.example.thony.pharma;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CartGroup extends ExpandableGroup<Cart>
{

    public CartGroup(String title, List<Cart> items) {
        super(title, items);
    }
}
