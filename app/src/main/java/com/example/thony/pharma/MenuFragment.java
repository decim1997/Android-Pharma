package com.example.thony.pharma;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener
{
    private CardView pharmacycard;
    private CardView remindercard;
    private CardView facturecard;
    private CardView  ordonancecard;
    private CardView faqcard;
    private CardView nouscontactercard;
    private Intent intent;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);


        pharmacycard=view.findViewById(R.id.pharmacy_card);
        remindercard=view.findViewById(R.id.reminder_card);
        facturecard=view.findViewById(R.id.facture_card);
         ordonancecard=view.findViewById(R.id.ordonance_card);
        faqcard=view.findViewById(R.id.faq_card);
        nouscontactercard=view.findViewById(R.id.contacter_card);

        pharmacycard.setOnClickListener(this);
        remindercard.setOnClickListener(this);
        facturecard.setOnClickListener(this);
        ordonancecard.setOnClickListener(this);
        faqcard.setOnClickListener(this);
        nouscontactercard.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View v)
    {

        switch(v.getId())
        {
            case R.id.pharmacy_card:
                intent = new Intent(getContext(),PharmacyActivity.class);
                startActivityForResult(intent,2);
                break;

            case R.id.reminder_card:
                System.out.println("Reminder card");
                break;

            case R.id.facture_card:
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container,new OrderFragment(),null).commit();
                break;

            case R.id.ordonance_card:
                System.out.println("Ordonance card");
                break;

            case R.id.faq_card:
                System.out.println("faq card");
                break;

            case R.id.contacter_card:
                System.out.println("Contacter card");
                break;
        }

    }
}
