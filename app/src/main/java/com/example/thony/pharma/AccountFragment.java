package com.example.thony.pharma;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private  PharmaDataBase db;
    private ImageView profilepic;
    private TextView txtpseudo;
    private TextView txtemail;

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

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        db = new PharmaDataBase(getContext());
        profilepic = view.findViewById(R.id.userprofilepicture);
        txtpseudo = view.findViewById(R.id.pseudo);
        txtemail = view.findViewById(R.id.email);


        Perssone p = Session(db);


        Picasso.get().load(p.getPhoto()).placeholder(R.drawable.images)
                .error(R.drawable.images)
                .into(profilepic, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Reussite");
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
        txtpseudo.setText(p.getPseudo());
        txtemail.setText(p.getEmail());



        return  view;
    }

}
