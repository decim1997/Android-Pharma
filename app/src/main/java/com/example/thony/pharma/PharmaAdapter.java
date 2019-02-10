package com.example.thony.pharma;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PharmaAdapter  extends RecyclerView.Adapter<PharmaAdapter.PharmaViewHolder>
{

    private Context context;
    private List<Pharmacy> pharmacies;
    private LayoutInflater inflater;
    private PharmaDataBase db;
    private  Intent intent;

    public PharmaAdapter(Context context,List<Pharmacy> pharmacies)
    {
        this.context = context;
        this.pharmacies = pharmacies;
    }

    @Override
    public PharmaViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(context);
        View view;
        view = inflater.inflate(R.layout.pharmdesign_layout,parent,false);

        return  new PharmaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PharmaViewHolder holder, int position)
    {

        Picasso.get().load(pharmacies.get(position).getPharmapicture())
                .placeholder(R.drawable.picpharma)
                .error(R.drawable.picpharma)
                .into(holder.pharmapic, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Reussite");
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

   holder.txtpharmaname.setText(pharmacies.get(position).getNom_pharmacie());
   holder.txtpharmaaddresse.setText(pharmacies.get(position).getAddresse());
   holder.txtpharmacity.setText(pharmacies.get(position).getVille());

   holder.btcallpharma.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v)
       {

          /* AlertDialog.Builder builder = new AlertDialog.Builder(context);

           builder.setMessage("Do yout want to call this Pharmacy")
                   .setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which)
                       {
                           final  int REQUEST_PHONE_CALL = 1;
                           Intent callIntent = new Intent(Intent.ACTION_CALL);
                           callIntent.setData(Uri.parse( pharmacies.get(position).getNumeros_pharmacie()));


                               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                               {
                                  try
                                  {
                                      PharmacyActivity   myactivity = (PharmacyActivity) context;

                                      if(ContextCompat.checkSelfPermission(myactivity,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                                      {

                                          ActivityCompat.requestPermissions(myactivity,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                                      }
                                      else
                                      {
                                          context.startActivity(callIntent);
                                      }
                                  }
                                  catch (ClassCastException e)
                                  {
                                      System.out.println(e.getMessage());
                                  }
                               }

                       }
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                       }
                   });

           AlertDialog alert = builder.create();
           alert.setTitle("Call Pharmacy");
           alert.show();

           */
       }
   });

   holder.pharmapic.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

         /*  AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());

           builder.setMessage("Do you wan to see this pharmacy on the map??")
                   .setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which)
                       {
                           System.out.println("hewantoseeit");
                       }
                   })
                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.cancel();
                       }
                   });

           AlertDialog alert = builder.create();
           alert.setTitle("Map");
           alert.show();*/

       }
   });

holder.pharmacard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
db = new PharmaDataBase(context);

      if(db.verifyIfPharmacyExist(1))
      {
          db.updatePharmacy(1,pharmacies.get(position).getId_pharmacie(),pharmacies.get(position).getId_pharmacien(),pharmacies.get(position).getNom_pharmacie(),
                  pharmacies.get(position).getDescription(),pharmacies.get(position).getNumeros_pharmacie(),pharmacies.get(position).getVille(),pharmacies.get(position).getPay(),
                  pharmacies.get(position).getAddresse(),pharmacies.get(position).getPharmapicture(),pharmacies.get(position).getGarde(),12,12);
      }
      else
      {
db.insererPharmacy(1,pharmacies.get(position).getId_pharmacie(),pharmacies.get(position).getId_pharmacien(),pharmacies.get(position).getNom_pharmacie(),
        pharmacies.get(position).getDescription(),pharmacies.get(position).getNumeros_pharmacie(),pharmacies.get(position).getVille(),pharmacies.get(position).getPay(),
        pharmacies.get(position).getAddresse(),pharmacies.get(position).getPharmapicture(),pharmacies.get(position).getGarde(),12,12);
      }
//intent = new Intent(context,PharmacyOwnInterface.class);

        intent = new Intent(context,PharmcyOwnInterface.class);
      context.startActivity(intent);

    }
});
    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    public static  class PharmaViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView pharmapic;
        private TextView txtpharmaname;
        private TextView txtpharmacity;
        private TextView txtpharmaaddresse;
        private Button btcallpharma;
        private CardView pharmacard;

        public PharmaViewHolder(View itemView) {
            super(itemView);

            pharmacard = itemView.findViewById(R.id.pharmacard);
            pharmapic = itemView.findViewById(R.id.picpharma);
            txtpharmaname = itemView.findViewById(R.id.pharmaname);
            txtpharmacity = itemView.findViewById(R.id.pharmacity);
            txtpharmaaddresse = itemView.findViewById(R.id.pharmaadresse);
            btcallpharma = itemView.findViewById(R.id.btcallpharma);
        }
    }

}
