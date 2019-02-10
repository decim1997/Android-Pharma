package com.example.thony.pharma;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkServer
{
    private static NetworkServer networkServer;

    private RequestQueue request;

    private static Context context;


    public NetworkServer(Context cntx)
    {
        context=cntx;
        request=getRequest();
    }

    public RequestQueue getRequest()
    {
        if(request==null)
        {
            request = Volley.newRequestQueue(context.getApplicationContext());
        }
        return  request;
    }


    public  static  synchronized NetworkServer getMySingleton(Context cntx)
    {
        if(networkServer ==null)
        {
            networkServer =new NetworkServer(cntx);
        }
        return networkServer;
    }


    public <T> void addToRequestQue(Request<T> req)
    {
        request.add(req);
    }

}
