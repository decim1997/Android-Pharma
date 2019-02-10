package com.example.thony.pharma;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class InscriptionActivity extends AppCompatActivity
{

    private EditText txtmail;
    private EditText txtpseudo;
    private EditText txtpassword;
    private EditText txtretypepassword;
    private Button btinscription;
    private  String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        txtmail= findViewById(R.id.useremail);
        txtpseudo = findViewById(R.id.userpseudo);
        txtpassword = findViewById(R.id.userpassword);
        txtretypepassword = findViewById(R.id.retypepassword);
        btinscription = findViewById(R.id.btinscription);

        btinscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtmail.getText().toString().length()>0 && txtpseudo.getText().toString().length()>0 && txtpassword.getText().toString().length()>0 && txtretypepassword.getText().toString().length()>0 && txtpassword.getText().toString().equals(txtretypepassword.getText().toString()))
                {
                    JSONObject jsonObject = new JSONObject();

                    try
                    {
             jsonObject.put("email",txtmail.getText().toString());
             jsonObject.put("pseudo",txtpseudo.getText().toString());
             jsonObject.put("password",txtpassword.getText().toString());
             jsonObject.put("photo","http://192.168.43.69/pim/picture/profile/thonypng.png");
                        jsonObject.put("role",0);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    final String requestBody = jsonObject.toString();

                    url = "http://192.168.43.69:3000/pharmacy/user/add";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try
                            {
                                System.out.println("Resultat: "+response.getString("resultat"));

                                if(response.get("resultat").equals("Success"))
                                {
                      txtmail.setText(null);
                      txtpassword.setText(null);
                      txtpseudo.setText(null);
                      txtpassword.setText(null);
                      txtretypepassword.setText(null);
                              Toast.makeText(getApplicationContext(),"Acoount create with success",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                          Toast.makeText(getApplicationContext(),"Erreur: Creation de compte",Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Vous devez saisire votre login et votre mot de passe",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("volleyError: "+error.getMessage());
                        }
                    });

                    NetworkServer.getMySingleton(getApplicationContext()).addToRequestQue(jsonObjectRequest);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Erreur: Donnes invalide",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
