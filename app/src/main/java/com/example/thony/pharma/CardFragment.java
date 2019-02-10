package com.example.thony.pharma;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {




      private  LayoutInflater myinflater;
      private Context cntx;
      private ImageView imgclose;

    public CardFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_card, container, false);

imgclose = view.findViewById(R.id.imgclose);


        return  view;
    }

    public ImageView getImgclose()
    {
        imgclose.setImageResource(R.drawable.himalayanemtab);
        return imgclose;
    }

    public void setImgclose(ImageView imgclose) {
        this.imgclose = imgclose;
    }


    public LayoutInflater getMyinflater() {
        return myinflater;
    }

    public void setMyinflater(LayoutInflater myinflater) {
        this.myinflater = myinflater;
    }

    public Context getCntx() {
        return cntx;
    }

    public void setCntx(Context cntx) {
        this.cntx = cntx;
    }
}
