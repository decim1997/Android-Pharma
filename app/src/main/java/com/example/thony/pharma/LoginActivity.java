package com.example.thony.pharma;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

private  Button btlogin;
private  Intent intent;
private  Button btregister;
private EditText login;
private EditText pwd;
private String url = "";
private SharedPreferences preferences;
private PharmaDataBase db;

public void goToclientMenu()
{
    intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivityForResult(intent,2);
}

public void goPharmacystMenu()
{
    intent = new Intent(getApplicationContext(),PharmacienInterfaceActivity.class);
    startActivityForResult(intent,2);
    //System.out.println("gotopharmacystmenu");
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new PharmaDataBase(getApplicationContext());
        login = findViewById(R.id.login);
        pwd = findViewById(R.id.pwd);
        btlogin=findViewById(R.id.btlogin);
        btregister = findViewById(R.id.btregister);
        preferences = getSharedPreferences("login",MODE_PRIVATE);

        if(preferences.contains("pseudo") && preferences.contains("password"))
        {
       login.setText(preferences.getString("pseudo","errorpseudo"));
       pwd.setText(preferences.getString("password","errorpassword"));
        }

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // intent=new Intent(getApplicationContext(),MainActivity.class);
                //startActivityForResult(intent,2);

                if(login.getText().toString().length()>0 && pwd.getText().toString().length()>0)
                {
        //url = "http://127.0.0.1:3000/pharmacy/user/thony/thony1997";

                    // 172.16.218.159
                    //192.168.1.7
                    //172.16.176.188
                    //172.18.88.196
//192.168.43.69
                    url="http://192.168.43.69:3000/pharmacy/user/";
                    url += login.getText().toString()+"/";
                    url += pwd.getText().toString();

                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println("datareponse: "+response);

                        if(response != null)
                        {
                            try
                            {
                   JSONObject userobject = response.getJSONObject(0);




                   if(userobject.getInt("activate") == 1)
                   {
                       preferences.edit().putString("pseudo", userobject.getString("pseudo")).commit();
                       preferences.edit().putString("password",userobject.getString("password")).commit();


                       Cursor res = db.getPersonneSession();

                       if(res.getCount() == 0)
                       {
                           Boolean etat =  db.insererPersonne(userobject.getInt("id"),userobject.getString("email"),userobject.getString("pseudo"),userobject.getString("password")
                                   ,userobject.getString("photo"),userobject.getString("numero"),userobject.getInt("role"),userobject.getInt("activate"));
                           System.out.println("etatinsertion: "+etat);
                           System.out.println("newpwersonne");
                       }
                       else

                       {
                           if(!db.verifyIfPersonneExist(userobject.getInt("id")))
                           {
                               Boolean etat =  db.insererPersonne(userobject.getInt("id"),userobject.getString("email"),userobject.getString("pseudo"),userobject.getString("password")
                                       ,userobject.getString("photo"),userobject.getString("numero"),userobject.getInt("role"),userobject.getInt("activate"));
                               System.out.println("etatinsertion: "+etat);
                               System.out.println("newpwersonne");
                           }

                       }




                       if(userobject.getInt("role") == 0)
                      {
                        goToclientMenu();
                      }
                      else if(userobject.getInt("role")== 1)
                      {
                          goPharmacystMenu();
                      }
                      else
                      {
Toast.makeText(getApplicationContext(),"Unknown Count",Toast.LENGTH_LONG).show();
                      }
                   }
                   else
                   {
                      /* AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());

                       builder.setMessage("Do you want to exit???")
                               .setCancelable(false)
                               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {

                                       moveTaskToBack(true);
                                       android.os.Process.killProcess(android.os.Process.myPid());
                                       System.exit(1);
                                   }
                               })
                               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {


                                       dialog.cancel();
                                   }
                               });

                       AlertDialog alert = builder.create();
                       alert.setTitle("Exit");
                       alert.show();*/
                       Toast.makeText(getApplicationContext(),"Your Acount is not activate,you must activate it in order to connecte to your acount",Toast.LENGTH_LONG).show();
                   }


                            }
                            catch (JSONException e)
                            {
                                System.out.println("msg: "+e.getMessage());
                            }

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"This account doesn't exist",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("erreurvolley: "+error.getMessage());
                    }
                });

                NetworkServer.getMySingleton(getApplicationContext()).addToRequestQue(jsonArrayRequest);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You must enter your login and your password",Toast.LENGTH_SHORT).show();
                }


            }
        });

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
      intent = new Intent(getApplicationContext(),InscriptionActivity.class);
      startActivityForResult(intent,2);
            }
        });

    }


}
